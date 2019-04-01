package com.fhs.core.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

/**
 * 多张上传图片.
 *
 * @Filename: MultiPictureUploadTag.java
 * @Description:
 * @Version: 1.0
 * @Author: Lins
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 *               陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
public class MultiPictureUploadTag extends BaseFormTag
{
    
    // 默认class
    private final String defaultClassName = "ueEditorText";
    
    /** 宽度. */
    private Integer width;
    
    /** 高度. */
    private Integer height;

    /** 上传描述. */
    private String uploadDesc;
    
    @Override
    public void doTag()
        throws JspException, IOException
    {
        defaultClassName(defaultClassName);
        write(  "<div class='fitem'> \r\n"
            + "    <div class='fitemDiv'>\r\n" 
            + "        <div class='uploadLableDiv'> \r\n" 
            + "            <label class='uploadLable'>"+ super.getTitle() +":</label>\r\n" 
            + "        </div>                       \r\n" 
            + "        <input type='file' id='"+super.getName()+"File'/>\r\n"
            + "    </div>                           \r\n" 
            + "</div>                               \r\n" 
            + "<div class='fitem'>                  \r\n" 
            + "    <div class='bigLabelDiv'>        \r\n" 
            + "        <label>&nbsp;</label>        \r\n" 
            + "    </div>                           \r\n"
            + "    &nbsp;&nbsp;&nbsp;&nbsp;" + uploadDesc+"\r\n"
            + "</div>                               \r\n" 
            + "<div class='fitem'>                  \r\n" 
            + "    <div class='bigLabelDiv'>        \r\n"     
            + "        <label>&nbsp;</label>        \r\n" 
            + "    </div>                           \r\n" 
            + "    <div class='bigContent' id='"+super.getName()+"FileShow'> \r\n"
            + "    </div>                           \r\n" 
            + "</div>                               \r\n"    
            + "<input type='hidden' name="+super.getName()+" id="+super.getName()+" />\r\n" );    
        
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
