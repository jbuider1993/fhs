package com.fhs.basics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhs.basics.dox.SysModelDO;
import com.fhs.core.base.mapper.FhsBaseMapper;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  系统模块DAO
 * </p>
 *
 * @author jianbo.qin
 * @since 2018-05-29
 */
@MapperDefinition(domainClass = SysModelDO.class)
@Repository
public interface SysModelMapper extends FhsBaseMapper<SysModelDO> {

}
