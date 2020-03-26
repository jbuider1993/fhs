package com.fhs.basics.controller;


import com.fhs.basics.dox.SysSystemDO;
import com.fhs.basics.service.SysSystemService;
import com.fhs.basics.vo.SysSystemVO;
import com.fhs.basics.vo.SysUserVO;
import com.fhs.module.base.controller.ModelSuperController;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("ms/ucenter_ms_system")
public class MsSystemController extends ModelSuperController<SysSystemVO, SysSystemDO> {

    @Autowired
    private SysSystemService sysSystemService;


    /**
     * 查询当前用户拥有权限的子系统列表
     *
     * @param request 请求
     * @return 子系统列表
     */
    @RequestMapping("/getSystemList")
    public List<SysSystemVO> getSystemList(HttpServletRequest request) {
        SysUserVO sessionUser = super.getSessionuser();
        return sysSystemService.getSystemList(sessionUser);
    }


}
