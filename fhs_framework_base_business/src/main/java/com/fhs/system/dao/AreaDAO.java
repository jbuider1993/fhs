package com.fhs.system.dao;

import com.fhs.core.base.dao.BaseDao;
import com.fhs.system.bean.Area;
import com.mybatis.jpa.annotation.MapperDefinition;

/**
 * 省市区字典
 *
 * @Filename: AreaDAO.java
 * @Description:
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
@MapperDefinition(domainClass = Area.class)
public interface AreaDAO extends BaseDao<Area>
{
}