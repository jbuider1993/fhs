package com.fhs.core.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

/**
 * 时间日期tags
 *
 * @Filename: DateInputTag.java
 * @Description:
 * @Version: 1.0
 * @Author: Lins
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 *               陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 *
 */
public class DateInputTag extends BaseFormTag
{
    /**
     * 类型 默认的text
     */
    private String type = "text";

    /**
     * 如果要做唯一的话
     */
    private String onClick;

    private final String defaultClassName = "Wdate";

    @Override
    public void doTag()
        throws JspException, IOException
    {
//        defaultClassName(defaultClassName);

        write(" <div class=\"fitemDiv\">");
        write("     <label>" + super.getTitle() + ":</label>");
        if(this.onClick == null)
        {
            if(this.getName().contains("date"))
            {
                this.onClick = "WdatePicker()";
            }
            else
            {
                 this.onClick = "WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})";
            }
        }
        this.onClick = this.onClick.replace("/", "\\");
        write("     <input type=\"" + this.type + "\" autocomplete=\"off\" onclick=\"" + this.onClick + "\" ");
        write(super.getHtml().toString());
        write(" </div>");
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public String getDefaultClassName() {
		return defaultClassName;
	}


}
