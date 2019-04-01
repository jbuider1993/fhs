package com.fhs.core.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;


/**
 *  百度地图标点
 * @Filename: BDPointTag.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwang
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
public class BDPointTag extends BaseFormTag
{

    /** 经度名称. */
    private String loName;

    /** 纬度名称. */
    private String laName;


    @Override
    public void doTag()
        throws JspException, IOException
    {
        write(" <div class=\"fitem\">");
        write(" <div class=\"bigLabelDiv\">");
        write("   <label>选取坐标</label>");
        write(" </div> <div class=\"bigContent\">");
        write(" <div id=\"allmap\" class=\"bMap\"></div> ");
        write(" </div> </div>");
    }

    /**
     * 获取 经度名称.
     *
     * @return bean的 经度名称
     */
    public String getLoName()
    {
        return loName;
    }

    /**
     * 设置 经度名称.
     *
     * @param loName 一个新的 经度名称
     */
    public void setLoName(String loName)
    {
        this.loName = loName;
    }

    /**
     * 获取 纬度名称.
     *
     * @return bean的 纬度名称
     */
    public String getLaName()
    {
        return laName;
    }

    /**
     * 设置 纬度名称.
     *
     * @param laName 一个新的 纬度名称
     */
    public void setLaName(String laName)
    {
        this.laName = laName;
    }


}
