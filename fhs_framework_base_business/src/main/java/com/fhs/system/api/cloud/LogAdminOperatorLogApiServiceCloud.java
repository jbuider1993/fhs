package com.fhs.system.api.cloud;

import com.fhs.common.utils.StringUtil;
import com.fhs.core.db.DataSource;
import com.fhs.core.result.HttpResult;
import com.fhs.system.api.FeignlogAdminOperatorLogApiService;
import com.fhs.system.bean.LogAdminOperatorLog;
import com.fhs.system.bean.LogAdminOperatorLogVo;
import com.fhs.system.service.LogAdminOperatorLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Filename: LogAdminOperatorLogApiServiceCloud.java
 * @Description: 后台操作日志API接口
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
@RestController
@RequestMapping("/api/logAdminOperatorLog")
@DataSource("log")
public class LogAdminOperatorLogApiServiceCloud implements FeignlogAdminOperatorLogApiService {

    /**
     * 操作日志服务
     */
    @Autowired
    private LogAdminOperatorLogService logAdminOperatorLogService;

    /**
     * 插入一条操作日志
     * @param logAdminOperatorLogVo 操作日志vo
     * @return
     */
    @RequestMapping("/addLogAdminOperatorLog")
    @Override
    public HttpResult<Integer> addLogAdminOperatorLog(LogAdminOperatorLogVo logAdminOperatorLogVo) {
        LogAdminOperatorLog log =new LogAdminOperatorLog();
        BeanUtils.copyProperties(logAdminOperatorLogVo,log);
        log.setId(StringUtil.getUUID());
        int result = logAdminOperatorLogService.insert(log);
        return HttpResult.success(result);
    }
}
