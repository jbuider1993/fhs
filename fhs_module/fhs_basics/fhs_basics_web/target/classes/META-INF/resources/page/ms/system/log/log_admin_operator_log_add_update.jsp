<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/page/ms/common/form_include.jsp"></jsp:include>
<%@taglib prefix="hfst" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="hfs" uri="http://www.ylmo2o.com/tag/hfs"%>
<form id="addUpdateForm" method="post">
	<div class="fitem">
		<hfs:input  maxLenth="32" name="operatorId" title="操作用户" required="true" />
		<hfs:wordBook name="logType" title="操作类型" required="true" code="log_type" />
	</div>
	<div class="fitem">
		<hfs:input  maxLenth="32" name="networkIp" title="ip地址" required="true" />
		<hfs:dateInputTag title="创建时间" name="createTime" onClick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss' })" required="true" />
	</div>
	<div class="fitem">
		<hfs:bigInput  maxLenth="200" name="url" title="请求的url"  required="true" />
	</div>
	<div class="fitem">
		<hfs:bigInput  maxLenth="500" name="operatDesc" title="操作描述" required="true" />
	</div>
	<div class="fitem">
		<hfs:textareaTag  rows="4" cols="20" name="reqParam" title="参数"  required="true" />
	</div>
</form>
<hfst:readyTag nameSpace="logAdminOperatorLog"/>
<script type="text/javascript">

function initHandler() {
//初始化
}
function loadFormHandler(info) {
//加载数据
}
function saveFormHandler(info) {
//保存数据
}
function saveAfterSuccessHandler() {
//保存成功处理
}
function saveAfterErrorHandler() {
//保存失败后处理
}
</script>