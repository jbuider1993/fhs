package com.fhs.system.service.impl;

import com.fhs.common.utils.CheckUtils;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.DataSource;
import com.fhs.core.trans.TransService;
import com.fhs.system.bean.LogAdminOperatorLog;
import com.fhs.system.service.LogAdminOperatorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author  qixiaobo
 * @version [版本号, 2018-08-11]
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2018 All Rights Reserved.
 */
@Service
@DataSource("base_business")
public class LogAdminOperatorLogServiceImpl extends BaseServiceImpl<LogAdminOperatorLog> implements LogAdminOperatorLogService {

    @Autowired
    private TransService transService;

    @Override
    public LogAdminOperatorLog findBeanById(Object primaryValue) {
        LogAdminOperatorLog logAdminOperatorLog = super.selectById(primaryValue);
        transService.transOne(logAdminOperatorLog);
        String operatorIdUserName = logAdminOperatorLog.getTransMap().get("operatorIdUserName");
        if (CheckUtils.isNotEmpty(operatorIdUserName)){
            logAdminOperatorLog.setOperatorId(operatorIdUserName);
        }
        return logAdminOperatorLog;
    }
}
