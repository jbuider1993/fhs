<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<jsp:include page="/src/main/webapp/page/common/js_css_include.jsp"></jsp:include>
	</head>

	<body>
		<form id="firstForm">
			<div id="cusForm" style="padding-top: 10px;padding-left: 10px;">

			</div>
			<div id="formExtends">

			</div>
			<center>

				<a id="subBtn" href="javascript:void(0);" class="easyui-linkbutton" onclick="save()">提交</a>
			</center>
		</form>
	</body>
	<script>
		var formId = '';
		$.post("${basePath}workFlowJbpmXml/getFirstForm.do", { id: '${param.id}' },
			function(formData) {
				$('#cusForm').html(formData.parseHtml);
				formId = formData.id;
				if(formData.extendJsp && formData.extendJsp != '') {
					$.post("${basePath}" + formData.extendJsp, {},
						function(jspHtml) {
							$('#formExtends').html(jspHtml);
						}, "html");
				}
			}, "json");

        //提交表单
		function save() {
			$.post("${basePath}pubWorkFlow/start.do?workFlowId=${param.id}&formId=" + formId, $('#firstForm').serializeObject(),
				function(data) {
					console.log(data);
				}, "json");
		}
	</script>

</html>