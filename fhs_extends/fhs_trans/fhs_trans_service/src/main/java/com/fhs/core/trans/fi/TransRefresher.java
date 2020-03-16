package com.fhs.core.trans.fi;

import java.util.Map;

/**
 * 实际刷新消息的干活的方法
 */
@FunctionalInterface
public interface TransRefresher {
    /**
     * 给一个消息去刷新transservice中的缓存数据
     * @param message  消息对象
     */
    void refreshCache(Map<String, Object> message);
}
