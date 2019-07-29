package com.fhs.ucenter.api;

import com.beust.jcommander.ParameterException;
import com.fhs.core.db.DataSource;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.api.service.UcenterMsOrganizationApiService;
import com.fhs.ucenter.bean.SysOrganization;
import com.fhs.ucenter.bean.SysUser;
import com.fhs.ucenter.service.SysOrganizationService;
import com.fhs.ucenter.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 机构管理rest接口实现
 */
@RestController
@RequestMapping("api/uMOrganization")
@DataSource("pagex/ucenter")
public class UcenterMsOrganizationServiceCloud implements UcenterMsOrganizationApiService {

    /**
     * 组织结构服务
     */
    @Autowired
    private SysOrganizationService sysOrganizationService;

    /**
     * 后台用户服务
     */
    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取用户主要结构
     * @param userId 用户id
     * @return 结构组织map
     */
    @GetMapping("/getMainGroup")
    @Override
    public HttpResult<Map<String,Object>> getMainGroup(@RequestParam("userId") String userId) {
        SysUser sysUser=sysUserService.findSysUserById(userId);
        Map<String,Object> result=new HashMap<>();
        if(sysUser!=null){
            SysOrganization sysOrganization= sysOrganizationService.findBeanById(sysUser.getOrganizationId());
            if(sysOrganization!=null){
                result.put("name",sysOrganization.getName());
                result.put("parentId",sysOrganization.getParentId());
                result.put("code",sysOrganization.getId());
                //type此系统没有
                result.put("id",sysOrganization.getId());
                result.put("createTime",sysOrganization.getCreateTime());
                result.put("createBy",sysOrganization.getCreateUser());
                result.put("updateTime",sysOrganization.getUpdateTime());
                result.put("updateBy",sysOrganization.getUpdateUser());
                return HttpResult.success(result);
            }
            throw new ParameterException("此用户的组织结构不存在:" + userId);
        }

        throw new ParameterException("此用户不存在:" + userId);

    }

    @GetMapping("/getGroupById")
    @Override
    public HttpResult<Map<String,Object>> getGroupById(@RequestParam("id") String id) {
        Map<String,Object> result=new HashMap<>();
        SysOrganization sysOrganization= sysOrganizationService.findBeanById(id);
        if(sysOrganization!=null){
            result.put("name",sysOrganization.getName());
            result.put("parentId",sysOrganization.getParentId());
            result.put("code",sysOrganization.getId());
            //type此系统没有
            result.put("id",sysOrganization.getId());
            result.put("createTime",sysOrganization.getCreateTime());
            result.put("createBy",sysOrganization.getCreateUser());
            result.put("updateTime",sysOrganization.getUpdateTime());
            result.put("updateBy",sysOrganization.getUpdateUser());
            return HttpResult.success(result);
        }
        throw new ParameterException("此组织结构不存在:" + id);


    }

}
