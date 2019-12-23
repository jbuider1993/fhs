package com.fhs.system.trans;

import java.util.Map;


/**
 * 实际干活的方法
 *
 * @author yutao
 * @date 2019 -11-13 10:21:11
 */
@FunctionalInterface
public interface FlowHandle {
    /**
     * 处理
     * @param message 消息对象
     */
    void handle(Map<String, Object> message);
}
