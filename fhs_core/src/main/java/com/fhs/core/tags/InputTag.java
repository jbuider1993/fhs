package com.fhs.core.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

/**
 * 普通的input tag
 *
 * @Filename: InputTag.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwang
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 *               陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 *
 */
public class InputTag extends BaseFormTag
{
    /**
     * 类型 默认的text
     */
    private String type = "text";

    /**
     *如果要做唯一的话
     */
    private String ajaxCheckUrl;

    /**
     * 获取 类型 默认的text.
     *
     * @return bean的 类型 默认的text
     */
    public String getType()
    {
        return type;
    }

    /**
     * 设置 类型 默认的text.
     *
     * @param type 一个新的 类型 默认的text
     */
    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public void doTag()
        throws JspException, IOException
    {
        super.setClassName(super.getClassName() + " easyui-validatebox");
        write(" <div class=\"fitemDiv\">");
        write("<label>" + super.getTitle() + ":</label>");
        //密码框禁止黏贴 右键 拷贝  自制
        if(type.equals("password")) {
            write("<input type=\"" + this.type + "\" oncopy=\"return false\" onpaste=\"return false\" oncut=\"return false\" oncontextmenu=\"return false\" autocomplete=\"off\" ");
        }else {
            write("<input type=\"" + this.type + "\" autocomplete=\"off\" ");
        }

        write(super.getHtml().toString());
        write(" </div>");
    }

    /**
     * 获取 如果要做唯一的话.
     *
     * @return bean的 如果要做唯一的话
     */
    public String getAjaxCheckUrl()
    {
        return ajaxCheckUrl;
    }

    /**
     * 设置 如果要做唯一的话.
     *
     * @param ajaxCheckUrl 一个新的 如果要做唯一的话
     */
    public void setAjaxCheckUrl(String ajaxCheckUrl)
    {
        this.ajaxCheckUrl = ajaxCheckUrl;
    }

}
