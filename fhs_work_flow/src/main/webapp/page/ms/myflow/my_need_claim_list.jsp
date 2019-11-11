<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<jsp:include page="/page/ms/common/js_css_include.jsp"></jsp:include>
	</head>

	<body>
		<table id="voDataGrid" title="我需要签收的工作" class="easyui-datagrid" fit="true" style="width: 90%;" url="${basePath}myWorks/getNeedClaimTask.do" pagination="true" rownumbers="true" fitcolumns="true" singleselect="true" pagesize="10" toolbar="#toolbar">
			<thead>
				<tr>
					<th align="center" field="title" width="15">工作流名称</th>
					<th align="center" field="creator" width="15%">申请人</th>
					<th align="center" field="create_datetime" width="15%">申请时间</th>
					<th align="center" field="NAME_" width="15%">任务名称</th>
					<th align="center" field="CREATE_TIME_" width="15%">任务到达时间</th>
					<th align="center" field="process_instance_id" width="15%">流程实例id</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
			<div style="margin-bottom: 5px;">
				<label>任务类型：</label><input id="titleNameF" name="titleNameF" type="text">
				<label>任务到达时间：</label><input id="taskTimeF" readonly="readonly" name="taskTimeF" class="Wdate" type="text" onClick="WdatePicker()">
				<label>流程实例创建时间：</label><input id="instanceTimeF" readonly="readonly"  class="Wdate"  name="instanceTimeF" type="text" onClick="WdatePicker()">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-search" plain="true" onclick="reload()">查询</a>
			    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit" plain="true" onclick="addSelectRowFun('voDataGrid',claimTask);">签收</a>
		    </div>
		</div>
		
		<script>
			
			
			/** * 处理工作 */
			function claimTask(row) {
				 $.ajax({
			    	type:"post",
			    	url:"${basePath}myWorks/claimTask.do",
			    	data:{taskId:row.ID_},
			    	dataType:'json',
			    	success:function(data)
			    	{
			    	    if(data && data.result) {
							Ealert("操作成功！");
							reload();
						} else {
							Ealert('操作失败');
						}
					}
			    });
			}
			
			/** * 重新加载 */
			function reload() {
				$('#voDataGrid').datagrid('load', {
					title: $('#titleNameF').val(),
					taskTime:$('#taskTimeF').val(),
					instanceTime:$('#instanceTimeF').val(),
				});
			}
		</script>

	</body>

</html>