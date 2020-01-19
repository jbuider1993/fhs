package com.fhs.demo.dao;

import com.fhs.demo.bean.ParkParking;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.fhs.core.base.dao.BaseDao;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 停车场表(ParkParking)表数据库访问层
 *
 * @author sb生成的代码
 * @since 2020-01-19 20:21:07
 */
@MapperDefinition(domainClass = ParkParking.class, orderBy = " update_time DESC")
public interface ParkParkingDao extends BaseDao<ParkParking> {

}