package com.fhs.core.db.ds;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import com.fhs.core.exception.BusinessException;
import com.fhs.logger.Logger;
import org.springframework.jdbc.datasource.AbstractDataSource;



/**
 *
 * 自定义datasource，来支持datasource路由功能
 *
 * @author wanglei/jackwong
 *
 */
public class ReadWriteDataSource extends AbstractDataSource
{

    /** The Constant log. */
    private static final Logger log = Logger.getLogger(ReadWriteDataSource.class);

    /** 默认的datasource. */
    private DataSource defaultDataSource;

    /** dataSourceMap key是datasourcename value是datasource对象. */
    private Map<String, DataSource> dataSourceMap;


    /**
     * 去选择datasource.
     *
     * @return DataSource
     */
    private DataSource determineDataSource()
    {

        if (ReadWriteDataSourceDecision.getDataSource() == null)
        {
            return  defaultDataSource;
        }
        DataSource result = dataSourceMap.get(ReadWriteDataSourceDecision.getDataSource());
        if (result == null)
        {
            throw new BusinessException("根据datasourcename拿不到 datasource:" + ReadWriteDataSourceDecision.getDataSource());
        }
        log.debug(
            "current determine other datasource datasource name is:" + ReadWriteDataSourceDecision.getDataSource());
        return result;

    }

    /**
     * @return
     * @throws SQLException
     * @see DataSource#getConnection()
     *<pre>
     *<li>Author: jackwang</li>
     *<li>Date: 2018年5月15日</li>
     *</pre>
     */
    @Override
    public Connection getConnection()
        throws SQLException
    {
        return determineDataSource().getConnection();
    }

    /**
     * @param username
     * @param password
     * @return
     * @throws SQLException
     * @see DataSource#getConnection(String, String)
     *<pre>
     *<li>Author: jackwang</li>
     *<li>Date: 2018年5月15日</li>
     *</pre>
     */
    @Override
    public Connection getConnection(String username, String password)
        throws SQLException
    {
        return determineDataSource().getConnection(username, password);
    }

    /**
     * 获取 dataSourceMap key是datasourcename value是datasource对象.
     *
     * @return bean的 dataSourceMap key是datasourcename value是datasource对象
     */
    public Map<String, DataSource> getDataSourceMap()
    {
        return dataSourceMap;
    }

    /**
     * 设置 dataSourceMap key是datasourcename value是datasource对象.
     *
     * @param dataSourceMap 一个新的 dataSourceMap key是datasourcename value是datasource对象
     */
    public void setDataSourceMap(Map<String, DataSource> dataSourceMap)
    {
        this.dataSourceMap = dataSourceMap;
    }

    /**
     * 获取 默认的datasource.
     *
     * @return bean的 默认的datasource
     */
    public DataSource getDefaultDataSource()
    {
        return defaultDataSource;
    }

    /**
     * 设置 默认的datasource.
     *
     * @param defaultDataSource 一个新的 默认的datasource
     */
    public void setDefaultDataSource(DataSource defaultDataSource)
    {
        this.defaultDataSource = defaultDataSource;
    }


}