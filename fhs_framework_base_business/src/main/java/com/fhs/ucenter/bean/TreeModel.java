package com.fhs.ucenter.bean;

import java.io.Serializable;
import java.util.Map;

public class TreeModel implements Serializable {

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
    private TreeNodeStyle font;
    /*节点类型*/
    private String nodeType;
    /*链接地址**/
    private String menuurl;
    /*如果某节点不显示 radio，请设置 treeNode.chkDisabled */
    /*true:禁用/默认状态为启用此radio*/
    private boolean chkDisabled;

    private String menuType;


    public String getMenuurl()
    {
        return menuurl;
    }

    public void setMenuurl(String menuurl)
    {
        this.menuurl = menuurl;
    }

    public String getMenuType()
    {
        return menuType;
    }

    public void setMenuType(String menuType)
    {
        this.menuType = menuType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String get_parentId() {
        return _parentId;
    }

    public void set_parentId(String _parentId) {
        this._parentId = _parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isNoselect() {
        return noselect;
    }

    public void setNoselect(boolean noselect) {
        this.noselect = noselect;
    }

    public boolean isIsparent() {
        return isparent;
    }

    public void setIsparent(boolean isparent) {
        this.isparent = isparent;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public TreeNodeStyle getFont() {
        return font;
    }

    public void setFont(TreeNodeStyle font) {
        this.font = font;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public boolean isChkDisabled() {
        return chkDisabled;
    }

    public void setChkDisabled(boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }

    @Override
    public String toString() {
        return "TreeModel [id=" + id + ", pid=" + pid + ", _parentId="
                + _parentId + ", code=" + code + ", name="
                + name + ", open=" + open + ", checked="
                + checked + ", icon=" + icon + ", noselect="
                + noselect + ", isparent=" + isparent
                + ", attributes=" + attributes + ", font="
                + font + ", nodeType=" + nodeType
                + ", chkDisabled=" + chkDisabled + ", menuurl="+menuurl+"]";
    }


   
}
