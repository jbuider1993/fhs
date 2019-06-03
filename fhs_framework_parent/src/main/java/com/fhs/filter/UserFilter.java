package com.fhs.filter;

import com.fhs.common.constant.Constant;
import com.fhs.common.spring.SpringContextUtil;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.CookieUtil;
import com.fhs.core.config.EConfig;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.api.form.GetSingleFrontUserForm;
import com.fhs.ucenter.api.service.FeignFrontUserApiService;
import com.fhs.ucenter.api.vo.FrontUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * c端用户登录过滤器
 */
@ServletComponentScan
@WebFilter(urlPatterns = {"/front/*", "/page/h5/*", "/page/pc/*","/b/*"}, filterName = "userFilter", asyncSupported = true)
public class UserFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserFilter.class);
    private static final String JSESSIONID_CODE = "JSESSIONID";
    private static Map jsessionidMapCache = new HashMap();

    /**
     * 存储用户信息的session
     */
    public static Map<String, HttpSession> sessionMap = new ConcurrentHashMap<String, HttpSession>();

    /**
     * key session value accessToken
     */
    public static Map<HttpSession, String> sessionAccessTokenMap = new ConcurrentHashMap<HttpSession, String>();

    /* 前台用户service */
    private FeignFrontUserApiService frontUserService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (frontUserService == null) {
            frontUserService = SpringContextUtil.getBeanByClassForApi(FeignFrontUserApiService.class);
        }

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String uri = request.getRequestURI();
        //如果url 是beetl的，并且不包含h5和pc直接放行
        if (uri.contains("/b/") && !uri.contains("/b/page-h5") && !uri.contains("/b/page-pc")) {
            chain.doFilter(req, res);
            return;
        }
        if (!CheckUtils.isNullOrEmpty(request.getParameter("accessToken"))) {
            // 如果验证通过则判断用户是否是vip如果不是vip但是又要vip 那么会 302 如果accessToken验证不通过 会302
            if (login(request, response)) {
                chain.doFilter(req, res);
            }
            return;
        }

        //普通会员
        boolean isUser = uri.contains("_u");
        FrontUserVo user = (FrontUserVo) request.getSession().getAttribute("frontUser");
        if (isUser && CheckUtils.isNullOrEmpty(user)) {
            send2Login(response, request);
            return;
        }
        chain.doFilter(req, res);
    }


    @Override
    public void destroy() {

    }


    /**
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private boolean login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String accessToken = request.getParameter("accessToken");
        HttpResult<FrontUserVo> resultFrontUser = frontUserService.getSingleFrontUser(GetSingleFrontUserForm.builder().accessToken(accessToken).build());
        if (resultFrontUser.getCode() != Constant.HPROSE_SUCCESS_CODE) {
            LOGGER.error("获取前端用户信息错误,accessToken为{}", accessToken);
            send2Login(response, request);
            return false;
        }
        FrontUserVo frontUser = resultFrontUser.getData();//前端用户信息
        if (frontUser == null) {
            send2Login(response, request);
            return false;
        } else {
            CookieUtil.writeCookie("isUser", "true", response);
            session.setAttribute("frontUser", frontUser);
            session.setAttribute("accessToken", accessToken);
            sessionMap.put(accessToken, session);
            sessionAccessTokenMap.put(session, accessToken);
            return true;
        }
    }

    /**
     * 跳转登陆页面
     *
     * @param response
     * @param request
     * @throws IOException
     */
    private void send2Login(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String extendsParam = "";
        if (!CheckUtils.isNullOrEmpty(request.getQueryString())) {
            extendsParam = request.getQueryString();
            String[] extendsParams = extendsParam.split("&");
            StringBuilder needExtendsParam = new StringBuilder();
            for (int i = 0; i < extendsParams.length; i++) {
                if (!extendsParams[i].contains("accessToken")) {
                    needExtendsParam.append(extendsParams[i]);
                    if (i != (extendsParams.length - 1)) {
                        needExtendsParam.append("&");
                    }
                }
            }
            extendsParam = needExtendsParam.toString().length() == 0 ? "" : "?" + needExtendsParam.toString();
        }
        String callBack = "&callBack=" + URLEncoder.encode(request.getRequestURL() + extendsParam, "utf-8");
        LOGGER.info("redirect_login_url:" + EConfig.getPathPropertiesValue("redirect_login_url"));
        response.sendRedirect(EConfig.getPathPropertiesValue("redirect_login_url") + callBack);
    }

}
