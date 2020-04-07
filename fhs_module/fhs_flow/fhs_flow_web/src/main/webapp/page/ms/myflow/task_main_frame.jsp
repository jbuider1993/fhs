<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<html>

	<head>
		<jsp:include page="/src/main/webapp/page/common/js_css_include.jsp"></jsp:include>
	</head>

	<body style="width: 100%; height: 100%;">

		<div class="easyui-tabs" style="width:100%;height:500px">
			<div title="工单处理" style="padding:10px">
				<iframe frameborder="no" border="0" marginwidth="0" marginheight="0" style="width: 100%;height: 100%;" scrolling="auto" allowtransparency="yes" id="historyTaskContent" src="${basePath}page/myworkflow/task_handle.jsp">
					
					
					
				</iframe>
			</div>
			<div title="流程图" style="padding:10px">
				<img src="${basePath}myWorks/getWorkFlowImg.do?processInstanceId=${param.processInstanceId}" style="max-width: 100%;max-height: 100%;" id="flowChart">
			</div>
			<div title="历史记录" style="padding:10px">
				<table id="voDataGrid" title="历史任务" class="easyui-datagrid" fit="true" style="width: 90%;" pagination="false" rownumbers="true" fitcolumns="true" singleselect="true" pagesize="10" toolbar="#toolbar">
					<thead>
						<tr>
							<th align="center" field="taskName" width="15">任务名称</th>
							<th align="center" field="operator" width="15%">完成人</th>
							<th align="center" field="startDateTime" width="15%">到达时间</th>
							<th align="center" field="complateDateTime" width="15%">完成时间</th>
							<th align="center" field="mainArgs" width="30%">流程参数</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</body>
	<script>
		 $(function(){
		 	var task = window.parent.getTaskInfo();
		 	$('#voDataGrid').datagrid('appendRow',{
				taskName: '创建',
				operator: task.creator,
				startDateTime: task.create_datetime,
				complateDateTime:task.create_datetime,
				mainArgs:''
			});
			if(task.extend_json)
			{
				task.extend_json = $.parseJSON(task.extend_json); ;
				for(i=0;i<task.extend_json.length;i++)
				{
					$('#voDataGrid').datagrid('appendRow',task.extend_json[i]);	
				}
			}
		 })
	</script>

</html>