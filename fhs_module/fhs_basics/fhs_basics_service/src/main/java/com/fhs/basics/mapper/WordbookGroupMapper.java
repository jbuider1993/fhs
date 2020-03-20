package com.fhs.basics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhs.basics.dox.WordbookGroupDO;
import com.fhs.core.base.mapper.FhsBaseMapper;
import com.mybatis.jpa.annotation.MapperDefinition;

/**
 * 字典分组
 *
 * @author  nanshouxiao
 * @version  [版本号, 2015/12/22 15:13:20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@MapperDefinition(domainClass = WordbookGroupDO.class)
public interface WordbookGroupMapper extends FhsBaseMapper<WordbookGroupDO>
{
}