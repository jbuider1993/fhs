package com.fhs.core.tags;

import com.fhs.core.config.EConfig;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * 列表操作标签
 * @author qh
 */
public class BtnOperationTag extends BaseFormTag {

    /**
     * 是否编辑操作
     * 默认:存在
     */
    private boolean showEdit = true;

    /**
     * 是否查看操作
     * 默认:存在
     */
    private boolean showView = true;

    /**
     * 是否删除操作
     * 默认:存在
     */
    private boolean showDel = true;

    /**
     * 其他菜单1是否存在
     * 默认 false
     */
    private boolean showOtherOpt1 = false;

    /**
     * 其他菜单1权限
     * 默认 see
     */
    private String otherOptPermission1 = "update";

    /**
     * 其他菜单1方法名称
     * 默认 see
     */
    private String otherOptFuncName1;

    /**
     * 其他菜单1名称
     * 默认 see
     */
    private String otherOptName1;

    /**
     * 其他菜单2是否存在
     * 默认 false
     */
    private boolean showOtherOpt2 = false;

    /**
     * 其他菜单2权限
     * 默认 see
     */
    private String otherOptPermission2 = "see";

    /**
     * 其他菜单2方法名称
     * 默认 see
     */
    private String otherOptFuncName2;

    /**
     * 其他菜单2名称
     * 默认 see
     */
    private String otherOptName2;

    /**
     * 对齐方式
     * 默认:居中对齐
     */
    private String alignWay = "center";

    /**
     * th长度
     */
    private String thWidth = "10%";

    /**
     *  删除请求地址
     */
    private String delReqUrl;

    /**
     * 当前列表主键id
     * 默认：id
     */
    private String pkField = "id";

    /**
     * 当前action请求的nameSpace
     */
    private String nameSpace;

    /**
     * 删除方法名称
     * 默认:pubDel_v2
     */
    private String delFuncName = "pubDel_v2";

    /**
     * 详情方法名称
     * 默认：view
     */
    private String viewFuncName = "view";

    /**
     * 更新方法名称
     * 默认：update
     */
    private String updateFuncName = "update";

    @Override
    public void doTag() throws JspException, IOException {
        String thHtmlInsert = "<th align='{1}' field='{2}' width='{3}' sortable='false' formatter='list_formatter'>操作</th>";
        thHtmlInsert = thHtmlInsert.replace ( "{1}", this.alignWay );
        thHtmlInsert = thHtmlInsert.replace ( "{2}", this.pkField );
        thHtmlInsert = thHtmlInsert.replace ( "{3}", this.thWidth );
        write(thHtmlInsert);

        String write_del_html = "";
        String write_view_html =  "";
        String write_edit_html = "";
        String write_other1_html = "";
        String write_other2_html = "";
        Subject subject = SecurityUtils.getSubject();
        if (this.showDel && subject.isPermitted(this.nameSpace + ":" + "del")){
            String delBtn = "" +this.delFuncName+ "(\\'" + EConfig.getPathPropertiesValue("basePath") + this.delReqUrl+ "' + val + '\\')";
            write_del_html = "+ '<a href=\"javascript:void(0)\" onclick=\"" + delBtn + "\" class=\"easyui-linkbutton linkbutton-del\"   plain=\"true\">删除</a>'";
        };

        if (this.showView && subject.isPermitted(this.nameSpace + ":" + "see")){
            String viewBtn = ""+ this.viewFuncName + "(\\'' + val + '\\', \\'' + rowIndex + '\\')";
            write_view_html =  "+ '<a href=\"javascript:void(0)\" onclick=\"" + viewBtn + "\" class=\"easyui-linkbutton linkbutton-view\" plain=\"true\">查看</a>'";
        };

        if (this.showEdit && subject.isPermitted(this.nameSpace + ":" + "update")){
            String editBtn = "" + this.updateFuncName + "(\\'' + val + '\\', \\'' + rowIndex + '\\')";
            write_edit_html = "+ '<a href=\"javascript:void(0)\" onclick=\"" + editBtn + "\" class=\"easyui-linkbutton linkbutton-update\" plain=\"true\">编辑</a>'";
        };

        if (this.showOtherOpt1 && subject.isPermitted(this.nameSpace + ":" + this.otherOptPermission1)){
            String otherBtn1 = "" + this.otherOptFuncName1 + "(\\'' + val + '\\', \\'' + rowIndex + '\\')";
            write_other1_html = "+ '<a href=\"javascript:void(0)\" onclick=\"" + otherBtn1 + "\" class=\"easyui-linkbutton\" style=\"background-color: #9a9eec !important;\" plain=\"true\">"+this.otherOptName1+"</a>'";
        };
        if (this.showOtherOpt2 && subject.isPermitted(this.nameSpace + ":" + this.otherOptPermission2)){
            String otherBtn2 = "" + this.otherOptFuncName2 + "(\\'' + val + '\\', \\'' + rowIndex + '\\')";
            write_other2_html = "+ '<a href=\"javascript:void(0)\" onclick=\"" + otherBtn2 + "\" class=\"easyui-linkbutton\" style=\"background-color: #c79ed7 !important;\" plain=\"true\">"+this.otherOptName2+"</a>'";
        };


        String write_html = "'<div id=\"opreatorFormatter\">' \r\n"
                + "@write_other1_html \r\n@write_other2_html \r\n"
                + "@write_edit_html \r\n@write_view_html \r\n@write_del_html \r\n"
                + "+ \"</div>\"";
        write_html = write_html.replace ( "@write_other1_html", write_other1_html );
        write_html = write_html.replace ( "@write_other2_html", write_other2_html );
        write_html = write_html.replace ( "@write_edit_html", write_edit_html );
        write_html = write_html.replace ( "@write_view_html", write_view_html );
        write_html = write_html.replace ( "@write_del_html", write_del_html );

        String scriptHtmlInsert = "<script type='text/javascript'>function list_formatter(val,row,rowIndex){ \r\n var _html = @content; \r\n return _html; \r\n }</script>";
        scriptHtmlInsert = scriptHtmlInsert.replace ("@content", write_html );
        write(scriptHtmlInsert);
    }

    public boolean isShowEdit() {
        return showEdit;
    }

    public void setShowEdit(boolean showEdit) {
        this.showEdit = showEdit;
    }

    public boolean isShowView() {
        return showView;
    }

    public void setShowView(boolean showView) {
        this.showView = showView;
    }

    public boolean isShowDel() {
        return showDel;
    }

    public void setShowDel(boolean showDel) {
        this.showDel = showDel;
    }

    public String getAlignWay() {
        return alignWay;
    }

    public void setAlignWay(String alignWay) {
        this.alignWay = alignWay;
    }

    public String getThWidth() {
        return thWidth;
    }

    public void setThWidth(String thWidth) {
        this.thWidth = thWidth;
    }

    public String getDelReqUrl() {
        return delReqUrl;
    }

    public void setDelReqUrl(String delReqUrl) {
        this.delReqUrl = delReqUrl;
    }

    public String getPkField() {
        return pkField;
    }

    public void setPkField(String pkField) {
        this.pkField = pkField;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getDelFuncName() {
        return delFuncName;
    }

    public void setDelFuncName(String delFuncName) {
        this.delFuncName = delFuncName;
    }

    public String getViewFuncName() {
        return viewFuncName;
    }

    public void setViewFuncName(String viewFuncName) {
        this.viewFuncName = viewFuncName;
    }

    public String getUpdateFuncName() {
        return updateFuncName;
    }

    public void setUpdateFuncName(String updateFuncName) {
        this.updateFuncName = updateFuncName;
    }

    public boolean isShowOtherOpt1() {
        return showOtherOpt1;
    }

    public void setShowOtherOpt1(boolean showOtherOpt1) {
        this.showOtherOpt1 = showOtherOpt1;
    }

    public String getOtherOptPermission1() {
        return otherOptPermission1;
    }

    public void setOtherOptPermission1(String otherOptPermission1) {
        this.otherOptPermission1 = otherOptPermission1;
    }

    public boolean isShowOtherOpt2() {
        return showOtherOpt2;
    }

    public void setShowOtherOpt2(boolean showOtherOpt2) {
        this.showOtherOpt2 = showOtherOpt2;
    }

    public String getOtherOptPermission2() {
        return otherOptPermission2;
    }

    public void setOtherOptPermission2(String otherOptPermission2) {
        this.otherOptPermission2 = otherOptPermission2;
    }

    public String getOtherOptFuncName1() {
        return otherOptFuncName1;
    }

    public void setOtherOptFuncName1(String otherOptFuncName1) {
        this.otherOptFuncName1 = otherOptFuncName1;
    }

    public String getOtherOptFuncName2() {
        return otherOptFuncName2;
    }

    public void setOtherOptFuncName2(String otherOptFuncName2) {
        this.otherOptFuncName2 = otherOptFuncName2;
    }

    public String getOtherOptName1() {
        return otherOptName1;
    }

    public void setOtherOptName1(String otherOptName1) {
        this.otherOptName1 = otherOptName1;
    }

    public String getOtherOptName2() {
        return otherOptName2;
    }

    public void setOtherOptName2(String otherOptName2) {
        this.otherOptName2 = otherOptName2;
    }
}
