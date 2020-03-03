package com.fhs.ucenter.api.service;


import com.fhs.core.result.HttpResult;
import com.fhs.feignConfig.FeignConfiguration;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

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
     * 根据用户id获取角色
     * @param userId 用户id
     * @return HttpResult 角色列表
     */
    @RequestLine("GET /api/sysRole/getRoleListPermissions?userId={userId}")
    HttpResult<String> getRoleListPermissions(@Param("userId") String userId);

    /**
     * 根据id查询角色
     * @param id 角色id
     * @return HttpResult 角色对象
     */
    @RequestLine("GET /api/sysRole/getRoleById?id={id}")
    HttpResult<String> getRoleById(@Param("id") String id);
}
