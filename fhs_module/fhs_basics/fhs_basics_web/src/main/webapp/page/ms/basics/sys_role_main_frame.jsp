<%@ page language="java" contentType="text/html;charset=utf-8"
         pageEncoding="utf-8"%>
<html>
<head>
    <jsp:include page="/page/ms/common/js_css_include.jsp"></jsp:include>
</head>
<body style="width: 100%; ">
<iframe width="17%" height="95%" frameborder="no" border="0"
        marginwidth="0" marginheight="0" scrolling="auto"
        allowtransparency="yes" id="left"
        src="${basePath}page/ms/ucenter/emp_organization_tree.jsp"></iframe>
<iframe width="82%" height="95%" frameborder="no" border="0"
        marginwidth="0" marginheight="0" scrolling="auto"
        allowtransparency="yes" id="rightFrame"
        src="${basePath}page/ms/ucenter/sys_role_list.jsp?organizationId=null"></iframe>

<div id="addOrUpdateDialog" class="easyui-dialog"
     data-options="iconCls:'icon-save'"  closed="true"
     style="width: 820px; height: 410px; padding: 5px;font-size: 14px;" >

    <script type="text/javascript">
        function changeRight(treeId, name) {
            if (treeId == '') {
                return;
            }
            $('#rightFrame').attr(
                'src',
                '${basePath}page/ms/basics/sys_role_list.jsp?organizationId='+treeId+"&organizationName="+name);
        }

        function openMenuAddOrUpdate(url , title) {
            openDialog(url,title);
            $(".window-mask").width("1000px")
        }
    </script>
</body>
</html>
