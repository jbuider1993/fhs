package com.fhs.basics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhs.basics.dox.WordbookDO;
import com.fhs.core.base.mapper.FhsBaseMapper;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.apache.ibatis.annotations.Param;

/**
 * 字典表dao
 * @author  wanglei
 * @version  [版本号, 2015年8月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@MapperDefinition(domainClass = WordbookDO.class)
public interface WordbookMapper extends FhsBaseMapper<WordbookDO>
{

}
