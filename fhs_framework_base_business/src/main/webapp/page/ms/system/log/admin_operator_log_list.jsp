<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<jsp:include page="/page/common/js_css_include.jsp"></jsp:include>
</head>

<body>
	<table id="voDataGrid" title="操作日志列表" class="easyui-datagrid"
		fit="true" style="width: 100%;"
		url="${basePath}logAdminOperatorLog/listData" pagination="true"
		rownumbers="true" fitcolumns="true" singleselect="true" pagesize="10"
		toolbar="#toolbar" striped="true">
		<thead>
			<tr>
				<th align="center" field="loginName" width="8%">操作用户</th>
				<th align="center" field="url" width="15%">请求的url</th>
				<th align="center" field="transMap.logTypeName" width="8%">操作类型</th>
				<th align="center" field="operatDesc" width="15%">操作描述</th>
				<th align="center" field="reqParam" width="25%">请求参数</th>
				<th align="center" field="networkIp" width="15%">ip地址</th>
				<th align="center" field="createTime" width="13%">操作时间</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<div style="margin-bottom: 5px;">
			<shiro:hasPermission name="logAdminOperatorLog:see">
				<input id="loginNameF" name="loginNameF" type="text" placeholder="操作用户" />
				<input id="networkIpF" name="networkIpF" type="text" placeholder="ip地址" />
				<select id="logTypeF" name="logTypeF" class="easyui-combobox" prompt="操作类型"
					data-options="
		                                valueField: 'wordbookCode',
		                                textField: 'wordbookDesc',
		                                editable : false,
		                                panelHeight: 'auto',
		                                url: '${pub_service_url}wordbook/getData?wordbookGroupCode=log_type&jsonpCallback=?',
		                                showAll:true
		                                ">
				</select>
				<input type="text" class="Wdate" style="margin-top: 5px;" placeholder="开始时间"
					id="startTime" name="startTime"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss' ,maxDate:'#F{$dp.$D(\'endTime\')}'});" />
				<input type="text" class="Wdate" style="margin-top: 5px;" placeholder="结束时间"
					id="endTime" name="endTime"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss' ,minDate:'#F{$dp.$D(\'startTime\')}'});" />
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-search" plain="true" onclick="reload()">查询</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconcls="icon-view" plain="true"
					onclick="addSelectRowFun('voDataGrid',view);">查看</a>
			</shiro:hasPermission>
		</div>
	</div>

	<div id="addOrUpdateDialog" class="easyui-dialog" title="查看日志"
		data-options="iconCls:'icon-save'" closed="true"
		style="width: 800px; height: 500px; padding: 10px" buttons="#dlg-buttons"></div>
	<div id="dlg-buttons">
		<a id="cloBtn" href="#" class="easyui-linkbutton" onclick="closeDialog()">关闭</a>
	</div>

	<script>
		/** * 重新加载 */
		function reload() {
			$('#voDataGrid').datagrid('load', {
				loginName : $('#loginNameF').val(),
				networkIp : $('#networkIpF').val(),
				logType : $('#logTypeF').combobox('getValue'),
				startTime : $('#startTime').val(),
				endTime : $('#endTime').val()
			});
		}

		/** * 查看 */
		function view(row) {
			openDialog('${basePath}/page/log/admin_operator_log_see.jsp?id='
							+ row.id, '查看日志');
		}
	</script>
</body>
</html>