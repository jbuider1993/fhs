<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="/page/ms/common/js_css_include.jsp"></jsp:include>
</head>
<body>

	<table id="adminMenuGrid" title="${param.menuName}权限列表"
		class="easyui-datagrid" fit="true" style="width: 90%;"
		url="${basePath}ms/sysMenuPermission/listData?menuId=${param.menuId}"
		pagination="false" rownumbers="true" fitColumns="true"
		singleSelect="true" pageSize="10" toolbar="#toolbar" striped="true">
		<thead>
			<tr>
				<th align='center' field="permissionName" width="40%">权限名称</th>
				<th align='center' field="method" width="40%">方法名称</th>
	 			<th align='center' field="state" width="19%">状态</th>
			</tr>
		</thead>
	</table>

	<div id="toolbar">
		<div style="margin-bottom: 5px;">

			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addMenu()">添加</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true"
				onclick="addSelectRowFun('adminMenuGrid',updateMenu);">修改</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true"
				onclick="pubDel('adminMenuGrid','${basePath}ms/sysMenuPermission/del?id=','permissionId');">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addBaseMenuBatch()">批量添加增删改查权限</a>
		</div>
	</div>
	<div id="dlgbutton" class="easyui-dialog" title="添加/修改按钮信息"
		data-options="iconCls:'icon-save'"  closed="true"
		style="width: 400px; height: 410px; padding: 10px"></div>
</body>

<script type="text/javascript">
	//默认关闭dialog
	$(function() {
		$('#dlgbutton').dialog('close');
		if('${param.parentId}' == '')
		{
			$('#adminMenuGrid').datagrid('clear');
		}
	});

	//关闭dialog方法
	function closeDialog() {
		$('#dlgbutton').dialog('close');
	}

	//打开dialog
	function openDialog(url, title) {
		$('#dlgbutton').load(url, function() {
			$('#dlgbutton').dialog('open').dialog('setTitle', title);
			$.parser.parse($('#dlgbutton'));
		});

	}

	// 添加按钮
	function addMenu() {
		var menuId = '${param.menuId}';
		if (menuId == '') {
			EalertE("请选择菜单项！");
			return;
		}
		openDialog(
				'${basePath}page/ms/ucenter/sys_menu_permission_add_update.jsp?isEdit=false&parentId=${param.menuId}',
				"添加");
	}

	// 修改按钮
	function updateMenu(row) {
		openDialog(
				'${basePath}page/ms/ucenter/sys_menu_permission_add_update.jsp?isEdit=true&parentId=${param.menuId}&permissionId='
						+ row.permissionId, "编辑");

	}
	//一键添加增删改查权限
	function addBaseMenuBatch(){
		var menuId = '${param.menuId}';
		if (menuId == '') {
			EalertE("请选择菜单项！");
			return;
		}
		$.ajax({
			url : '${basePath}ms/sysMenuPermission/addBaseMenuBatch',
			dataType : 'json',
			type : 'POST',
			data : {
				menuId : menuId
			},
			success : function(data){
				if(data.result){
					Ealert('添加成功');
					reload();
				}else{
					Ealert('添加失败');
				}
			}
		})
	}

	//重新加载
	function reload() {
		$('#adminMenuGrid').datagrid('load', {
			name : encodeURI($('#user_rel_name').val())
		});
	}
</script>
</html>
