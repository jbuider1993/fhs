package com.fhs.ucenter.dao;

import com.fhs.ucenter.bean.SysSystem;
import com.fhs.core.base.dao.BaseDao;
import com.mybatis.jpa.annotation.MapperDefinition;
import com.fhs.ucenter.bean.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: Mapper 接口
 * @author  qixiaobo
 * @version [版本号, 2018-09-26]
 * @versio 1.0 陕西小伙伴网络科技有限公司  Copyright (c) 2018 All Rights Reserved.
 */
@MapperDefinition(domainClass = SysSystem.class, orderBy = "sort ASC")
@Repository
public interface SysSystemDAO extends BaseDao<SysSystem> {

    /**
     * 查询当前用户拥有权限的子系统列表
     * @param sysUser 用户
     * @return 子系统列表
     */
    List<SysSystem> getSystemList(SysUser sysUser);
}