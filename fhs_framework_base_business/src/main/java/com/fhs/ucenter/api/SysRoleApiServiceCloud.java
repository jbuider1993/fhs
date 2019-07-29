package com.fhs.ucenter.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.JsonUtils;
import com.fhs.core.db.DataSource;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.api.service.FeignSysRoleApiService;
import com.fhs.ucenter.bean.SysRole;
import com.fhs.ucenter.bean.SysUser;
import com.fhs.ucenter.service.SysRoleService;
import com.fhs.ucenter.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Filename: SysRoleApiServiceCloud.java
 * @Description: 角色api接口
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
@RestController
@RequestMapping("api/sysRole")
@DataSource("pagex/ucenter")
public class SysRoleApiServiceCloud implements FeignSysRoleApiService {

    /**
     * 后台用户服务
     */
    @Autowired
    private SysUserService userService;


    /**
     * 角色服务
     */
    @Autowired
    private SysRoleService roleService;



    /**
     * 根据用户ID获取角色数据权限
     * @param userId 用户ID
     * @return HttpResult 角色数据权限
     */
    @Override
    @GetMapping("/getRoleListPermissions")
    public HttpResult<String> getRoleListPermissions(@RequestParam("userId") String userId) {
        if(CheckUtils.isNullOrEmpty(userId))
        {
            return HttpResult.error(null, "用户ID不可为空");
        }
        List<SysRole> roles = roleService.findRolesByUserId(userId);
        SysUser sysUser = userService.selectById(userId);
        List<Map<String,Object>> mapList=new ArrayList<>();
        for (SysRole sysRole:roles) {
            Map<String,Object> map=new HashMap<>();
            map.put("roleId",sysRole.getRoleId());
            map.put("userId",sysUser.getUserId());
            map.put("fullname",sysUser.getUserName());
            map.put("roleName",sysRole.getRoleName());
            map.put("alias",sysRole.getRoleId());
            mapList.add(map);
        }
        return HttpResult.success(JsonUtils.list2json(mapList));
    }

    @Override
    @GetMapping("/getRoleById")
    public HttpResult<String> getRoleById(@RequestParam("id") String id) {
        if(CheckUtils.isNullOrEmpty(id))
        {
            return HttpResult.error(null, "角色ID不可为空");
        }
        SysRole sysRole = roleService.findBeanById(id);
        Map<String,Object> map=new HashMap<>();
        map.put("roleId",sysRole.getRoleId());
        map.put("roleName",sysRole.getRoleName());
        map.put("isDisable",sysRole.getRoleName());
        map.put("alias",sysRole.getRoleId());
        return HttpResult.success(JsonUtils.object2json(map));
    }
}
