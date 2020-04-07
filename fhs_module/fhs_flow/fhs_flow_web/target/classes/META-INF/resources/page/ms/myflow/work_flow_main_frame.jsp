<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<jsp:include page="/src/main/webapp/page/common/js_css_include.jsp"></jsp:include>
</head>
<body style="width: 100%; height: 100%;">
	<iframe width="17%" height="100%" frameborder="no" border="0"
		marginwidth="0" marginheight="0" scrolling="auto"
		allowtransparency="yes" id="left" style="border-right: 1px solid;"
		src="${basePath}page/myworkflow/emp_orgtree.jsp"></iframe>
	<iframe width="70%" height="100%" frameborder="no" border="0"
		marginwidth="0" marginheight="0" scrolling="auto"
		allowtransparency="yes" id="rightFrame" ></iframe>

	
</body>
<script>
		function changeRight(treeId) {
			$('#rightFrame').attr(
					'src',
					'${basePath}page/myworkflow/work_flow_first_form.jsp?id=' + treeId);
		}
</script>
</html>
