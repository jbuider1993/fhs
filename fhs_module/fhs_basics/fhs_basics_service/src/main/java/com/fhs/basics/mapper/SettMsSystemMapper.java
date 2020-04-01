package com.fhs.basics.mapper;

import com.fhs.basics.dox.SettMsSystemDO;
import com.fhs.basics.dox.UcenterMsUserDO;
import com.fhs.core.base.mapper.FhsBaseMapper;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qixiaobo
 * @version [版本号, 2018-09-26]
 * @Description: Mapper 接口
 * @versio 1.0 陕西小伙伴网络科技有限公司  Copyright (c) 2018 All Rights Reserved.
 */
@MapperDefinition(domainClass = SettMsSystemDO.class, orderBy = "sort ASC")
@Repository
public interface SettMsSystemMapper extends FhsBaseMapper<SettMsSystemDO> {

    /**
     * 查询当前用户拥有权限的子系统列表
     *
     * @param sysUser 用户
     * @return 子系统列表
     */
    List<SettMsSystemDO> getSystemList(UcenterMsUserDO sysUser);
}