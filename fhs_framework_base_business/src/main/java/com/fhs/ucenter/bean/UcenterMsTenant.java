package com.fhs.ucenter.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fhs.core.base.bean.BaseDO;
import com.fhs.core.group.Add;
import com.fhs.core.group.Delete;
import com.fhs.core.group.Update;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 租户管理(UcenterMsTenant)实体类
 *
 * @author jackwong
 * @since 2019-05-15 14:21:04
 */
@Data
@Builder
@TableName("t_ucenter_ms_tenant")
public class UcenterMsTenant extends BaseDO<UcenterMsTenant> {
    private static final long serialVersionUID = -45809111607636131L;

    @TableField("group_code")
    private String groupCode;

    //租户集团编码
    @NotEmpty
    @NotNull(message = "租户集团编码字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "租户集团编码字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    //租户名称
    @NotEmpty
    @NotNull(message = "租户名称字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "租户名称字段的长度最大为100", groups = {Add.class, Update.class}, max = 100)
    @TableField("tenant_name")
    private String tenantName;

    //联系人
    @Length(message = "联系人字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("contacts")
    private String contacts;

    //电话
    @Length(message = "电话字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("mobile")
    private String mobile;

    @TableField("logo")
    private String logo;

    @TableField("remark")
    private String remark;

    @TableField("email")
    private String email;

    @TableField("system_ids")
    private String systemIds;


    public UcenterMsTenant() {
    }

    public UcenterMsTenant(String groupCode, String id, String tenantName, String contacts, String mobile, String logo, String remark, String email, String systemIds) {
        this.groupCode = groupCode;
        this.id = id;
        this.tenantName = tenantName;
        this.contacts = contacts;
        this.mobile = mobile;
        this.logo = logo;
        this.remark = remark;
        this.email = email;
        this.systemIds = systemIds;
    }
}