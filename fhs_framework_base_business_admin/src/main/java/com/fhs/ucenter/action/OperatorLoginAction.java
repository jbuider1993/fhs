package com.fhs.ucenter.action;

import com.fhs.common.constant.Constant;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.Logger;
import com.fhs.core.config.EConfig;
import com.fhs.core.exception.ParamException;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.api.vo.SysUserVo;
import com.fhs.ucenter.bean.SysUser;
import com.fhs.ucenter.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class OperatorLoginAction {

    Logger LOG = Logger.getLogger(OperatorLoginAction.class);

    @Autowired
    SysUserService sysUserService;

    /**
     * 运营人员登录
     *
     * @param sysUser
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/operatorLogin")
    @ResponseBody
    public HttpResult<Boolean> operatorLogin(SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
        if (ConverterUtils.toBoolean(EConfig.getOtherConfigPropertiesValue("isSaasModel"))) {
            sysUser.setGroupCode(EConfig.getOtherConfigPropertiesValue("operator_group_code"));
            String identifyCode = request.getParameter("identifyCode");
            Object sessionIdentify = request.getSession().getAttribute("identifyCode");
            if (null == sessionIdentify)//session 失效
            {
                throw new ParamException("验证码失效，请刷新验证码后重新输入");
            }
            if (!sessionIdentify.toString().equals(identifyCode)) {
                throw new ParamException("验证码错误，请重新输入");
            }
            request.getSession().setAttribute("identifyCode", null);
            sysUser = sysUserService.login(sysUser);
            if (sysUser == null) {
                throw new ParamException("用户名或者密码错误");
            }
            //如果不是admin就去加载全部的数据
            if (sysUser.getIsAdmin() == Constant.INT_TRUE) {
                request.getSession().setAttribute(Constant.SESSION_USER_DATA_PERMISSION, new HashMap<>());
            } else {
                request.getSession().setAttribute(Constant.SESSION_USER_DATA_PERMISSION, sysUserService.findUserDataPermissions(sysUser.getUserId()));
            }
            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUserLoginName(), sysUser.getPassword());
            SecurityUtils.getSubject().login(token);
            Subject subjects = SecurityUtils.getSubject();
            // 显示调用，让程序重新去加载授权数据
            subjects.isPermitted("init");

            SysUserVo sysUserVo = new SysUserVo();
            BeanUtils.copyProperties(sysUser, sysUserVo);
            request.getSession().setAttribute(Constant.SESSION_USER, sysUserVo);
            return HttpResult.success(true);
        }
        throw new ParamException("未开启saas支持");
    }

}
