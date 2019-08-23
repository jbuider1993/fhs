package com.fhs.pagex.tag.form;

import com.fhs.common.utils.Logger;
import com.fhs.pagex.common.BeetlUtil;
import org.springframework.stereotype.Component;


/**
 * 下拉列表标签 easyui combogrid
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
public class SelectGridFormTag extends  InputFormTag {

    private static final Logger LOG = Logger.getLogger(SelectGridFormTag.class);
    static
    {
        FormTagFactory.regTag("selectGrid",SelectGridFormTag.class);
    }

    @Override
    public String getContentHtml() {
        // 为了防止大家copy不知道怎么写，这里在说明一下，给form中 写的代码在这里 如果 isNewRow() 返回true的话那么 <div class="fitem">以及他的结束都要自己在这个方法中写，
        // 如果返回isNewRow() 返回false的话不需要管<div class="fitem">
        StringBuilder resultHtmlBuilder = new StringBuilder();
        resultHtmlBuilder.append(getTitleHtml());
        resultHtmlBuilder.append(" <input type='text' autocomplete='off' " + super.formartDataType());
        resultHtmlBuilder.append(formartClass("easyui-validatebox easyui-combogrid"));
        resultHtmlBuilder.append(formartIdNameHtml());
        resultHtmlBuilder.append(formartPlaceholderHtml() + " />");
        resultHtmlBuilder.append(formartRequiredHtml());
        resultHtmlBuilder.append("</div>");
        return resultHtmlBuilder.toString();
    }
    @Override

    public String readyJs() {
        try {
            if(tagSett.get("url")!=null&&!tagSett.get("url").toString().contains("?"))
            {
                tagSett.put("url",tagSett.get("url") + "?1=1");
            }
            request.setAttribute("tagSett",super.tagSett);
            return BeetlUtil.renderBeelt("/pagex/tags/select_grid_tag.html",super.getBeetlParamMap());
        } catch (Exception e) {
            LOG.error("渲染select_grid出错:",e);
        }
        return "";
    }



    @Override
    protected String[] getHandelKeys() {
        return new String[0];
    }
}
