package com.fhs.ucenter.service;

import com.fhs.core.base.service.BaseService;
import com.fhs.ucenter.bean.UcenterFrontUser;

/**
 * 前端用户表(UcenterFrontUser)表服务接口
 *
 * @author jackwong
 * @since 2019-03-11 14:14:58
 */
public interface UcenterFrontUserService extends BaseService<UcenterFrontUser>{

    /**
     * 未知
     */
    String SEX_UNKNOWN = "0";

    /**
     * 男
     */
    String SEX_BOY = "1";

    /**
     * 女
     */
    String SEX_GIRL = "2";

}