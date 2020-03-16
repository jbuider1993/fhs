package com.fhs.pagex.tag.form;

import com.fhs.common.utils.Logger;
import com.fhs.pagex.common.BeetlUtil;
import org.springframework.stereotype.Component;

/**
 *  单图上传
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
public class UploadFormTag extends BaseFormTag {
    private static final Logger LOG = Logger.getLogger(UploadFormTag.class);
    static
    {
        FormTagFactory.regTag("up", UploadFormTag.class);
    }
    @Override
    public String getContentHtml() {
        try {
            return BeetlUtil.renderBeelt("/pagex/tags/upload_tag.html",super.getBeetlParamMap());
        } catch (Exception e) {
            LOG.error(this,e);
        }
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
    public String loadSuccessJs() {
        // 回显图片并且将值赋值到inputvalue
        return "uploadHeaderLoadSuccess_" + super.tagSett.get("name") + "(info)";
    }

    @Override
    public String saveJs() {
        return  "uploadHeaderOnsave_" + super.tagSett.get("name") + "()";
    }

    @Override
    public String overallJs() {
        return "";
    }

    @Override
    protected String[] getHandelKeys() {
        return new String[]{"desc","maxSize"};
    }
}
