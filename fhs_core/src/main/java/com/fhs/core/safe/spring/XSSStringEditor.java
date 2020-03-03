package com.fhs.core.safe.spring;

import com.fhs.common.utils.ConverterUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.beans.PropertyEditorSupport;

/**
 * 解决XSS漏洞
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.core.safe.spring
 * @ClassName: XSSStringEditor
 * @Author: JackWang
 * @CreateDate: 2018/12/9 0009 14:06
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/9 0009 14:06
 * @Version: 1.0
 */
public class XSSStringEditor extends PropertyEditorSupport implements WebBindingInitializer {

    /**
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String formBody= Jsoup.clean(text, Whitelist.relaxed().addAttributes(":all", "style","controls","src","data-setup")
                .addTags("video"));
        setValue(formBody);
    }

    /**
     * @see java.beans.PropertyEditorSupport#getAsText()
     */
    @Override
    public String getAsText() {
        return  getValue()==null?null : ConverterUtils.toString(getValue());
    }

    @Override
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, this);
    }


}