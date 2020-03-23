<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="/page/ms/common/js_css_include.jsp"></jsp:include>
<style>
</style>
</head>
<body>

	<table id="groupListGrid" title="字典分组列表" class="easyui-datagrid"
		fit="true" style="width: 100%;"
		url="${systemServiceUrl}ms/wordbook/findWordbookGroupForPage" pagination="true"
		rownumbers="true" fitColumns="true" singleSelect="true" pageSize="10" data-options="onClickRow:changeRight"
		striped="true" toolbar="#atoolbar">
		<thead>
			<tr>
				<th align='center' field="groupName" width="50%">分组名称</th>
				<th align='center' field="wordbookGroupCode" width="49">分组编码</th>

			</tr>
		</thead>
	</table>


	 <div id="atoolbar">
		<input id="groupName_query" name="groupName_query" type="text" placeholder="分组名称"/>
		<input id="wordbookGroupCode_query" name="wordbookGroupCode_query" type="text" placeholder="分组code"/>

				<shiro:hasPermission name="wordbook:see">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="query()">查询</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="wordbook:add">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addGroup()">添加</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="wordbook:update">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick=" addSelectRowFun('groupListGrid',updateGroup)">修改</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="wordbook:del">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="addSelectRowFun('groupListGrid',deleteWordbookGroup)">删除</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="wordbook:refreshRedisCache">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="addSelectRowFun('groupListGrid',refreshRedisCache)">刷新选中字典类型的redis缓存</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="wordbook:refreshRedisCache">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="refreshRedisCache()">刷新所有字典缓存</a>
				</shiro:hasPermission>

		</div>


		<div id="addOrUpdateDialog" class="easyui-dialog" style="width: 400px; height: 200px; padding: 10px 20px"
			data-options="modal:true" closed="true" buttons="#dlg-buttons">
			<form id="addOrUpdateForm" method="post">
				<input type="hidden" id="groupId" name="groupId" />
				<div class="fitem">
					<label>分组编码:</label>
					<input type="text" id="wordbookGroupCode" name="wordbookGroupCode" datatype="*" nullmsg="请填写编码"  />
					<span class="form-field-required">*</span>
				</div>
				<div class="fitem">
					<label>分组名称:</label>
					<input type="text" id="groupName" name="groupName" datatype="*" nullmsg="请填写分组名称"  />
					<span class="form-field-required">*</span>
				</div>
			</form>
		</div>
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()">确定</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeDialog('addOrUpdateDialog')">关闭</a>
		</div>




</body>

<script type="text/javascript">

	$(function(){
		taskForm  = $('#addOrUpdateForm').Validform({
			tiptype:5
		 });
	})

	var addOrUpdateDialogName = null;

	function openDialog() {
		removeValidate('addOrUpdateForm');
		$('#addOrUpdateDialog').dialog('open').dialog('setTitle', addOrUpdateDialogName);

	}

	// 切换右侧
	function changeRight(index,row)
	{
		parent.changeRight(row.wordbookGroupCode);
	}

	// 重新加载列表数据
	function reload() {
		$('#groupListGrid').datagrid('load', {
			groupName : $('#groupName_query').val().trim(),
			wordbookGroupCode : $('#wordbookGroupCode_query').val().trim()
		});
		parent.clearWordbookGrid();
	}
	function query(){
		$('#groupListGrid').datagrid('reload', {
			groupName : $('#groupName_query').val().trim(),
			wordbookGroupCode : $('#wordbookGroupCode_query').val().trim()
		});
	}

	// 修改
	function updateGroup(row) {
		$('#addOrUpdateForm').form('clear');
		addOrUpdateDialogName = '修改分组';
		addOrUpdateUrl = '${systemServiceUrl}ms/wordbook/updateWordbookGroup';
		//初始化表单
		$('#addOrUpdateForm').form('load','${systemServiceUrl}ms/wordbook/getWordbookGroupBean?groupId='+row.groupId);
		openDialog();
	}


	//添加
	function addGroup() {
		addOrUpdateDialogName = '添加分组';
		addOrUpdateUrl = '${systemServiceUrl}ms/wordbook/addWordbookGroup';
		$('#addOrUpdateForm').form('clear');
		openDialog();
	}

	function deleteWordbookGroup(row){
		var deleteUrl = '${systemServiceUrl}ms/wordbook/delWordbookGroup?wordbookGroupCode=' + row.wordbookGroupCode + '&groupId=';
		pubDel('groupListGrid',deleteUrl,'groupId')
	}

	function save(){
		$('#addOrUpdateForm').form('submit',{
			url : addOrUpdateUrl,
		    onSubmit: function(){
		    	if(!taskForm.check()){
		    		return false;
		    	};
		    },
		    success:function(data){
		    	data = $.parseJSON(data);
				if(data.code == 200)
				{
					Ealert('操作成功');
					reload();
				}
				else
				{
					Ealert('操作失败');
				}
				closeDialog('addOrUpdateDialog');
		    }
		});
	}
	function refreshRedisCache(row){
		var wordbookGroupCode = "";
		if(row != null && row != undefined){
			wordbookGroupCode = row.wordbookGroupCode;
		}
		$.ajax({
			url : '${systemServiceUrl}ms/wordbook/refreshRedisCache',
			data : {
				wordbookGroupCode : wordbookGroupCode
			},
			dataType: "json",
			success : function(data){
				if(data.code == 200)
				{
					Ealert('刷新成功');
				}
				else
				{
					Ealert('刷新失败');
				}
		    }
		})
	}


</script>
</html>
