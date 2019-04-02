package com.fhs.pagex.tag.form;

import org.springframework.stereotype.Component;

/**
 *  日期选择
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
public class DateFormTag extends  EmptyFormTag{

    static{
        FormTagFactory.regTag("date", DateFormTag.class);
    }

    @Override
    public String getContentHtml() {
        //支持args formart 和默认3种，默认是yyyy-MM-dd格式
        if(super.tagSett.containsKey("args"))
        {
            super.tagSett.put("onfocus","WdatePicker(" + super.tagSett.get("args") + ")");
        }
        else if(super.tagSett.containsKey("formart"))
        {
            super.tagSett.put("onfocus","WdatePicker({dateFmt:\"" + super.tagSett.get("formart") + "\"})");
        }
        else
        {
            super.tagSett.put("onfocus","WdatePicker()");
        }
        StringBuilder resultHtmlBuilder = new StringBuilder();
        resultHtmlBuilder.append(getTitleHtml());
        resultHtmlBuilder.append(" <input type='text' readonly autocomplete='off' " + super.formartDataType());
        resultHtmlBuilder.append(formartClass("Wdate"));
        resultHtmlBuilder.append(formartIdNameHtml());
        resultHtmlBuilder.append(getOtherAttrValHtml());
        resultHtmlBuilder.append(formartPlaceholderHtml() + " />");
        resultHtmlBuilder.append(formartRequiredHtml());
        resultHtmlBuilder.append("</div>");
        return resultHtmlBuilder.toString();
    }

    @Override
    public boolean isNewRow() {
        // 这个组件是否要强制新启一行，需要强制的有 bigInput textare 上传 百度编辑器  省市区插件 地图插件 经纬度插件
        return false;
    }

    @Override
    protected String[] getHandelKeys() {
        // 哪些参数是你这个控件特有的，我这个input没啥特有的控件，所以返回了空数组
        return new String[]{"args","formart"};
    }
}
