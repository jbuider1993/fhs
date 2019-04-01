package com.fhs.core.tags;

import java.io.IOException;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.commons.lang3.StringUtils;



/**
 * 基础的formtag
 * @Filename: baseForm.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwang
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
public class BaseFormTag extends SimpleTagSupport
{
    /** 字段名称. */
    private String name;

    /** 是否为空. */
    private boolean required;

    /** 格式. */
    private String dataType;

    /** 字段标题. */
    private String title;

    /** 样式. */
    private String className;

    /** 最大长度. */
    private String maxLenth;

    /** 最小长度. */
    private String minLength;

    /** 长度校验类型 */
    private String lengthCheckType;

    /**
     * 错误提示，如果不给 默认为 title+格式错误
     */
    private String errorMsg;

    /**
     * 其他的属性
     */
    private Map<String, String> docAttr;

    /**
     * html类型
     */
    private String htmlType;


    public String getHtmlType() {
		return htmlType;
	}



	public void setHtmlType(String htmlType) {
		this.htmlType = htmlType;
	}



	/**
     * 获取 字段名称.
     *
     * @return bean的 字段名称
     */
    public String getName()
    {
        return name;
    }



    /**
     * 设置 字段名称.
     *
     * @param name 一个新的 字段名称
     */
    public void setName(String name)
    {
        this.name = name;
    }



    /**
     * 是否 是否为空.
     *
     * @return bean的 是否为空
     */
    public boolean isRequired()
    {
        return required;
    }



    /**
     * 设置 是否为空.
     *
     * @param required 一个新的 是否为空
     */
    public void setRequired(boolean required)
    {
        this.required = required;
    }



    /**
     * 获取 格式.
     *
     * @return bean的 格式
     */
    public String getDataType()
    {
        return dataType;
    }



    /**
     * 设置 格式.
     *
     * @param dataType 一个新的 格式
     */
    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }



    /**
     * 获取 字段标题.
     *
     * @return bean的 字段标题
     */
    public String getTitle()
    {
        return title;
    }



    /**
     * 设置 字段标题.
     *
     * @param title 一个新的 字段标题
     */
    public void setTitle(String title)
    {
        this.title = title;
    }



    /**
     * 获取 样式.
     *
     * @return bean的 样式
     */
    public String getClassName()
    {
        return className;
    }



    /**
     * 设置 样式.
     *
     * @param className 一个新的 样式
     */
    public void setClassName(String className)
    {
        this.className = className;
    }






    public String getMaxLenth() {
		return maxLenth;
	}



	public void setMaxLenth(String maxLenth) {
		this.maxLenth = maxLenth;
	}



	public String getMinLength() {
		return minLength;
	}



	public void setMinLength(String minLength) {
		this.minLength = minLength;
	}


    public String end="/>";

	/**
     * 获取html字符串
     * @throws JspException JspException
     * @throws IOException
     */
    public StringBuilder getHtml()
        throws JspException, IOException
    {




        if(this.title==null || this.name == null)
        {
            throw new JspException("title和name 不能为空");
        }
        StringBuilder htmlBuilder = new StringBuilder(" name=\"" + name +"\" id=\"" + name + "\"");
        if(this.isRequired() && dataType==null && this.lengthCheckType == null)
        {
            htmlBuilder.append(" datatype=\"*\" ");
            htmlBuilder.append(" nullmsg=\"" + title + "不能为空\" ");
        }

        if(dataType!=null)
        {
            htmlBuilder.append(" datatype=\"" + dataType + "\" ");
            htmlBuilder.append(" nullmsg=\"" + title + "不能为空\" ");
            htmlBuilder.append(" errormsg=\"" + title + "格式不正确\" ");
        }
        if(this.className !=null)
        {
            htmlBuilder.append(" class=\"" + className + "\" ");
        }

        if(this.maxLenth != null & this.minLength !=null && dataType == null)
        {
            htmlBuilder.append(" datatype=\"" + this.lengthCheckType + this.minLength + "-" + this.maxLenth + "\" ");
            htmlBuilder.append(" nullmsg=\"" + title + "不能为空\" ");
            htmlBuilder.append(" errormsg=\"" + title + "长度在 "+this.minLength+"到" + this.maxLenth  + "之间\" ");
        }
        if(this.isRequired())
        {
            htmlBuilder.append("  placeholder=\"请填写" + title + "\" prompt=\"请选择" + title + "\"");
        }

        htmlBuilder.append(" />");
        htmlBuilder.append("</textarea>");
        if(this.isRequired())
        {
            htmlBuilder.append(" <span class=\"form-field-required\">*</span> ");
        }
        return htmlBuilder;
    }

    /**
     * 写一段js
     * @param jsContent jsContent
     * @return jsContent
     */
    protected String getScript(String jsContent)
    {
        return "<script type=\"text/javascript\">"+ jsContent + "</script>";
    }

    /**
     *  写字符串到前台
     * @param str 需要写的字符串
     * @throws IOException
     */
    protected void write(String str) throws IOException
    {
        getJspContext().getOut().write(str);
    }



    /**
     * 获取 错误提示，如果不给 默认为 title+格式错误.
     *
     * @return bean的 错误提示，如果不给 默认为 title+格式错误
     */
    public String getErrorMsg()
    {
        return errorMsg;
    }



    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }



    public Map<String, String> getDocAttr()
    {
        return docAttr;
    }



    public void setDocAttr(Map<String, String> docAttr)
    {
        this.docAttr = docAttr;
    }

    public String getLengthCheckType() {
        return lengthCheckType;
    }

    public void setLengthCheckType(String lengthCheckType) {
        this.lengthCheckType = lengthCheckType;
    }

    /**
     * 默认className
     *
     * @param defaultClassName
     *            <li>Author: Lins</li>
     *            <li>Date: 2018年5月11日</li>
     */

    protected void defaultClassName(String defaultClassName)
    {
        if (StringUtils.isEmpty(this.getClassName()))
        {
            this.setClassName(defaultClassName);
        }
        else
        {
            if (!this.getClassName().equals(defaultClassName))
            {
                this.setClassName(defaultClassName + " " + this.getClassName());
            }
            else
            {
                this.setClassName(this.getClassName());
            }

        }

    }

}
