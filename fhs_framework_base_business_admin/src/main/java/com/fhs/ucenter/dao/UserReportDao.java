package com.fhs.ucenter.dao;

import org.springframework.stereotype.Repository;

/**
 * 用户报表sql
 */
@Repository
public interface UserReportDao {

    /**
     * 获取当天注册了多少用户
     * @return 用户个数
     */
    int getTodayFrontUserRegNum();
}
