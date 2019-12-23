package com.fhs.ucenter.dto;

import lombok.Data;

@Data
public class SysUserFlowDto {

    private String userId;

    private String name;    //姓名

    private String roleName;    //角色名称

    private String organName;   //机构名称

    private String mobile;     //手机号码

    private String email;   //邮箱

    public SysUserFlowDto() {
    }

    public SysUserFlowDto(String userId, String name, String roleName, String organName, String mobile, String email) {
        this.userId = userId;
        this.name = name;
        this.roleName = roleName;
        this.organName = organName;
        this.mobile = mobile;
        this.email = email;
    }
}
