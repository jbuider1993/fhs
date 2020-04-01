package com.fhs.basics.service;

import com.fhs.basics.dox.ServiceAreaDO;
import com.fhs.basics.vo.ServiceAreaVO;
import com.fhs.core.base.service.BaseService;

import java.util.Map;


/**
 * 省市区字典
 *
 * @Filename: ServiceAreaService.java
 * @Description:
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br> 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 */

public interface ServiceAreaService extends BaseService<ServiceAreaVO, ServiceAreaDO> {

    /**
     * 返回完整的省市区地址
     * map参数：provinceId，cityId，areaId
     */
    String findAddressById(Map<String, Object> map);

    /**
     * 刷新区域缓存
     */
    void refreshRedisCache();


}