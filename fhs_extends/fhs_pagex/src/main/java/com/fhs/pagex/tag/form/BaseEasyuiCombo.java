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
public abstract  class BaseEasyuiCombo extends EmptyFormTag {

    /**
     * 获取子类的class
     * @return
     */
    public abstract  String getEasyuiComboType();

    protected String name;

    @Override
    public String getContentHtml() {
        if(!tagSett.containsKey("onLoadSuccess"))
        {
            tagSett.put("onLoadSuccess",formartDefault());
        }
        StringBuilder resultHtmlBuilder = new StringBuilder();
        boolean multiple = ConverterUtils.toBoolean(tagSett.get("multiple"));
        if(multiple){
            if(!isOne2XModel)
            {
                resultHtmlBuilder.append("<div class='fitem'>");
                resultHtmlBuilder.append(" <input type='hidden' " );
                resultHtmlBuilder.append(formartIdNameHtml());;
                resultHtmlBuilder.append("/>");
            }
            if(name == null)
            {
                name = ConverterUtils.toString(super.tagSett.get("name"));
                super.tagSett.put("name",super.tagSett.get("name")+"_select");
            }
            super.tagSett.put("class","big_text");
        }
        resultHtmlBuilder.append(getTitleHtml());
        resultHtmlBuilder.append(" <input type='text' autocomplete='off' " + super.formartDataType());
        resultHtmlBuilder.append(formartClass("easyui-" +  getEasyuiComboType()));
        resultHtmlBuilder.append(formartIdNameHtml());
        resultHtmlBuilder.append(getOtherAttrValHtml());
        resultHtmlBuilder.append(formartEasyuiDataOptions());
        resultHtmlBuilder.append(formartPlaceholderHtml() + " />");
        resultHtmlBuilder.append(formartRequiredHtml());
        resultHtmlBuilder.append(super.getEndDiv());
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
    public String overallJs() {
        if(!ConverterUtils.toBoolean(tagSett.get("multiple")))
        {
            return "";
        }
        //这块js给你当做全局变量，比如你储存一个全局变量，就可以把代码写到这里
        return "function  setSelectHiddenValue_" + name + "(){" +
                "$('#" + name + "').val($('#" + name + "_select')."+getEasyuiComboType()+"('getValues')+'');" +
                "return true;}";
    }

    @Override
    public String loadSuccessJs() {
        // 如果不是多选 return "";
        if (ConverterUtils.toBoolean(tagSett.get("multiple"))) {
            return "if(info." + name + " && info." + name + " !=''){" +
                    " $('#" + name + "_select')."+getEasyuiComboType()+"('setValues',info." + name + ".split(','))}";
        }
        return "";
    }

    @Override
    public String saveJs() {
        //当点击保存的时候需要执行的js，比如图片的value获取，图片的必填校验，UE的必填校验都写到这里
        //写必填校验的时候记得用EalertE 记得验证不通过 使用return来组织程序继续往下面执行。
        if (ConverterUtils.toBoolean(tagSett.get("multiple"))) {
            return "setSelectHiddenValue_" + name + "()";
        }
        return "";
    }

    public String formartDefault(){
        StringBuilder sb  = new StringBuilder("function(_data){");
        if(tagSett.containsKey("dftIndex"))
        {
            sb.append( "        var _vf = $(this)."+getEasyuiComboType()+"('options').valueField;\n" +
                    " $(this)."+getEasyuiComboType()+"('setValue', eval('_data["+tagSett.get("dftIndex")+"].' + _vf));");

        }
        if(tagSett.containsKey("dftVal"))
        {
            sb.append("$(this)."+getEasyuiComboType()+"('setValue','" + tagSett.get("dftVal") +"');");

        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    protected String[] getHandelKeys() {
        return new String[]{"dftIndex","dftVal"};
    }




    public String setValueJs() {
        //多选
        if(ConverterUtils.toBoolean(tagSett.get("multiple")))
        {
            return getEasyuiComboType()+"('setValues',";
        }
        return getEasyuiComboType()+"('setValue',";
    }


    public String getValueJs() {
        if(ConverterUtils.toBoolean(tagSett.get("multiple")))
        {
            return getEasyuiComboType()+"('getValues') + ''";
        }
        return getEasyuiComboType()+"('getValue')";
    }

}
