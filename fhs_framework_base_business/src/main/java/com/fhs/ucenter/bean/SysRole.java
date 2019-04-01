/*
 * 文 件 名:  AdminRole.java
 * 版    权:  sxpartner Technology International Ltd.
 * 描    述:  &lt;描述&gt;.
 * 修 改 人:  wanglei
 * 修改时间:  ${date}
 * 跟踪单号:  &lt;跟踪单号&gt;
 * 修改单号:  &lt;修改单号&gt;
 * 修改内容:  &lt;修改内容&gt;
 */
package com.fhs.ucenter.bean;

import com.fhs.common.constant.Constant;
import com.fhs.common.utils.EncryptUtils;
import com.fhs.core.base.bean.BaseDO;
import com.fhs.core.group.Add;
import com.fhs.core.group.Delete;
import com.fhs.core.group.Update;
import com.fhs.core.trans.Trans;
import com.fhs.core.trans.TransTypes;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * &lt;描述一下Bean&gt;
 *
 * @author 朱俊
 * @version [版本号, 2015/08/13 11:37:58]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_ucenter_ms_role")
@TransTypes(types = {Constant.WORD_BOOK, Constant.USER_INFO, Constant.SYS_ORGANIZATION_INFO})
public class SysRole extends BaseDO<SysRole>
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @Id
    @NotNull(message = "{test.roleId.null}", groups = {Update.class, Delete.class})
    @Max(message = "{test.roleId.max}", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "{test.roleId.min}", value = -2147483648, groups = {Add.class, Update.class})
    private Integer roleId;

    /** 加密主键 */
    @NotNull(message = "{test.roleIdE.null}")
    @Length(message = "{test.roleIdE.length}", max = 10, min = 0)
    @Transient
    private String roleIdE;

    /**
     * 角色名称
     */
    @NotNull(message = "{test.roleName.null}", groups = {Update.class, Add.class})
    @Length(message = "{test.roleName.length}", max = 20, min = 0)
    private String roleName;

    /**
     * 备注
     */
    @NotNull(message = "{test.remark.null}")
    @Length(message = "{test.remark.length}", max = 200, min = 0)
    private String remark;

    /**
     * 是否禁用 0:启用 1:禁用
     */
    @NotNull(message = "{test.isDisable.null}", groups = {Update.class, Add.class})
    @Max(message = "{test.isDisable.max}", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "{test.isDisable.min}", value = -2147483648, groups = {Add.class, Update.class})
    @Trans(type = "wordbook", key = "is_disable")
    private Integer isDisable;

    /**
     * 所属机构
     */
    @NotEmpty
    @NotNull(message="所属机构字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "所属机构字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "organization_id")
    @Trans(type = Constant.SYS_ORGANIZATION_INFO, key = Constant.SYS_ORGANIZATION_NAME)
    private String organizationId;

    /**
     * 数据权限(资源类型，部门及小区)
     */
    @NotEmpty
    @NotNull(message="数据权限(资源类型，部门及小区)字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "数据权限(资源类型，部门及小区)字段的长度最大为500", groups = {Add.class, Update.class}, max = 500)
    @Column(name = "data_permissions")
    private String dataPermissions;



    /**
     * 菜单按钮数据
     */
    @Transient
    private String[] methods;

    public String[] getMethods()
    {
        return methods;
    }

    public void setMethods(String[] methods)
    {
        this.methods = methods;
    }

    /**
     * 状态
     */
    @Transient
    private String state;

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    /** 给主键进行加密 */
    public void setRoleIdE(String roleIdE)
    {
        roleId = EncryptUtils.getDecodeIdFromIdE(roleIdE);
        this.roleIdE = roleIdE;
    }

    /** 获取加密主键 */
    public String getRoleIdE()
    {
        return roleIdE;
    }

    /**
     * 给角色id赋值
     */
    public void setRoleId(Integer roleId)
    {
        roleIdE = EncryptUtils.getEncodeIdEFromId(roleId);
        this.roleId = roleId;
    }

    /**
     * 获取角色id
     */
    public Integer getRoleId()
    {
        return roleId;
    }

    /**
     * 给角色名称赋值
     */
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    /**
     * 获取角色名称
     */
    public String getRoleName()
    {
        return roleName;
    }

    /**
     * 给赋值
     */
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    /**
     * 获取
     */
    public String getRemark()
    {
        return remark;
    }

    /**
     * 给 是否禁用 0:启用 1:禁用 赋值
     */
    public void setIsDisable(Integer isDisable)
    {
        this.isDisable = isDisable;
    }

    /**
     * 获取 是否禁用 0:启用 1:禁用
     */
    public Integer getIsDisable()
    {
        return isDisable;
    }

    /**
     * 获取 所属机构
     * @return
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * 给 所属机构 赋值
     * @param organizationId
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * 获取 数据权限(资源类型，部门及小区)
     * @return
     */
    public String getDataPermissions() {
        return dataPermissions;
    }

    /**
     * 给 数据权限(资源类型，部门及小区) 赋值
     * @param dataPermissions
     */
    public void setDataPermissions(String dataPermissions) {
        this.dataPermissions = dataPermissions;
    }


}
