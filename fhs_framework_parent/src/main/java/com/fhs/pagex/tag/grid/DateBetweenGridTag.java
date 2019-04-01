package com.fhs.pagex.tag.grid;

import com.fhs.common.utils.ConverterUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  起止日期 列表页标签
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
public class DateBetweenGridTag extends  BaseGridTag{

    static
    {
        GridTagFactory.regTag("dateBT", DateBetweenGridTag.class);
    }

    @Override
    public String getHtmlForToolsBar() {
        String formart = "yyyy-MM-dd HH:mm:ss";
        if(tagSett.containsKey("formart"))
        {
            formart = ConverterUtils.toString(tagSett.get("formart"));
        }
        String startOnclick = "WdatePicker({dateFmt:'"+formart+"' ,maxDate:'#F{$dp.$D(\\'"  + super.tagSett.get("name")+ "_end_F"  +"\\')}'})";
        String endOnclick =  "WdatePicker({dateFmt:'"+formart+"' ,minDate:'#F{$dp.$D(\\'" + super.tagSett.get("name")+ "_start_F" +  "\\')}'})";
        return "<input type='text' class='Wdate' onclick=\"" + startOnclick
                + "\" " + super.getOtherAttrValHtml() + "id='" + super.tagSett.get("name")+"_start_F"  + "'  placeholder='请选择最小" +  super.tagSett.get("title")
                + "' />-<input  class='Wdate' onclick=\"" +  endOnclick + "\" type='text'" + super.getOtherAttrValHtml() + "id='" + super.tagSett.get("name")+"_end_F"
                + "' placeholder='请选择最大" +  super.tagSett.get("title")
                + "'  />";
    }

    @Override
    public void initReloadParam(List<Map<String, String>> filterParams, List<Map<String, String>> filterParamsForBetween) {
        Map<String,String> reloadParam = new HashMap<>();
        reloadParam.put("name",ConverterUtils.toString(super.tagSett.get("name")) + "Min");
        reloadParam.put("val","$('#" + super.tagSett.get("name") + "_start_F').val()");
        filterParamsForBetween.add(reloadParam);
        filterParams.add(reloadParam);
        reloadParam = new HashMap<>();
        reloadParam.put("name",ConverterUtils.toString(super.tagSett.get("name")) + "Max");
        reloadParam.put("val","$('#" + super.tagSett.get("name") + "_end_F').val()");
        filterParamsForBetween.add(reloadParam);
        filterParams.add(reloadParam);
    }

    @Override
    protected String[] getHandelKeys() {
        return new String[]{"formart"};
    }
}
