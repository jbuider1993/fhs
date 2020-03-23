package com.fhs.basics.controller;

import com.fhs.base.action.ModelSuperAction;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.Md5Util;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.exception.ParamChecker;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.bean.SysUser;
import com.fhs.ucenter.bean.UcenterMsTenant;
import com.fhs.ucenter.service.SysUserService;
import com.mybatis.jpa.context.MultiTenancyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 租户管理(UcenterMsTenant)表控制层
 *
 * @author makejava
 * @since 2019-05-15 14:21:04
 */
@RestController
@RequestMapping("/ms/tenant")
public class UcenterMsTenantAction extends ModelSuperAction<UcenterMsTenant> {

    @Autowired
    private SysUserService userService;

    /**
     * 重置某个停车场密码
     * @param groupCode 租户集团编码
     * @return 重置后的密码
     */
    @RequestMapping("resetAdminPass")
    public HttpResult<String> resetAdminPass(String groupCode)
    {
        ParamChecker.isNotNull(groupCode,"groupCode不能为空");
        String newPass = StringUtil.getUUID();
        SysUser user = new SysUser();
        user.setUserLoginName(groupCode + "_admin");
        MultiTenancyContext.setProviderId(null);
        user = userService.selectBean(user);
        ParamChecker.isNotNull(user,"用户信息为空，请联系运维");
        user.setIsDisable(Constant.INT_FALSE);
        user.setPassword(Md5Util.MD5(newPass).toLowerCase());
        userService.updateJpa(user);
        return HttpResult.success(newPass);
    }
}