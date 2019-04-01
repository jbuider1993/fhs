package com.fhs.ucenter.dao;

import com.fhs.core.base.dao.BaseDao;
import com.fhs.ucenter.bean.UcenterFrontUser;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.springframework.stereotype.Repository;

/**
 * 前端用户表(UcenterFrontUser)表数据库访问层
 *
 * @author jackwong
 * @since 2019-03-11 14:14:58
 */
@Repository
@MapperDefinition(domainClass = UcenterFrontUser.class, orderBy = " update_time DESC")
public interface UcenterFrontUserDao extends BaseDao<UcenterFrontUser> {

}