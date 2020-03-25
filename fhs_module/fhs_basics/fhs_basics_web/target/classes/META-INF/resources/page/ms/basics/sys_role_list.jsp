<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="hfs" uri="http://www.ylmo2o.com/tag/hfs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:include page="/page/ms/common/js_css_include.jsp"></jsp:include>
<style>
input[type=checkbox]{
	margin-top: 6px;
}
</style>
</head>
<body>

	<table id="roleGrid" title="角色列表" class="easyui-datagrid" fit="true"
		style="width: 95%;" url="${basePath}ms/sysRole/listData/${param.organizationId}"
		pagination="true" rownumbers="true" fitColumns="true"
		singleSelect="true" pageSize="10" toolbar="#toolbar" striped="true">
		<thead>
			<tr>
				<th align='center' field="roleName" width="20%">角色名称</th>
				<th align='center' field="transMap.isDisableName" width="10%">状态</th>
				<th align='center' field="transMap.organizationIdOrganizationName" width="29%">所属机构</th>
				<th align='center' field="remark" width="20%">备注</th>
				<hfs:listOperator nameSpace="sysRole" updateFuncName="updateRole" showView="false" delReqUrl="ms/sysRole/delSysRole?roleId=" pkField="roleId" alignWay="center" thWidth="20%"></hfs:listOperator>
			</tr>
		</thead>
	</table>


	<div id="toolbar">
		<div style="margin-bottom: 5px;">
			<shiro:hasPermission name="sysRole:add">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" onclick="addRole()">新建</a>
			</shiro:hasPermission>
		</div>
	</div>
	<div id="dlg" class="easyui-dialog"  closed="true"
		style="width: 1100px; height: 600px; padding: 10px;max-width: 95%;max-height: 90%" buttons="#dlg-buttons"></div>
	<div id="dlg-buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton"
			onclick="save()">保存</a><a href="javascript:void(0);"
			class="easyui-linkbutton" onclick="closeDialog()">返回</a>
	</div>

</body>

<script type="text/javascript">
	//默认关闭dialog
	$(function() {
		$('#dlg').dialog('close');
	});

	//关闭dialog方法
	function closeDialog() {
		$('#dlg').dialog('close');
	}

	//打开dialog
	function openDialog(url, title) {
		$('#dlg').load(url, function() {
			$('#dlg').dialog('open').dialog('setTitle', title);
			$.parser.parse($('#dlg'));
		});

	}

	//  角色添加
	function addRole() {
		openDialog(
				'${basePath}page/ms/basics/sys_role_add_update.jsp?isEdit=false&organizationId=${param.organizationId}',
				'添加角色');
	}

	//角色修改
	function updateRole(pkField) {
		openDialog(
				'${basePath}page/ms/basics/sys_role_add_update.jsp?isEdit=true&parentId=${param.parentId}&roleId=' + pkField, '修改角色');
	}

	//重新加载
	function reload() {
		$('#roleGrid').datagrid('load', {});
	}
</script>
</html>
