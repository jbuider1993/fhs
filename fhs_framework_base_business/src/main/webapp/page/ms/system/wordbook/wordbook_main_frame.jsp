<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<jsp:include page="/page/ms/common/js_css_include.jsp"></jsp:include>
</head>
<body style="width: 100%; height: 750px;">
	
	<iframe width="50%" height="100%" frameborder="no" border="0"
		marginwidth="0" marginheight="0" scrolling="auto"
		allowtransparency="yes" id="leftFrame"
		src="${basePath}page/ms/system/wordbook/wordbook_group_list.jsp"></iframe>
	<iframe width="48%" height="100%" frameborder="no" border="0"
		marginwidth="0" marginheight="0" scrolling="auto"
		allowtransparency="yes" id="rightFrame"
		src="${basePath}page/ms/system/wordbook/wordbook_list.jsp"></iframe>
	<script type="text/javascript">
		function changeRight(wordbookGroupCode) {
			document.getElementById('rightFrame').contentWindow.getWordbookList(wordbookGroupCode);
		}
		function clearWordbookGrid() {
			document.getElementById('rightFrame').contentWindow.clearWordbookGrid();
		}

	</script>
</body>
</html>
