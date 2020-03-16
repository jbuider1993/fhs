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
package com.fhs.ucenter.dox;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fhs.common.utils.CheckUtils;
import com.fhs.core.base.dox.BaseDO;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.valid.group.Add;
import com.fhs.core.valid.group.Delete;
import com.fhs.ucenter.constant.BaseTransConstant;
import lombok.Data;
import net.sf.jsqlparser.statement.update.Update;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * &lt;描述一下Bean&gt;
 *
 * @author 朱俊
 * @version [版本号, 2015/08/13 11:37:31]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Data
@Table(name = "t_ucenter_ms_menu")
@TransTypes(types={BaseTransConstant.WORD_BOOK})
public class SysMenuDO extends BaseDO<SysMenuDO>
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
    @TableField(exist=false)
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
    @TableField(exist=false)
    private Integer menuLevel;

    /**
     * 所属系统
     */
    private String systemId;


    /**
     * 菜单类型0 物业公司菜单1 物业集团菜单2 平台菜单
     */
    @Trans(type=BaseTransConstant.WORD_BOOK,key="menu_type")
    private String menuType;


    @TableField(exist=false)
    private String[] menuTypes;

    /**
     * 适配的主页菜单
     */
    @TableField(exist=false)
    private String configurationHomeMenu;

    @TableField(exist=false)
    private String[] configurationHomeMenus;




    public String[] getConfigurationHomeMenus()
    {
        return configurationHomeMenus;
    }

    public void setConfigurationHomeMenus(String[] configurationHomeMenus)
    {
        this.configurationHomeMenus = configurationHomeMenus;
    }

    public String getConfigurationHomeMenu()
    {
        return configurationHomeMenu;
    }

    public void setConfigurationHomeMenu(String configurationHomeMenu)
    {
        this.configurationHomeMenu = configurationHomeMenu;
        if (CheckUtils.isNullOrEmpty(configurationHomeMenu))
        {
            configurationHomeMenus = new String[0];
            return;
        }
        this.configurationHomeMenus = configurationHomeMenu.split(",");
    }

    /**
     * 是否禁用 0:启用 1:禁用
     */
    @NotNull(message = "{test.isDisable.null}", groups = {Update.class, Add.class})
    @Max(message = "{test.isDisable.max}", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "{test.isDisable.min}", value = -2147483648, groups = {Add.class, Update.class})
    private Integer isDisable;


    private String checkHelpUrl;

    /**
     * 子菜单
     */
    @TableField(exist=false)
    private List<SysMenuDO> sonMenu;

    private Integer menuState;

    public List<SysMenuDO> getSonMenu()
    {
        if (sonMenu == null)
        {
            sonMenu = new ArrayList<>();
        }
        return sonMenu;
    }

    private String image;

    /**
     * 是否关注
     */
    private String isAttention;

    private Integer serverNameId = 1;

    private Integer orderIndex;
    /**
     * 给菜单类型0 运营菜单 1 园区菜单
     */
    public void setMenuType(String menuType)
    {
        this.menuType = menuType;
        if (CheckUtils.isNullOrEmpty(menuType))
        {
            menuTypes = new String[0];
            return;
        }
        this.menuTypes = menuType.split(",");
    }



}
