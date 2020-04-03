package com.fhs.front.wx.tools;

import com.fhs.common.utils.CheckUtils;
import com.fhs.core.exception.ParamException;
import com.fhs.front.wx.storage.WxMpInRedisConfigStorage;
import com.fhs.logger.Logger;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信工具 by jackwong
 */
@Component
public class WxTools {

    /**
     * 日志
     */
    private static final Logger LOG = Logger.getLogger(WxTools.class);


    @Value("${fhs.wxmp.appid}")
    private String appId;

    @Value("${fhs.wxmp.secret}")
    private String secret;

    @Value("${fhs.wxmp.token:}")
    private String token;

    @Value("${fhs.wxmp.aesKey:}")
    private String aesKey;

    /**
     * key 编码 val 是对应的WxMpService
     */
    private Map<String, WxMpService> wxMpServiceMap = new HashMap<>();

    @Autowired
    private RedisTemplate redisTemplate;


    private WxMpInRedisConfigStorage storage;

    private WxMpService mpService;

    public synchronized WxMpService getWxMpService() {
        if (mpService == null) {
            mpService = new WxMpServiceImpl();
            mpService.setWxMpConfigStorage(getWxConfigStorage());
        }
        return mpService;
    }


    /**
     * 创建微信调用config对象
     *
     * @return 微信调用config对象
     */
    public WxMpConfigStorage getWxConfigStorage() {
        if (storage == null) {
            storage = new WxMpInRedisConfigStorage(redisTemplate);
            storage.setAppId(this.appId); // 设置微信公众号的appid
            storage.setSecret(this.secret); // 设置微信公众号的app corpSecret
            storage.setToken(this.token); // 设置微信公众号的token
            storage.setAesKey(this.aesKey); // 设置微信公众号的EncodingAESKey
            LOG.info("加载微信配置:" + storage);
        }
        return storage;

    }

}
