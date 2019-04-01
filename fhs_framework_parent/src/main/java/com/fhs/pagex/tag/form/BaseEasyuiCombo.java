package com.fhs.pagex.tag.form;

import com.fhs.common.utils.ConverterUtils;

/**
 *  easyui下拉的基类
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.tag.form
 * @ClassName: BaseEasyuiCombo
 * @Author: JackWang
 * @CreateDate: 2018/12/4 0004 19:52
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/4 0004 19:52
 * @Version: 1.0
 */
public abstract  class BaseEasyuiCombo extends EmptyFormTag{

    /**
     * 获取子类的class
     * @return
     */
    public abstract  String getEasyuiClass();

    protected String name;

    @Override
    public String getContentHtml() {
        StringBuilder resultHtmlBuilder = new StringBuilder();
        boolean multiple = ConverterUtils.toBoolean(tagSett.get("multiple"));
        if(multiple){
            resultHtmlBuilder.append("<div class='fitem'>");
            resultHtmlBuilder.append(" <input type='hidden' " );
            resultHtmlBuilder.append(formartIdNameHtml());;
            resultHtmlBuilder.append("/>");
            if(name == null)
            {
                name = ConverterUtils.toString(super.tagSett.get("name"));
                super.tagSett.put("name",super.tagSett.get("name")+"_select");
            }
            super.tagSett.put("class","big_text");
        }
        resultHtmlBuilder.append(getTitleHtml());
        resultHtmlBuilder.append(" <input type='text' autocomplete='off' " + super.formartDataType());
        resultHtmlBuilder.append(formartClass(getEasyuiClass()));
        resultHtmlBuilder.append(formartIdNameHtml());
        resultHtmlBuilder.append(getOtherAttrValHtml());
        resultHtmlBuilder.append(formartEasyuiDataOptions());
        resultHtmlBuilder.append(formartPlaceholderHtml() + " />");
        resultHtmlBuilder.append(formartRequiredHtml());
        resultHtmlBuilder.append("</div>");
        if(multiple){
            resultHtmlBuilder.append("</div>");
        }
        return resultHtmlBuilder.toString();
    }



    @Override
    public boolean isNewRow() {
        if(ConverterUtils.toBoolean(tagSett.get("multiple"))){
            return true;
        }
        return false;
    }

    @Override
    protected String[] getHandelKeys() {
        return new String[0];
    }
}
