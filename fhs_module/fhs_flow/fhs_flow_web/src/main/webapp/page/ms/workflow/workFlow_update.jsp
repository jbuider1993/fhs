<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/src/main/webapp/page/common/form_include.jsp"></jsp:include>
<form id="addOrUpdateForm" method="post">
	<div id="addUpateUserForm">
		<div class="fitem clearBR">
			<div class="fitem">

				<div class="fitemDiv">
					<label>是否允许选择下一步处理人:</label>
					<select id="typeselect" name="isChooseNext" class="easyui-combobox" data-options="
                                valueField: 'wordbookCode',
                                textField: 'wordbookDesc',
                                panelWidth: 155,
                                editable : false,
                                panelHeight: 'auto',
                                url: '${pub_service_url}wordbook/getData.do?wordbookGroupCode=pub_boolean&jsonpCallback=?',
							" datatype="*" nullmsg="请选择">
						</select>
				</div>

				<div class="fitemDiv">
					<label>是否允许使用公共表单:</label>
					<select id="typeselect" name="isPubForm" class="easyui-combobox" data-options="
                                valueField: 'wordbookCode',
                                textField: 'wordbookDesc',
                                panelWidth: 155,
                                editable : false,
                                panelHeight: 'auto',
                                url: '${pub_service_url}wordbook/getData.do?wordbookGroupCode=pub_boolean&jsonpCallback=?',
							" datatype="*" nullmsg="请选择">
						</select>
				</div>

			</div>

			<div class="fitem">
				<div class="fitemDiv">
					<label>公共表单名称:</label><input id="formName" class="easyui-validatebox" type="text" name="formName" />
				</div>
			</div>
			<div class="fitem">
				请注意：如果您使用公共表单则需要填写公共表单名称，如果不使用无需填写，此配置填写错误会引起后面流程启动问题请谨慎
			</div>
			<div class="fitem">
				<center>
					<a id="subBtn" href="javascript:void(0);" class="easyui-linkbutton" onclick="save()">提交</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="closeDialog()">返回</a>
				</center>
			</div>

		</div>
		<script type="text/javascript">
			var url_save = '${basePath}workFlowJbpmXml/update.do?id=${param.id}';

			var addUpateUserForm = null;
			$(document).ready(function() {
				//form验证。
				addUpateUserForm = $('#addUpateUserForm').Validform({
					tiptype: 5
				});
				initError('addOrUpdateDialog');
				loadForm();

			})

			// 初始化编辑的form
			function loadForm() {
				$('#addOrUpdateForm').form('load', '${basePath}workFlowJbpmXml/getBeanData.do?id=${param.id}');
			}

			function save() {
				$('#addOrUpdateForm').form('submit', {
					url: url_save,
					onSubmit: function() {
						return(addUpateUserForm.check());
					},
					success: function(d) {
						d = $.parseJSON(d);
						if(d && d.result) {
							Ealert("操作成功！");
							closeDialog();
							reload();
						} else {
							Ealert('操作失败');
						}
					}
				});
			};
		</script>
	</div>
	</div>
</form>