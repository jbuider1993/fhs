package com.fhs.pagex.tag.grid;

import org.springframework.stereotype.Component;

/**
 * 下拉标签 easyui combobox
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
public class SelectGridTag extends  BaseEasyuiComboGridTag {

    static
    {
        GridTagFactory.regTag("select",SelectGridTag.class);
    }

    @Override
    public String getEasyuiClass() {
        return "combobox";
    }


    @Override
    protected String[] getHandelKeys() {
        return new String[0];
    }
}
