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
		src="${basePath}page/ms/ucenter/emp_organization_tree.jsp"></iframe>
<iframe width="80%" height="98%" frameborder="no" border="0"
		marginwidth="0" marginheight="0" scrolling="auto"
		allowtransparency="yes" id="rightFrame"
		src="${basePath}page/ms/ucenter/sys_organization_list.jsp"></iframe>

<script type="text/javascript">
    function changeRight(treeId, name) {
        /* alert(treeId+","+ menuType+"," + parentId); */
        if (!name) {
            name = "";
        }
        if (treeId == '') {
            return;
        }
        $('#rightFrame').attr(
            'src',
            '${basePath}page/ms/basics/sys_organization_list.jsp?id='
            + treeId+'&name='
            + encodeURI(encodeURI(name)));
    }
    /*  function changeRight_but(treeId, name, Id) {
     if (treeId != '')
     $('#buttonFrame').attr(
     'src',
     '${basePath}page/basics/admin_menubutton_list.jsp?menuId='
		 + Id + '&parentId=' + treeId + "&menuName="
		 + encodeURI(name));
		 }  */
</script>
</body>
</html>
