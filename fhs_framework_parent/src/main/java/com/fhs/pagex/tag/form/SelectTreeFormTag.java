package com.fhs.pagex.tag.form;

import org.springframework.stereotype.Component;

/**
 * 下拉tree标签 easyui combotree
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
public class SelectTreeFormTag extends  BaseEasyuiCombo {
    static
    {
        FormTagFactory.regTag("selectTree",SelectTreeFormTag.class);
    }

    @Override
    public String getEasyuiComboType() {
        return "combotree";
    }


}
