package com.fhs.redis.service;

/**
 *
 * 内存数据库Redis的辅助类，负责对内存数据库的所有操作
 *
 * @author wangpengfei
 * @version [版本号, 2016年7月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface RedisLockService
{
    /**
     * 给redis锁添加一个groupCode
     *
     * @param lockMark
     * @return 成功true,失败false
     */
    boolean addRedisLock(String lockMark);

    /**
     * 如果所存在则等待，不存在则直接返回
     * @param lockMark 锁的key
     * @param timeout 超时时间
     * @return 是否成功
     */
    boolean checkLockExit(String lockMark, long timeout);

    /**
     * 给redis锁添加一个groupCode
     *
     * @param lockMark 锁的key
     * @param timeout 超时时间(毫秒)
     * @param expire 锁的超时时间（秒）,过期删除
     * @return 成功true,失败false
     */
    boolean addRedisLock(String lockMark, long timeout, int expire);

    /**
     * 从redis锁中删除一个groupCode
     *
     * @param lockMark
     */
    void delRedisLock(String lockMark);

}