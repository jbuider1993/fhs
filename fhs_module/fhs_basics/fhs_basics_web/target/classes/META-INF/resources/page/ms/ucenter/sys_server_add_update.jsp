<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/page/ms/common/form_include.jsp"></jsp:include>
<jsp:include page="/page/ms/common/bdmap_include.jsp"></jsp:include>
<%@ taglib prefix="hfs" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="hfst" uri="/WEB-INF/hfs.tld"%>

<form id="addUpdateForm" method="post">

  	<div class="fitem">
		<hfst:bigInput  maxLenth="100" name="serverName" title="服务名称" required="true" />
	</div>
	<div class="fitem">
		<hfst:bigInput  maxLenth="200" name="serverUrl" title="服务链接" required="true" />
	</div>
  	<div class="fitem">
		<hfst:textareaTag  maxLenth="200" rows="3" cols="20" name="remarks" title="备注"  required="true" />
	</div>
</form>
<hfs:readyTag nameSpace="sysServer"/>
<script type="text/javascript">
function initHandler() {

}
function loadFormHandler(info) {

}
function saveFormHandler(info) {

}
function saveAfterSuccessHandler() {

}
function saveAfterErrorHandler() {

}
</script>