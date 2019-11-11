<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<jsp:include page="/page/common/js_css_include.jsp"></jsp:include>
	</head>

	<body>
		<table id="voDataGrid" title="工作流监听列表" class="easyui-datagrid" fit="true" style="width: 90%;" url="${basePath}workFlowListener/listData.do" pagination="true" rownumbers="true" fitcolumns="true" singleselect="true" pagesize="10" toolbar="#toolbar">
			<thead>
				<tr>
					<th align="center" field="listenerName" width="15%">监听器名称</th>
					<th align="center" field="transMap.listenerTypeName" width="15%">监听器名称</th>
					<th align="center" field="transMap.eventName" width="15%">事件</th>
					<th align="center" field="transMap.excuteTypeName" width="15%">执行类型</th>
					<th align="center" field="excuteContent" width="15%">执行内容</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
			<div style="margin-bottom: 5px;">
				<shiro:hasPermission name="workFlowListener:see"> <label>班级：</label><input id="listenerNameF" name="listenerNameF" type="text">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-search" plain="true" onclick="reload()">查询</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="workFlowListener:add">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" plain="true" onclick="add()">添加</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="workFlowListener:see">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-view" plain="true" onclick="addSelectRowFun('voDataGrid',view);">查看</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="workFlowListener:update">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit" plain="true" onclick="addSelectRowFun('voDataGrid',update);">修改</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="workFlowListener:del">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-remove" plain="true" onclick="pubDel('voDataGrid','${basePath}workFlowListener/del.do?id=','id');">删除</a>
				</shiro:hasPermission>
			</div>
		</div>
		<div id="addOrUpdateDialog" class="easyui-dialog" title="添加/修改工作流监听" data-options="iconCls:'icon-save'" closed="true" style="width: 800px; height: 410px; padding: 10px" buttons="#dlg-buttons"></div>
		<div id="dlg-buttons">
			<a href="#" class="easyui-linkbutton" id="saveBtn" onclick="save()">保存</a>
			<a href="#" class="easyui-linkbutton" onclick="closeDialog()">返回</a>
		</div>
		<script>
			/** * 添加 */
			function add() { openDialog('${basePath}page/workflow/listener_add_update.jsp', '添加工作流监听'); }
			/** * 修改 */
			function update(row) { openDialog('${basePath}page/workflow/listener_add_update.jsp?isEdit=true&id=' + row.id, '修改工作流监听'); } 
			/** * 查看 */
			function view(row) { openDialog('${basePath}page/workflow/listener_add_update.jsp?isView=true&id=' + row.id, '查看工作流监听'); } 
			/** * 重新加载 */
			function reload() { $('#voDataGrid').datagrid('load', { listenerName: $('#listenerNameF').val() }); }
		</script>
	</body>

</html>