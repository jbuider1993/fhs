package com.fhs.base.api.ucenter.rpc;


import com.fhs.base.api.config.FeignConfiguration;
import com.fhs.core.base.pojo.pager.Pager;
import com.fhs.core.result.HttpResult;
import com.fhs.basics.form.SysUserForm;
import com.fhs.basics.vo.SysUserVO;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestBody;
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

@FeignClient(value = "system", configuration= FeignConfiguration.class,primary = false)
public interface FeignSysUserApiService {

    /**
     * 根据用户登录名获取用户信息
     * @param userLoginName 用户登录名
     * @return HttpResult 包含用户信息
     */
    @RequestLine("GET /api/com.fhs.base.api.basics.rpc.FeignSysUserApiService/getSysUserByLoginName")
    HttpResult<SysUserVO> getSysUserByName(@Param("userLoginName") String userLoginName);

    /**
     * 根据用户登录名获取用户权限列表
     * @param userLoginName 用户登录名
     * @return 用户权限列表
     */
    @RequestLine("GET /api/com.fhs.base.api.basics.rpc.FeignSysUserApiService/selectMenuByUname")
    HttpResult<List<String>> selectMenuByUname(@Param("userLoginName") String userLoginName);

    /**
     * @desc 获取后端用户信息(带分页)
     * @param sysUserForm 后端用户的form对象
     * @return 处理结果
     */
    @RequestLine("POST /api/com.fhs.base.api.basics.rpc.FeignSysUserApiService/getSysUserList")
    HttpResult<Pager<SysUserVO>> getSysUserList(@RequestBody SysUserForm sysUserForm);

    /**
     * 根据用户ID获取用户权限URL
     * @param userId 用户ID
     * @return 用户权限URL列表
     */
    @RequestLine("GET /api/com.fhs.base.api.basics.rpc.FeignSysUserApiService/getPermissionUrlByUserId")
    HttpResult<List<String>> getPermissionUrlByUserIdFeign(@Param("userId") String userId);

    /**
     * 获取用户的数据权限
     * @param userId  用户id
     * @return 数据权限配置
     */
    @RequestLine("GET /api/com.fhs.base.api.basics.rpc.FeignSysUserApiService/getDataUserPermisstion")
    HttpResult<Map<String,String>> getDataUserPermisstion(@Param("userId") String userId);


    /**
     * @desc 根据userId获取用户信息
     * @param sysUserForm 后端用户form
     * @return 后端用户信息
     */
    @RequestLine("GET /api/com.fhs.base.api.basics.rpc.FeignSysUserApiService/getSysUserByUserId")
    HttpResult<SysUserVO> getSysUserByUserId(SysUserForm sysUserForm);

    /**
     * 根据组织id查询用户
     * @param organizationId 组织id
     * @return 用户列表
     */
    @RequestLine("GET /api/com.fhs.base.api.basics.rpc.FeignSysUserApiService/getSysUserByOrganizationId")
    HttpResult<List<SysUserVO>> getSysUserByOrganizationId(@Param("organizationId") String organizationId);
}
