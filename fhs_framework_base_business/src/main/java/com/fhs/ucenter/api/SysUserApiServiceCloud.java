package com.fhs.ucenter.api;

import com.beust.jcommander.ParameterException;
import com.fhs.common.utils.CheckUtils;
import com.fhs.core.db.DataSource;
import com.fhs.core.exception.ParamChecker;
import com.fhs.core.page.Pager;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.api.form.SysUserForm;
import com.fhs.ucenter.api.service.FeignSysUserApiService;
import com.fhs.ucenter.api.vo.SysUserVo;
import com.fhs.ucenter.bean.SysUser;
import com.fhs.ucenter.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Filename: SysUserApiServiceCloud.java
 * @Description: 后台用户api接口
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
@RestController
@RequestMapping("api/sysUser")
@DataSource("pagex/ucenter")
public class SysUserApiServiceCloud implements FeignSysUserApiService {

    /**
     * 后台用户服务
     */
    @Autowired
    private SysUserService sysUserService;

    /**
     * 根据用户登录名查询用户
     * @param userLoginName 用户登录名m
     * @return 用户信息
     */
    @GetMapping("/getSysUserByLoginName")
    @Override
    public HttpResult<SysUserVo> getSysUserByName(@RequestParam("userLoginName") String userLoginName) {
        SysUser sysUser = sysUserService.selectBean(new SysUser().mk("userLoginName", userLoginName));
        if (CheckUtils.isNotEmpty(sysUser)){
            SysUserVo result = new SysUserVo();
            BeanUtils.copyProperties(sysUser,result);
            return HttpResult.success(result);
        }
        throw new ParameterException("用户名不存在:" + userLoginName);
    }


    /**
     * 根据用户登录名查询用户权限列表
     * @param userLoginName 用户登录名
     * @return 权限列表
     */
    @GetMapping("/selectMenuByUname")
    @Override
    public HttpResult<List<String>> selectMenuByUname(@RequestParam("userLoginName") String userLoginName) {
        List<String> list = new ArrayList<>();
        list = sysUserService.selectMenuByUname( sysUserService.selectBean(new SysUser().mk("userLoginName",userLoginName)));
        return HttpResult.success(list);
    }

    /**
     * @desc 获取后端用户信息
     * @param sysUserForm 后端用户的form对象
     * @return 处理结果
     */
    @PostMapping("/getSysUserList")
    @Override
    public HttpResult<Pager<SysUserVo>> getSysUserList(@RequestBody SysUserForm sysUserForm) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserForm, sysUser);
        List<SysUser> sysUsersList = this.sysUserService.findForList(sysUser, sysUserForm.getPageStart()-1, sysUserForm.getPageSize());
        if(sysUsersList.size() > 0) {
            List<SysUserVo> sysUserVoList = new ArrayList<>();
            sysUsersList.forEach(sysUserForEach -> {
                SysUserVo sysUserVo = new SysUserVo();
                //正则表达式，替换手机号中间4位
                sysUserForEach.setMobile(sysUserForEach.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
                BeanUtils.copyProperties(sysUserForEach, sysUserVo);
                sysUserVoList.add(sysUserVo);
            });
            int count = this.sysUserService.findCount(sysUser);
            return count > 0 ? HttpResult.success(new Pager<>(count,sysUserVoList)) : HttpResult.success(new Pager<SysUserVo>(0, null));
        }else {
            return HttpResult.success(new Pager<SysUserVo>(0, null));
        }
    }

    /**
     * 根据用户ID获取用户权限URL
     * @param userId 用户ID
     * @return 用户权限URL列表
     */
    @Override
    @GetMapping("/getPermissionUrlByUserId")
    public HttpResult<List<String>> getPermissionUrlByUserId(@RequestParam("userId") String userId) {
        SysUser sysUser = sysUserService.findBeanById(userId);
        if(sysUser == null)
        {
            return HttpResult.error(null, "没有此用户");
        } else {
            return HttpResult.success(sysUserService.getPermissionUrl(sysUser));
        }
    }

    /**
     * 获取用户的数据权限
     * @param userId  用户id
     * @return 数据权限配置
     */
    @GetMapping("/getDataUserPermisstion")
    public HttpResult<Map<String,String>> getDataUserPermisstion(@RequestParam("userId")String userId)
    {
        ParamChecker.isNotNullOrEmpty(userId,"userId不能为空");
        return HttpResult.success(sysUserService.findUserDataPermissions(userId));
    }


    /**
     * @desc 根据userId获取用户信息
     * @param sysUserForm 后端用户form
     * @return 后端用户信息
     */
    @PostMapping("/getSysUserByUserId")
    @Override
    public HttpResult<SysUserVo> getSysUserByUserId(@RequestBody SysUserForm sysUserForm) {
        SysUserVo vo = new SysUserVo();
        if(!CheckUtils.isNullOrEmpty(sysUserForm) && !CheckUtils.isNullOrEmpty(sysUserForm.getUserId())) {
            SysUser sysUser = sysUserService.selectById(sysUserForm.getUserId());
            BeanUtils.copyProperties(sysUser, vo);
        }
        return HttpResult.success(vo);
    }

}
