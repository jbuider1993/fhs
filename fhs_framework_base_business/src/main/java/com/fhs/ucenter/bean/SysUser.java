/*
 * 文 件 名:  AdminUser.java
 * 版    权:  sxpartner Technology International Ltd.
 * 描    述:  &lt;描述&gt;.
 * 修 改 人:  wanglei
 * 修改时间:  ${date}
 * 跟踪单号:  &lt;跟踪单号&gt;
 * 修改单号:  &lt;修改单号&gt;
 * 修改内容:  &lt;修改内容&gt;
 */
package com.fhs.ucenter.bean;

import com.mybatis.jpa.annotation.Like;
import com.mybatis.jpa.annotation.RLike;
import com.fhs.common.constant.Constant;
import com.fhs.core.base.bean.BaseDO;
import com.fhs.core.group.Add;
import com.fhs.core.group.Delete;
import com.fhs.core.group.Update;
import com.fhs.core.trans.Trans;
import com.fhs.core.trans.TransTypes;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * &lt;描述一下Bean&gt;
 *
 * @author 朱俊
 * @version [版本号, 2015/08/13 11:31:39]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Data
@Builder
@Table(name = "t_ucenter_ms_user")
@TransTypes(types = {Constant.WORD_BOOK, Constant.SYS_ORGANIZATION_INFO})
public class SysUser extends BaseDO<SysUser> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @Id
    @NotNull(message = "{test.userId.null}", groups = {Update.class, Delete.class})
    @Column(name = "user_id")
    private String userId;

    /**
     * 加密主键
     */

    @Transient
    private String userIdE;

    /**
     * 登录名
     */
    @NotNull(message = "{test.userLoginName.null}", groups = {Update.class, Add.class})
    @Length(message = "{test.userLoginName.length}", max = 20, min = 0)
    @Column(name = "user_login_name")
    private String userLoginName;

    /**
     * 用户的名字
     */
    @NotNull(message = "{test.userName.null}", groups = {Update.class, Add.class})
    @Length(message = "{test.userName.length}", max = 20, min = 0)
    @Like
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 手机号
     */
    @NotNull(message = "{test.mobile.null}", groups = {Update.class, Add.class})
    @Column(name = "mobile")
    private String mobile;

    /**
     * 集团编码-saas模式适用
     */
    @Column(name = "group_code")
    private String groupCode;

    /**
     * 邮箱
     */
    @NotNull(message = "{test.email.null}", groups = {Update.class, Add.class})
    @Length(message = "{test.email.length}", max = 255, min = 0)
    @Column(name = "email")
    private String email;

    /**
     * 是否禁用 0:启用 1:禁用
     */
    @NotNull(message = "{test.isDisable.null}", groups = {Update.class, Add.class})
    @Max(message = "{test.isDisable.max}", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "{test.isDisable.min}", value = -2147483648, groups = {Add.class, Update.class})
    @Trans(type = Constant.WORD_BOOK, key = "is_disable")
    private Integer isDisable;

    /**
     * 是否超管 0:否 1:是
     */
    private Integer isAdmin;

    /**
     * 省
     */
    @Length(message = "的provinceId字段的长度最大为64", groups = {Add.class, Update.class}, max = 64, min = 0)
    @Column(name = "province_id", nullable = true, length = 64)
    private String provinceId;

    /**
     * 市
     */
    @Length(message = "的cityId字段的长度最大为64", groups = {Add.class, Update.class}, max = 64, min = 0)
    @Column(name = "city_id", nullable = true, length = 64)
    private String cityId;

    /**
     * 区
     */
    @Length(message = "的areaId字段的长度最大为64", groups = {Add.class, Update.class}, max = 64, min = 0)
    @Column(name = "area_id", nullable = true, length = 64)
    private String areaId;

    /**
     * 地址
     */
    @NotNull(message = "{test.address.null}", groups = {Update.class, Add.class})
    @Length(message = "{test.address.length}", max = 200, min = 0)
    private String address;

    /**
     * 性别
     */
    @Max(message = "前端用户的sex字段大于int最大值", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "前端用户的sex字段小于int小值", value = -2147483648, groups = {Add.class, Update.class})
    @Column(name = "sex", length = 11)
    @Trans(type = Constant.WORD_BOOK, key = "sex")
    private Integer sex;

    /**
     * 组织机构ID
     */
    @Length(message = "{test.organizationId.length}", groups = {Add.class, Update.class}, max = 32, min = 0)
    @NotNull(message = "{test.organizationId.null}", groups = {Update.class, Add.class})
    @RLike
    @Trans(type = Constant.SYS_ORGANIZATION_INFO, key = Constant.SYS_ORGANIZATION_NAME)
    private String organizationId;

    /**
     * 状态
     */
    @Transient
    private String state;

    /**
     * 当前用户的角色
     */
    @Transient
    private String[] roleList;

    /**
     * 原始密码
     */
    @Transient
    private String oldPassword;

    /**
     * 新密码
     */
    @Transient
    private String newPassword;

    @Transient
    private Integer menuType;

    @Transient
    private List<SysRole> roles;

    /**
     * 验证码
     */
    @Transient
    private String checkCode;

    public SysUser() {
    }

    public SysUser(String userId, String userIdE, String userLoginName, String userName, String password, String mobile, String groupCode, String email, Integer isDisable, Integer isAdmin, String provinceId, String cityId, String areaId, String address, Integer sex, String organizationId, String state, String[] roleList, String oldPassword, String newPassword, Integer menuType, List<SysRole> roles, String checkCode) {
        this.userId = userId;
        this.userIdE = userIdE;
        this.userLoginName = userLoginName;
        this.userName = userName;
        this.password = password;
        this.mobile = mobile;
        this.groupCode = groupCode;
        this.email = email;
        this.isDisable = isDisable;
        this.isAdmin = isAdmin;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.areaId = areaId;
        this.address = address;
        this.sex = sex;
        this.organizationId = organizationId;
        this.state = state;
        this.roleList = roleList;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.menuType = menuType;
        this.roles = roles;
        this.checkCode = checkCode;
    }
}
