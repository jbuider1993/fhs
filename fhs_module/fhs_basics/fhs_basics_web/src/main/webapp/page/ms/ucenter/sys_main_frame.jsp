<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<jsp:include page="/page/ms/common/js_css_include.jsp"></jsp:include>
</head>
<body style="width: 100%; ">
	<iframe width="17%" height="98%" frameborder="no" border="0"
		marginwidth="0" marginheight="0" scrolling="auto"
		allowtransparency="yes" id="left"
		src="${basePath}page/ms/ucenter/sys_menu_tree.jsp"></iframe>
	<iframe width="45%" height="98%" frameborder="no" border="0"
		marginwidth="0" marginheight="0" scrolling="auto"
		allowtransparency="yes" id="rightFrame"
		src="${basePath}page/ms/ucenter/sys_menu_list.jsp"></iframe>
	<iframe width="37%" height="98%" frameborder="no" border="0"
		marginwidth="0" marginheight="0" scrolling="auto"
		allowtransparency="yes" id="buttonFrame"
		src="${basePath}page/ms/ucenter/sys_menu_permission_list.jsp"></iframe>

	<div id="addOrUpdateDialog" class="easyui-dialog" 
		data-options="iconCls:'icon-save'"  closed="true"
		style="width: 820px; height: 410px; padding: 5px;font-size: 14px;" >
		
	<script type="text/javascript">
		function changeRight(treeId, menuType, menuname) {
			if (!menuType) {
				menuType = "";
			}
			if (treeId == '') {
				return;
			}
			$('#rightFrame').attr(
					'src',
					'${basePath}page/ms/ucenter/sys_menu_list.jsp?menuId=' + treeId
							+ '&menuType=' + menuType + '&menuName='
							+ encodeURI(encodeURI(menuname)));
			$('#buttonFrame').attr('src',
					'${basePath}page/ms/ucenter/sys_menu_permission_list.jsp');
		}

		function changeRight_but(treeId, name, Id) {
			if (treeId != '')
				$('#buttonFrame').attr(
						'src',
						'${basePath}page/ms/ucenter/sys_menu_permission_list.jsp?menuId='
								+ Id + '&parentId=' + treeId + "&menuName="
								+ encodeURI(name));
		}

		function openMenuAddOrUpdate(url , title) {
			openDialog(url,title);
			$(".window-mask").width("1000px")
		}
	</script>
	</div>
</body>
</html>
