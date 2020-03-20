package com.fhs.basics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhs.basics.dox.UcenterAlipaySettDO;
import com.fhs.core.base.mapper.FhsBaseMapper;
import com.mybatis.jpa.annotation.MapperDefinition;

/**
 * (UcenterAlipaySett)表数据库访问层
 *
 * @author jackwong
 * @since 2019-03-19 16:10:29
 */
@MapperDefinition(domainClass = UcenterAlipaySettDO.class, orderBy = " update_time DESC")
public interface UcenterAlipaySettMapper extends FhsBaseMapper<UcenterAlipaySettDO> {

}