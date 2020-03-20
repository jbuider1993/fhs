package com.fhs.basics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhs.basics.dox.UcenterFrontUserBindDO;
import com.fhs.core.base.mapper.FhsBaseMapper;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.springframework.stereotype.Repository;

/**
 * (UcenterFrontUserBind)表数据库访问层
 *
 * @author jackwong
 * @since 2019-03-11 14:37:18
 */
@Repository
@MapperDefinition(domainClass = UcenterFrontUserBindDO.class, orderBy = " update_time DESC")
public interface UcenterFrontUserBindMapper extends FhsBaseMapper<UcenterFrontUserBindDO> {

}