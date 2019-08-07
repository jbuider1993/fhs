package com.fhs.ucenter.service.impl;

import com.fhs.ucenter.bean.UcenterFrontUser;
import com.fhs.ucenter.dao.UserReportDao;
import com.fhs.ucenter.service.UcenterFrontUserService;
import com.fhs.ucenter.service.UserReportService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户报表服务
 */
@Service
public class UserReportServiceImpl implements UserReportService {

    @Autowired
    private UserReportDao userReportDao;

    @Autowired
    private UcenterFrontUserService frontUserService;

    @Override
    public Map<String, Object> getFrontUserRegReport() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("todayFrontUserTotal",userReportDao.getTodayFrontUserRegNum());
        resultMap.put("frontUserTotal",frontUserService.selectCount(UcenterFrontUser.builder().build()));
        return resultMap;
    }
}
