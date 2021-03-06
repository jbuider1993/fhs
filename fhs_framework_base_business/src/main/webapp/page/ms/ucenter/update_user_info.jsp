<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="/page/ms/common/js_css_include.jsp"></jsp:include>
</head>
<body>
	<form id="userInfoForm" method="post">
		<div class="fitem">
			<div class="fitemDiv">
				<label>集团编码:</label> <input type="text" id="groupCode"
					name="groupCode" datatype="/^[a-z0-9]+$/" nullmsg="请填写集团编码"
					errormsg="集团编码必须是小写字母或数字" /> <span class="form-field-required">*</span>
			</div>
			<div class="fitemDiv">
				<label>登录名:</label> <input type="text" id="userLoginName"
					name="userLoginName" datatype="*" nullmsg="请填写用户姓名" /> <span
					class="form-field-required">*</span>
			</div>
		</div>

		<div class="fitem">
			<div class="fitemDiv">
				<label>姓名:</label> <input type="text" id="userName" name="userName" />
			</div>
			<div class="fitemDiv">
				<label>密码:</label> <input type="password" id="password" name="password" />
			</div>
		</div>
		<div class="fitem">
			<div class="fitemDiv">
				<label>手机:</label> <input type="text" id="phone" name="phone"
					datatype="m" nullmsg="请填写电话" ignore="ignore" errormsg="请输入正确的手机号码" />
			</div>
			<div class="fitemDiv">
				<label>邮箱:</label> <input type="text" id="email" name="email"
					datatype="e" nullmsg="请填写邮箱" ignore="ignore" errormsg="邮箱格式错误" />
			</div>
		</div>
	</form>
	<div>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="save()">保存</a> 
	</div>
<script type="text/javascript">
	$(document).ready(function() 
		{
			taskForm  = $('#userInfoForm').Validform({
				tiptype:5
			 });
		});


	function save() 
	{
		$('#userInfoForm').form('submit', {
			url : '${basePath}ms/updateUserInfoAction/updateUserInfo',
			type:'post',
			onSubmit: function(){
				//隐藏input取消表单验证功能
		    	return showCheck($('#userInfoForm').Validform(),'userInfoForm');
		    },
			success : function(result) {
				result = eval('(' + result + ')');
				if (result.result) {
					Ealert('操作成功');
				} else {
					Ealert('操作失败');
				}
			}
		});
	}
	
	
</script>
</body>


</html>
