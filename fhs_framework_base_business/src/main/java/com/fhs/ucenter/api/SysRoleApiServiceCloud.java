package com.fhs.ucenter.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.JsonUtils;
import com.fhs.core.db.DataSource;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.api.service.FeignSysRoleApiService;
import com.fhs.ucenter.bean.SysRole;
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
    @GetMapping("/getRoleDataPermissions")
    public HttpResult<String> getRoleDataPermissions(@RequestParam("userId") String userId) {
        if(CheckUtils.isNullOrEmpty(userId))
        {
            return HttpResult.error(null, "用户ID不可为空");
        }
        Map<String, Set<String>> resultMap = new HashMap<>();
        List<SysRole> roles = roleService.findRolesByUserId(userId);
        if(roles != null && roles.size() > 0)
        {
            Set<String> resourcetypesSet = new HashSet<>();
            Set<String> deptsSet = new HashSet<>();
            Set<String> projectsSet = new HashSet<>();
            // 遍历，组合各角色数据权限
            for (SysRole sysRole : roles)
            {
                String dataPermissions = sysRole.getDataPermissions();
                if(CheckUtils.isNotEmpty(dataPermissions))
                {
                    JSONObject jsonObject = JSON.parseObject(dataPermissions);
                    String resourcetypes = jsonObject.getString(SysRoleService.RESOURCETYPES);
                    if(CheckUtils.isNotEmpty(resourcetypes))
                    {
                        List<String> list = JSON.parseArray(resourcetypes, String.class);
                        for(String str : list)
                        {
                            resourcetypesSet.add(str);
                        }
                    }
                    String depts = jsonObject.getString(SysRoleService.DEPTS);
                    if(CheckUtils.isNotEmpty(depts))
                    {
                        List<String> list = JSON.parseArray(depts, String.class);
                        for(String str : list)
                        {
                            deptsSet.add(str);
                        }
                    }
                    String projects = jsonObject.getString(SysRoleService.PROJECTS);
                    if(CheckUtils.isNotEmpty(projects))
                    {
                        List<String> list = JSON.parseArray(projects, String.class);
                        for(String str : list)
                        {
                            projectsSet.add(str);
                        }
                    }
                }

            }
            resultMap.put(SysRoleService.RESOURCETYPES, resourcetypesSet);
            resultMap.put(SysRoleService.DEPTS, deptsSet);
            resultMap.put(SysRoleService.PROJECTS, projectsSet);
        }
        return HttpResult.success(JsonUtils.map2json(resultMap));
    }

}
