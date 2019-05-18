package com.fhs.ucenter.action;


import com.fhs.base.action.ModelSuperAction;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.JsonUtils;
import com.fhs.core.log.LogDesc;
import com.fhs.core.page.Pager;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.api.vo.SysUserVo;
import com.fhs.ucenter.bean.SysOrganization;
import com.fhs.ucenter.bean.TreeModel;
import com.fhs.ucenter.service.SysOrganizationService;
import com.fhs.ucenter.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:后台组织机构表
 * @author  qixiaobo
 * @version [版本号, 2018-09-04]
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2018 All Rights Reserved.
 */
@Controller
@RequestMapping("ms/sysOrganization")
public class MsOrganizationAction extends ModelSuperAction<SysOrganization> {

    /**
     * 机构服务
     */
    @Autowired
    private SysOrganizationService sysOrganizationService;


    /**
     * 后台用户服务
     */
    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取机构菜单树结构json字符串对象 菜单管理左侧树形结构
     *
     * @param request
     * @param response
     */
    @RequiresPermissions("sysOrganization:see")
    @RequestMapping("getTreesData")
    public void getTreesData(HttpServletRequest request, HttpServletResponse response)
    {
        Map<String, Object> map = super.getPageTurnNum(request);
        SysUserVo sysUser = super.getSessionuser(request);
        map.put("organizationId", sysUser.getOrganizationId());
        List<TreeModel> treesData = sysOrganizationService.getTreesData(map);
        String jsonTree = JsonUtils.list2json(treesData);
        request.setAttribute("datas", jsonTree);
        super.outJsonp(jsonTree, response,request);
    }

    /**
     * 查询机构
     *
     * @param request
     * @param response
     * @param id
     */
    @RequiresPermissions("sysOrganization:see")
    @RequestMapping("getPageListData")
    @ResponseBody
    public Pager<SysOrganization> getPageListData(HttpServletRequest request, HttpServletResponse response, String id)
    {
        Map<String, Object> map = super.getPageTurnNum(request);
        map.put("parentId", id);
        int count = sysOrganizationService.findCountFromMap(map);
        List<SysOrganization> dataList = sysOrganizationService.findForListFromMap(map);
        return new Pager<SysOrganization>(count , dataList);
    }

    /**
     * 更新
     * @param e bean
     * @param check 检查结果
     * @param request
     * @param response
     * @return
     */
    @Override
    @LogDesc(type = LogDesc.UPDATE, value = "更新")
    public HttpResult update(SysOrganization e, BindingResult check, HttpServletRequest request, HttpServletResponse response) {
        try {
            SysOrganization oldOrg = sysOrganizationService.findBeanById(e.getId());
            // 如果是启用改为禁用
            if(Constant.ENABLED == oldOrg.getIsDisable() && Constant.DISABLE == e.getIsDisable()) {
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("organizationId", e.getId());
                paramMap.put("isDisable", Constant.ENABLED);
                // 查询当前机构下级机构数
                Integer orgCount = sysOrganizationService.findChildCountById(paramMap);
                if (orgCount > Constant.ENABLED) {
                    return HttpResult.error(null, "该机构拥有子机构,不可禁用!");
                }
                // 查询当前机构和下级机构人员
                Integer userCount = sysUserService.findUserByOrgId(paramMap);
                if (userCount > Constant.ENABLED) {
                    return HttpResult.error(null, "该机构下拥有用户,不可禁用!");
                }
            }
            return super.update(e, check, request, response);
        } catch (Exception ex)
        {
            return HttpResult.error();
        }
    }

    /**
     * 删除
     * @param id id
     * @param request
     * @return
     */
    @Override
    @LogDesc(type = LogDesc.DEL, value = "删除")
    public HttpResult del(String id, HttpServletRequest request) {
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("organizationId", id);
            paramMap.put("isDisable", null);
            // 查询当前机构下级机构数
            Integer orgCount = sysOrganizationService.findChildCountById(paramMap);
            if (orgCount > Constant.ENABLED) {
                return HttpResult.error(null, "该机构拥有子机构,不可删除!");
            }
            // 查询当前机构和下级机构人员
            Integer userCount = sysUserService.findUserByOrgId(paramMap);
            if (userCount > Constant.ENABLED) {
                return HttpResult.error(null, "该机构下拥有用户,不可删除!");
            }
            // 正常删除
            return super.del(id, request);

        } catch (Exception e) {
            return HttpResult.error();
        }
    }

    /**
     * 新增机构
     *
     * @param request
     * @param response
     * @param sysOrganization
     */
    @RequiresPermissions("sysOrganization:add")
    @RequestMapping("insertOrganization")
    @ResponseBody
    @LogDesc(type = LogDesc.ADD, value = "添加")
    public HttpResult insertOrganization(HttpServletRequest request, HttpServletResponse response,
                                         SysOrganization sysOrganization)
    {
        // 效验机构是否存在
        boolean notExist = sysOrganizationService.validataOrgName(sysOrganization);
        if(notExist)
        {
            if(!CheckUtils.isNullOrEmpty(sysOrganization.getParentId())) {
                SysOrganization sysOrganizationQuery = sysOrganizationService.findBeanById(sysOrganization.getParentId());
                if(!CheckUtils.isNullOrEmpty(sysOrganizationQuery) && Constant.ENABLED != sysOrganizationQuery.getIsDisable()) {
                    return HttpResult.error(sysOrganizationQuery, "父机构处于禁用状态，不能添加子机构");
                }
            }

            try
            {
                sysOrganization.setGroupCode(super.getSessionuser(request).getGroupCode());
                sysOrganization.preInsert(super.getSessionuser(request).getUserId());
                sysOrganizationService.insertOrganization(sysOrganization);
            }
            catch (Exception e)
            {
                return HttpResult.error(e);
            }
            return HttpResult.success();
        } else {
            return HttpResult.error(notExist, "机构名称已存在!");
        }
    }

    /**
     * 获取机构ID combotree格式
     * @param request 请求
     * @return combotree数据格式
     */
    @RequestMapping(value="/getOrgIdComBoxData",produces = "application/json;charset=utf-8")
    public void getOrgIdComBoxData(HttpServletRequest request, HttpServletResponse response)
    {
        // 查询根级组织 为当前系统的登录用户组织
        SysUserVo sysUser = super.getSessionuser(request);
        super.outJsonp(JsonUtils.list2json(sysOrganizationService.getSubNode(sysUser.getOrganizationId(),request.getParameter("parentId")))
        ,response,request);
    }

    /**
     * @desc 刷新所有机构缓存
     * @return 刷新缓存结果
     */
    @RequiresPermissions("sysOrganization:refreshRedisCache")
    @RequestMapping("/refreshRedisCache")
    @ResponseBody
    public HttpResult<Map> refreshRedisCache(){
        return sysOrganizationService.refreshRedisCache();
    }



}
