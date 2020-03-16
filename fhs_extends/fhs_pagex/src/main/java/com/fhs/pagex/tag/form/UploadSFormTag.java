package com.fhs.pagex.tag.form;

import com.fhs.common.utils.Logger;
import com.fhs.pagex.common.BeetlUtil;
import org.springframework.stereotype.Component;

/**
 *  多 文件/图片 上传
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.tag.form
 * @ClassName: UploadFormTag
 * @Author: JackWang
 * @CreateDate: 2018/12/4 0004 19:58
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/4 0004 19:58
 * @Version: 1.0
 */
@Component
public class UploadSFormTag extends BaseFormTag {
    private static final Logger LOG = Logger.getLogger(UploadSFormTag.class);
    static
    {
        FormTagFactory.regTag("ups", UploadSFormTag.class);
    }
    @Override
    public String getContentHtml() {
        if(!tagSett.containsKey("multi")){
            tagSett.put("multi",true);
        }
        try {
            return BeetlUtil.renderBeelt("/pagex/tags/uploads_tag.html",super.getBeetlParamMap());
        } catch (Exception e) {
            LOG.error(this,e);
        }
        return "";
    }



    @Override
    public String loadSuccessJs() {
        // 回显图片并且将值赋值到inputvalue
        return "uploadsFileLoadSuccess_" + super.tagSett.get("name") + "(info)";
    }

    @Override
    public String saveJs() {
        return  "uploadsFileOnsave_" + super.tagSett.get("name") + "()";
    }

    @Override
    public String overallJs() {
        return "";
    }

    @Override
    public boolean isNewRow() {
        return true;
    }

    @Override
    public String readyJs() {
        return "";
    }

    @Override
    protected String[] getHandelKeys() {
        return new String[]{"desc","maxFileNumer","acceptFileType","multi"};
    }
}
