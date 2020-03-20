package com.fhs.basics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhs.basics.dox.UcenterMpSettDO;
import com.fhs.core.base.mapper.FhsBaseMapper;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.springframework.stereotype.Repository;

/**
 * 公众号配置(UcenterMpSett)表数据库访问层
 *
 * @author jackwong
 * @since 2019-03-11 14:09:24
 */
@Repository
@MapperDefinition(domainClass = UcenterMpSettDO.class, orderBy = " update_time DESC")
public interface UcenterMpSettMapper extends FhsBaseMapper<UcenterMpSettDO> {

}