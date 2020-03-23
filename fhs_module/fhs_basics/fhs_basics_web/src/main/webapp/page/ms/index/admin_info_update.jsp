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
        <div class="fitem">
            <input id="userId" name="userId" hidden="true"/>
            <hfs:input  maxLenth="20" name="userName" dataType="s2-10" title="用户名" required="true" />
            <hfs:input  maxLenth="20" name="userLoginName" dataType="/^[a-zA-Z0-9_]{1,}$/" title="登录名" required="true" />
        </div>
        <div class="fitem">
            <hfs:input  maxLenth="20" name="mobile" dataType="*,m" title="手机号" required="true" />
            <hfs:input name="sex" title="性别" />
        </div>
        <div class="fitem">
            <hfs:input  maxLenth="255" name="email" dataType="e|empty" title="邮箱"  />
            <hfs:province name="provinceId" areaName="areaId" cityName="cityId" required="false" title="省份" />
        </div>
        <div class="fitem">
            <hfs:input  maxLenth="255" name="organizationId" title="所属机构" required="true" />
        </div>
	</div>
	<div id="dlg-buttons">
		<center>
			 <a href="javascript:void(0)"
				class="easyui-linkbutton btn btn-can l-btn l-btn-small" onclick="top.closeDialog();">关闭</a>
		</center>
	</div>

<script type="text/javascript">
    var isInitProvinceId = false;
	$(document).ready(function() {
        renderView("updateUserInfoDiv");
        getOwnUserInfo();
	})

    function getOwnUserInfo() {
        $.ajax({
            type: 'get',
            url: "${basePath}ms/sysUser/getOwnUserInfo",
            dataType: 'json',
            async:false,
            success: function(res){
                $("#userId").val(res.userId);
                $("input[name='userName']").val(res.userName);
                $("input[name='userLoginName']").val(res.userLoginName);
                $("input[name='mobile']").val(res.mobile)
                $("input[name='sex']").val(res.transMap.sexName);
                $("input[name='email']").val(res.email);
                $("input[name='organizationId']").val(res.transMap.organizationIdOrganizationName);
                $("input[name='provinceId']").val(res.provinceId);
            }
        });
    }
</script>
<c:if test="${param.isFrame==true}">
	</body>
	</html>
</c:if>