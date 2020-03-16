package com.alicp.jetcache;

import com.alicp.jetcache.anno.aop.CachePointcut;
import com.alicp.jetcache.anno.method.CacheInvokeConfig;
import com.alicp.jetcache.anno.method.CacheInvokeContext;
import com.alicp.jetcache.anno.support.CachedAnnoConfig;
import com.alicp.jetcache.anno.support.ConfigMap;
import com.alicp.jetcache.anno.support.GlobalCacheConfig;
import com.fhs.common.spring.ScannerUtils;
import com.fhs.common.utils.ReflectUtils;
import com.fhs.core.cache.annotation.AutoClearCache;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 缓存刷新管理器
 * 在项目启动完成后获取所有的@AutoRefresCache  标记的方法
 */
@Data
public class CacheUpdateManager implements ApplicationContextAware, ApplicationListener<ApplicationReadyEvent> {

    /**
     * service的包路径
     */
    private String[] packageNames = new String[]{};
    @Autowired
    private ConfigMap cacheConfigMap;
    private ApplicationContext applicationContext;
    private GlobalCacheConfig globalCacheConfig;

    /**
     * key namespace value  value namespace 对应的刷新器
     */
    private Map<String, List<MethodPoint>> methodPontMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 清除某个namespace的所有缓存
     *
     * @param namespace
     */
    public void clearCache(String namespace) {
        List<MethodPoint> methodPoints = methodPontMap.get(namespace);
        if (methodPoints == null || methodPoints.isEmpty()) {
            return;
        }
        for (MethodPoint methodPoint : methodPoints) {
            clearAndRefreshMethodCache(methodPoint);
        }
    }

    /**
     * 清除并且自动刷新方法缓存
     *
     * @param point 方法
     */
    public void clearAndRefreshMethodCache(MethodPoint point) {
        if (globalCacheConfig == null) {
            globalCacheConfig = applicationContext.getBean(GlobalCacheConfig.class);
        }
        if (globalCacheConfig == null || !globalCacheConfig.isEnableMethodCache()) {
            return;
        }
        String key = CachePointcut.getKey(point.getMethod(), point.getClazz());
        CacheInvokeConfig cac = cacheConfigMap.getByMethodInfo(key);
        if (cac == null || cac == CacheInvokeConfig.getNoCacheInvokeConfigInstance()) {
            return;
        }
        CacheInvokeContext context = globalCacheConfig.getCacheContext().createCacheInvokeContext(cacheConfigMap);
        context.setCacheInvokeConfig(cac);
        context.setHiddenPackages(globalCacheConfig.getHiddenPackages());
        CacheInvokeConfig cic = context.getCacheInvokeConfig();
        CachedAnnoConfig cachedAnnoConfig = cic.getCachedAnnoConfig();
        Cache cache = context.getCacheFunction().apply(context, cachedAnnoConfig);
        if(cache == null){
            return;
        }
        if (cache instanceof RefreshCache) {
            ConcurrentHashMap<Object, RefreshCache.RefreshTask> taskMap = (ConcurrentHashMap<Object, RefreshCache.RefreshTask>) ReflectUtils.getValue(cache, "taskMap");
            if (taskMap != null && !taskMap.isEmpty()) {
                Collection<RefreshCache.RefreshTask> tasks = taskMap.values();
                for (RefreshCache.RefreshTask task : tasks) {
                    cache.remove(ReflectUtils.getValue(task, "key"));
                }
            }
        }
    }



    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //spring容器初始化完成之后，就会自行此方法。
        Set<Class<?>> entitySet = ScannerUtils.scan(AutoClearCache.class, packageNames);
        // 遍历所有class，获取所有用@autowareYLM注释的字段
        if (entitySet != null) {
            String[] namespaces = null;
            for (Class<?> entity : entitySet) {
                //过滤 包含注解并且是public的缓存
                List<Method> methods = ReflectUtils.getAllMethod(entity).stream().filter(method -> (method.getModifiers() & 1) == 1)
                        .filter(method -> method.isAnnotationPresent(AutoClearCache.class)).collect(Collectors.toList());
                for (Method method : methods) {
                    namespaces = method.getAnnotation(AutoClearCache.class).namespaces();
                    for (String namespace : namespaces) {
                        MethodPoint tempPoint = MethodPoint.builder().clazz(entity)
                                .method(method).build();
                        List<MethodPoint> points = methodPontMap.containsKey(namespace) ? methodPontMap.get(namespace) : new ArrayList<>();
                        points.add(tempPoint);
                        methodPontMap.put(namespace, points);
                    }
                }
            }
        }
    }
}

/**
 * 方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class MethodPoint {
    private Method method;
    private Class clazz;

}