package com.fhs.core.base.form;

import com.fhs.common.constant.Constant;

/**
 *
 * @Description: 分页form
 * @Version: 1.0
 * @Author: 肖锐
 * @Email: xr@sxpartner.com 陕西小伙伴网络科技有限公司 Copyright (c) 2018 All Rights Reserved.
 *
 *
 */
public class PagerForm extends  BaseForm {

    /**
     *每页多少条数据
     */
    private Integer rows;

    /**
     * 当前第几页
     */
    private Integer page;

    /**
     * 开始行
     */
    private Integer start;

    public Integer getStart(){
        return page;
    }

    public PagerForm(Integer page, Integer rows) {
        this.page = page;
        this.rows = rows;
    }
    public PagerForm(){

     }
    /**
     * 获取分页信息
     */
    public PagerForm getPageInfo(Integer page, Integer rows)
    {
        if (page == Constant.ZERO || rows == Constant.ZERO)
        {
            return new PagerForm(0, 10);
        }
        else

        {
            return new PagerForm((page - 1) * rows, rows);
        }
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
