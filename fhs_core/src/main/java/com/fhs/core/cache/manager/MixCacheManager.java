package com.fhs.core.cache.manager;

import lombok.Data;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 根据cache的value自动选择cache的manager
 * 暂时只支持redis，后期会支持caffeine
 * @Filename: MixCacheManager.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwong
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 *陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
@Data
public class MixCacheManager  implements CacheManager
{

    /**
     *redisCacheManager
     */
    private CacheManager redisCacheManager;

    /**
     *eCacheManager
     */
    private CacheManager eCacheManager;


    /**
     * REDIS 开头的放redis里面
     */
    private String redisPrefix = "redis-";

   public Cache getCache(String value) {
        if (value.startsWith(redisPrefix))
            return redisCacheManager.getCache(value);
        else
           return eCacheManager.getCache(value);//后面会修改
   }

    public Collection<String> getCacheNames() {
        Collection<String> cacheNames = new ArrayList<String>();
        if (redisCacheManager != null) {
            cacheNames.addAll(redisCacheManager.getCacheNames());
        }
        if (eCacheManager != null) {
            cacheNames.addAll(eCacheManager.getCacheNames());
        }
        return cacheNames;
    }



}
