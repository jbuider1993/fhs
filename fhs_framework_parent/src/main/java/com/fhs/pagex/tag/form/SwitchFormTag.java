package com.fhs.pagex.tag.form;

import com.fhs.pagex.common.BeetlUtil;
import org.springframework.stereotype.Component;

/**
 *  开关标签
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
public class SwitchFormTag extends  InputFormTag {
    static
    {
        FormTagFactory.regTag("switch", SwitchFormTag.class);
    }

    @Override
    public String getContentHtml() {
        // 为了防止大家copy不知道怎么写，这里在说明一下，给form中 写的代码在这里 如果 isNewRow() 返回true的话那么 <div class="fitem">以及他的结束都要自己在这个方法中写，
        // 如果返回isNewRow() 返回false的话不需要管<div class="fitem">
        StringBuilder resultHtmlBuilder = new StringBuilder();
        resultHtmlBuilder.append("<div class=\"fitem\">");
        resultHtmlBuilder.append(getTitleHtml());
        resultHtmlBuilder.append(" <input type='hidden' " + super.formartDataType());
        resultHtmlBuilder.append(formartIdNameHtml()+ " />");
        resultHtmlBuilder.append(" <input type='checkbox'class='lcs_check' id='" +  super.tagSett.get("name") + "_switch'/>" );
        resultHtmlBuilder.append(formartRequiredHtml());
        resultHtmlBuilder.append("</div></div>");
        request.setAttribute("tagSett",super.tagSett);
        try {
            resultHtmlBuilder.append(BeetlUtil.renderBeelt("/pagex/tags/switch_tag.html",super.getBeetlParamMap()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultHtmlBuilder.toString();
    }


    @Override
    public String loadSuccessJs() {
        return super.tagSett.get("name") + "_switch_loadSuccess(info)";
    }

    @Override
    public String saveJs() {
        return super.tagSett.get("name") + "_switch_onSave()";
    }

    @Override
    public boolean isNewRow() {
        return true;
    }

    @Override
    protected String[] getHandelKeys() {
        return new String[]{"dft"};
    }
}
