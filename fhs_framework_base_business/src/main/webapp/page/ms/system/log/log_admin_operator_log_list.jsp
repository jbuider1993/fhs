<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="hfs" uri="http://www.ylmo2o.com/tag/hfs"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="/page/ms/common/js_css_include.jsp"></jsp:include>
</head>


<body>
<table id="listGrid" title="操作日志列表" class="easyui-datagrid"
	fit="true" style="width: 90%;" url="${basePath}ms/logAdminOperatorLog/findPage"
	pagination="true" rownumbers="true" fitColumns="true"
	singleSelect="true" pageSize="10" striped="true" toolbar="#toolbar">
	<thead>
		<tr>
			<th align="center" field="transMap.operatorIdUserName" sortable="false" width="8%">操作用户</th>
			<%--<th align="center" field="url" width="15%">请求的url</th>--%>
			<th align="center" field="transMap.logTypeName" width="8%">操作类型</th>
			<th align="center" field="operatDesc" width="17%">操作描述</th>
			<th align="center" field="reqParam" width="28%">请求参数</th>
			<th align="center" field="networkIp" width="15%">ip地址</th>
			<th align="center" field="createTime" width="13%">操作时间</th>
			<hfs:listOperator showEdit="false" showDel="false" nameSpace="logAdminOperatorLog" showView="true" viewFuncName="view" pkField="id" alignWay="center" thWidth="10%"></hfs:listOperator>
		</tr>
	</thead>
</table>

<div id="toolbar">
	<div style="margin-bottom: 5px;">
		<shiro:hasPermission name="logAdminOperatorLog:see">
			<select id="operatorIdF" name="operatorIdF" class="easyui-combobox" prompt="操作用户"
					data-options="
				valueField: 'userId',
				textField: 'userName',
				editable : false,
				url: '${fhs_framework_ucenter_basePath}webApi/sysUser/findList?jsonpCallback=?',
				showAll:true
				">
			</select>
			<select id="logTypeF" name="logTypeF" class="easyui-combobox" prompt="操作类型"
					data-options="
				valueField: 'wordbookCode',
				textField: 'wordbookDesc',
				editable : false,
				panelHeight: 'auto',
				url: '${pub_service_url}ms/wordbook/getData?wordbookGroupCode=log_type&jsonpCallback=?',
				showAll:true
				">
			</select>
			<input id="operatDescF" name="operatDescF" type="text" placeholder="操作描述" />
			<input id="reqParamF" name="reqParamF" type="text" placeholder="请求参数" />
			<input id="networkIpF" name="networkIpF" type="text" placeholder="ip地址" />
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-search" plain="true" onclick="reload()">查询</a>

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
	 * 查看
	 */
	function view(pkField) {
		openDialog('${basePath}page/ms/system/log/log_admin_operator_log_add_update.jsp?isView=true&id=' + pkField, '查看');
	}
	//重新加载
	function reload() {
		$('#listGrid').datagrid('load', {
            operatorId : $('#operatorIdF').combobox('getValue'),
            logType : $('#logTypeF').combobox('getValue'),
            operatDesc : $('#operatDescF').val(),
            reqParam : $('#reqParamF').val(),
            networkIp : $('#networkIpF').val()
		});
	}
</script>
</html>
