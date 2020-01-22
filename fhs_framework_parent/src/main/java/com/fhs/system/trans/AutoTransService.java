package com.fhs.system.trans;

import com.fhs.common.spring.SpringContextUtil;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.ReflectUtils;
import com.fhs.core.base.bean.BaseDO;
import com.fhs.core.base.bean.SuperBean;
import com.fhs.core.base.service.BaseService;
import com.fhs.core.trans.AutoTrans;
import com.fhs.core.trans.ITransTypeService;
import com.fhs.core.trans.Trans;
import com.fhs.core.trans.TransService;
import com.mybatis.jpa.common.scanner.AnnotationTypeFilterBuilder;
import com.mybatis.jpa.common.scanner.SpringClassScanner;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @Description: 自动翻译服务
 * @Author: Wanglei
 * @Date: Created in 10:14 2019/10/15
 */
@Data
@Service
public class AutoTransService implements ITransTypeService, InitializingBean, ApplicationListener<ApplicationReadyEvent> {

    public static final Logger LOGGER = LoggerFactory.getLogger(AutoTransService.class);


    /**
     * service的包路径
     */
    @Value("${fhs.autotrans.package:com.*.*.service.impl}")
    private String[] packageNames;

    /**
     * 翻译数据缓存map
     */
    private Map<String, Map<String, String>> cacheMap = new HashMap<>();

    /**
     * 基础服务
     */
    private Map<String, BaseService> baseServiceMap = new HashMap<>();

    /**
     * 配置
     */
    private Map<String, AutoTrans> transSettMap = new HashMap<>();

    /**
     * 如果直接去表里查询，放到这个cache中
     */
    private ThreadLocal<Map<String, Map<String, String>>> threadLocalCache = new ThreadLocal<>();

    /**
     * 翻译字段配置map
     */
    private Map<Field, TransFieldSett> transFieldSettMap = new HashMap<>();

    @Override
    public void transOne(SuperBean<?> obj, List<Field> toTransList) {
        Trans tempTrans = null;
        for (Field tempField : toTransList) {
            TransFieldSett transFieldSett = transFieldSettMap.containsKey(tempField) ? transFieldSettMap.get(tempField) : new TransFieldSett(tempField);
            tempTrans = transFieldSett.getTrans();
            String namespace = transFieldSett.getNamespace();
            String alias = transFieldSett.getAlias();
            String pkey = ConverterUtils.toString(ReflectUtils.getValue(obj, tempField.getName()));
            if (StringUtils.isEmpty(pkey)) {
                continue;
            }
            Map<String, String> transCache = null;
            // 主键可能是数组
            pkey = pkey.replace("[", "").replace("]", "");
            if (pkey.contains(",")) {
                String[] pkeys = pkey.split(",");
                transCache = new HashMap<>();
                Map<String, String> tempTransCache = null;
                for (String tempPkey : pkeys) {
                    tempTransCache = getTempTransCacheMap(namespace, ConverterUtils.toInteger(tempPkey));
                    if (tempTransCache == null) {
                        LOGGER.error("auto trans缓存未命中:" + namespace + "_" + tempPkey);
                        continue;
                    }
                    // 比如学生表  可能有name和age 2个字段
                    for (String key : tempTransCache.keySet()) {
                        transCache.put(key, transCache.containsKey(key) ? transCache.get(key) + "," + tempTransCache.get(key) : tempTransCache.get(key));
                    }
                }
            } else {
                transCache = getTempTransCacheMap(namespace, pkey);
                if (transCache == null) {
                    LOGGER.error("auto trans缓存未命中:" + namespace + "_" + pkey);
                    continue;
                }
            }
            if (alias != null) {
                Map<String, String> tempMap = new HashMap<>();
                Set<String> keys = transCache.keySet();
                for (String key : keys) {
                    tempMap.put(alias + key.substring(0, 1).toUpperCase() + key.substring(1), transCache.get(key));
                }
                transCache = tempMap;
            }
            Map<String, String> transMap = obj.getTransMap();
            Set<String> keys = transCache.keySet();
            for (String key : keys) {
                if (CheckUtils.isNullOrEmpty(transMap.get(key))) {
                    transMap.put(key, transCache.get(key));
                }
            }
        }
    }

    @Override
    public void transMore(List<? extends  SuperBean<?>>  objList, List<Field> toTransList) {
        threadLocalCache.set(new HashMap<>());
        // 由于一些表数据比较多，所以部分数据不是从缓存取的，是从db先放入缓存的，翻译完了释放掉本次缓存的数据
        for (Field tempField : toTransList) {
            tempField.setAccessible(true);
            Trans tempTrans = tempField.getAnnotation(Trans.class);
            String namespace = tempTrans.key();
            // 如果是 good#student  翻译出来应该是 goodStuName goodStuAge  customer#customer  customerName
            if (namespace.contains("#")) {
                namespace = namespace.substring(0, namespace.indexOf("#"));
            }
            if (!this.baseServiceMap.containsKey(namespace)) {
                LOGGER.warn("namesapce对应的service没有标记autotrans:" + namespace);
                continue;
            }
            AutoTrans autoTransSett = this.transSettMap.get(namespace);
            if (autoTransSett.useCache()) {
                continue;
            }
            Set<Object> ids = new HashSet<>();
            objList.forEach(obj -> {
                try {
                    ids.add(tempField.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            List<BaseDO> dbDatas = baseServiceMap.get(namespace).findByIds(new ArrayList<>(ids));
            for (BaseDO dbData : dbDatas) {
                threadLocalCache.get().put(namespace + "_" + dbData.getId(), createTempTransCacheMap((BaseDO) dbData, autoTransSett));
            }
        }
        objList.forEach(obj -> {
            this.transOne(obj, toTransList);
        });
        threadLocalCache.set(null);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        TransService.registerTransType("auto", this);
        TransMessageListener.regTransRefresher("auto", this::refreshCache);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        init(applicationReadyEvent);
    }

    public void init(ApplicationReadyEvent applicationReadyEvent) {
        //spring容器初始化完成之后，就会自行此方法。
        Set<Class<?>> entitySet = scan(AutoTrans.class, packageNames);
        // 遍历所有class，获取所有用@autowareYLM注释的字段
        if (entitySet != null) {
            for (Class<?> entity : entitySet) {
                // 获取该类
                Object baseService = SpringContextUtil.getBeanByClass(entity);
                if (!(baseService instanceof BaseService)) {
                    LOGGER.warn("AutoTrans 只能用到baseserivce上,不能用到:" + baseService.getClass());
                    continue;
                }
                AutoTrans autoTransSett = entity.getAnnotation(AutoTrans.class);
                this.baseServiceMap.put(autoTransSett.namespace(), (BaseService) baseService);
                this.transSettMap.put(autoTransSett.namespace(), autoTransSett);
            }
        }
        refreshCache(new HashMap<>());
    }

    /**
     * 刷新缓存
     *
     * @param messageMap 消息
     */
    public void refreshCache(Map<String, Object> messageMap) {
        //这里必须能拿到namespace 拿不到,就当作全部刷新
        String namespace = messageMap.get("namespace") != null ?
                messageMap.get("namespace").toString() : null;
        if (namespace == null) {
            Set<String> namespaceSet = this.transSettMap.keySet();
            namespaceSet.forEach(temp -> {
                refreshOneNamespace(temp);
            });
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error("刷新缓存错误:", e);
            }
            refreshOneNamespace(namespace);
        }
    }

    /**
     * 刷新一个namespace下的所有的缓存
     *
     * @param namespace namespace
     */
    public void refreshOneNamespace(String namespace) {
        LOGGER.info("开始刷新auto-trans缓存:" + namespace);
        if (!this.transSettMap.containsKey(namespace)) {
            LOGGER.info("本系统无需刷新此缓存namespace:" + namespace);
            return;
        }

        List<BaseDO> dos = this.baseServiceMap.get(namespace).select();
        if (dos == null || dos.isEmpty()) {
            return;
        }
        Object pkeyVal = null;
        String fielVal = null;
        Map<String, String> tempCacheTransMap = null;
        BaseDO po = null;
        AutoTrans autoTrans = this.transSettMap.get(namespace);
        //不适用缓存的不做缓存
        if (!autoTrans.useCache()) {
            return;
        }
        for (int i = 0; i < dos.size(); i++) {
            po = dos.get(i);
            pkeyVal = po.getId();
            cacheMap.put(namespace + "_" + pkeyVal, createTempTransCacheMap(po, autoTrans));
        }
        LOGGER.info("刷新auto-trans缓存完成:" + namespace);
    }

    /**
     * 创建一个临时缓存map
     *
     * @param po        po
     * @param autoTrans 配置
     * @return
     */
    private Map<String, String> createTempTransCacheMap(BaseDO po, AutoTrans autoTrans) {
        String fielVal = null;
        Map<String, String> tempCacheTransMap = new LinkedHashMap<>();
        for (String field : autoTrans.fields()) {
            fielVal = ConverterUtils.toString(ReflectUtils.getValue(po, field));
            tempCacheTransMap.put(field, fielVal);
        }
        return tempCacheTransMap;
    }

    /**
     * 获取用于翻译的缓存
     *
     * @param namespace namespace
     * @param pkey      主键
     * @return 缓存
     */
    private Map<String, String> getTempTransCacheMap(String namespace, Object pkey) {
        AutoTrans autoTrans = this.transSettMap.get(namespace);
        if (autoTrans == null) {
            LOGGER.warn("namespace对应的service没有使用autotrans注解标记:" + namespace);
            return null;
        }
        if (autoTrans.useCache()) {
            return cacheMap.get(namespace + "_" + pkey);
        }
        if (this.threadLocalCache.get() == null) {
            BaseDO vo = (BaseDO)this.baseServiceMap.get(namespace).selectById(pkey);
            return createTempTransCacheMap((BaseDO) vo, autoTrans);
        }
        return this.threadLocalCache.get().get(namespace + "_" + pkey);
    }

    /**
     * 翻译单个的key
     *
     * @param namespace namespace
     * @param pkeyVal   主键
     * @return
     */
    public String transKey(String namespace, String pkeyVal) {

        Map<String, String> tempCacheTransMap = cacheMap.get(namespace + "_" + pkeyVal);
        if (tempCacheTransMap == null) {
            LOGGER.error("auto trans缓存未命中:" + namespace + "_" + pkeyVal);
        } else {
            for (String key : tempCacheTransMap.keySet()) {
                return tempCacheTransMap.get(key);
            }
        }
        return null;
    }
    /**
     * 类扫描器
     * @param annotationClass 注解
     * @param packageNames 包
     * @return 符合条件的类
     */
    public static Set<Class<?>> scan(Class<? extends Annotation> annotationClass, String[] packageNames){
        TypeFilter entityFilter = AnnotationTypeFilterBuilder.build(annotationClass);
        SpringClassScanner entityScanner = new SpringClassScanner.Builder().typeFilter(entityFilter).build();
        for (String packageName : packageNames) {
            entityScanner.getScanPackages().add(packageName);
        }
        Set<Class<?>> entitySet = null;
        try {
            entitySet = entityScanner.scan();
        } catch (ClassNotFoundException | IOException e) {
            LOGGER.error("包扫描错误" ,e);
            // log or throw runTimeExp
            throw new RuntimeException(e);
        }
        return entitySet;
    }
}

/**
 * 被翻译的字段的实体
 */
@Data
class TransFieldSett {
    /**
     * trans注解
     */
    private Trans trans;
    /**
     * 命名空间
     */
    private String namespace;
    /**
     * 别名
     */
    String alias;

    public TransFieldSett(Field transField) {
        transField.setAccessible(true);
        trans = transField.getAnnotation(Trans.class);
        namespace = trans.key();
        // 如果是 good#student  翻译出来应该是 goodStuName goodStuAge  customer#customer  customerName
        if (namespace.contains("#")) {
            alias = namespace.substring(namespace.indexOf("#") + 1);
            namespace = namespace.substring(0, namespace.indexOf("#"));
        }
    }
}