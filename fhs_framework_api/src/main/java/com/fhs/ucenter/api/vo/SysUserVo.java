package com.fhs.ucenter.api.vo;

import com.fhs.core.base.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 返回后端用户的vo
 * @author  zhangqiang
 * @version  [版本号, 2018/08/23 10:54:28]
 * @versio  1.0
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("后端用户信息VO")
public class SysUserVo extends BaseVo {

    /**
     * @desc 用户id
     */
    private String userId;
    /**
     * @desc 用户登录名
     */
    private String userLoginName;
    /**
     * @desc 用户姓名
     */
    private String userName;
    /**
     * @desc 手机号
     */
    private String phoneNum;
    /**
     * @desc 邮箱
     */
    private String email;

    /**
     * @desc 城市编码
     */
    private String cityCode;

    /**
     * 组织机构id
     */
    private String organizationId;

    /**
     * 是否超管 0:否 1:是
     */
    private Integer isAdmin;

    /**
     * 集团编码-saas模式适用
     */
    private String groupCode;

    /**
     * 是否启用
     */
    private Integer isDisable;

    /**
     * 密码
     */
    private String  password;
}
