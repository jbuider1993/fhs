package com.fhs.ucenter.service;

import java.util.Map;


/**
 * 用户相关报表
 */
public interface UserReportService {

    /**
     * 获取用户一共多少人，今日注册多少人
     * @return  用户一共多少人，今日注册多少人
     */
    Map<String,Object> getFrontUserRegReport();
}
