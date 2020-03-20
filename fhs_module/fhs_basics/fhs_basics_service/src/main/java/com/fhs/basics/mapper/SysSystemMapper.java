package com.fhs.basics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhs.basics.dox.SysSystemDO;
import com.fhs.basics.dox.SysUserDO;
import com.fhs.core.base.mapper.FhsBaseMapper;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: Mapper 接口
 * @author  qixiaobo
 * @version [版本号, 2018-09-26]
 * @versio 1.0 陕西小伙伴网络科技有限公司  Copyright (c) 2018 All Rights Reserved.
 */
@MapperDefinition(domainClass = SysSystemDO.class, orderBy = "sort ASC")
@Repository
public interface SysSystemMapper extends FhsBaseMapper<SysSystemDO> {

    /**
     * 查询当前用户拥有权限的子系统列表
     * @param sysUser 用户
     * @return 子系统列表
     */
    List<SysSystemDO> getSystemList(SysUserDO sysUser);
}