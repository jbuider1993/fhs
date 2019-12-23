package com.fhs.system.trans;

import com.fhs.common.utils.JsonUtils;
import com.fhs.common.utils.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * 工作流 消息分发
 *
 * @author yutao
 * @date 2019 -11-13 10:21:11
 */
@Component
public class FlowMessageListener {

    private static final Logger LOGGER = Logger.getLogger(FlowMessageListener.class);

    private static Map<String, FlowHandle> flowRefresherMap = new HashMap<>();

    /**
     * 分发
     *
     * @param message
     */
    public void flowMsg(String message) {
        System.out.println("收到了消息:" + message);
        Map<String, Object> stringObjectMap = JsonUtils.parseJSON2Map(message);
        if (flowRefresherMap.containsKey(stringObjectMap.get("flowType"))) {
            flowRefresherMap.get(stringObjectMap.get("flowType")).handle(stringObjectMap);
        } else {
            LOGGER.error(stringObjectMap.get("flowType") + "没有实现对应的缓存刷新器");
        }
    }

    /**
     * 注册执行方法
     * @param flowType
     * @param flowHandle
     */
    public static void registerFlow(String flowType, FlowHandle flowHandle) {
        flowRefresherMap.put(flowType, flowHandle);
    }

}
