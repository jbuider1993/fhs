package com.fhs.ucenter.api.service;


import com.fhs.core.page.Pager;
import com.fhs.core.result.HttpResult;
import com.fhs.feignConfig.FeignConfiguration;
import com.fhs.ucenter.api.form.SysUserForm;
import com.fhs.ucenter.api.vo.SysUserVo;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 后管用户api服务
 *
 * @Filename: FeignSysUserApiService.java
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
public interface FeignSysUserApiService {

    /**
     * 根据用户登录名获取用户信息
     * @param userLoginName 用户登录名
     * @return HttpResult 包含用户信息
     */
    @RequestLine("GET /api/sysUser/getSysUserByLoginName?userLoginName={userLoginName}")
    HttpResult<SysUserVo> getSysUserByName(@Param("userLoginName") String userLoginName);

    /**
     * 根据用户登录名获取用户权限列表
     * @param userLoginName 用户登录名
     * @return 用户权限列表
     */
    @RequestLine("GET /api/sysUser/selectMenuByUname?userLoginName={userLoginName}")
    HttpResult<List<String>> selectMenuByUname(@Param("userLoginName") String userLoginName);

    /**
     * @desc 获取后端用户信息(带分页)
     * @param sysUserForm 后端用户的form对象
     * @return 处理结果
     */
    @RequestLine("POST /api/sysUser/getSysUserList")
    HttpResult<Pager<SysUserVo>> getSysUserList(@RequestBody SysUserForm sysUserForm);

    /**
     * 根据用户ID获取用户权限URL
     * @param userId 用户ID
     * @return 用户权限URL列表
     */
    @RequestLine("GET /api/sysUser/getPermissionUrlByUserId?userId={userId}")
    HttpResult<List<String>> getPermissionUrlByUserId(@Param("userId") String userId);

    /**
     * 获取用户的数据权限
     * @param userId  用户id
     * @return 数据权限配置
     */
    @RequestLine("GET /api/sysUser/getDataUserPermisstion?userId={userId}")
    HttpResult<Map<String,String>> getDataUserPermisstion(@Param("userId")String userId);


    /**
     * @desc 根据userId获取用户信息
     * @param sysUserForm 后端用户form
     * @return 后端用户信息
     */
    @RequestLine("GET /api/sysUser/getSysUserByUserId")
    HttpResult<SysUserVo> getSysUserByUserId(SysUserForm sysUserForm);

    /**
     * 根据组织id查询用户
     * @param organizationId 组织id
     * @return 用户列表
     */
    @RequestLine("GET /api/sysUser/getSysUserByOrganizationId?organizationId={organizationId}")
    HttpResult<List<SysUserVo>> getSysUserByOrganizationId(@Param("organizationId")String organizationId);
}
