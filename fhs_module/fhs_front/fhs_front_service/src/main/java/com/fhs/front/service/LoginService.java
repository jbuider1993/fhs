package com.fhs.front.service;


import com.fhs.front.dox.UcenterFrontUserDO;
import com.fhs.front.interfaces.FhsOauth302;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @Description: 用户登录服务
 * @Version: 1.0
 * @Author: jackwong
 * @Date 2020-03-19
 */
public interface LoginService {

    /**
     * 根据accessToken获取userid
     *
     * @param accessToken accessToken
     * @return 对应的userid
     */
    String getUserIdByAccessToken(String accessToken);


    /**
     * 根据userid获取accessToken
     *
     * @param userId userId
     * @return accessToken
     */
    String login(String userId);

    /**
     * 添加一个user和bind到db 返回userid
     *
     * @param user       user
     * @param openId     openid
     * @param openIdType openid类型
     * @return
     */
    String addBindAndUser(UcenterFrontUserDO user, String openId, int openIdType);

    /**
     * 获取系统支持的OAUTH登录服务
     * @return
     */
    Map<String,FhsOauth302> getOauthServiceMap();

    /**
     * 根据UA获取实现类
     * @param ua  浏览器UA
     * @return 对应的服务实现
     */
    FhsOauth302 getOauth302Impl(String ua);

    /**
     * 完成登录和返回
     * @param request
     * @param response
     * @param userId 用户id
     */
    void loginAndRedirect(HttpServletRequest request, HttpServletResponse response, String userId);

    /**
     * 对URL参数进行过滤处理.
     *
     * @param callback    回调地址
     * @param accessToken token信息
     * @return 解码后的回调地址
     */
     String checkUrl(String callback, String accessToken) throws UnsupportedEncodingException;
}
