package com.fhs.core.listener;

import java.util.Map;
import java.util.Set;

/**
 * 分布式监听器
 *
 * @author Jackwong
 * @date 2019 -12-11 11:22:30
 */
public interface DistributedListener {

    /**
     * 目标业务namespace
     * @return
     */
    Set<String> namespace();

    /**
     * 事件类型
     * @return   事件类型
     */
    String eventType();

    /**
     * 监听器回调
     * @param param
     * @return
     */
    Map<String,Object> onMessage(Map<String,Object> param);

    /**
     * 获取排序 数字越小越 排到前面
     * @return
     */
    default int getOrder(){
        return 0;
    }

    /**
     * 如果是rpc监听器，那么feign接口class是谁返回谁
     * @return  rpcclass
     */
    default Class<? extends DistributedListener> getRpcInterfaceClass(){
        return null;
    }

    /**
     * 实现是否是rpc 默认false
     * @return  是 返回true  不是返回false
     */
    default  boolean isRpc(){
        return false;
    }

}
