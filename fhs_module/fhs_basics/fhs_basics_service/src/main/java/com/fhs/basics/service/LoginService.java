package com.fhs.basics.service;

import com.fhs.basics.dox.UcenterFrontUserDO;

/**
 * @Description: 用户登录服务
 * @Version: 1.0
 * @Author: jackwong
 * @Date 2020-03-19
 */
public interface LoginService
{

    /**
     * 根据accessToken获取userid
     *
     * @param accessToken accessToken
     * @return 对应的userid
     */
    String getUserIdByAccessToken(String accessToken);


    /**
     * 根据userid获取accessToken
     * @param userId userId
     * @return accessToken
     */
    String login(String userId);

    /**
     * 添加一个user和bind到db 返回userid
     * @param user  user
     * @param openId openid
     * @param openIdType openid类型
     * @return
     */
    String addBindAndUser(UcenterFrontUserDO user, String openId, int openIdType);
}
