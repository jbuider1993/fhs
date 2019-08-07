package com.fhs.ucenter.action;

import com.fhs.common.utils.JsonUtils;
import com.fhs.core.base.action.BaseAction;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.service.UserReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/ms/front/report")
public class UserReportAction extends BaseAction {

    @Autowired
    private UserReportService userReportService;

    /**
     * 获取用户一共多少人，今日注册多少人
     * @return  用户一共多少人，今日注册多少人
     */
    @RequestMapping("frontUserRegReport")
    public void getFrontUserRegReport(HttpServletResponse response, HttpServletRequest request){
        super.outJsonp(JsonUtils.map2json(userReportService.getFrontUserRegReport()),response,request);
    }
}
