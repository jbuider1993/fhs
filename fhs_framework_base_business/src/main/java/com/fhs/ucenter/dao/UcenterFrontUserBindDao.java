package com.fhs.ucenter.dao;

import com.fhs.ucenter.bean.UcenterFrontUserBind;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.fhs.core.base.dao.BaseDao;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * (UcenterFrontUserBind)表数据库访问层
 *
 * @author jackwong
 * @since 2019-03-11 14:37:18
 */
@Repository
@MapperDefinition(domainClass = UcenterFrontUserBind.class, orderBy = " update_time DESC")
public interface UcenterFrontUserBindDao extends BaseDao<UcenterFrontUserBind> {

}