package com.fhs.basics.controller;

import com.fhs.basics.dox.SysUserDO;
import com.fhs.basics.service.SysUserService;
import com.fhs.basics.vo.LeftMenuVO;
import com.fhs.basics.vo.SysUserOrgVO;
import com.fhs.basics.vo.SysUserVO;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.*;
import com.fhs.core.base.pojo.pager.Pager;
import com.fhs.core.exception.NotPremissionException;
import com.fhs.core.exception.ParamException;
import com.fhs.core.result.HttpResult;
import com.fhs.core.valid.checker.ParamChecker;
import com.fhs.core.valid.group.Delete;
import com.fhs.logger.anno.LogDesc;
import com.fhs.module.base.controller.ModelSuperController;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统用户管理Action
 *
 * @author jianbo.qin
 */
@RestController
@RequestMapping("ms/sysUser")
public class MsUserController extends ModelSuperController<SysUserVO, SysUserDO> {


    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取用户jsontree 用于easyui下拉tree数据源
     */
    @RequestMapping("getUserTree")
    public List<SysUserOrgVO> getUserTree() {
        return sysUserService.getUserOrgTreeList(super.getSessionuser().getGroupCode());
    }

    /**
     * 添加平台用户
     *
     * @param request
     * @param response
     * @param sysUser
     * @param attr
     */
    @RequiresPermissions("sysUser:add")
    @RequestMapping("addUser")
    @LogDesc(type = LogDesc.ADD, value = "添加后台用户")
    public HttpResult addUser(HttpServletRequest request, HttpServletResponse response, SysUserVO sysUser,
                              RedirectAttributes attr) {
        // 添加用户信息
        boolean notExist = sysUserService.validataLoginName(sysUser);
        if (notExist) {
            SysUserVO loginSysUser = super.getSessionuser();
            sysUser.setUpdateTime(new Date());
            sysUser.setUpdateUser(loginSysUser.getUserId());
            if (StringUtil.isEmpty(sysUser.getUserId())) { //新增
                sysUser.setCreateTime(new Date());
                sysUser.setCreateUser(loginSysUser.getUserId());
                sysUser.setGroupCode(loginSysUser.getGroupCode());
                sysUser.setIsAdmin(Constant.INT_FALSE);
            }
            Map<String, Object> resultMap = sysUserService.addUser(sysUser);
            boolean retult = ConverterUtils.toBoolean(resultMap.get("retult"));
            if (retult) {
                String passWord = ConverterUtils.toString(resultMap.get("passWord"));
                SysUserVO mailUser = (SysUserVO) resultMap.get("sysUser");
                // 发送邮件
                sysUserService.sendMail(mailUser, passWord);
            }
            return HttpResult.success(retult);
        } else {
            throw new ParamException("用户名重复");
        }
    }

    /**
     * 获取密码
     *
     * @param request
     * @param response
     * @param sysUser
     */
    @RequestMapping("readPass")
    public void readPass(HttpServletRequest request, HttpServletResponse response, SysUserVO sysUser) {
        String isSuccess = sysUserService.readPass(sysUser.getUserName());
        super.outWrite(isSuccess);
    }

    /**
     * 根据Id删除用户
     *
     * @param request
     * @param response
     * @param sysUser
     */
    @RequiresPermissions("sysUser:del")
    @RequestMapping("delUser")
    @LogDesc(type = LogDesc.DEL, value = "删除后台用户")
    public HttpResult<Boolean> delUser(HttpServletRequest request, HttpServletResponse response,
                                       @Validated({Delete.class}) SysUserVO sysUser) {
        sysUserService.delete(sysUser);
        return HttpResult.success(true);
    }

    /**
     * @param id
     * @param request
     * @return
     * @desc 根据id删除对象
     */
    @RequiresPermissions("sysUser:del")
    @RequestMapping("/delSysUser")
    @LogDesc(value = "删除", type = LogDesc.DEL)
    public HttpResult<Boolean> delSysUser(@RequestParam("id") String id, HttpServletRequest request) {
        SysUserVO sysUser = sysUserService.findBeanById(id);
        if (sysUser.getIsAdmin() == sysUserService.SYS_USER_IS_ADMIN) {
            throw new ParamException("超级用户不可删除");
        }
        sysUserService.deleteSysUserById(id);
        return HttpResult.success(true);
    }


    /**
     * 更新用户信息
     *
     * @param request
     * @param response
     * @param sysUser
     */
    @RequiresPermissions("sysUser:update")
    @RequestMapping("updateUser")
    @LogDesc(type = LogDesc.UPDATE, value = "修改后台用户")
    public HttpResult<Boolean> update(HttpServletRequest request, HttpServletResponse response, SysUserVO sysUser) {
        if ("defaultPass".equals(sysUser.getPassword())) {
            sysUser.setPassword(null);
        } else {
            sysUser.setPassword(Md5Util.MD5(sysUser.getPassword()).toLowerCase());
        }
        // 删除原有的角色
        sysUserService.updateUser(sysUser);
        return HttpResult.success(Boolean.TRUE);
    }

    /**
     * 插入用户角色关系表
     *
     * @param request
     * @param response
     * @param sysUser
     */
    @RequiresPermissions("sysUser:add")
    @RequestMapping("addUserRole")
    @ResponseBody
    public HttpResult<Boolean> addUserRole(HttpServletRequest request, HttpServletResponse response, SysUserVO sysUser) {
        sysUserService.addUserRole(sysUser);
        return HttpResult.success(true);
    }

    /**
     * 查询用户角色
     *
     * @param request
     * @param response
     * @param sysUser
     */
    @RequiresPermissions("sysUser:see")
    @RequestMapping("searchUserRole")
    public List<Map<String, Object>> searchUserRole(HttpServletRequest request, HttpServletResponse response, SysUserVO sysUser) {
        return sysUserService.searchUserRole(sysUser);
    }

    /**
     * 根据用户查询菜单
     *
     * @param request
     * @param response
     */
    @RequestMapping("seachMenuByUser")
    public List<LeftMenuVO> seachMenuByUser(@RequestParam("menuType") String menuType, HttpServletRequest request, HttpServletResponse response) {
        SysUserVO user = super.getSessionuser();
        ParamChecker.isNotNull(user, "用户没有登录");
        return sysUserService.getMenu(user, menuType);
    }

    /**
     * 修改密码
     *
     * @param sysUser 前端用户信息
     */
    @RequestMapping("updatePass")
    public void updatePass(HttpServletRequest request, HttpServletResponse response, SysUserVO sysUser) {
        SysUserVO user = super.getSessionuser();
        sysUser.setUserId(user.getUserId());
        boolean isSuccess = sysUserService.updatePass(sysUser);
        super.outToClient(isSuccess);
    }

    /**
     * 修改自己的个人信息
     *
     * @param formSysUser 前端参数
     */
    @RequestMapping("updateOwnUserInfo")
    public void updateOwnUserInfo(HttpServletRequest request, HttpServletResponse response, SysUserVO formSysUser) {
        SysUserVO user = super.getSessionuser();
        formSysUser.setUserName(formSysUser.getUserName());
        //把密码设置为空不修改密码
        formSysUser.setPassword(null);
        formSysUser.setEmail(formSysUser.getEmail());
        formSysUser.setMobile(formSysUser.getMobile());
        formSysUser.setUserId(user.getUserId());
        super.outToClient(sysUserService.updateSelectiveById(formSysUser) > 0);

    }

    /**
     * 校验密码
     *
     * @param request
     * @param response
     */
    @RequestMapping("validataPass")
    public void validataPass(HttpServletRequest request, HttpServletResponse response) {
        String param = request.getParameter("param");
        SysUserVO user = super.getSessionuser();
        SysUserVO sysUser = new SysUserVO();
        sysUser.setUserId(user.getUserId());
        sysUser.setOldPassword(param);
        boolean isSuccess = sysUserService.validataPass(sysUser);
        if (isSuccess) {
            super.outWrite("y");
        } else {
            super.outWrite("原密码错误");
        }

    }

    /**
     * 校验登录名是否存在
     *
     * @param request
     * @param response
     * @param sysUser
     */
    @RequestMapping("validataLoginName")
    public void validataLoginName(HttpServletRequest request, HttpServletResponse response, SysUserVO sysUser) {
        String param = request.getParameter("param");
        String isEdit = request.getParameter("isEdit");
        if ("true".equals(isEdit)) {
            super.outWrite("y");
            return;
        }

        if (StringUtil.validtIsChinese(param)) {
            super.outWrite("用户登录名不能包含中文");
        }
        sysUser.setUserLoginName(param);
        boolean isSuccess = sysUserService.validataLoginName(sysUser);
        if (isSuccess) {
            super.outWrite("y");
        } else {
            super.outWrite("登录名已存在");
        }

    }

    /**
     * 刷新所有用户缓存
     *
     * @return
     */
    @RequiresPermissions("sysUser:refreshRedisCache")
    @RequestMapping("/refreshRedisCache")
    public HttpResult refreshRedisCache() {
        return sysUserService.refreshRedisCache();
    }

    /**
     * @param request http请求
     * @param
     * @return 前端分页请求
     * @desc 后台用户分页
     */
    @RequiresPermissions("sysUser:see")
    @RequestMapping("/findPage/{organizationId}")
    @ResponseBody
    public Pager<SysUserVO> findPage(@PathVariable(value = "organizationId") String organizationId, HttpServletRequest request, SysUserVO sysUser) {
        if (isPermitted(request, "see")) {
            if (!CheckUtils.isNullOrEmpty(organizationId)) sysUser.setOrganizationId(organizationId);
            PageSizeInfo pgeSizeInfo = getPageSizeInfo();
            List<SysUserVO> dataList = sysUserService.findForList(sysUser, pgeSizeInfo.getPageStart(), pgeSizeInfo.getPageSize());
            int count = sysUserService.findCountJpa(sysUser);
            return new Pager<SysUserVO>(count, dataList);
        } else {
            throw new NotPremissionException();
        }
    }

    /**
     * 根据ID集合查询对象数据
     *
     * @param id id
     * @throws Exception
     */
    @Override
    @RequiresPermissions("sysUser:see")
    @RequestMapping("info/{id}")
    @ResponseBody
    public SysUserVO info(@PathVariable(value = "id", required = true) String id,HttpServletRequest request) throws Exception {
        //根据用户id用户信息
        SysUserVO user = sysUserService.findSysUserById(id);
        return user;
    }

    /**
     * 获取自己的个人信息
     *
     * @param request
     * @return
     */
    @RequestMapping("getOwnUserInfo")
    @ResponseBody
    public SysUserVO getOwnUserInfo(HttpServletRequest request) {
        return sysUserService.findSysUserById(super.getSessionuser().getUserId());
    }

}
