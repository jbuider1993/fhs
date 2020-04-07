<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>

<html>
<head>
<jsp:include page="/src/main/webapp/page/common/js_css_include.jsp"></jsp:include>

<!--  -->
<link href="${basePath}js/zTree_v3/css/zTreeStyle/zTreeStyle.css"
	rel="stylesheet" type="text/css" />
<%-- <link href="${basePath}js/zTree_v3/css/demo.css" rel="stylesheet" type="text/css" /> --%>
<script type="text/javascript"
	src="${basePath}js/zTree_v3/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="${basePath}js/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="${basePath}js/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript"
	src="${basePath}js/zTree_v3/js/jquery.ztree.exedit-3.5.js"></script>

</head>
<title>选择工作流</title>

<script type="text/javascript">
	//配置
	var setting = {
		data : {
		},
		callback : {
			onClick : clickSelect
		}
	};
	$(function() {
		$.post("${basePath}workFlowJbpmXml/getWorkFlowTree.do", {},
				function(jsonStr) {
					$.fn.zTree.init($("#treeData"), setting, jsonStr);
					var treeObj = $.fn.zTree.getZTreeObj("treeData");
					var nodes = treeObj.getNodes();
					if (nodes.length > 0) {
						var node = nodes[0];
						treeObj.selectNode(node);
						treeObj.expandAll(false);
						treeObj.expandNode(node, true, false, false);
					}

				}, "json");

	});
	function clickSelect(event, treeId, treeNode, clickFlag) {
	    if(treeNode.id)
	    {
	        console.log(window.parent.changeRight);
	        window.parent
				.changeRight(treeNode.id);
	    }
		
	}
</script>

</head>
<body>
	<div>
		<ul id="treeData" class="ztree"></ul>
	</div>
</body>
</html>
