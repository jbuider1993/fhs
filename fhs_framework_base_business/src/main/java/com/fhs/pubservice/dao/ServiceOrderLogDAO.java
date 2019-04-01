package com.fhs.pubservice.dao;

import com.fhs.core.base.dao.BaseDao;
import com.fhs.pubservice.bean.ServiceOrderLog;
import com.mybatis.jpa.annotation.MapperDefinition;

/**
 * @Description:生成订单号
 * @author  jianbo.qin
 * @version  [版本号, 2018/05/10 15:09:42]
 * @versio  1.0
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 * */
@MapperDefinition(domainClass = ServiceOrderLog.class)
public interface ServiceOrderLogDAO extends BaseDao<ServiceOrderLog>
{
}