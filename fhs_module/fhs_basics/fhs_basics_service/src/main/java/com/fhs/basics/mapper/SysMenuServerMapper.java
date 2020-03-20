package com.fhs.basics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhs.basics.dox.SysMenuServerDO;
import com.fhs.core.base.mapper.FhsBaseMapper;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.springframework.stereotype.Repository;

/**
 * @Description: Mapper 接口
 * @author  jianbo.qin
 * @version [版本号, 2018-06-01]
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
@MapperDefinition(domainClass = SysMenuServerDO.class)
@Repository
public interface SysMenuServerMapper extends FhsBaseMapper<SysMenuServerDO> {

}
