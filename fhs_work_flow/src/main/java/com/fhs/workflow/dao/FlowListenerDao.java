package com.fhs.workflow.dao;

import com.fhs.workflow.bean.FlowListener;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.fhs.core.base.dao.BaseDao;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 监听器(FlowListener)表数据库访问层
 *
 * @author sb生成的代码
 * @since 2019-11-11 14:28:44
 */
@MapperDefinition(domainClass = FlowListener.class, orderBy = " update_time DESC")
public interface FlowListenerDao extends BaseDao<FlowListener> {

}