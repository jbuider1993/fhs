package com.fhs.core.tags;

import com.fhs.core.config.EConfig;

import javax.servlet.jsp.JspException;
import java.io.IOException;


// TODO: Auto-generated Javadoc
/**
 * 字典.
 *
 * @Filename: WordBookTag.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwang
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 *               陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
public class WordBookTag extends BaseFormTag
{
    
    /** 字典编码. */
    private String code;
    
    // 是否显示全部
    private boolean showAll = false;

    
    @Override
    public void doTag()
        throws JspException, IOException
    {
        super.setClassName(super.getClassName() + " easyui-combobox");
        write(" <div class=\"fitemDiv\">");
        write("<label>" + super.getTitle() + ":</label>");
        write("<input  data-options=\"\r\n" + "                                        valueField: 'wordbookCode',\r\n"
            + "                                        textField: 'wordbookDesc',\r\n"
            + "                                        editable : false,\r\n"
            + "                                        panelHeight: 'auto',\r\n"
            + "                                        showAll: " + showAll + ",\r\n"
            + "                                        url: '" + EConfig.getPathPropertiesValue("systemServiceUrl")
            + "webApi/wordbook/getData?wordbookGroupCode=" + code + "&jsonpCallback=?' \"");
        write(super.getHtml().toString());
        write(" </div>");
    }
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public boolean isShowAll()
    {
        return showAll;
    }
    
    public void setShowAll(boolean showAll)
    {
        this.showAll = showAll;
    }
    
}
