<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="/page/ms/common/js_css_include.jsp"></jsp:include>
</head>


<body>
<table id="listGrid" title="列表" class="easyui-datagrid"
	fit="true" style="width: 90%;" url="${basePath}ms/sysServer/findPage"
	pagination="true" rownumbers="true" fitColumns="true"
	singleSelect="true" pageSize="10" striped="true" toolbar="#toolbar">
	<thead>
		<tr>
			<th align='center' field="serverName" width="50%">服务名称</th>
			<th align='center' field="serverUrl" width="49%">服务链接</th>
		</tr>
	</thead>
</table>

<div id="toolbar">
	<div style="margin-bottom: 5px;">
		<shiro:hasPermission name="sysServer:see">
			<input type="text" id="title_rel" name="title_rel"  datatype="*" placeholder="标题"  />
                    
			<label id="btnLable"></label>
			
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search" plain="true" onclick="reload()">查询</a>
				
		</shiro:hasPermission>

		<shiro:hasPermission name="sysServer:add">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="add()">添加</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sysServer:update">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true"
				onclick="addSelectRowFun('listGrid',update);">修改</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sysServer:see">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconcls="icon-view" plain="true"
				onclick="addSelectRowFun('listGrid',view);">查看</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sysServer:del">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true"
				onclick="pubDel('listGrid','${basePath}ms/sysServer/del?id=','id');">删除</a>
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
	var id = '${param.id}';
	/**
	 * 添加
	 */
	function add() {
		openDialog('${basePath}page/ms/basics/sys_server_add_update.jsp?id='+ id, '添加');
	}
	/**
	 * 修改
	 */
	function update(row) {
		openDialog('${basePath}page/ms/basics/sys_server_add_update.jsp?isEdit=true&id=' + row.id, '修改');
	}
	/**
	 * 查看
	 */
	function view(row) {
		openDialog('${basePath}page/ms/basics/sys_server_add_update.jsp?isView=true&id=' + row.id, '查看');
	}
	//重新加载
	function reload() {
		$('#listGrid').datagrid('load', {
			serverName : $('#title_rel').val()
		});
	}
</script>
</html>
