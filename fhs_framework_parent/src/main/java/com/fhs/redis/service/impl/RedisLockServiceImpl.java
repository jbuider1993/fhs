package com.fhs.redis.service.impl;

import com.fhs.common.utils.Logger;
import com.fhs.core.db.DataSource;
import com.fhs.redis.bean.RedisLock;
import com.fhs.redis.service.RedisLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * 内存数据库Redis的辅助类，负责对内存数据库的所有操作
 *
 * @author wangpengfei
 * @version [版本号, 2016年7月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("redisLockServiceImpl")
public class RedisLockServiceImpl implements RedisLockService
{
    /**
     * 日志记录
     */
    private static final Logger LOG = Logger.getLogger(RedisLockServiceImpl.class);


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * redisLock工具类
     */
    private RedisLock redisLock;



    @Override
    public boolean addRedisLock(String lockMark)
    {
        try
        {
            // 加锁失败或者异常后,返回false
            redisLock = new RedisLock(lockMark, redisTemplate);
            LOG.info("加锁:" + lockMark);
            return redisLock.lock(5000, 1 * 60);
        }
        catch (Exception e)
        {
            LOG.error("加锁失败，key:" + lockMark + ",异常:", e);
            // 异常之后发邮件通知管理员
            return false;
        }
    }



    @Override
    public boolean checkLockExit(String lockMark, long timeout)
    {
        try
        {
            // 加锁失败或者异常后,返回false
            redisLock = new RedisLock(lockMark, redisTemplate);
            LOG.warn("判断锁是否存在:" + lockMark);
            return redisLock.checkLockExit(timeout);
        }
        catch (Exception e)
        {
            LOG.error("加锁失败，key:" + lockMark + ",异常:", e);
            // 异常之后发邮件通知管理员
            return false;
        }
    }



    @Override
    public boolean addRedisLock(String lockMark, long timeout, int expire)
    {
        try
        {
            // 加锁失败或者异常后,返回false
            redisLock = new RedisLock(lockMark, redisTemplate);
            LOG.info("addLock:" + lockMark);
            return redisLock.lock(timeout, expire);
        }
        catch (Exception e)
        {
            LOG.error("add Lock Error，key:" + lockMark + ",异常:", e);
            // 异常之后发邮件通知管理员
            return false;
        }
    }

    @Override
    public void delRedisLock(String lockMark)
    {
        try
        {
            redisLock = new RedisLock(lockMark, redisTemplate);
            LOG.warn("解锁:" + lockMark);
            redisLock.unlock();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            LOG.error("删除锁失败，key:" + lockMark + ",异常:", e);
            // 异常之后发邮件通知管理员
        }
    }
}