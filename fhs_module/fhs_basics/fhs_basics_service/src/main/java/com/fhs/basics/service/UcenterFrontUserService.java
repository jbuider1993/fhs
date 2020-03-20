package com.fhs.basics.service;

import com.fhs.basics.dox.UcenterFrontUserDO;
import com.fhs.basics.vo.UcenterFrontUserVO;
import com.fhs.core.base.service.BaseService;

/**
 * 前端用户表(UcenterFrontUser)表服务接口
 *
 * @author jackwong
 * @since 2019-03-11 14:14:58
 */
public interface UcenterFrontUserService extends BaseService<UcenterFrontUserVO, UcenterFrontUserDO>{

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