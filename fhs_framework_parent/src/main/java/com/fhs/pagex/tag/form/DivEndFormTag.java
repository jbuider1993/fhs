package com.fhs.pagex.tag.form;

import org.springframework.stereotype.Component;

/**
 * div start
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
public class DivEndFormTag extends  InputFormTag {
    static
    {
        FormTagFactory.regTag("divEnd", DivEndFormTag.class);
    }

    @Override
    public String getContentHtml() {
        return "</div>";
    }
    @Override

    public String readyJs() {
        return "";
    }

    @Override
    public boolean isNewRow() {
        return true;
    }

    @Override
    protected String[] getHandelKeys() {
        return new String[0];
    }
}
