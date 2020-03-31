package com.fhs.basics.mapper;

import com.fhs.basics.dox.SettMsModelDO;
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
@Repository
@MapperDefinition(domainClass = SettMsModelDO.class)
public interface SettMsModelMapper extends FhsBaseMapper<SettMsModelDO> {

}
