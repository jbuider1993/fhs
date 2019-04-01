package com.fhs.ucenter.dao;

import com.mybatis.jpa.annotation.MapperDefinition;
import com.fhs.core.base.dao.BaseDao;
import com.fhs.ucenter.bean.SysMenuServer;
import org.springframework.stereotype.Repository;

/**
 * @Description: Mapper 接口
 * @author  jianbo.qin
 * @version [版本号, 2018-06-01]
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
@MapperDefinition(domainClass = SysMenuServer.class)
@Repository
public interface SysMenuServerDAO extends BaseDao<SysMenuServer> {

}
