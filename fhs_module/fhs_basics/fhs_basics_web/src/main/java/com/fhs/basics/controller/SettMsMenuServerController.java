package com.fhs.basics.controller;


import com.fhs.basics.dox.SettMsMenuServerDO;
import com.fhs.basics.vo.SettMsMenuServerVO;
import com.fhs.common.utils.JsonUtils;
import com.fhs.module.base.controller.ModelSuperController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微服务管理
 *
 * @author jianbo.qin
 * @version [版本号, 2018-06-01]
 * @Description:
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
@RestController
@RequestMapping("ms/sett_ms_menu_server")
public class SettMsMenuServerController extends ModelSuperController<SettMsMenuServerVO, SettMsMenuServerDO> {
    /**
     * 获取服务列表for jsonp接口
     * @param request  request
     * @param response response
     */
    @RequestMapping("getMenuServersJsonp")
    public void getMenuServersJsonp(HttpServletRequest request, HttpServletResponse response) {
        super.outJsonp(JsonUtils.list2json(super.getBaseService().findForList(new SettMsMenuServerVO())));
    }

}

