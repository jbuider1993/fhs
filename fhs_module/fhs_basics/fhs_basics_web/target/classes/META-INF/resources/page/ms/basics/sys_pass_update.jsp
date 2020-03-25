
<%@ page contentType="text/html; charset=gbk" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>修改密码</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
</head>

<body class="skin-1">
	<!--修改密码-->
	<div id="resetPasswordlog">
		<form id="resetPasswordForm" method="post">
			<input id="userId" name="userId" type="hidden"
				value="${param.userId}" />
			<div class="fitem">
				<label>原&#8195;密&#8195;码:</label> <input type="password" name="oldPassword"
					datatype="*" nullmsg="请填写原密码" ajaxPost="true"
					ajaxurl="${basePath}adminUser/validataPass" />
			</div>
			<div class="fitem">
				<label>新&#8195;密&#8195;码:</label> <input type="password" id="newPassword"
					name="newPassword" class="inputxt Validform_error" datatype="*6-16"
					nullmsg="请填写新密码" errormsg="密码范围在6~16位之间！" style="width:200px;" />
			</div>
			<div class="fitem">
				<label>确认新密码:</label> <input type="password" id="againNewPassword"
					name="againNewPassword" recheck="newPassword"
					class="inputxt Validform_error" datatype="*" nullmsg="请再次填写新密码"
					errormsg="您两次输入的账号密码不一致！" />
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<center>
			<a href="javascript:void(0)" class="easyui-linkbutton btn btn-suc l-btn l-btn-small"
				onclick="save()">提交</a> <a href="javascript:void(0)"
				class="easyui-linkbutton btn btn-can l-btn l-btn-small" onclick="closeDialog();">关闭</a>
		</center>
	</div>

	<script type="text/javascript">
		var url_temp = "${basePath}adminUser/updatePass";
		var resetPasswordForm = null;
		$(document).ready(function() {
			//form验证。
            resetPasswordForm = $('#resetPasswordForm').Validform({
				tiptype : 5
			});
			// 如果是编辑页面，就初始化一些数据
		})

		function save() {

			$('#resetPasswordForm').form('submit', {
				url : url_temp,
				onSubmit : function() {
					return (resetPasswordForm.check());
				},
				success : function(d) {
					d = $.parseJSON(d);
					if (d && d.result) {
						Ealert("操作成功！");
						closeDialog();
						reload();
					} else {
						Ealert('操作失败');
					}
				}
			});

		}
	</script>
</body>

</html>

