package com.fhs.basics.service;

import com.fhs.basics.dox.UcenterFrontUserBindDO;
import com.fhs.basics.vo.UcenterFrontUserBindVO;
import com.fhs.core.base.service.BaseService;
/**
 *  前端用户openid绑定
 *
 * @author jackwong
 * @since 2019-03-11 14:37:18
 */
public interface UcenterFrontUserBindService extends BaseService<UcenterFrontUserBindVO, UcenterFrontUserBindDO>{

    /**
     * 公众号
     */
    int OPENID_TYPE_WXMP = 0;

    /**
     *  支付宝
     */
    int OPENID_TYPE_ALIPAY = 1;
    

}