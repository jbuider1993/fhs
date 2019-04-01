package com.fhs.core.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

/**
 * 地区标签
 * @Filename: AreaTag.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwang
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
public class AreaTag  extends BaseFormTag
{

    @Override
    public void doTag()
        throws JspException, IOException
    {
        super.setClassName(super.getClassName() + " easyui-combobox");
       write(" <div class=\"fitemDiv\">");
       write("<label>" + super.getTitle() + ":</label>");
       write("<input editable=\"true\" data-options=\"\r\n" +
            "                                    valueField: 'id',\r\n" +
            "                                    textField:'areaName',\r\n" +
            "                                    onSelect: function(rec){\r\n" +
            "                                        if(typeof(selShowMap) == 'function') {selShowMap(rec.areaName);}\r\n" +
            "                                    }\" ");
       write(super.getHtml().toString());
       write(" </div>");
    }

}
