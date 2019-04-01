package com.fhs.ucenter.dao;

import com.fhs.ucenter.bean.UcenterAlipaySett;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.fhs.core.base.dao.BaseDao;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * (UcenterAlipaySett)表数据库访问层
 *
 * @author jackwong
 * @since 2019-03-19 16:10:29
 */
@MapperDefinition(domainClass = UcenterAlipaySett.class, orderBy = " update_time DESC")
public interface UcenterAlipaySettDao extends BaseDao<UcenterAlipaySett> {

}