package com.fhs.basics.controller;

import com.fhs.basics.dox.SysRoleDO;
import com.fhs.basics.service.SysRoleService;
import com.fhs.basics.vo.SysRoleVO;
import com.fhs.basics.vo.SysUserVO;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.*;
import com.fhs.core.base.pojo.pager.Pager;
import com.fhs.core.exception.ParamException;
import com.fhs.core.result.HttpResult;
import com.fhs.logger.anno.LogDesc;
import com.fhs.module.base.controller.ModelSuperController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 角色控制器类
 *
 * @author Administrator
 */
@RestController
@RequestMapping("ms/sysRole")
public class MsRoleController extends ModelSuperController<SysRoleVO, SysRoleDO> {

    /**
     * 角色服务
     */
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 获取角色集合
     *
     * @param organizationId 机构id
     * @param request
     * @param response
     */
    @RequiresPermissions("sysRole:see")
    @RequestMapping("/listData/{organizationId}")
    public Pager<SysRoleVO> listData(@PathVariable(value = "organizationId") String organizationId, HttpServletRequest request, HttpServletResponse response) {
        SysUserVO sysUser = super.getSessionuser();
        Map<String, Object> map = super.getPageTurnNum();
        if (CheckUtils.isNotEmpty(organizationId)) {
            map.put("organizationId", organizationId);
        } else {
            map.put("organizationId", sysUser.getOrganizationId());
        }
        List<SysRoleVO> dataList = sysRoleService.findForListFromMap(map);
        int count = sysRoleService.findCountFromMap(map);
        return new Pager<SysRoleVO>(count,dataList);
    }



    /**
     * 验证角色名称是否存在
     *
     * @param request
     * @param reponse
     * @param sysRole
     */
    @RequiresPermissions("sysRole:see")
    @RequestMapping("validataName")
    public void validataName(HttpServletRequest request, HttpServletResponse reponse, SysRoleVO sysRole) {
        Map<String, Object> map = super.getParameterMap();
        sysRole = sysRoleService.findBean(sysRole);
        if (sysRole != null && !ConverterUtils.toBoolean(map.get("isEdit"))) {
            super.outWrite("角色名称重复");
        } else {
            super.outWrite("y");
        }
    }

    /**
     * 查询角色的按钮信息
     * @param sysRole
     */
    @RequestMapping("searchButtons")
    public List<Map<String, Object>> searchButtons( SysRoleVO sysRole) {
        return sysRoleService.searchButtons(sysRole);
    }

    /**
     * @desc 新增修改后台用户  获取当前机构下的角色数据
     */
    @RequestMapping("/getCurrentOrganizationSysRoles")
    public List<SysRoleVO> getCurrentOrganizationSysRoles(HttpServletRequest request, HttpServletResponse response) {
        //获取当前登录用户
        SysUserVO sysUser = super.getSessionuser();
        SysRoleVO sysRole = new SysRoleVO();
        return sysRoleService.findForList(sysRole);
    }

    /**
     * 角色下拉框获取数据
     *
     * @return
     */
    @RequestMapping("realRoleComBoxData")
    public List<SysRoleVO> realRoleComBoxData() {
        Map<String, Object> map = super.getPageTurnNum();
        map.put("organizationId", super.getSessionuser().getOrganizationId());
        return sysRoleService.findForListFromMap(map);
    }

    /**
     * 根据机构id获取角色下拉框数据
     * @return
     */
    @RequestMapping("/getSelectOrganSysRoles/{organizationId}")
    public  List<SysRoleVO> getSelectOrganSysRoles(@PathVariable("organizationId") String organizationId) {
        if (StringUtil.isEmpty(organizationId)) {
            throw new ParamException("organizationId 为必传");
        } else {
            Map<String, Object> map = super.getParameterMap();
            map.put("organizationId", organizationId);
            return sysRoleService.findForListFromMap(map);
        }
    }

    /**
     * 更新角色信息
     *
     * @param request
     * @param response
     * @paramadminRole
     */
    @ResponseBody
    @LogDesc(value = "更新", type = LogDesc.UPDATE)
    @RequestMapping("updateSysRole")
    @RequiresPermissions("sysRole:update")
    public HttpResult<Boolean> updateSysRole(HttpServletRequest request, HttpServletResponse response, SysRoleVO sysRole) {
        SysRoleVO oldRole = sysRoleService.findBeanById(sysRole.getRoleId());
        if (Constant.ENABLED == oldRole.getIsDisable() && Constant.DISABLE == sysRole.getIsDisable()) {
            // 根据roleid查询用户关联表用户数
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("roleId", sysRole.getRoleId());
            Integer count = sysRoleService.findUserCountByRoleId(paramMap);
            if (count > Constant.ENABLED) {
                return HttpResult.error(count > Constant.ENABLED, "该角色拥有关联用户,不可禁用!");
            }
        }
        SysUserVO sysUser = super.getSessionuser();
        sysRole.setUpdateUser(sysUser.getUserId());
        sysRole.setUpdateTime(new Date());
        sysRoleService.updateRole(sysRole);
        return HttpResult.success(true);
    }

    /**
     * 添加角色
     *
     * @param request
     * @param response
     * @paramadminRole
     */
    @RequiresPermissions("sysRole:add")
    @RequestMapping("addSysRole")
    @LogDesc(type = LogDesc.ADD, value = "添加角色")
    public HttpResult<Boolean> add(HttpServletRequest request, HttpServletResponse response, SysRoleVO sysRole) {
        SysUserVO sysUser = super.getSessionuser();
        sysRole.setCreateUser(sysUser.getUserId());
        sysRole.setCreateTime(new Date());
        sysRole.setGroupCode(sysUser.getGroupCode());
        sysRoleService.addRole(sysRole);
        return HttpResult.success(true);
    }

    /**
     * 根据Id删除角色
     *
     * @param request
     * @param response
     * @paramadminRole
     */
    @RequiresPermissions("sysRole:del")
    @RequestMapping("delSysRole")
    public HttpResult<Boolean> del(HttpServletRequest request, HttpServletResponse response, SysRoleVO sysRole) {
        sysRoleService.deleteRole(sysRole);
        return HttpResult.success(true);

    }
}
