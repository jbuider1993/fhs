package com.fhs.basics.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.fhs.basics.service.ServiceAreaService;
import com.fhs.basics.vo.ServiceAreaVO;
import com.fhs.common.utils.JsonUtils;
import com.fhs.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("webApi/area")
public class ServiceAreaOpenWebApiController extends BaseController {

    @Autowired
    private ServiceAreaService areaService;

    /**
     * 省市区接口,传递areaParentId即可,顶级传递0
     *
     * @param request
     * @param response
     * @param area
     */
    @RequestMapping("getProvinceData")
    @ResponseBody
    public void getProvinceData(HttpServletRequest request, HttpServletResponse response, ServiceAreaVO area) {
        Map<String, Object> map = new HashMap<String, Object>();
        int areaParentId = area.getAreaParentId();
        map.put("areaParentId", areaParentId);
        List<ServiceAreaVO> areaList = areaService.findForListFromMap(map);
        super.outJsonp(JsonUtils.list2json(areaList));
    }

    /**
     * 前端浏览器获取省市区的接口
     *
     * @param area 省市区过滤条件
     */
    @RequestMapping("getProvinceList")
    public void getProvinceList(ServiceAreaVO area) {
        List<ServiceAreaVO> areaList = areaService.findForList(area);
        super.outJsonp(JSONUtils.toJSONString(areaList));
    }
}
