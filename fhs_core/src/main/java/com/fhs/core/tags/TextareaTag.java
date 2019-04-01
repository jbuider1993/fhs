package com.fhs.core.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

/**
 * 多行文本框
 *
 * @Filename: TextareaTag.java
 * @Description:
 * @Version: 1.0
 * @Author: Lins
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 *               陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 *
 */
public class TextareaTag extends InputTag
{
    private final String defaultClassName = "big_text";

    private String rows;

    private String cols;


    @Override
    public void doTag()
        throws JspException, IOException
    {
        super.setHtmlType("textarea");
        defaultClassName(defaultClassName);
        write(" <div class=\"bigLabelDiv\">");
        write(" <label>" + super.getTitle() + ":</label>");
        write(" </div> ");
        write(" <div class=\"bigContent\">");
        write(" <textarea style=\"margin-top:10px\"  rows=\"" + this.getRows() + "\" cols=\"" + this.getCols() + "\""
            + super.getHtml());
        write(" </div> ");
    }

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

}
