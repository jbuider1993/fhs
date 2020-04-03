package com.fhs.core.listener.register;


import com.fhs.common.spring.SpringContextUtil;
import com.fhs.common.utils.MapUtils;
import com.fhs.core.listener.DistributedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 分布式监听器注册中心
 *
 * @author Jackwong
 * @date 2019 -12-11 11:22:30
 */
@Component
public class DistributedListenerRegister implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(DistributedListenerRegister.class);

    /**
     * 注册进来的监听分组
     * 第一个 key 是namespace  第二个是eventType
     */
    private Map<String, Map<String, List<DistributedListener>>> listenerDataMap = new HashMap<>();

    /**
     * springboot项目启动成功后，自动找到所有的监听(支持springcloud FeignClient代理)
     *
     * @param applicationReadyEvent
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        List<DistributedListener> listeners = SpringContextUtil.getBeansByClass(DistributedListener.class);
        listeners.forEach(listener -> {
            Set<String> namespaces = listener.namespace();
            for (String namespace : namespaces) {
                if (!listenerDataMap.containsKey(namespace)) {
                    listenerDataMap.put(namespace, new HashMap<>());
                }
                Map<String, List<DistributedListener>> aNamespaceListenerMap = listenerDataMap.get(namespace);
                if (!aNamespaceListenerMap.containsKey(listener.eventType())) {
                    aNamespaceListenerMap.put(listener.eventType(), new ArrayList<>());
                }
                List<DistributedListener> eventListenerList = aNamespaceListenerMap.get(listener.eventType());
                eventListenerList.add(listener);
            }
        });
        LOG.info("DistributedListenerRegister load listener success");
    }

    /**
     * 获取指定namespace的指定eventType的监听集合
     *
     * @param namespace namespace
     * @param eventType 监听类型
     * @return 监听器集合
     */
    public List<DistributedListener> getListener(String namespace, String eventType) {
        List<DistributedListener> defaultEventListeners = new ArrayList<>();
        if (!listenerDataMap.containsKey(namespace)) {
            return defaultEventListeners;
        }
        Map<String, List<DistributedListener>> aNamespaceListenerMap = listenerDataMap.get(namespace);
        if (!aNamespaceListenerMap.containsKey(eventType)) {
            return defaultEventListeners;
        }
        List<DistributedListener> cacheListener = aNamespaceListenerMap.get(eventType);
        for (DistributedListener listener : cacheListener) {
            //判断是否是rpc的listener，如果是的话，判断是否存在非代理的实现类，存在只添加非代理实现类
            if((!listener.isRpc())  || SpringContextUtil.getBeanByClassForApi(listener.getRpcInterfaceClass()) == listener){
                defaultEventListeners.add(listener);
            }
        }
        defaultEventListeners.sort(Comparator.comparingInt(DistributedListener::getOrder));
        return defaultEventListeners;
    }


    /**
     * 调用listener
     *
     * @param namespace namespace
     * @param eventType 事件类型
     * @param paramObj  参数 po 或者vo
     */
    public void callListener(String namespace, String eventType, Object paramObj) {
        callListener(namespace, eventType, paramObj, null);
    }

    /**
     * 调用listener
     *
     * @param namespace    namespace
     * @param eventType    事件类型
     * @param paramObj     参数 po 或者vo
     * @param extendsParam 扩展参数
     */
    public void callListener(String namespace, String eventType, Object paramObj, Map<String, Object> extendsParam) {
        Map<String, Object> map = MapUtils.bean2Map(paramObj);
        map.put("namespace", namespace);
        if (extendsParam != null) {
            map.putAll(extendsParam);
        }
        //获取对应监听器
        List<DistributedListener> listeners = this.getListener(namespace, eventType);
        listeners.forEach(listener -> {
            listener.onMessage(map);
        });
    }
}
