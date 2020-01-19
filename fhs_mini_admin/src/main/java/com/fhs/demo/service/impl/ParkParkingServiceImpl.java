package com.fhs.demo.service.impl;

import com.fhs.core.trans.AutoTrans;
import com.fhs.demo.bean.ParkParking;
import com.fhs.demo.dao.ParkParkingDao;
import com.fhs.demo.service.ParkParkingService;
import org.springframework.stereotype.Service;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import javax.annotation.Resource;
import java.util.List;

/**
 * 停车场表(ParkParking)表服务实现类
 *
 * @author sb生成的代码
 * @since 2020-01-19 20:21:07
 */
@Service("parkParkingService")
@AutoTrans(namespace = "parking",fields = {"parkName"},useCache = false)
public class ParkParkingServiceImpl extends BaseServiceImpl<ParkParking>   implements ParkParkingService {
    
}