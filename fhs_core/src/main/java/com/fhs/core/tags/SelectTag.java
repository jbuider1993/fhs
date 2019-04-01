package com.fhs.core.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

/**
 * 下拉框tag
 * 
 * @Filename: SelectTag.java
 * @Description:
 * @Version: 1.0
 * @Author: Lins
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 *  陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 * 
 */
public class SelectTag extends BaseFormTag
{
    // 下拉数据的url
    private String dataUrl;
    
    // 下拉数据赋值字段
    private String valueField;
    
    // 下拉数据显示字段
    private String textField;
    
    // 是否显示全部
    private boolean showAll = false;
    
    // 默认class
    private final String defaultClassName = "easyui-combobox";
    
    @Override
    public void doTag()
        throws JspException, IOException
    {
        defaultClassName(defaultClassName);
        
        write(" <div class=\"fitemDiv\">");
        write("<label>" + super.getTitle() + ":</label>");
        write("<input    data-options=\"\r\n" + "                                        valueField: '" + valueField
            + "',\r\n" + "                                        textField: '" + textField + "',\r\n"
            + "                                        editable : false,\r\n"
            + "                                        panelHeight: 'auto',\r\n"
            + "                                        showAll: " + showAll + ",\r\n"
            + "                                        url: '" + dataUrl + "', \r\n"
            + "                                         \"\r\n");
        write(super.getHtml().toString());
        write(" </div>");
    }
    
    public String getDataUrl()
    {
        return dataUrl;
    }
    
    public void setDataUrl(String dateUrl)
    {
        this.dataUrl = dateUrl;
    }
    
    public String getValueField()
    {
        return valueField;
    }
    
    public void setValueField(String valueField)
    {
        this.valueField = valueField;
    }
    
    public String getTextField()
    {
        return textField;
    }
    
    public void setTextField(String textField)
    {
        this.textField = textField;
    }
    
    public boolean isShowAll()
    {
        return showAll;
    }
    
    public void setShowAll(boolean showAll)
    {
        this.showAll = showAll;
    }
    
    public String getDefaultClassName()
    {
        return defaultClassName;
    }
    
}
