package com.fhs.pagex.tag.form;

import org.springframework.stereotype.Component;

/**
 *  多行文本框 小
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.tag.form
 * @ClassName: InputFormTag
 * @Author: JackWang
 * @CreateDate: 2018/12/3 0003 18:08
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/3 0003 18:08
 * @Version: 1.0
 */
@Component
public class TextFormTag extends  EmptyFormTag{

    static{
        FormTagFactory.regTag("text",TextFormTag.class);
    }

    @Override
    public String getContentHtml() {
        // 为了防止大家copy不知道怎么写，这里在说明一下，给form中 写的代码在这里 如果 isNewRow() 返回true的话那么 <div class="fitem">以及他的结束都要自己在这个方法中写，
        // 如果返回isNewRow() 返回false的话不需要管<div class="fitem">
        StringBuilder resultHtmlBuilder = new StringBuilder("<div class=\"fitem\">");
        resultHtmlBuilder.append(getTitleHtml());
        resultHtmlBuilder.append(" <textarea  " + super.formartDataType());
        resultHtmlBuilder.append(formartClass("form_textarea"));
        resultHtmlBuilder.append(formartIdNameHtml());
        resultHtmlBuilder.append(formartPlaceholderHtml() + " /></textarea>");
        resultHtmlBuilder.append(formartRequiredHtml());
        resultHtmlBuilder.append("</div></div>");
        return resultHtmlBuilder.toString();
    }

    @Override
    public boolean isNewRow() {
        // 这个组件是否要强制新启一行，需要强制的有 bigInput textare 上传 百度编辑器  省市区插件 地图插件 经纬度插件
        return true;
    }

    @Override
    protected String[] getHandelKeys() {
        // 哪些参数是你这个控件特有的，我这个input没啥特有的控件，所以返回了空数组
        return new String[]{};
    }
}
