/*
 * 文 件 名:  AdminMenu.java
 * 版    权:  sxpartner Technology International Ltd.
 * 描    述:  &lt;描述&gt;.
 * 修 改 人:  wanglei
 * 修改时间:  ${date}
 * 跟踪单号:  &lt;跟踪单号&gt;
 * 修改单号:  &lt;修改单号&gt;
 * 修改内容:  &lt;修改内容&gt;
 */
package com.fhs.ucenter.api.vo;

import com.fhs.core.base.vo.BaseVo;
import com.fhs.core.group.Add;
import com.fhs.core.group.Delete;
import lombok.Data;
import net.sf.jsqlparser.statement.update.Update;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * &lt;描述一下Bean&gt;
 *
 * @author 朱俊
 * @version [版本号, 2015/08/13 11:37:31]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
public class SysMenuVo extends BaseVo
{

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    @Id
    @NotNull(message = "{test.menuId.null}", groups = {Update.class, Delete.class})
    @Max(message = "{test.menuId.max}", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "{test.menuId.min}", value = 0, groups = {Add.class, Update.class})
    private Integer menuId;


    /**
     * 菜单名称
     */
    @NotNull(message = "{test.menuName.null}", groups = {Update.class, Add.class})
    @Length(message = "{test.menuName.length}", max = 20, min = 0)
    private String menuName;

    /**
     * 父菜单id
     */
    @NotNull(message = "{test.fatherMenuId.null}", groups = {Update.class, Add.class})
    @Max(message = "{test.fatherMenuId.max}", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "{test.fatherMenuId.min}", value = -2147483648, groups = {Add.class, Update.class})
    private Integer fatherMenuId;

    /**
     * 服务器地址
     */
    private String serverUrl;

    /**
     * 链接地址
     */
    private String menuUrl;

    /**
     * Namespace
     */

    private String namespace;

    /**
     * 菜单等级-1根0一级1二级2三级....
     */
    private Integer menuLevel;

    /**
     * 所属系统
     */
    private String systemId;






    /**
     * 是否禁用 0:启用 1:禁用
     */
    @NotNull(message = "{test.isDisable.null}", groups = {Update.class, Add.class})
    @Max(message = "{test.isDisable.max}", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "{test.isDisable.min}", value = -2147483648, groups = {Add.class, Update.class})
    private Integer isDisable;

}
