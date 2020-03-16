package com.fhs.pagex.tag.form;

import org.springframework.stereotype.Component;

/**
 * 下拉标签 easyui combobox
 *
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
public class SelectFormTag extends BaseEasyuiCombo implements IOne2XTag {
    static {
        FormTagFactory.regTag("select", SelectFormTag.class);
        FormTagFactory.regOne2XTag("select", SelectFormTag.class);
    }

    @Override
    public String getEasyuiComboType() {
        return "combobox";
    }


}
