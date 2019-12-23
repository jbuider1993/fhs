<%@ page language="java" contentType="text/html; charset=utf-8"
isELIgnored="false" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--链接样式表-->
<link rel="Shortcut Icon" href="${staticPath}images/favicon.ico" />
<link rel="stylesheet" type="text/css"
      href="${staticPath}js/easyui/themes/metro/easyui.css" />

<link rel="stylesheet" type="text/css"
      href="${staticPath}css/jquery.toast.css" />
<link rel="stylesheet" type="text/css"
      href="${staticPath}js/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css"
      href="${staticPath}css/common.css" />
<link rel="stylesheet" type="text/css"
      href="${staticPath}css/tipso.min.css" />
<link rel="stylesheet" type="text/css"
      href="${staticPath}js/uploadfive/uploadifive.css" />
<link rel="stylesheet" type="text/css"
      href="${staticPath}js/time/timedropper.css" />
<link rel="stylesheet" type="text/css"
      href="${staticPath}css/validform.css" />
<link rel="stylesheet" type="text/css"
      href="${staticPath}css/loading_preloader.css" />

<link rel="stylesheet" type="text/css"
      href="${staticPath}js/viewer/viewer.min.css" />
<link href="${staticPath}/js/switch/lc_switch.css" rel="stylesheet" />

<script type="text/javascript" src="${staticPath}js/jquery.min.js"></script>
<script type="text/javascript" src="${staticPath}js/json2.js"></script>
<script type="text/javascript" src="${staticPath}js/tipso.min.js"></script>
<script type="text/javascript"
        src="${staticPath}js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}js/systemConfig.js"></script>
<script type="text/javascript" src="${staticPath}js/ecommon.js"></script>
<script type="text/javascript"
	src="${staticPath}js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${staticPath}js/uploadfive/jquery.uploadifive.js"></script>
<script type="text/javascript"
	src="${staticPath}js/viewer/viewer.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${staticPath}/js/copy_util.js"></script>
<script type="text/javascript" src="${staticPath}js/jquery.toast.js"></script>
<script type="text/javascript" src="${staticPath}js/listUploadFiles.js"></script>
<script type="text/javascript" src="${staticPath}js/uploadUtil.js"></script>
<script type="text/javascript" src="${staticPath}js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${staticPath}js/sweetalert/sweetalert2.js"></script>
<link rel="stylesheet" type="text/css"
	  href="${staticPath}js/sweetalert/sweetalert2.css" />
<script type="text/javascript">
var isEdit = false;
<c:if test="${!empty param.isEdit}">
isEdit = true;
</c:if>
</script>
<style type="text/css">
#ueditor,.edui-editor,.edui-editor-iframeholder{
	width: auto !important;
}

.tabPan {
	margin: 5px 0;
}
</style>
<script type="text/javascript">
<%if ("true".equals(session.getAttribute("Refresh"))) {
				out.println("location.reload();");
				session.removeAttribute("Refresh");
}%>
	$(function(){
	  	 try {

	  	     //如果我是top 就跳转到ucenter去
             if(top.location==self.location && window.location.href.indexOf("ms/index") ==-1 && window.location.href.indexOf("/ms/")!=-1) {
               //  self.location.href = '${fhs_framework_ucenter_basePath}/ms/index';
             }
         }catch(e)
		 {

		 }
	});
</script>
<script type="text/javascript">
	var ucenterUrl = "${fhs_framework_ucenter_basePath}";
var loading = window.setTimeout(hideMask,100);
var preloader = null;
var ue = null;
//隐藏遮罩层
function hideMask(){
    $("#loading").hide(100);
    preloader = window.setTimeout(hidePreloader,1000);
    window.clearTimeout(loading);
}
function hidePreloader()
{
    $("#preloader").hide();
	window.clearTimeout(preloader);
}

</script>


<div id="loading" class="loading"></div>
<div id="preloader">
	<div id="preloader_4">
		<span></span> <span></span> <span></span> <span></span> <span></span>
	</div>
</div>