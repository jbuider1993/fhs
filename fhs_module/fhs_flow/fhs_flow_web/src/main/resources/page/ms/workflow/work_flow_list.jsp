<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<jsp:include page="/page/common/js_css_include.jsp"></jsp:include>
	</head>

	<body>
		<table id="voDataGrid" title="工作流列表" class="easyui-datagrid" fit="true" style="width: 90%;" url="${basePath}workFlowJbpmXml/listData.do" pagination="true" rownumbers="true" fitcolumns="true" singleselect="true" pagesize="10" toolbar="#toolbar">
			<thead>
				<tr>
					<th align="center" field="processName" width="20%">工作流名称</th>
					<th align="center" field="transMap.typeIdName" width="20%">分类名称</th>
					<th align="center" field="processKey" width="20%">工作流key</th>
					<th align="center" field="transMap.stateName" width="20%">工作流部署状态</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
			<div style="margin-bottom: 5px;">
				<shiro:hasPermission name="workFlowJbpmXml:see"> <label>名称：</label><input id="processNameF" name="processNameF" type="text">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-search" plain="true" onclick="reload()">查询</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="workFlowJbpmXml:add">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" plain="true" onclick="add()">添加</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="workFlowJbpmXml:update">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit" plain="true" onclick="addSelectRowFun('voDataGrid',update);">修改</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="workFlowJbpmXml:releaseWorkFlow">
				    <a href="javascript:void(0)" class="easyui-linkbutton"  plain="true" onclick="addSelectRowFun('voDataGrid',releaseWorkFlow);">部署</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="workFlowJbpmXml:update">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit" plain="true" onclick="addSelectRowFun('voDataGrid',upFormParam);">工作流表单参数</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="workFlowJbpmXml:del">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-remove" plain="true" onclick="pubDel('voDataGrid','${basePath}workFlowJbpmXml/del.do?id=','id');">删除</a>
				</shiro:hasPermission>
			</div>
		</div>
		<div id="addOrUpdateDialog" class="easyui-dialog" title="添加/修改工作流" data-options="iconCls:'icon-save'" closed="true" style="width: 90%; height: 95%; padding: 10px">
			<div id="loadDialogInfo">
			</div>
			<iframe id="dialogFrame" src="" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
		</div>

		<script>
			/** * 添加 */
			function add() {
				$('#dialogFrame').show();
				$('#loadDialogInfo').hide();
			    $('#dialogFrame').attr('src','${basePath}page/workflow/design.jsp');
				$('#addOrUpdateDialog').dialog('open').dialog('setTitle', '添加工作流');
			}
			/** * 添加 */
			function update(row) {
				$('#dialogFrame').show();
				$('#loadDialogInfo').hide();
				$('#dialogFrame').attr('src','${basePath}page/workflow/design.jsp?id=' + row.id);
				$('#addOrUpdateDialog').dialog('open').dialog('setTitle', '修改工作流');
			}
			//部署工作流到引擎里面去
			function releaseWorkFlow(row)
			{
			    $.ajax({
			    	type:"post",
			    	url:"${basePath}workFlowJbpmXml/releaseWorkFlow.do",
			    	data:{id:row.id},
			    	dataType:'json',
			    	success:function(data)
			    	{
			    	    if(data && data.result) {
							Ealert("操作成功！");
							closeDialog();
							reload();
						} else {
							Ealert('操作失败');
						}
					}
			    });
			}
			
			//部署工作流到引擎里面去
			function upFormParam(row)
			{
			 	$('#dialogFrame').hide();
			 	$('#loadDialogInfo').show();
			 	
			 	$('#loadDialogInfo').load('${basePath}page/workflow/workFlow_update.jsp?id=' + row.id, function() {
					$('#addOrUpdateDialog').dialog('open').dialog('setTitle', '添加工作流监听');
					$.parser.parse($('#addOrUpdateDialog'));
				});
			}
			
			/** * 重新加载 */
			function reload() {
				$('#voDataGrid').datagrid('load', {
					processName: $('#processNameF').val()
				});
			}
		</script>

	</body>

</html>