package com.fhs.system.action;

import com.alibaba.druid.support.json.JSONUtils;
import com.fhs.common.utils.JsonUtils;
import com.fhs.core.base.action.BaseAction;
import com.fhs.system.bean.Area;
import com.fhs.system.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("webApi/area")
public class AreaOpenWebApiAction extends BaseAction<Area> {

    @Autowired
    private AreaService areaService;

    /**
     * 省市区接口,传递areaParentId即可,顶级传递0
     * @param request
     * @param response
     * @param area
     */
    @RequestMapping("getProvinceData")
    @ResponseBody
    public void getProvinceData(HttpServletRequest request, HttpServletResponse response, Area area)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        int areaParentId = area.getAreaParentId();
        map.put("areaParentId", areaParentId);
        List<Area> areaList = areaService.findForListFromMap(map);
        super.outJsonp(JsonUtils.list2json(areaList), response, request);
    }
    /**
     * 前端浏览器获取省市区的接口
     * @param area 省市区过滤条件
     * @param response response
     * @param request request
     */
    @RequestMapping("getProvinceList")
    public void getProvinceList(Area area, HttpServletResponse response, HttpServletRequest request){

        Map<String, Object> map = new HashMap<String, Object>();
        int areaParentId = area.getAreaParentId();
        map.put("areaParentId", areaParentId);
        List<Area> areaList = areaService.findForListFromMap(map);
        super.outJsonp(JSONUtils.toJSONString(areaList),response,request);
    }
}
