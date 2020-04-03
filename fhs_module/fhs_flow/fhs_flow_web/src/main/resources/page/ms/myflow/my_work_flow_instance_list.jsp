<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<jsp:include page="/page/common/js_css_include.jsp"></jsp:include>
	</head>

	<body>
		<table id="voDataGrid" title="工作流实例列表" class="easyui-datagrid" fit="true" style="width: 90%;" url="${basePath}workFlowInstance/listData.do" pagination="true" rownumbers="true" fitcolumns="true" singleselect="true" pagesize="10" toolbar="#toolbar">
			<thead>
				<tr>
					<th align="center" field="title" width="15%">工单名称</th>
					<th align="center" field="creator" width="15%">提单人</th>
					<th align="center" field="createDatetime" width="15%">提单时间</th>
					<th align="center" field="processInstanceId" width="15%">工作流实例id</th>
					<th align="center" field="newTaskName" width="15%">当前步骤</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
			<div style="margin-bottom: 5px;">
				<shiro:hasPermission name="workFlowInstance:see"> <label>工单名称：</label><input id="titleF" name="titleF" type="text">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-search" plain="true" onclick="reload()">查询</a>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="workFlowInstance:see">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-view" plain="true" onclick="addSelectRowFun('voDataGrid',view);">查看</a>
				</shiro:hasPermission>
			</div>
		</div>
		<div id="addOrUpdateDialog" class="easyui-dialog" title="查看工作流实例" data-options="iconCls:'icon-save'" closed="true" style="width:80%; height: 90%; padding: 10px" buttons="#dlg-buttons">
			<iframe id="dialogFrame" src="" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
		</div>
		<div id="dlg-buttons">
			<a href="#" class="easyui-linkbutton" onclick="closeDialog()">返回</a>
		</div>
		<script>
			
			var taskInfo = {};
			
			//获取任务的信息
			function getTaskInfo(){
				taskInfo.isView = true;
				taskInfo.process_instance_id = taskInfo.processInstanceId;
				taskInfo.form_id = taskInfo.formId;
				taskInfo.form_key = taskInfo.formKey;
				taskInfo.NAME_ = taskInfo.newTaskName;
				taskInfo.extend_json = taskInfo.extendJson;
				taskInfo.create_datetime = taskInfo.createDatetime;
				return taskInfo;
			}
			
			/** 查看 */
			function view(row) { 
				taskInfo = row;
				$('#dialogFrame').attr('src','${basePath}page/myworkflow/task_main_frame.jsp?processInstanceId=' + row.processInstanceId);
				$('#addOrUpdateDialog').dialog('open').dialog('setTitle', '查看工作:' + row.title );
			} 
			/** * 重新加载 */
			function reload() { 
				$('#voDataGrid').datagrid('load', { title: $('#titleF').val() }); 
			}
		</script>

	</body>

</html>