package com.fhs.pagex.tag.grid;

import com.fhs.core.config.EConfig;
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
public class WordBookGridTag extends  SelectGridTag {

    static
    {
        GridTagFactory.regTag("book", WordBookGridTag.class);
    }

    @Override
    public String getHtmlForToolsBar() {
        tagSett.put("valueField","wordbookCode");
        tagSett.put("textField","wordbookDesc");
        tagSett.put("url", EConfig.getPathPropertiesValue("systemServiceUrl") + "/webApi/wordbook/getData?wordbookGroupCode="+
                tagSett.get("code") + "&jsonpCallback=?");
        return super.getHtmlForToolsBar();
    }

    @Override
    protected String[] getHandelKeys() {
        return new String[]{"key"};
    }
}
