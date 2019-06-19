package com.fhs.ucenter.tools;

import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.Logger;
import com.fhs.core.exception.ParamException;
import com.fhs.ucenter.bean.UcenterMpSett;
import com.fhs.ucenter.service.UcenterMpSettService;
import com.fhs.wx.WxMpInRedisConfigStorage;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信工具 by jackwong
 *
 */
@Component
public class WxTools
{

    /** 日志 */
    private static final Logger LOG = Logger.getLogger(WxTools.class);

    @Autowired
    private UcenterMpSettService mpSettService;

    /**
     *key 编码 val 是对应的WxMpService
     */
    private Map<String, WxMpService> wxMpServiceMap = new HashMap<>();

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *key 编码 val 是对应的WxMpMessageRouter
     */
    private Map<String, WxMpMessageRouter> wxMpMessageRouterMap = new HashMap<>();

    /**
     *key 编码 val 是对应的WxMpService
     */
    private Map<String, WxMpConfigStorage> wxMpConfigStorageMap = new HashMap<>();

    public synchronized WxMpService getWxMpService(String parkCode)
    {

        if(wxMpServiceMap.containsKey(parkCode))
        {
            return wxMpServiceMap.get(parkCode);
        }
        WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(getWxConfig(parkCode));
        wxMpServiceMap.put(parkCode, wxService);
        return wxService;
    }

    /**
     * 创建微信调用config对象
     * @param code 编码
     * @return 微信调用config对象
     */
    public WxMpConfigStorage getWxConfig(String code)
    {
        if(wxMpConfigStorageMap.containsKey(code))
        {
            return wxMpConfigStorageMap.get(code);
        }
        if(CheckUtils.isNullOrEmpty(code))
        {
            throw new ParamException("parkCode不能為空");
        }
        UcenterMpSett mpSett = mpSettService.selectBean(new UcenterMpSett().mk("extendsCode",code));
        if(mpSett == null)
        {
            throw new ParamException("找不到对应的配置:" + code);
        }
        WxMpInRedisConfigStorage config = new WxMpInRedisConfigStorage(redisTemplate);
        config.setAppId(mpSett.getAppId()); // 设置微信公众号的appid
        config.setSecret(mpSett.getAppSecret()); // 设置微信公众号的app corpSecret
        config.setToken(mpSett.getToken()); // 设置微信公众号的token
        config.setAesKey(mpSett.getAesKey()); // 设置微信公众号的EncodingAESKey
        wxMpConfigStorageMap.put(code, config);
        LOG.info("加载微信配置:"+mpSett.asJson());
        return  config;
    }

}
