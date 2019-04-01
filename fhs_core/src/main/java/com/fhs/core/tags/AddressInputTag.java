package com.fhs.core.tags;
import java.io.IOException;

import javax.servlet.jsp.JspException;

/**
 * 详细地址
 *
 * @Filename: AddressInputTag.java
 * @Description:
 * @Version: 1.0
 * @Author: Lins
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 *               陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 *
 */
public class AddressInputTag extends InputTag
{
    private final String defaultClassName = "big_text";

    @Override
    public void doTag()
        throws JspException, IOException
    {
        defaultClassName(defaultClassName);
        if(this.isRequired()){
            write("<div class='bigLabelDiv'>");
            write("    <label>" + super.getTitle() + "</label>");
            write("</div>");
            write("<div class='bigContent'>");
            write("    <input type='text' id='address2' autocomplete='off' class='big_text'");
            write("     <onfocus='setValue(this)' onkeyup='mysearch(this)' /> ");
            write("    <span class='form-field-required'>*</span> ");
            write("    <input style='height: 0px; visibility: hidden' id=\"address\"  class=\"big_text\" type=\"text\"	name=\"address\"   /> ");
            write("    <div id='searchResultPanel'");
            write("        style='border: 1px solid #C0C0C0; width: 150px; height: auto; display: none; z-index: 110005; position: absolute;'>");
            write("    </div>");
            write("</div>");
        }else {
            write("<div class='bigLabelDiv'>");
            write("    <label>" + super.getTitle() + "</label>");
            write("</div>");
            write("<div class='bigContent'>");
            write("    <input type='text' id='address2' class='big_text'");
            write("     <onfocus='setValue(this)' onkeyup='mysearch(this)' /> ");
            write("    <input style='height: 0px; visibility: hidden' id=\"address\"  class=\"big_text\" type=\"text\"	name=\"address\"   /> ");
            write("    <div id='searchResultPanel'");
            write("        style='border: 1px solid #C0C0C0; width: 150px; height: auto; display: none; z-index: 110005; position: absolute;'>");
            write("    </div>");
            write("</div>");
        }
    }

	public String getDefaultClassName() {
		return defaultClassName;
	}

}
