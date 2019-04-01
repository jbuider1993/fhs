<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="hfs" uri="http://www.ylmo2o.com/tag/hfs"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${param.isFrame eq true}">
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<jsp:include page="/page/ms/common/js_css_include.jsp"></jsp:include>
	</head>
	<body class="skin-1">
</c:if>

<jsp:include page="/page/ms/common/form_include.jsp"></jsp:include>
	<!--修改个人信息-->
	<div id="updateUserInfoDiv">
		<form id="updateUserInfoForm" method="post">
			<div class="fitem">
				<hfs:input  maxLenth="20" name="userName" dataType="s2-10" title="用户名" required="true" />
				<hfs:input  maxLenth="255" name="email" dataType="e|empty" title="邮箱"  />
			</div>
			<div class="fitem">
				<hfs:input  maxLenth="20" name="mobile" dataType="*,m" title="手机号" required="true" />
			</div>

		</form>
	</div>
	<div id="dlg-buttons">
		<center>
			<a href="javascript:void(0)" class="easyui-linkbutton btn btn-suc l-btn l-btn-small"
				onclick="save()">提交</a> <a href="javascript:void(0)"
				class="easyui-linkbutton btn btn-can l-btn l-btn-small" onclick="top.closeDialog();">关闭</a>
		</center>
	</div>

<script type="text/javascript">
	var url_temp = "${basePath}ms/sysUser/updateOwnUserInfo";
	var updateUserInfoForm = null;
	$(document).ready(function() {
		//form验证。
        updateUserInfoForm = $('#updateUserInfoForm').Validform({
			tiptype : 5
		});
        $('#userName').val('${sessionUser.userName}');
        $('#email').val('${sessionUser.email}');
        $('#mobile').val('${sessionUser.mobile}');
		// 如果是编辑页面，就初始化一些数据
	})

	function save() {

		$('#updateUserInfoForm').form('submit', {
			url : url_temp,
			onSubmit : function() {
				return (updateUserInfoForm.check());
			},
			success : function(d) {
				d = $.parseJSON(d);
				if (d.result) {
					Ealert('修改成功');
                    setTimeout(function(){top.closeDialog()},2000);
				} else {
					EalertE('修改失败，请联系管理员');
				}
			}
		});

	}
</script>
<c:if test="${param.isFrame==true}">
	</body>
	</html>
</c:if>