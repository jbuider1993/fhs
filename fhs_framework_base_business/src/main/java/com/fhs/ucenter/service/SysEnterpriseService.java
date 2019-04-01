package com.fhs.ucenter.service;

import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.bean.SysEnterprise;
import com.fhs.core.base.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 企业service
 * @author  zhangqiang
 * @version [版本号, 2018-08-13]
 * @versio 1.0 陕西小伙伴网络科技有限公司  Copyright (c) 2018 All Rights Reserved.
 */
public interface SysEnterpriseService extends BaseService<SysEnterprise> {

    /**
     * 企业信息
     */
    String SYS_ENTERPRISE_INFO = "sysEnterpriseInfo";

    /**
     * 企业名称
     */
    String SYS_ENTERPRISE_NAME = "sysEnterpriseInfo:enterpriseName";

    /**
     * @desc 企业新增修改 企业名称同名校验
     * @param sysEnterprise 企业对象
     * @return
     */
    int findSysEntByIdAndName(SysEnterprise sysEnterprise);

    /**
     * @desc 刷新所有企业缓存
     * @return httpResult
     */
    HttpResult<Map> refreshRedisCache();

    /**
     * @desc 搜索获取企业
     * @return 企业列表信息
     */
    List<SysEnterprise> getSysEnterpriseList(SysEnterprise sysEnterprise);

    /**
     * @desc 修改企业信息
     * @param sysEnterprise 企业对象
     * @return 受影响行数
     */
    Integer updateSysEnterprise(SysEnterprise sysEnterprise);
}
