package com.fhs.ucenter.action;


import com.fhs.base.action.ModelSuperAction;
import com.fhs.common.bean.ComboboxNode;
import com.fhs.ucenter.api.vo.SysUserVo;
import com.fhs.ucenter.bean.SysSystem;
import com.fhs.ucenter.bean.SysUser;
import com.fhs.ucenter.service.SysSystemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: 子系统管理
 * @author  qixiaobo
 * @version [版本号, 2018-09-26]
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2018 All Rights Reserved.
 */
@RestController
@RequestMapping("ms/sysSystem")
public class MsSystemAction extends ModelSuperAction<SysSystem> {

    @Autowired
    private SysSystemService sysSystemService;

    /**
     * 获取子系统combobox格式数据
     * @return combobox格式数据列表
     */
    @RequestMapping("/getSystemComBoxData")
    public List<ComboboxNode> getSystemComBoxData(){
        return sysSystemService.getSystemComBoxData();
    }

    /**
     * 查询当前用户拥有权限的子系统列表
     * @param request 请求
     * @return 子系统列表
     */
    @RequestMapping("/getSystemList")
    public List<SysSystem> getSystemList(HttpServletRequest request)
    {
        SysUserVo sessionUser = super.getSessionuser(request);
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sessionUser,sysUser);
        return sysSystemService.getSystemList(sysUser);
    }

    /**
     * 根据id获取对象数据
     * @param id id
     * @param request request request
     * @return 子系统信息
     * @throws Exception
     */
    @RequestMapping("systemInfo/{id}")
    @ResponseBody
    public SysSystem systemInfo(@PathVariable("id") String id, HttpServletRequest request) throws Exception {
        return super.info(id,request);
    }
}
