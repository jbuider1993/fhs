package com.fhs.config;

import com.fhs.core.cache.manager.ExtendedRedisCacheManager;
import com.fhs.core.cache.manager.MixCacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 缓存管理器配置
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.config
 * @ClassName: CacheConfig
 * @Author: JackWang
 * @CreateDate: 2018/11/12 0012 15:44
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/11/12 0012 15:44
 * @Version: 1.0
 */
@Configuration
@EnableCaching
public class CacheMangerConfig {

    /**
     * redis 的cache
     * @param redisTemplate
     * @return
     */
    @Bean("extendedRedisCacheManager")
    @Primary
    public ExtendedRedisCacheManager initRedisCache(RedisTemplate redisTemplate){
        ExtendedRedisCacheManager redisCacheManager = new ExtendedRedisCacheManager(redisTemplate);
        // 默认5分钟过期
        redisCacheManager.setDefaultExpiration(300);
        return  redisCacheManager;
    }

    /**
     * 自定义cache管理器
     * @param redisTemplate
     * @return
     */
    @Bean("mixCacheManager")
    @DependsOn("extendedRedisCacheManager")
    public MixCacheManager initMixCacheManager(RedisTemplate redisTemplate){
        MixCacheManager mixCacheManager = new MixCacheManager();
        mixCacheManager.setRedisCacheManager(initRedisCache(redisTemplate));
        return mixCacheManager;
    }

}
