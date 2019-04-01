package com.fhs.core.cache;



/**
 *  开放设置dbindex
 * @Filename: JedisShardInfo.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwang
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
public  class JedisShardInfo extends redis.clients.jedis.JedisShardInfo
{

    /**
     * Instantiates a new jedis shard info.
     *
     * @param host the host
     * @param port the port
     */
    public JedisShardInfo(String host, int port)
    {
        super(host, port);
    }

    /** 数据库index. */
    private int db;

    /**
     * 获取 数据库index.
     *
     * @return bean的 数据库index
     */
    public int getDb()
    {
        return db;
    }

    /**
     * 设置 数据库index.
     *
     * @param db 一个新的 数据库index
     */
    public void setDb(int db)
    {
        this.db = db;
    }


}