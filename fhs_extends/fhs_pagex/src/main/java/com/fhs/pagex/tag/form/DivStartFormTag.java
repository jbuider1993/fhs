package com.fhs.pagex.tag.form;

import org.springframework.stereotype.Component;

/**
 * div start
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.tag.form
 * @ClassName: SelectFormTag
 * @Author: JackWang
 * @CreateDate: 2018/12/4 0004 19:32
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/4 0004 19:32
 * @Version: 1.0
 */
@Component
public class DivStartFormTag extends  InputFormTag {
    static
    {
        FormTagFactory.regTag("divStart", DivStartFormTag.class);
    }

    @Override
    public String getContentHtml() {
        // 为了防止大家copy不知道怎么写，这里在说明一下，给form中 写的代码在这里 如果 isNewRow() 返回true的话那么 <div class="fitem">以及他的结束都要自己在这个方法中写，
        // 如果返回isNewRow() 返回false的话不需要管<div class="fitem">
        StringBuilder resultHtmlBuilder = new StringBuilder("<div id='"+ super.tagSett.get("id") + "'");
        resultHtmlBuilder.append(formartClass(""));
        resultHtmlBuilder.append(getOtherAttrValHtml() + "'>");
        return resultHtmlBuilder.toString();
    }
    @Override

    public String readyJs() {
        return "";
    }

    @Override
    public boolean isNewRow() {
        return true;
    }

    @Override
    protected String[] getHandelKeys() {
        return new String[0];
    }
}
