package com.fhs.basics.controller;

import com.fhs.basics.service.SysSystemService;
import com.fhs.basics.service.SysUserService;
import com.fhs.basics.vo.SysUserVO;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.Md5Util;
import com.fhs.common.utils.SCaptcha;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.cache.service.RedisCacheService;
import com.fhs.core.config.EConfig;
import com.fhs.core.exception.ParamException;
import com.fhs.core.result.HttpResult;
import com.fhs.logger.Logger;
import com.fhs.module.base.shiro.StatelessSubject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 非分布式运行时候的登录登出
 */
@RestController
@RequestMapping("/")
public class MsLoginController {

    private static Logger LOGGER = Logger.getLogger(MsLoginController.class);

    private static final String USER_KEY = "shiro:user:";

    /**
     * 后台用户服务
     */
    @Autowired
    private SysUserService sysUserService;

    /**
     * 子系统服务
     */
    @Autowired
    private SysSystemService sysSystemService;

    /**
     * 登录地址
     */
    @Value("${fhs.login.url:http://default.fhs-opensource.com}")
    private String shrioLoginUrl;

    /*
     * 当前工程对外提供的服务地址
     */
    @Value("${fhs.usevue:false}")
    private boolean useVue;

    @Autowired
    private RedisCacheService redisCacheService;


    /**
     * 用户登录
     */
    @RequestMapping("/securityLogin")
    public HttpResult<Boolean> securityLogin(SysUserVO sysUser, HttpServletRequest request, HttpServletResponse response) {
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
        request.getSession().setAttribute(Constant.SESSION_USER, sysUser);
        return HttpResult.success(true);
    }

    /**
     * 用户登录
     */
    @RequestMapping("/vueLogin")
    public HttpResult<Map<String, String>> vueLogin(SysUserVO sysUser, HttpServletRequest request, HttpServletResponse response) {
        if (!useVue) {
            throw new ParamException("vue模式才能使用此方法");
        }
        sysUser.setPassword(Md5Util.MD5(sysUser.getPassword()));
        sysUser = sysUserService.login(sysUser);
        if (sysUser == null) {
            throw new ParamException("用户名或者密码错误");
        }
        String tokenStr = StringUtil.getUUID();
        //如果不是admin就去加载全部的数据
        if (sysUser.getIsAdmin() == Constant.INT_TRUE) {
            redisCacheService.put("shiro:dp:" + tokenStr, new HashMap<>());
        } else {
            redisCacheService.put("shiro:dp:" + tokenStr, sysUserService.findUserDataPermissions(sysUser.getUserId()));
        }
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUserLoginName(), sysUser.getPassword());
        SecurityUtils.getSubject().login(token);
        Subject subject = SecurityUtils.getSubject();
        // 显示调用，让程序重新去加载授权数据
        subject.isPermitted("init");
        redisCacheService.put("shiro:" + tokenStr, new StatelessSubject((WebDelegatingSubject) subject));
        redisCacheService.put(USER_KEY + tokenStr, sysUser);
        Map<String, String> result = new HashMap<>();
        result.put("token", tokenStr);
        return HttpResult.success(result);
    }

    /**
     * 根据token获取用户信息
     *
     * @param token token
     * @return
     */
    @RequestMapping("/getUserByToken")
    public HttpResult<Map<String, Object>> getUserByToken(@RequestHeader("token") String token) {
        Map<String, Object> resultMap = new HashMap<>();
        SysUserVO user = (SysUserVO) redisCacheService.get(USER_KEY + token);
        if (user == null) {
            throw new ParamException("token失效");
        }
        resultMap.put("user", "user");
        resultMap.put("menu", sysUserService.getMenu(user, "2"));
        return HttpResult.success(resultMap);
    }

    /**
     * 生成二维码
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SCaptcha sCaptcha = new SCaptcha();
        String code = sCaptcha.getCode();
        HttpSession session = request.getSession();
        session.setAttribute("identifyCode", code);
        try {
            sCaptcha.write(response.getOutputStream());
        } catch (IOException e) {
            LOGGER.error("二维码生成错误", e);
        }
    }

    /**
     * 退出
     */
    @RequestMapping("ms/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute(Constant.SESSION_USER);
        SecurityUtils.getSubject().logout();
        response.sendRedirect(EConfig.getPathPropertiesValue("basePath") + shrioLoginUrl);
    }

}
