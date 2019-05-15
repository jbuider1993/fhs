package com.fhs.ucenter.dao;

import com.fhs.ucenter.bean.UcenterMsTenant;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.fhs.core.base.dao.BaseDao;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 租户管理(UcenterMsTenant)表数据库访问层
 *
 * @author makejava
 * @since 2019-05-15 14:21:04
 */
@MapperDefinition(domainClass = UcenterMsTenant.class, orderBy = " update_time DESC")
public interface UcenterMsTenantDao extends BaseDao<UcenterMsTenant> {

}