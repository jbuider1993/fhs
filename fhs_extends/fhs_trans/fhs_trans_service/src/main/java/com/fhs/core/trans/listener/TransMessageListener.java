package com.fhs.core.trans.listener;
import com.fhs.common.utils.JsonUtils;
import com.fhs.logger.Logger;
import org.springframework.stereotype.Component;
import com.fhs.core.trans.fi.TransRefresher;
import java.util.HashMap;
import java.util.Map;

/**
 * 翻译服务redis消息接收转发器
 */
@Component
public class TransMessageListener {

    /**
     * 日志记录
     */
    private static final Logger LOGGER = Logger.getLogger(TransMessageListener.class);
    /**
     * key trans Type value是transtype对应 的 缓存刷新方法
     */
    private static Map<String,TransRefresher> transRefresherMap = new HashMap<>();

    /**
     *
     * @param message
     */
    public void handelMsg(String message){
        System.out.println("收到了消息:" + message);
        Map<String,Object> messageMap = JsonUtils.parseJSON2Map(message);
        if(transRefresherMap.containsKey(messageMap.get("transType")))
        {
            transRefresherMap.get(messageMap.get("transType")).refreshCache(messageMap);
        }
        else
        {
            LOGGER.error(messageMap.get("transType") + "没有实现对应的缓存刷新器");
        }
    }

    /**
     * 注册消息刷新器
     * @param transType transtype
     * @param transRefresher 对应的消息刷新器(functioninterface)
     */
    public static void regTransRefresher(String transType, TransRefresher transRefresher)
    {
        transRefresherMap.put(transType,transRefresher);
    }

}
