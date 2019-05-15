package com.fhs.ucenter.action;

import com.fhs.base.action.ModelSuperAction;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.*;
import com.fhs.core.exception.ParamException;
import com.fhs.core.log.LogDesc;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.api.vo.SysUserVo;
import com.fhs.ucenter.bean.SysRole;
import com.fhs.ucenter.bean.SysUser;
import com.fhs.ucenter.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 角色控制器类
 *
 * @author Administrator
 *
 */
@Controller
@RequestMapping("ms/sysRole")
public class MsRoleAction extends ModelSuperAction<SysRole>
{

    Logger LOG = Logger.getLogger(MsRoleAction.class);

    /**
     * 角色服务
     */
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 获取角色集合
     * @param organizationId 机构id
     * @param request
     * @param response
     */
    @RequiresPermissions("sysRole:see")
    @RequestMapping("/listData/{organizationId}")
    public void listData(@PathVariable(value="organizationId") String organizationId, HttpServletRequest request, HttpServletResponse response)
    {
        SysUserVo sysUser = super.getSessionuser(request);
        Map<String, Object> map = super.getPageTurnNum(request);
        if(CheckUtils.isNotEmpty(organizationId))
        {
            map.put("organizationId", organizationId);
        }else {
            map.put("organizationId", sysUser.getOrganizationId());
        }
        List<SysRole> dataList = sysRoleService.findForListFromMap(map);
        int count = sysRoleService.findCountFromMap(map);
        super.writeJsonForPager(dataList, count, response);
    }


    /**
     * 获取bean数据(根据id)
     *
     * @param request
     * @param reponse
     * @param sysRole
     * @return
     */
    @RequiresPermissions("sysRole:see")
    @RequestMapping("toUpdate")
    public void getBeanData(HttpServletRequest request, HttpServletResponse reponse, SysRole sysRole)
    {
        SysRole bean = sysRoleService.findBean(sysRole);
        String dataPermissions = bean.getDataPermissions();
        String data = JsonUtils.bean2json(bean);
        super.outWrite(data, reponse);
    }

    /**
     * 验证角色名称是否存在
     * @param request
     * @param reponse
     * @param sysRole
     */
    @RequiresPermissions("sysRole:see")
    @RequestMapping("validataName")
    public void validataName(HttpServletRequest request, HttpServletResponse reponse, SysRole sysRole)
    {
        Map<String, Object> map = super.getParameterMap(request);
        String param = (String)map.get("param");
        map.put("roleName", param);
        sysRole = sysRoleService.findBeanFromMap(map);
        if (sysRole != null && !ConverterUtils.toBoolean(map.get("isEdit")))
        {
            super.outWrite("角色名称重复", reponse);
        }
        else
        {
            super.outWrite("y", reponse);
        }
    }

    /**
     * 查询角色的按钮信息
     *
     * @param request
     * @param response
     * @param sysRole
     */
    @RequestMapping("searchButtons")
    public void searchButtons(HttpServletRequest request, HttpServletResponse response, SysRole sysRole)
    {
        List<Map<String, Object>> dataList = sysRoleService.searchButtons(sysRole);
        String result = JsonUtils.list2json(dataList);
        super.outWrite(result, response);
    }

    /**
     * @desc 新增修改后台用户  获取当前机构下的角色数据
     */

    @RequestMapping("/getCurrentOrganizationSysRoles")
    public void getCurrentOrganizationSysRoles(HttpServletRequest request, HttpServletResponse response) {
        //获取当前登录用户
        SysUser sysUser = (SysUser)request.getSession().getAttribute(Constant.SESSION_USER);
        SysRole sysRole = new SysRole();
        List<SysRole> sysRoleList = sysRoleService.findForList(sysRole);
        String result = JsonUtils.list2json(sysRoleList);
        super.outWrite(result, response);
    }

    /**
     * 角色下拉框获取数据
     *
     * @param request
     * @param response
     * @param sysRole
     * @return
     */
    @RequestMapping("realRoleComBoxData")
    public void realRoleComBoxData(HttpServletRequest request, HttpServletResponse response, SysRole sysRole)
    {
        Map<String, Object> map = super.getPageTurnNum(request);
        map.put("organizationId", super.getSessionuser(request).getOrganizationId());
        List<SysRole> dataList = sysRoleService.findForListFromMap(map);
        String result = JsonUtils.list2json(dataList);
        super.outWrite(result, response);
    }

    /**
     * @desc 根据机构id获取角色下拉框数据
     * @param response
     * @return
     */
    @RequestMapping("/getSelectOrganSysRoles/{organizationId}")
    public void getSelectOrganSysRoles(@PathVariable("organizationId") String organizationId,HttpServletRequest request, HttpServletResponse response) {
        if(StringUtil.isEmpty(organizationId)) {
           throw new ParamException("organizationId 为必传");
        }else {
            Map<String, Object> map = super.getParameterMap(request);
            map.put("organizationId", organizationId);
            List<SysRole> dataList = sysRoleService.findForListFromMap(map);
            String result = JsonUtils.list2json(dataList);
            super.outWrite(result, response);
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
    public HttpResult updateSysRole(HttpServletRequest request, HttpServletResponse response, SysRole sysRole)
    {

        try
        {
            SysRole oldRole = sysRoleService.findBeanById(sysRole.getRoleId());
            if(Constant.ENABLED == oldRole.getIsDisable() && Constant.DISABLE == sysRole.getIsDisable())
            {
                // 根据roleid查询用户关联表用户数
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("roleId", sysRole.getRoleId());
                Integer count = sysRoleService.findUserCountByRoleId(paramMap);
                if(count > Constant.ENABLED)
                {
                    return HttpResult.error(count > Constant.ENABLED, "该角色拥有关联用户,不可禁用!");
                }
            }
            SysUserVo sysUser = super.getSessionuser(request);
            sysRole.setUpdateUser(sysUser.getUserId());
            sysRole.setUpdateTime(DateUtils.getCurrentDateStr(DateUtils.DATETIME_PATTERN));
            sysRoleService.updateRole(sysRole);
        }
        catch (Exception e)
        {
            LOG.error(this,e);
            return HttpResult.error(false);
        }
        return HttpResult.success();
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
    @ResponseBody
    @LogDesc(type = LogDesc.ADD, value = "添加角色")
    public HttpResult add(HttpServletRequest request, HttpServletResponse response, SysRole sysRole)
    {
        try
        {
            SysUserVo sysUser = super.getSessionuser(request);
            sysRole.setCreateUser(sysUser.getUserId());
            sysRole.setCreateTime(DateUtils.getCurrentDateStr(DateUtils.DATETIME_PATTERN));
            sysRole.setGroupCode(sysUser.getGroupCode());
            sysRoleService.addRole(sysRole);
        }
        catch (Exception e)
        {
            return HttpResult.error(e);
        }
        return HttpResult.success();
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
    @ResponseBody
    public HttpResult del(HttpServletRequest request, HttpServletResponse response, SysRole sysRole)
    {
        try
        {
            sysRoleService.deleteRole(sysRole);
        }
        catch (Exception e)
        {
            return HttpResult.error(e);
        }
        return HttpResult.success();

    }

}
