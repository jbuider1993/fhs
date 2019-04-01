<%@ page language="java" contentType="text/html;charset=gbk"
	pageEncoding="GBK"%>

<html>
<head>
<jsp:include page="/page/common/js_css_include.jsp"></jsp:include>

<!-- 引入ZTree插件 -->
<link href="${staticPath}js/zTree_v3/css/zTreeStyle/zTreeStyle.css"
	rel="stylesheet" type="text/css" />
<%-- <link href="${basePath}js/zTree_v3/css/demo.css" rel="stylesheet" type="text/css" /> --%>
<script type="text/javascript"
	src="${staticPath}js/zTree_v3/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="${staticPath}js/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="${staticPath}js/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript"
	src="${staticPath}js/zTree_v3/js/jquery.ztree.exedit-3.5.js"></script>
<!-- #引入ZTree插件 -->
</head>
<title>菜单tree</title>

<script type="text/javascript">
	$(function() {
		$.post("${basePath}systemOrganization/getTreesData", {},
				function(jsonStr) {
					$.fn.zTree.init($("#organizationTreeData"), setting, jsonStr);
					var treeObj = $.fn.zTree.getZTreeObj("organizationTreeData");
					//获取节点
					var nodes = treeObj.getNodes();
					if (nodes.length > 0) {
						var node = nodes[0];
						treeObj.selectNode(node);
						treeObj.expandAll(false);
						treeObj.expandNode(node, true, false, false);
						window.parent.changeRight(node.id, node.name);
					}

				}, "json");

	});
</script>
<script type="text/javascript">
	//ZTree 配置
	var setting = {
		data : {
			simpleData : {
				idKey : "id", //主键id
				pIdKey : "fatherId",
				name : "name",
				enable : true
			}
		},
		callback : {
			onExpand : zTreeRegisterTarget,
			beforeClick : zTreeBeforeClick,
			onClick : clickSelect,
			beforeDrag : beforeDrags
		}
	};
	function zTreeRegisterTarget(event, treeId, treeNode) {

	}
	function zTreeBeforeClick(treeId, treeNode, clickFlag) {

	};
	function clickSelect(event, treeId, treeNode, clickFlag) {
		window.parent
				.changeRight(treeNode.id,  treeNode.name);
	}
	function beforeDrags(treeId, treeNodes) {
		return false;
	}
</script>

</head>
<body>
	<div>
		<ul id="organizationTreeData" class="ztree"></ul>
	</div>
</body>
</html>
