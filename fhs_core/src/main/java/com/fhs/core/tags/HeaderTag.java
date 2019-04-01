package com.fhs.core.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;


/**
 * 上传图片.
 *
 * @Filename: HeaderTag.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwang
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 *              陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
public class HeaderTag extends BaseFormTag
{

    /** 宽度. */
    private Integer width;

    /** 高度. */
    private Integer height;

    public static String staticPath;
    @Override
    public void doTag()
        throws JspException, IOException
    {
        super.setClassName(super.getClassName() + " ueEditorText");
        write("<div class=\"fitem\">\r\n" + "        <div class=\"fitemDiv\">\r\n"
            + "            <label>&nbsp;</label> <img id=\"" + super.getName() + "InputImg\" class=\"headerImg\" src=\""
            + staticPath + "/images/upload_default_show.png\"/>\r\n"
            + "            <input type=\"hidden\" id=\"" + super.getName() + "\">\r\n" + "        </div>\r\n"
            + "    </div>\r\n" + "\r\n" + "    <div class=\"fitem\">\r\n" + "        <div class=\"fitemDiv\">\r\n"
            + "            <div class=\"uploadLableDiv\">\r\n" + "                <label class=\"uploadLable\">"
            + super.getTitle() + ":</label>\r\n" + "            </div>\r\n" + "            <input type=\"button\" id=\""
            + super.getName() + "Input\" style=\"display: none;\" />\r\n" + "        </div>\r\n" + "    </div>\r\n"
            + "\r\n" + "    <input type=\"hidden\" id=\"" + super.getName() + "\" name=\"" + super.getName()
            + "\" />\r\n" + "\r\n" + "    <div class=\"fitem\">\r\n" + "        <div class=\"bigLabelDiv\">\r\n"
            + "            <label>&nbsp;</label>\r\n" + "        </div>\r\n");
        if (this.height != null && this.width != null)
        {
            write("        &nbsp;&nbsp;&nbsp;&nbsp;请上传尺寸为" + width + "px*" + height + "px的图片\r\n");
        }
        write("    </div>");
        write(getScript("$(function(){inituploadifyHeader(\"headerInput\", \"上传" + super.getTitle() + "\");})"));

    }

    /**
     * 获取 宽度.
     *
     * @return bean的 宽度
     */
    public Integer getWidth()
    {
        return width;
    }

    /**
     * 设置 宽度.
     *
     * @param width 一个新的 宽度
     */
    public void setWidth(Integer width)
    {
        this.width = width;
    }

    /**
     * 获取 高度.
     *
     * @return bean的 高度
     */
    public Integer getHeight()
    {
        return height;
    }

    /**
     * 设置 高度.
     *
     * @param height 一个新的 高度
     */
    public void setHeight(Integer height)
    {
        this.height = height;
    }

}
