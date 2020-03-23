<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<form id="menuButtonForm" method="post">
	<div id="formulaCaclDiv">
		<div class="fitem clearBR">
			<div class="fitem">
				<label>权限名称:</label><input id="permissionName"
					class="easyui-validatebox" type="text" value="${permissionName }"
					name="permissionName" style="width: 200px;" datatype="*"
					nullmsg="请填写权限名称" /> <input type="hidden" id="permissionId"
					name="permissionId" value="${param.permissionId}">
			</div>
			<div class="fitem">
				<label>方法名称:</label><input id="method" class="easyui-validatebox"
					name="method" type="text" value="${method }" style="width: 200px;"
					datatype="*" nullmsg="请填写权限方法" /> <input type="hidden" id="menuId"
					name="menuId" value="${param.parentId}">
			</div>
			<div class="fitem">
				<label>权限类型:</label> <select id="typeselect" name="permissionType"
					class="easyui-combobox"
					data-options="
                                valueField: 'wordbookCode',
                                textField: 'wordbookDesc',
                                editable : false,
                                panelHeight: 'auto',
                                url: '${systemServiceUrl}webApi/wordbook/getData?wordbookGroupCode=button_type&jsonpCallback=?',
                                onSelect: onSelectType
							" style="width:200px;">

				</select>
			</div>
			<div class="fitem">
				<label>是否启用:</label> <select class="easyui-combobox" id="isDisable"
					data-options="editable : false" name="isDisable" style="width:200px;">
					<option value="0" selected="selected">启用</option>
					<option value="1">禁用</option>
				</select>
			</div>

			<script type="text/javascript">
				var url_temp = "${basePath}ms/sysMenuPermission/add";
				isEdit = ("${param.isEdit}" == "true");
				$(function() {
					if (isEdit) {
						var permissionId = "${param.permissionId}";
						$
								.post(
										"${basePath}ms/sysMenuPermission/toUpdate",
										{
											"permissionId" : permissionId
										},
										function(d) {
											if (d) {
												$("#permissionId").val(d.permissionId);
												$("#permissionName").val(
														d.permissionName);
												$("#method").val(d.method);
												$("#menuId").val(d.menuId);
												// 						$('#buttonType').combobox('setValue',d.permissionType);
												$('#typeselect').combobox(
														'setValue',
														d.permissionType);
												$('#isDisable')
														.combobox('setValue',
																d.isDisable);
												url_temp = "${basePath}ms/sysMenuPermission/update";
											}
										}, "json");

					}
				});
				var addUpateMenuForm = null;
				$(document).ready(function() {
					//form验证。
					addUpateMenuForm = $('#menuButtonForm').Validform({
						tiptype : 5
					});
					// 如果是编辑页面，就初始化一些数据
				})
				
				function save() {
					$('#menuButtonForm').form('submit', {
						url : url_temp,
						onSubmit : function() {
							return (addUpateMenuForm.check());
						},
						success : function(d) {
							d = $.parseJSON(d);
							if (d != null && d.result) {
								Ealert("操作成功！");
								closeDialog();
								reload();
							} else {
								Ealert('操作失败');
							}
						}
					});

				}
				function onSelectType(rec){
					$('#permissionName').val(rec.wordbookDesc);
					var buttonPermission = '';
					switch(rec.wordbookDesc){
					case '添加' : buttonPermission = 'add'; break;
					case '修改' : buttonPermission = 'update'; break;
					case '删除' : buttonPermission = 'del'; break;
					case '查询' : buttonPermission = 'see'; break;
					}
					$('#method').val(buttonPermission);
					
				}
			</script>
		</div>
	</div>
	<div class="fitem ">
		<center>
			<a href="#" class="easyui-linkbutton btn btn-suc l-btn l-btn-small" onclick="save()">保存</a> <a
				href="#" class="easyui-linkbutton btn btn-can l-btn l-btn-small" onclick="closeDialog()">返回</a>
		</center>
	</div>
</form>