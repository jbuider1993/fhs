package com.fhs.redis.bean;

import com.fhs.common.utils.Logger;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * redis实现的跨jvm的lock
 *
 * @author wangpengfei
 * @version [版本号, 2016年7月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RedisLock
{
    private static Logger LOG = Logger.getLogger(RedisLock.class);

    /** 加锁标志 */
    public static final String LOCKED = "TRUE";

    /** 毫秒与毫微秒的换算单位 1毫秒 = 1000000毫微秒 */
    public static final long MILLI_NANO_CONVERSION = 1000 * 1000L;

    /** 默认超时时间（毫秒） */
    public static final long DEFAULT_TIME_OUT = 5000;

    public static final Random RANDOM = new Random();

    /** 锁的超时时间（秒），过期删除 */
    public static final int EXPIRE = 5 * 60;

    private RedisTemplate redisTemplate;


    private String key;

    // 锁状态标志
    private boolean locked = false;

    public RedisLock(String key, RedisTemplate redisTemplate)
    {
        Date date = new Date();
        try
        {   this.redisTemplate = redisTemplate;
        }
        catch (Exception e)
        {
            LOG.error("获取redis连接错误", e);
        }
        this.key = key + "_lock";
    }

    /**
     * 加锁 应该以： lock(); try { doSomething(); } finally { unlock()； } 的方式调用
     *
     * @param timeout 超时时间
     * @return 成功或失败标志
     */
    public boolean lock(long timeout)
    {
        long nano = System.nanoTime();
        timeout *= MILLI_NANO_CONVERSION;
        try
        {
            while ((System.nanoTime() - nano) < timeout)
            {
                if (setNx(this.key,LOCKED))
                {
                    redisTemplate.expire(this.key, EXPIRE, TimeUnit.SECONDS);
                    this.locked = true;
                    // LOG.warn("the key is lock -- " + this.key + " exe param log :\n"
                    // + StackTraceElementUtils.getStackStr(3));
                    return this.locked;
                }
                // 短暂休眠，避免出现活锁
                Thread.sleep(100, RANDOM.nextInt(500));
            }
        }
        catch (Exception e)
        {
            LOG.error(this, e);
            throw new RuntimeException("Locking error", e);
        }
        // LOG.warn(StackTraceElementUtils.getStackStr(3) + " \n lock error " + this.key);
        return false;
    }

    public boolean checkLockExit(long timeout)
    {
        long nano = System.nanoTime();
        timeout *= MILLI_NANO_CONVERSION;
        try
        {
            while ((System.nanoTime() - nano) < timeout)
            {
                RedisConnection connection = getConnection();
                if (connection.exists(redisTemplate.getStringSerializer().serialize(key)))
                {
                    closeRedisConnection(connection);
                    return true;
                }
                closeRedisConnection(connection);
                // 短暂休眠，避免出现活锁
                Thread.sleep(100, RANDOM.nextInt(500));
            }
        }
        catch (Exception e)
        {
            LOG.error(this, e);
            throw new RuntimeException("Locking error", e);
        }
        // LOG.warn(StackTraceElementUtils.getStackStr(3) + " \n lock error " + this.key);
        return false;
    }

    /**
     * 加锁 应该以： lock(); try { doSomething(); } finally { unlock()； } 的方式调用
     *
     * @param timeout 超时时间(毫秒)
     * @param expire 锁的超时时间（秒），过期删除
     * @return 成功或失败标志
     */
    public boolean lock(long timeout, int expire)
    {
        long nano = System.nanoTime();
        timeout *= MILLI_NANO_CONVERSION;
        try
        {
            while ((System.nanoTime() - nano) < timeout)
            {
                if (setNx(this.key, LOCKED))
                {
                    LOG.debug("获取到lock" + this.key);
                    this.redisTemplate.expire(this.key, expire,TimeUnit.SECONDS);
                    this.locked = true;
                    return this.locked;
                }
                // 短暂休眠，避免出现活锁
                Thread.sleep(10);
            }
        }
        catch (Exception e)
        {
            LOG.error("加锁失败", e);
            throw new RuntimeException("Locking error", e);
        }
        return false;
    }

    /**
     * 加锁 应该以： lock(); try { doSomething(); } finally { unlock()； } 的方式调用
     *
     * @return 成功或失败标志
     */
    public boolean lock()
    {
        return lock(DEFAULT_TIME_OUT);
    }

    /**
     * 解锁 无论是否加锁成功，都需要调用unlock 应该以： lock(); try { doSomething(); } finally { unlock()； } 的方式调用
     */
    public void unlock()
    {
        try
        {
            RedisConnection connection =  getConnection();
            connection.del(redisTemplate.getStringSerializer().serialize(this.key));
            closeRedisConnection(connection);
        }
        catch (Exception e)
        {
            LOG.error("解锁错误:" + this.key, e);
        }
    }

    /**
     * 封装设置分布式锁api
     * @param keyStr
     * @param valueStr
     * @return
     */
    private Boolean setNx(String keyStr, String valueStr){
        RedisSerializer<String> redisSerializer = this.redisTemplate.getStringSerializer();
        byte[] key = redisSerializer.serialize(keyStr);
        byte[] value = redisSerializer.serialize(valueStr);
        RedisConnection connection = getConnection();
        boolean  result = connection.setNX(key,value);
        closeRedisConnection(connection);
        return result;
    }

    /**
     * 归还 connection
     * @param connection connection
     */
    private void closeRedisConnection(RedisConnection connection){
        if(connection!=null && !connection.isClosed())
        {
            connection.close();
        }
    }

    /**
     * 获取redis连接
     * @return
     */
    private RedisConnection getConnection(){
        return redisTemplate.getConnectionFactory().getConnection();
    }

}