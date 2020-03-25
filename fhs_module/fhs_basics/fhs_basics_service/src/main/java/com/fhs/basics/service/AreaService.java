package com.fhs.basics.service;

import com.fhs.basics.dox.AreaDO;
import com.fhs.basics.vo.AreaVO;
import com.fhs.core.base.service.BaseService;

import java.util.Map;


/**
 * 省市区字典
 *
 * @Filename: AreaService.java
 * @Description:
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */

public interface AreaService extends BaseService<AreaVO, AreaDO>
{

    /**
     * 返回完整的省市区地址
     * map参数：provinceId，cityId，areaId
     *
     */
    String findAddressById(Map<String, Object> map);


}