package com.fhs.pagex.mapper;
import com.fhs.core.base.mapper.FhsBaseMapper;
import com.fhs.pagex.dox.DefaultPageXDO;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *  此DAO没什么用
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.dao
 * @ClassName: DefaultPageXDAO
 * @Author: JackWang
 * @CreateDate: 2018/12/17 0017 19:59
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/17 0017 19:59
 * @Version: 1.0
 */
@MapperDefinition(domainClass = DefaultPageXDO.class)
@Repository
public interface DefaultPageXMapper extends FhsBaseMapper<DefaultPageXDO> {
    /**
     * 查询数据给join用
     * 本方法就是执行一个sql 返回结果的集合
     * @param sql sql
     * @return 结果集合
     */
    List<Map<String,Object>> selectListForJoin(@Param("sql") String sql);
}
