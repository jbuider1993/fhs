package com.fhs.basics.mapper;

import com.fhs.basics.dox.UcenterFrontUserDO;
import com.fhs.core.base.mapper.FhsBaseMapper;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.springframework.stereotype.Repository;

/**
 * 前端用户表(UcenterFrontUser)表数据库访问层
 *
 * @author jackwong
 * @since 2019-03-11 14:14:58
 */
@Repository
@MapperDefinition(domainClass = UcenterFrontUserDO.class, orderBy = " update_time DESC")
public interface UcenterFrontUserMapper extends FhsBaseMapper<UcenterFrontUserDO> {

}