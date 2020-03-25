package com.fhs.basics.controller;

import com.fhs.basics.dox.AreaDO;
import com.fhs.basics.service.AreaService;
import com.fhs.basics.vo.AreaVO;
import com.fhs.common.utils.JsonUtils;
import com.fhs.core.result.HttpResult;
import com.fhs.module.base.controller.ModelSuperController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 省市区字典action
 *
 * @Filename: AreaAction.java
 * @Description:
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br> 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 */
@RestController
@RequestMapping("ms/area")
public class AreaController extends ModelSuperController<AreaVO, AreaDO> {
    @Autowired
    private AreaService areaService;


    /**
     * 省市区接口,传递areaParentId即可,顶级传递0
     *
     * @param request
     * @param response
     * @param area
     */
    @RequestMapping("getProvinceData")
    public List<AreaVO> getProvinceData(HttpServletRequest request, HttpServletResponse response, AreaVO area) {
        Map<String, Object> map = new HashMap<String, Object>();
        int areaParentId = area.getAreaParentId();
        map.put("areaParentId", areaParentId);
        return areaService.findForListFromMap(map);
    }



}