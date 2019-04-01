package com.fhs.core.cache.manager;

import com.fhs.common.utils.Logger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCachePrefix;
import org.springframework.data.redis.core.RedisOperations;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 * 扩展的redis cache manager，支持过期时间
 *
 * @Filename: ExtendedRedisCacheManager.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwong
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 *陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
public class ExtendedRedisCacheManager extends RedisCacheManager implements InitializingBean {

    private static final Logger logger = Logger.getLogger(ExtendedRedisCacheManager.class);

    private static final ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");

    private static final Pattern pattern = Pattern.compile("[+\\-*/%]");

    private String defaultCacheName;

    private char separator = '#';

    public ExtendedRedisCacheManager(@SuppressWarnings("rawtypes") RedisOperations redisOperations) {
        this(redisOperations, Collections.<String>emptyList());
    }

    public ExtendedRedisCacheManager(@SuppressWarnings("rawtypes") RedisOperations redisOperations, Collection<String> cacheNames) {
        super(redisOperations, cacheNames);
    }


    @Override
    public void afterPropertiesSet()
    {
        logger.debug("ExtendedRedisCacheManager init");
        super.afterPropertiesSet();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Cache getCache(String name) {
        // there's no cache which has given name
        // find separator in cache name
        int index = name.lastIndexOf(getSeparator());
        RedisCache cache =  null;
        if (index < 0) {
            // try to get cache by name
            cache = (RedisCache) super.getCache(name);
            if (cache != null) {
                return cache;
            }
            return null;
        }

        // split name by the separator
        String cacheName = name.substring(0, index);
        if(StringUtils.isBlank(cacheName)){
            cacheName = defaultCacheName;
        }
        cache = (RedisCache) super.getCache(cacheName);
        if (cache == null) {
            return null;
        }

        // get expiration from name
        Long expiration = getExpiration(name, index);
        if (expiration == null || expiration < 0) {
            logger.warn("Default expiration time will be used for cache '{}' because cannot parse '{}', cacheName : " + cacheName + ", name : " + name);
            return cache;
        }

        return new RedisCache(cacheName, (isUsePrefix() ? getCachePrefix().prefix(cacheName) : null), getRedisOperations(), expiration);
    }


    public char getSeparator() {
        return separator;
    }

    /**
     * Char that separates cache name and expiration time, default: #.
     *
     * @param separator
     */
    public void setSeparator(char separator) {
        this.separator = separator;
    }

    private Long getExpiration(final String name, final int separatorIndex) {
        Long expiration = null;
        String expirationAsString = name.substring(separatorIndex + 1);
        try {
            // calculate expiration, support arithmetic expressions.
            if(pattern.matcher(expirationAsString).find()){
                expiration = (long) Double.parseDouble(scriptEngine.eval(expirationAsString).toString());
            }else{
                expiration = Long.parseLong(expirationAsString);
            }
        } catch (NumberFormatException ex) {
            logger.error(String.format("Cannnot separate expiration time from cache: '%s'", name), ex);
        } catch (ScriptException e) {
            logger.error(String.format("Cannnot separate expiration time from cache: '%s'", name), e);
        }

        return expiration;
    }

    @Override
    public void setUsePrefix(boolean usePrefix) {
        super.setUsePrefix(usePrefix);
    }

    @Override
    public void setCachePrefix(RedisCachePrefix cachePrefix) {
        super.setCachePrefix(cachePrefix);
    }

    public void setDefaultCacheName(String defaultCacheName) {
        this.defaultCacheName = defaultCacheName;
    }
}