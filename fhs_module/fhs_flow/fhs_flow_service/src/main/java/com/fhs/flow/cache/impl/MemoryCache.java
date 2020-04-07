package com.fhs.flow.cache.impl;

import com.fhs.flow.cache.ICache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 内存的cache实现。
 */
public class MemoryCache<T> implements ICache<T> {
    private Logger logger = LoggerFactory.getLogger(MemoryCache.class);

    private Map<String, T> map = new ConcurrentHashMap<String, T>();

    @Override
    public void add(String key, T obj) {
        if (key == null) {
            return;
        }
        map.put(key, obj);
        logger.info("MemoryCache add " + key);
    }

    @Override
    public void delByKey(String key) {
        if (key == null) return;
        map.remove(key);
        logger.info("MemoryCache delByKey " + key);
    }

    @Override
    public void clearAll() {
        map.clear();
        logger.info("MemoryCache clearAll");
    }

    @Override
    public T getByKey(String key) {
        if (key == null) {
            return null;
        }
        return map.get(key);
    }

    @Override
    public boolean containKey(String key) {
        if (key == null) {
            return false;
        }
        return map.containsKey(key);
    }

    @Override
    public void add(String key, T obj, int timeout) {

    }

}
