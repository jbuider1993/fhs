package com.fhs.pagex.tag.grid;

import com.fhs.common.utils.ConverterUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  input 列表页标签
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
public class InputGridTag extends  BaseGridTag{

    static
    {
        GridTagFactory.regTag("input", InputGridTag.class);
    }

    @Override
    public String getHtmlForToolsBar() {
        return "<input type='text'" + super.getOtherAttrValHtml() + formartIdHtml() + formartPlaceholderHtml() + " />";
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
        return new String[]{};
    }
}
