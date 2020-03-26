/*
 * 文 件 名:  AdminMenuButton.java
 * 版    权:  sxpartner Technology International Ltd.
 * 描    述:  &lt;描述&gt;.
 * 修 改 人:  wanglei
 * 修改时间:  ${date}
 * 跟踪单号:  &lt;跟踪单号&gt;
 * 修改单号:  &lt;修改单号&gt;
 * 修改内容:  &lt;修改内容&gt;
 */
package com.fhs.basics.dox;

import com.fhs.common.utils.EncryptUtils;
import com.fhs.core.base.dox.BaseDO;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
import com.fhs.core.valid.group.Add;
import com.fhs.core.valid.group.Delete;
import com.fhs.core.valid.group.Update;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * &lt;描述一下Bean&gt;
 *
 * @author 朱俊
 * @version [版本号, 2015/08/13 11:37:45]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_ucenter_ms_menu_permission")
public class SysMenuPermissionDO extends BaseDO<SysMenuPermissionDO> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 按钮id
     */
    @Id
    @NotNull(message = "{test.permissionId.null}", groups = {Update.class, Delete.class})
    @Max(message = "{test.permissionId.max}", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "{test.permissionId.min}", value = -2147483648, groups = {Add.class, Update.class})
    private Integer permissionId;
    /**
     * 加密主键
     */
    @NotNull(message = "{test.permissionIdE.null}")
    @Length(message = "{test.permissionIdE.length}", max = 10, min = 0)
    @Transient
    private String permissionIdE;
    /**
     * 按钮名称
     */
    @NotNull(message = "{test.permissionName.null}", groups = {Update.class, Add.class})
    @Length(message = "{test.permissionName.length}", max = 64, min = 0)
    private String permissionName;

    private String method;
    /**
     * 菜单id
     */
    @NotNull(message = "{test.menuId.null}", groups = {Update.class, Add.class})
    @Max(message = "{test.menuId.max}", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "{test.menuId.min}", value = -2147483648, groups = {Add.class, Update.class})
    private Integer menuId;
    /**
     * 是否禁用 0:启用 1:禁用
     */
    @NotNull(message = "{test.isDisable.null}", groups = {Update.class, Add.class})
    @Max(message = "{test.isDisable.max}", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "{test.isDisable.min}", value = -2147483648, groups = {Add.class, Update.class})
    @Trans(type = TransType.WORD_BOOK,key = "yesOrNo")
    private Integer isEnable;
    /**
     * 按钮类型12345，查询，添加，修改，删除，其他
     */
    @NotNull(message = "{test.permissionType.null}", groups = {Update.class, Add.class})
    @Max(message = "{test.permissionType.max}", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "{test.permissionType.min}", value = -2147483648, groups = {Add.class, Update.class})
    private Integer permissionType;

    /**
     * 状态
     */
    @Transient
    private String state;



}
