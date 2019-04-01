package com.fhs.ucenter.dao;

import com.mybatis.jpa.annotation.MapperDefinition;
import com.fhs.core.base.dao.BaseDao;
import com.fhs.ucenter.bean.SysModel;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  系统模块DAO
 * </p>
 *
 * @author jianbo.qin
 * @since 2018-05-29
 */
@MapperDefinition(domainClass = SysModel.class)
@Repository
public interface SysModelDAO extends BaseDao<SysModel> {

}
