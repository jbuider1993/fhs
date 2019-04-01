package com.fhs.ucenter.api.service;


import com.fhs.core.result.HttpResult;
import com.fhs.feignConfig.FeignConfiguration;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 角色api服务
 *
 * @Filename: FeignSysRoleApiService.java
 * @Description:
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
@FeignClient(value = "system", configuration=FeignConfiguration.class)
public interface FeignSysRoleApiService {

    /**
     * 根据用户ID获取角色数据权限
     * @param userId 用户ID
     * @return HttpResult 角色数据权限
     */
    @RequestLine("GET /api/sysRole/getRoleDataPermissions?userId={userId}")
    HttpResult<String> getRoleDataPermissions(@Param("userId") String userId);
}
