package com.fhs.module.base.index;

import com.fhs.basics.api.rpc.FeignSysUserApiService;
import com.fhs.basics.vo.SysUserVO;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.*;
import com.fhs.core.cache.service.RedisCacheService;
import com.fhs.core.exception.BusinessException;
import com.fhs.core.feign.autowired.annotation.AutowiredFhs;
import com.fhs.core.result.HttpResult;
import com.fhs.logger.Logger;
import io.buji.pac4j.subject.Pac4jPrincipal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 首页请求action
 *
 * @Filename: IndexController.java
 * @Description:
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
@Controller
@AutowiredFhs
public class IndexController {

    private static final Logger LOGGER = Logger.getLogger(IndexController.class);

    /** 后台用户feign服务 */
    @AutowiredFhs
    private FeignSysUserApiService feignSysUserService;

    /**
     * @desc redis service
     */
    @Autowired
    private RedisCacheService redisCacheService;

    @Value("${fhs.login.enable-cas}")
    private boolean isEnableCas;

    /**
     * 初始化项目主页
     * @return
     */
    @RequestMapping("/")
    public ModelAndView initIndex(HttpServletRequest request,HttpServletResponse response) throws IOException {
        return index(request,response);
    }

    /**
     * 判断用户是否登录及转发用户请求地址
     * @param request
     * @return
     */
    @RequestMapping("/ms/index")
    public ModelAndView index(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String serviceURL = null;
        // session中的url不为空就取session中的
        if(!CheckUtils.isNullOrEmpty(request.getSession().getAttribute("serviceURL")))
        {
            serviceURL = (String) request.getSession().getAttribute("serviceURL");
        }
        // 参数中的url不为空则取request的
        else if(!CheckUtils.isNullOrEmpty(request.getParameter("serviceUrl")))
        {
            try {
                serviceURL = URLDecoder.decode(request.getParameter("serviceUrl"),"UTF-8") ;
            } catch (UnsupportedEncodingException e) {
                LOGGER.error(this,e);
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        // 如果有其他的进程已经放了用户，则直接302
        if(request.getSession().getAttribute(Constant.SESSION_USER)!=null)
        {
            // 如果url为空则到子系统选择页面
            if(serviceURL == null)
            {
                modelAndView.setViewName("page/ms/index/indexMenuLayui");
            }else
            {
                modelAndView.setViewName("redirect:" + serviceURL);
            }
            return modelAndView;
        }

        // 获取登录名称
        String userLoginName = null;
        if(isEnableCas)
        {
            userLoginName = ((Pac4jPrincipal) SecurityUtils.getSubject().getPrincipal()).getProfile().getId();
        }
        else{
            userLoginName = SecurityUtils.getSubject().getPrincipal().toString();
        }
        // 根据登录名称获取用户信息,并放入session
        HttpResult<SysUserVO> result = feignSysUserService.getSysUserByName(userLoginName);
        LOGGER.infoMsg("后端用户登录成功，用户信息:{}",result.getData());
        if (result.getCode() == 200){
            SysUserVO user = result.getData();
           /* feignlogAdminOperatorLogApiService.addLogAdminOperatorLog(
                    LogAdminOperatorLogVo.builder().operatorId(user.getUserId()).url(request.getRequestURI())
                    .createTime(DateUtils.getCurrentDateStr(DateUtils.DATE_FULL_STR_SSS))
                            .networkIp(NetworkUtil.getIpAddress(request)).logType(LogDesc.OTHER).operatDesc("登录").reqParam("不可见").menuId(0).build());
            */if(user.getIsAdmin() == Constant.INT_TRUE)
            {
                request.getSession().setAttribute(Constant.SESSION_USER_DATA_PERMISSION, new HashMap<>());
                request.getSession().setAttribute(Constant.SESSION_USER, user);
            }
            else
            {
                HttpResult<Map<String,String>> userDataPermission = feignSysUserService.getDataUserPermisstion(user.getUserId());
                if (result.getCode() == Constant.SUCCESS_CODE){
                    request.getSession().setAttribute(Constant.SESSION_USER_DATA_PERMISSION, userDataPermission.getData());
                    request.getSession().setAttribute(Constant.SESSION_USER, user);
                }
                else
                {
                    LOGGER.error("获取用户数据权限失败" +user.getUserId() + userDataPermission.asJson() );
                    throw new BusinessException("获取用户数据权限失败:" + userLoginName);
                }
            }

        }else {
            LOGGER.error("获取用户信息失败" + userLoginName + result.asJson());
            throw new BusinessException("根据用户名获取用户信息失败:" + userLoginName);
        }
        String uuid = StringUtil.getUUID();
        CookieUtil.writeCookie("jackToken",uuid,response);
        request.getSession().setAttribute("jackToken", uuid);
        request.getSession().removeAttribute("serviceURL");
        if(CheckUtils.isNotEmpty(serviceURL)){
            modelAndView.setViewName("redirect:" + serviceURL);
        }else {
            modelAndView.setViewName("page/ms/index/indexMenuLayui");
        }
        return modelAndView;
    }

    /**
     * 子系统首页
     * @param request 跳转到子系统首页
     * @return 子系统首页
     */
    @RequestMapping("/ms/indexMenu")
    public ModelAndView indexMenu(HttpServletRequest request,HttpServletResponse response) throws IOException {
       if(request.getSession().getAttribute(Constant.SESSION_USER)==null)
        {
            return index(request,response);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/page/ms/index/indexMenuLayui");
        return modelAndView;
    }


}
