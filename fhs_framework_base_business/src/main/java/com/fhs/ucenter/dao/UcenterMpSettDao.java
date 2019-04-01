package com.fhs.ucenter.dao;

import com.fhs.ucenter.bean.UcenterMpSett;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.fhs.core.base.dao.BaseDao;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 公众号配置(UcenterMpSett)表数据库访问层
 *
 * @author jackwong
 * @since 2019-03-11 14:09:24
 */
@Repository
@MapperDefinition(domainClass = UcenterMpSett.class, orderBy = " update_time DESC")
public interface UcenterMpSettDao extends BaseDao<UcenterMpSett> {

}