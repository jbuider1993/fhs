package com.fhs.core.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

/**
 * 大的输入框，一行只占用一个的
 * @Filename: BigInput.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwang
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
public class BigInputTag extends InputTag
{

    @Override
    public void doTag()
        throws JspException, IOException
    {
        super.setClassName(super.getClassName() + " big_text");

        write(" <div class=\"bigLabelDiv\" style=\"margin-top:8px;\" >");
        write("   <label>"+super.getTitle()+":</label>");
        write(" </div> <div class=\"bigContent\">");
        write(" <input type=\"text\" autocomplete=\"off\" " + super.getHtml());
        write(" </div> ");
    }

}
