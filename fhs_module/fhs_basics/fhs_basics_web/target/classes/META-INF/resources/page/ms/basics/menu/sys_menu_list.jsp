<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
    String menuName = "";
			String parm = request.getParameter("menuName");
			if (parm != null)
				menuName = URLDecoder.decode(parm, "UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="/page/ms/common/js_css_include.jsp"></jsp:include>
</head>
<body>

	<table id="sysMenuGrid" title="<%=menuName %>子菜单列表"
		class="easyui-datagrid" fit="true" style="width: 90%;"
		url="${basePath}ms/sysMenu/listData?menuId=${param.menuId}"
		pagination="false" rownumbers="true" fitColumns="true"
		singleSelect="true" pageSize="10" toolbar="#toolbar"
		data-options="onClickRow:readButtons" striped="true">
		<thead>
			<tr>
				<th align='center' field="menuName" width="50%">菜单名</th>
				<th align='center' field="menuUrl" width="49%">菜单链接</th>
			</tr>
		</thead>
	</table>


	<div id="toolbar">
		<div style="margin-bottom: 5px;">
			<shiro:hasPermission name="sysMenu:add">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" onclick="addMenu()">添加</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sysMenu:update">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true"
					onclick="addSelectRowFun('sysMenuGrid',updateMenu);">修改</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sysMenu:del">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true"
					onclick="pubDel('sysMenuGrid','${basePath}ms/sysMenu/del?id=','menuId');">删除</a>
			</shiro:hasPermission>

		</div>
	</div>
</body>

<script type="text/javascript">
	//根据菜单编号获取按钮列表
	function readButtons(index, row) {
		parent.changeRight_but(row.menuIdE, row.menuName, row.menuId);
	}
	
	//添加菜单
	function addMenu() {
		parent.openMenuAddOrUpdate('${basePath}/b/page-ms-basics/sys_menu_add_update?isEdit=false&parentId=${param.menuId}&menuType=${menuType}','添加菜单');
	}

	//修改菜单
	function updateMenu(row) {
		parent.openMenuAddOrUpdate('${basePath}/b/page-ms-basics/sys_menu_add_update?isEdit=true&parentId=${param.menuId}&menuType=${menuType}&id='+row.menuId,'修改菜单');
	}

	//重新加载
	function reload() {
		$('#sysMenuGrid').datagrid('load',{});
	}
</script>
</html>
