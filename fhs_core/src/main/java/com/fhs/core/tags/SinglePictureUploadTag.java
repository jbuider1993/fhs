package com.fhs.core.tags;

import com.fhs.core.config.EConfig;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * 单张上传图片.
 *
 * @Filename: HeaderTag.java
 * @Description:
 * @Version: 1.0
 * @Author: Lins
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 *               陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
public class SinglePictureUploadTag extends BaseFormTag
{

    // 默认class
    private final String defaultClassName = "ueEditorText";

    /** 宽度. */
    private Integer width;

    /** 高度. */
    private Integer height;

    /** 描述. */
    private String uploadDesc;


    //id=super.getName() + "InputVal"
    //id=super.getName() + "InputLabel"
    @Override
    public void doTag()
            throws JspException, IOException
    {
        defaultClassName(defaultClassName);
        String required = "";
        String requiredSpan = "";
        if(this.isRequired ()){
            required = "datatype = '*' nullmsg = '请先上传图片' ";
            requiredSpan = "<span class=\"form-field-required\">*</span>";
        }
        write("	<div class=\"fitem\" tag=\"singlePicture\" inputImg=\""+ super.getName() + "\"> \r\n"
                + "		<div class=\"fitemDiv\">						\r\n"
                + "			<label>&nbsp;</label>						\r\n"
                + "         <img id=\"" + super.getName()+ "InputImg\" class=\"headerImg\" src=\"" + EConfig.getPathPropertiesValue("staticPath") + "/images/upload_default_show.png\"/>\r\n"
                +  requiredSpan
                + "         <input type=\"hidden\" id=\"" + super.getName() + "InputVal\" " +required+ "/>\r\n"
                + "     </div>											\r\n"
                + " </div>\r\n"
                + "														\r\n"
                + " <div class=\"fitem\">								\r\n"
                + " 	<div class=\"fitemDiv\">						\r\n"
                + " 		<div class=\"uploadLableDiv\">				\r\n"
                + " 			<label class=\"uploadLable\"> " + super.getTitle() + ":</label>\r\n"
                + " 		</div>										\r\n"
                + " 		<input type=\"button\" id=\"" + super.getName() + "Input\" style=\"display: none;\" />\r\n"
                + " 	</div>											\r\n"
                + " </div>												\r\n"
                + "														\r\n"
                + " <input type=\"hidden\" id=\"" + super.getName() + "\" name=\"" + super.getName() + "\" />\r\n"
                + "														\r\n"
                + "														\r\n"
                + " <div class=\"fitem\">								\r\n"
                + " 	<div class=\"bigLabelDiv\">						\r\n"
                + " 		<label>&nbsp;</label>						\r\n"
                + " 	</div>\r\n");
        if (this.height != null && this.width != null)
        {
            write(" &nbsp;&nbsp;&nbsp;&nbsp;请上传尺寸为" + width + "px*" + height + "px的图片\r\n");
        }
        if (this.uploadDesc != null)
        {
            write(uploadDesc + "\r\n");
        }
        write(" </div>");
        // write(getScript("$(function(){inituploadifyHeader("+super.getName()+"Input, \"上传" + super.getTitle() +
        // "\");})"));

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

    public String getUploadDesc() {
        return uploadDesc;
    }

    public void setUploadDesc(String uploadDesc) {
        this.uploadDesc = uploadDesc;
    }
}
