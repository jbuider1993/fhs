package com.fhs.basics.controller;


import com.fhs.basics.dox.SettMsSystemDO;
import com.fhs.basics.service.SettMsSystemService;
import com.fhs.basics.vo.SettMsSystemVO;
import com.fhs.basics.vo.UcenterMsUserVO;
import com.fhs.module.base.controller.ModelSuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author qixiaobo
 * @version [版本号, 2018-09-26]
 * @Description: 子系统管理
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2018 All Rights Reserved.
 */
@RestController
@RequestMapping("ms/sett_ms_system")
public class SettMsSystemController extends ModelSuperController<SettMsSystemVO, SettMsSystemDO> {

    @Autowired
    private SettMsSystemService sysSystemService;


    /**
     * 查询当前用户拥有权限的子系统列表
     *
     * @param request 请求
     * @return 子系统列表
     */
    @RequestMapping("/getSystemList")
    public List<SettMsSystemVO> getSystemList(HttpServletRequest request) {
        UcenterMsUserVO sessionUser = super.getSessionuser();
        return sysSystemService.getSystemList(sessionUser);
    }


}
