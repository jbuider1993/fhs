package com.fhs.ucenter.api.form;

import com.fhs.core.base.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Description: 获取后端用户form
 * @author  zhangqiang
 * @version  [版本号, 2018/08/23 10:29:28]
 * @versio  1.0
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 **/
@Data
@Builder
@ApiModel("获取后端用户Form")
public class SysUserForm extends BaseForm {


    /**
     * @desc 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    @Length(min = 1)
    @ApiModelProperty(value="用户ID", required = true)
    private String userId;

    /**
     * @desc 用户姓名
     */
    @ApiModelProperty(value="用户姓名")
    private String userName;

    /**
     * @desc 用户手机号
     */
    @ApiModelProperty(value = "用户手机号")
    private String phoneNum;

    /**
     * @desc 查询起始条数
     */
    @ApiModelProperty(value = "查询起始条数")
    private int pageStart;

    /**
     * @desc 查询每页条数
     */
    @NotNull(message = "查询每页条数不能为空")
    @ApiModelProperty(value = "查询每页条数")
    private int pageSize;

    public SysUserForm() {
    }

    public SysUserForm(String userId, String userName, String phoneNum, int pageStart, int pageSize) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNum = phoneNum;
        this.pageStart = pageStart;
        this.pageSize = pageSize;
    }
}
