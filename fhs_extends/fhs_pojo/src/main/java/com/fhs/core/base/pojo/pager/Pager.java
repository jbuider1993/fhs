package com.fhs.core.base.pojo.pager;

import java.util.Collection;

/**
 * 分页对象
 * @Filename: Pager.java
 * @Description:
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
public class Pager<T>
{

    /**
     *一共多少条数据
     */
    private long total;

    /**
     *当页显示数据
     */
    private Collection<T> rows;


    public Pager(long total, Collection<T> rows)
    {
        super();
        this.total = total;
        this.rows = rows;
    }

    public long getTotal()
    {
        return total;
    }
    public void setTotal(int total)
    {
        this.total = total;
    }
    public Collection<T> getRows()
    {
        return rows;
    }
    public void setRows(Collection<T> rows)
    {
        this.rows = rows;
    }

}
