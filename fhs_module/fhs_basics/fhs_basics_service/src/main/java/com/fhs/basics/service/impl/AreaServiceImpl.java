package com.fhs.basics.service.impl;

import com.fhs.basics.constant.BaseTransConstant;
import com.fhs.basics.dox.AreaDO;
import com.fhs.basics.service.AreaService;
import com.fhs.basics.vo.AreaVO;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.cache.service.RedisCacheService;
import com.fhs.core.db.ds.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 省市区字典
 *
 * @Filename: AreaServiceImpl.java
 * @Description:
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
@Service
@DataSource("base_business")
public class AreaServiceImpl extends BaseServiceImpl<AreaVO, AreaDO> implements AreaService
{
    /**
     *redis缓存服务
     */
    @Autowired
    private RedisCacheService<String> redisCacheService;

    @Override
    public String findAddressById(Map<String, Object> map)
    {
        String address = null;
        String provinceId = (String)map.get("provinceId");
        if (!CheckUtils.isNullOrEmpty(provinceId))
        {
            address = super.findBeanById(provinceId).getAreaName();
        }
        String cityId = (String)map.get("cityId");
        if (!CheckUtils.isNullOrEmpty(cityId))
        {
            address = address + super.findBeanById(cityId).getAreaName();
        }
        String areaId = (String)map.get("areaId");
        if (!CheckUtils.isNullOrEmpty(areaId))
        {
            address = address + super.findBeanById(areaId).getAreaName();
        }
        return address;
    }

    @Override
    public void refreshRedisCache() {
        List<AreaVO> areaList = this.select();
        areaList.forEach(area -> {
            if(!StringUtil.isEmpty(area.getAreaName())){
                try {
                    redisCacheService.remove(BaseTransConstant.AREA_NAME + area.getId());
                    redisCacheService.addStr(BaseTransConstant.AREA_NAME + area.getId(), area.getAreaName());
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }


}