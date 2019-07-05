package com.fhs.shiro.cache;


import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

/**
 * 自定义缓存管理器
 */
public class ShiroSpringCacheManager implements CacheManager, Destroyable {

    private org.springframework.cache.CacheManager cacheManager;

    public org.springframework.cache.CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void destroy() throws Exception {
        cacheManager = null;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name)  {
        if (name == null ){
            return null;
        }
        return new ShiroSpringCache<K,V>(name,getCacheManager());
    }


}