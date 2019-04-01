package com.fhs.system.dao;

import com.fhs.core.base.dao.BaseDao;
import com.fhs.system.bean.Wordbook;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.apache.ibatis.annotations.Param;

/**
 * 字典表dao
 * @author  wanglei
 * @version  [版本号, 2015年8月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@MapperDefinition(domainClass = Wordbook.class)
public interface WordbookDAO extends BaseDao<Wordbook>
{

    /**
     * 根据字典类型删除字典
     *
     * @param wordbookGroupCode
     */
    void batchDelete(@Param("wordbookGroupCode") String wordbookGroupCode);

}
