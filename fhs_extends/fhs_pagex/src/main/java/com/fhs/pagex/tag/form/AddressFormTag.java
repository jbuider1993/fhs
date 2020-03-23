package com.fhs.pagex.tag.form;

import com.fhs.logger.Logger;
import com.fhs.pagex.common.BeetlUtil;
import org.springframework.stereotype.Component;

/**
 *  省市区 + 百度地图 + 经纬度
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
public class AddressFormTag extends BaseFormTag {
    private static final Logger LOG = Logger.getLogger(AddressFormTag.class);
    static
    {
        FormTagFactory.regTag("address", AddressFormTag.class);
    }
    @Override
    public String getContentHtml() {
        try {
           return BeetlUtil.renderBeelt("/pagex/tags/address_tag.html",super.getBeetlParamMap());
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
        // 回填地址以及省市区
        return "addressFormLoadSuccess(info);";
    }

    @Override
    public String saveJs() {
        return  "";
    }

    @Override
    public String overallJs() {
        return "";
    }

    @Override
    protected String[] getHandelKeys() {
        return new String[0];
    }
}
