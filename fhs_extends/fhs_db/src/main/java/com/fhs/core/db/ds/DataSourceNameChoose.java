package com.fhs.core.db.ds;

/**
 * 核心业务库读写分离
 * @Filename: DataSouceNameChoose.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwong
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
public interface DataSourceNameChoose
{
    /**
     * 获取读的DataSourceName
     * @param catDBFlag 分库标志
     * @return  读的DataSourceName
     */
    String getReadDataSourceName(String catDBFlag);

    /**
     * 获取写的DataSourceName
     * @param catDBFlag 分库标志
     * @return  写的DataSourceName
     */
    String getWriteDataSourceName(String catDBFlag);
}
