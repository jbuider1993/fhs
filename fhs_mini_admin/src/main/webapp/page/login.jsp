<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>登录</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="${staticPath}/images/favicon.ico">
<link href="${basePath}/css/login.css" rel='stylesheet' type='text/css' />
<script src="${staticPath}/js/jquery.min.js"></script>
<script src="${staticPath}/js/jQuery.md5.js"></script>
<link href="${staticPath}/js/toastr/toastr.css" rel="stylesheet"
	type="text/css" />
<script src="${staticPath}/js/toastr/toastr.min.js"></script>
<script src="${staticPath}/js/ecommon.js"></script>

</head>
<body>


<script type="text/javascript">
	var checkCodeBool = false;
	var userNameBool = true;
	var userPassWord = true;
	$(function() {
		if(top.location!=self.location){
			top.location = self.location;
		}
		if('${loginError}' != '')
		{
			showToast('用户名或密码输入错误!');
		}
		if('${codeError}' != '')
		{
			showToast('验证码输入错误!');
			$('#identifyCode').focus();
		}
	});

	var login = function() {
		var bool = check();
		if (bool) {
			var password = $('#imPassword').val();
			var newPassword = $.md5(password).toUpperCase();
			$('#imPassword').val(newPassword);
			$('form').submit();
		}
	}
	var check = function() {
		var bool = false;
		checkUserName();
		checkUserPassWord();
		if (!userNameBool) {
			showToast('用户名格式错误，在1到20个字符之间!');
		} else if (!userPassWord) {
			showToast('密码格式错误，在6到32个字符之间!');
		} else {
			bool = true;
		}
		return bool;
	}

	var checkUserName = function() {
		if ($('#userLoginName').val().length > 20) {
			userNameBool = false;
		} else if ($('#userLoginName').val().length < 1) {
			showToast('账户不能少于1个长度!');
			userNameBool = false;
		} else {
			userNameBool = true;
		}
	}

	var checkUserPassWord = function() {
		if ($('#imPassword').val().length > 64) {
			userPassWord = false;
		} else if ($('#imPassword').val().length < 6) {
			userPassWord = false;
		} else {
			userPassWord = true;
		}
	}

	$("body").keydown(function() {
		if (event.keyCode == "13") {
			login();
	    };
	});

	function reloadImg(){
		$('#imgId').attr('src', '${basePath}/defaultKaptcha?date=' + new Date());
		$('#identifyCode').focus();
	}

</script>

<body id="login-user">
    <div class="main">
        <div class="login">
            <img src="${basePath}/images/logo1.png" alt="FHS-演示系统">
            <form action="${basePath}/securityLogin" method="POST">
                <label for="">用户</label>
                <input type="text" id="userLoginName" name="userLoginName" placeholder="请输入用户名">
                <label for="">密码</label>
                <input type="password" name="password" id="imPassword" placeholder="请输入密码">

                <label for="">验证码</label>
                <span style="width:100%;display:inline-block; position:relative;">
                	<input type="text" name="identifyCode" id="identifyCode" placeholder="请输入验证码" maxlength="4">
                	<img id="imgId" src="${basePath}/defaultKaptcha" onclick="reloadImg()" style="position:absolute;right:0px;top:-20px;z-index:9;" />
                </span>

	            <div style="clear: both;"></div>
                <button type="button" onclick="login();">登录</button>
            </form>
        </div>
        <div class="right"></div>
    </div>
</body>

</html>