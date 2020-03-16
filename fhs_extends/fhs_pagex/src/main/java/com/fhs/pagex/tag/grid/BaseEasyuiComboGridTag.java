package com.fhs.pagex.tag.grid;

import com.fhs.common.utils.ConverterUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.tag.grid
 * @ClassName: BaseEasyuiComboGridTag
 * @Author: JackWang
 * @CreateDate: 2018/12/28 0028 16:35
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/28 0028 16:35
 * @Version: 1.0
 */
public abstract class BaseEasyuiComboGridTag extends BaseGridTag {
    /**
     * 获取子类的class
     * @return
     */
    public abstract  String getEasyuiClass();


    public String getHtmlForToolsBar() {
        // 为了防止大家copy不知道怎么写，这里在说明一下，给form中 写的代码在这里 如果 isNewRow() 返回true的话那么 <div class="fitem">以及他的结束都要自己在这个方法中写，
        // 如果返回isNewRow() 返回false的话不需要管<div class="fitem">
        StringBuilder resultHtmlBuilder = new StringBuilder();
        resultHtmlBuilder.append(" <input type='text' autocomplete='off' ");
        resultHtmlBuilder.append(formartClass("easyui-" + getEasyuiClass()));
        resultHtmlBuilder.append(formartIdHtml());
        resultHtmlBuilder.append(formartEasyuiDataOptions());
        resultHtmlBuilder.append(getOtherAttrValHtml());
        resultHtmlBuilder.append(formartPlaceholderHtml() + " />");
        return resultHtmlBuilder.toString();
    }

    @Override
    public void initReloadParam(List<Map<String, String>> filterParams, List<Map<String, String>> filterParamsForBetween) {
        Map<String,String> reloadParam = new HashMap<>();
        reloadParam.put("name", ConverterUtils.toString(super.tagSett.get("name")));
        String getValMethod = ConverterUtils.toBoolean(this.tagSett.get("multiple")) ? "getValues" : "getValue";
        reloadParam.put("val","$('#" + super.tagSett.get("name") + "F' )." + getEasyuiClass()  + "('" + getValMethod + "')");
        filterParams.add(reloadParam);
    }

}
