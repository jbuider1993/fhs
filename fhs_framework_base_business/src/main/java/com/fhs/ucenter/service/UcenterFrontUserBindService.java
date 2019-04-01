package com.fhs.ucenter.service;

import com.fhs.core.base.service.BaseService;
import com.fhs.ucenter.bean.UcenterFrontUserBind;
/**
 * (UcenterFrontUserBind)表服务接口
 *
 * @author jackwong
 * @since 2019-03-11 14:37:18
 */
public interface UcenterFrontUserBindService extends BaseService<UcenterFrontUserBind>{

    /**
     * 公众号
     */
    int OPENID_TYPE_WXMP = 0;

    /**
     *  支付宝
     */
    int OPENID_TYPE_ALIPAY = 1;
    

}