<%@ page language="java" contentType="text/html; charset=UTF-8"
		 isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="/page/ms/common/js_css_include.jsp"></jsp:include>
</head>

<body>
<table id="listGrid" title="子系统列表" class="easyui-datagrid"
	   fit="true" style="width: 90%;" url="${basePath}ms/sysSystem/findPage"
	   pagination="true" rownumbers="true" fitColumns="true"
	   singleSelect="true" pageSize="10" striped="true" toolbar="#toolbar">
	<thead>
		<tr>
			<th align="center" field="name" width="25%">名称</th>
			<th align="center" field="transMap.isDisableName" width="14%">状态</th>
			<th align="center" field="transMap.createUserUserName" width="15%">创建人</th>
			<th align="center" field="createTime" width="15%">创建时间</th>
			<th align="center" field="transMap.updateUserUserName" width="15%">修改人</th>
			<th align="center" field="updateTime" width="15%">修改时间</th>
		</tr>
	</thead>
</table>

<div id="toolbar">
	<div style="margin-bottom: 5px;">
		<shiro:hasPermission name="sysSystem:see">
			<input id="nameF" name="nameF" type="text" placeholder="名称" />
			<select id="isDisableF" name="isDisableF" class="easyui-combobox" prompt="状态"
					data-options="
				valueField: 'wordbookCode',
				textField: 'wordbookDesc',
				editable : false,
				url: '${systemServiceUrl}webApi/wordbook/getData?wordbookGroupCode=is_disable&jsonpCallback=?',
				showAll:true
				">
			</select>
			<a href="javascript:void(0)" class="easyui-linkbutton"
			   iconCls="icon-search" plain="true" onclick="reload()">查询</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sysSystem:add">
			<a href="javascript:void(0)" class="easyui-linkbutton linkbutton-add"
			   iconCls="icon-add" plain="true" onclick="add()">添加</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sysSystem:update">
			<a href="javascript:void(0)" class="easyui-linkbutton linkbutton-update"
			   iconCls="icon-edit" plain="true"
			   onclick="addSelectRowFun('listGrid',update);">修改</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sysSystem:see">
			<a href="javascript:void(0)" class="easyui-linkbutton"
			   iconcls="icon-view" plain="true"
			   onclick="addSelectRowFun('listGrid',view);">查看</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sysSystem:del">
			<a href="javascript:void(0)" class="easyui-linkbutton linkbutton-del"
			   iconCls="icon-remove" plain="true"
			   onclick="pubDel('listGrid','${basePath}ms/sysSystem/del?id=','id');">删除</a>
		</shiro:hasPermission>
	</div>
</div>
<div id="addOrUpdateDialog" class="easyui-dialog" title="添加/修改分类"
	 data-options="iconCls:'icon-save'" closed="true"
	 style="width: 820px; height: 410px; padding: 10px"
	 buttons="#dlg-buttons"></div>
<div id="dlg-buttons">
	<a href="#" id="subBtn" class="easyui-linkbutton" onclick="save()">保存</a>
	<a href="#" class="easyui-linkbutton" onclick="closeDialog()">返回</a>
</div>
</body>

<script type="text/javascript">
    /**
     * 添加
     */
    function add() {
        openDialog('${basePath}page/ms/ucenter/sys_system_add_update.jsp', '添加');
    }
    var id = '${param.id}';

    /**
     * 修改
     */
    function update(row) {
        openDialog('${basePath}page/ms/ucenter/sys_system_add_update.jsp?isEdit=true&id=' + row.id, '修改');
    }
    /**
     * 查看
     */
    function view(row) {
        openDialog('${basePath}page/ms/ucenter/sys_system_add_update.jsp?isView=true&id=' + row.id, '查看');
    }
    //重新加载
    function reload() {
        $('#listGrid').datagrid('load', {
            name : $('#nameF').val(),
            isDisable : $('#isDisableF').combobox('getValue')
        });
    }
</script>
</html>

