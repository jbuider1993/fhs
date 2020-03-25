<%@ page language="java" contentType="text/html;charset=gbk"
	pageEncoding="GBK"%>
<html>
<head>
<!-- 引入ZTree插件 -->
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
<!-- #引入ZTree插件 -->
</head>
<title>菜单tree</title>

<script type="text/javascript">
	$(function() {
		$.post("${basePath}adminMenu/getMenuTreesData", {},
				function(jsonStr) {
					$.fn.zTree.init($("#webTreeData"), setting, jsonStr);
					var webtreeObj = $.fn.zTree.getZTreeObj("webTreeData");
					var webNodeArrays = '${param.webKeys}';
					webtreeObj.checkAllNodes(false);
					var webNodeArray = webNodeArrays.split(",");
					$.each(webNodeArray,function(i,n){
						var webnodes = webtreeObj.getNodesByParam("id", webNodeArray[i], null);
						if(webnodes && webnodes.length > 0){
							webtreeObj.checkNode(webnodes[0],true,false);
						}
					})
					
				}, "json");

	});
</script>
<script type="text/javascript">
	//ZTree 配置
	var setting = {
		check: {
			        enable: true
			    },
		data : {
			simpleData : {
				idKey : "id", //主键id
				pIdKey : "pid",
				name : "name",
				menuType : "menuType",
				enable : true
			}
		},
		callback : {
			onCheck : checkWebMenu,
		}
	};
	
	function checkWebMenu(event, treeId, treeNode) {
		var webNodesIds = new Array();
		var webCheckNodesObj = $.fn.zTree.getZTreeObj(treeId).getCheckedNodes(true);
		$.each(webCheckNodesObj,function(i,n){
			webNodesIds.push(webCheckNodesObj[i].id);
		})
		window.parent.setMenuId('#webModelKeys',webNodesIds.join(","))
	}
</script>

</head>
<body>
	<div>
		<ul id="webTreeData" class="ztree"></ul>
	</div>
</body>
</html>
