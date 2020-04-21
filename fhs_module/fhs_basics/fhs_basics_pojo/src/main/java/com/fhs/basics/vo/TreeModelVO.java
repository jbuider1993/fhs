package com.fhs.basics.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * easyui tree
 *
 * @author admin
 */
@Data
public class TreeModelVO implements Serializable {

    private static final long serialVersionUID = -2847892870633694735L;

    /*节点ID*/
    private String id;
    /*父节点ID*/
    private String pid;
    /*EasyUI父节点ID*/
    private String _parentId;
    /*节点编码*/
    private String code;
    /*节点名称*/
    private String name;
    /*节点打开标识*/
    private boolean open = true;
    /*是否选中*/
    private boolean checked = false;
    /*节点图标*/
    private String icon;
    /*节点可选标识*/
    private boolean noselect;
    /*是否存在子节点*/
    private boolean isparent;
    /*附加属性*/
    private Map<String, Object> attributes;
    /*节点字体样式*/
    private TreeNodeStyleVO font;
    /*节点类型*/
    private String nodeType;
    /*链接地址**/
    private String menuurl;
    /*如果某节点不显示 radio，请设置 treeNode.chkDisabled */
    /*true:禁用/默认状态为启用此radio*/
    private boolean chkDisabled;

    private String menuType;


}
