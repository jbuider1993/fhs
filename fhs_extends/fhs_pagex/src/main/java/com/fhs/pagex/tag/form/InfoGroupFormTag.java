package com.fhs.pagex.tag.form;

import org.springframework.stereotype.Component;

/**
 * 分组信息
 */
@Component
public class InfoGroupFormTag extends EmptyFormTag {
    static{
        FormTagFactory.regTag("group", InfoGroupFormTag.class);
    }
    @Override
    public boolean isNewRow() {
        return true;
    }

    @Override
    public String getContentHtml() {
        return "<div  " + super.formartClass("infoGroup") + ">" + super.tagSett.get("title") + "</div>";
    }

    @Override
    protected String[] getHandelKeys() {
        return new String[0];
    }
}
