package com.fhs.basics.api.rpc;

import com.fhs.core.feign.config.FeignConfiguration;
import com.fhs.core.result.HttpResult;
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
@FeignClient(value = "basics", configuration= FeignConfiguration.class,primary = false)
public interface FeignSysRoleApiService {



    /**
     * 根据用户id获取角色
     * @param userId 用户id
     * @return HttpResult 角色列表
     */
    @RequestLine("GET /api/FeignSysRoleApiService/getRoleListPermissions?userId={userId}")
    HttpResult<String> getRoleListPermissions(@Param("userId") String userId);

    /**
     * 根据id查询角色
     * @param id 角色id
     * @return HttpResult 角色对象
     */
    @RequestLine("GET /api/FeignSysRoleApiService/getRoleById?id={id}")
    HttpResult<String> getRoleById(@Param("id") String id);
}
