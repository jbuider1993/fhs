package com.fhs.basics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhs.basics.dox.UcenterMsTenantDO;
import com.fhs.core.base.mapper.FhsBaseMapper;
import com.mybatis.jpa.annotation.MapperDefinition;

/**
 * 租户管理(UcenterMsTenant)表数据库访问层
 *
 * @author makejava
 * @since 2019-05-15 14:21:04
 */
@MapperDefinition(domainClass = UcenterMsTenantDO.class, orderBy = " update_time DESC")
public interface UcenterMsTenantMapper extends FhsBaseMapper<UcenterMsTenantDO> {

}