package com.fhs.pagex.tag.grid;

import com.fhs.common.utils.ConverterUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  日期 列表页标签
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.tag.grid
 * @ClassName: InputGridTag
 * @Author: JackWang
 * @CreateDate: 2018/12/3 0003 17:58
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/3 0003 17:58
 * @Version: 1.0
 */
@Component
public class DateGridTag extends  BaseGridTag{

    static
    {
        GridTagFactory.regTag("date", DateGridTag.class);
    }



    @Override
    public String getHtmlForToolsBar() {
        //支持args formart 和默认3种，默认是yyyy-MM-dd格式
        String formart = "yyyy-MM-dd";
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
        return "<input type='text' class='Wdate'"  + super.getOtherAttrValHtml() + formartIdHtml()
                + formartPlaceholderHtml() + " />";
    }

    @Override
    public void initReloadParam(List<Map<String, String>> filterParams, List<Map<String, String>> filterParamsForBetween) {
        Map<String,String> reloadParam = new HashMap<>();
        reloadParam.put("name", ConverterUtils.toString(super.tagSett.get("name")));
        reloadParam.put("val","$('#" + super.tagSett.get("name") + "F').val()");
        filterParams.add(reloadParam);
    }

    @Override
    protected String[] getHandelKeys() {
        return new String[]{"args","formart"};
    }
}
