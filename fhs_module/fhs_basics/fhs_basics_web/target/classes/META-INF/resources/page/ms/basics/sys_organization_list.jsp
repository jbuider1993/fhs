<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 isELIgnored="false" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="hfs" uri="http://www.ylmo2o.com/tag/hfs"%>
<%
	String menuName = "";
	String parm = request.getParameter("name");
	if (parm != null)
		menuName = URLDecoder.decode(parm, "UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="/page/ms/common/js_css_include.jsp"></jsp:include>
</head>
<body>

<table id="sysOrganizationGrid" title="<%=menuName %>子机构列表"
	   class="easyui-datagrid" fit="true" style="width: 90%;"
	   url="${basePath}ms/sysOrganization/getPageListData?id=${param.id}"
	   pagination="true" rownumbers="true" fitColumns="true"
	   singleSelect="true" pageSize="10" toolbar="#toolbar"
	   data-options="onClickRow:readButtons" striped="true">
	<thead>
	<tr>
		<th align='center' field="name" width="21%">机构名称</th>
		<th align='center' field="transMap.isDisableName" width="8%">状态</th>
		<th align='center' field="transMap.createUserUserName" width="10%">创建人</th>
		<th align='center' field="createTime" width="15%">创建时间</th>
		<th align='center' field="transMap.updateUserUserName" width="10%">修改人</th>
		<th align='center' field="updateTime" width="15%">修改时间</th>
		<hfs:listOperator nameSpace="sysOrganization" updateFuncName="updateMenu" showView="false" delFuncName="deleteOrg" delReqUrl="ms/sysOrganization/del?id=" pkField="id" alignWay="center" thWidth="20%"></hfs:listOperator>
	</tr>
	</thead>
</table>


<div id="toolbar">
	<div style="margin-bottom: 5px;">
		<shiro:hasPermission name="sysOrganization:add">
			<a href="javascript:void(0)" class="easyui-linkbutton"
			   iconCls="icon-add" plain="true" onclick="addMenu()">添加</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sysOrganization:refreshRedisCache">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="refreshRedisCache()">刷新机构缓存</a>
		</shiro:hasPermission>
	</div>
</div>
<div id="addOrUpdateDialog" class="easyui-dialog"  closed="true"
	 style="width: 850px; height: 410px; padding: 10px" buttons="#dlg-buttons"></div>
</body>
<div id="dlg-buttons">
	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="save()">保存</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="closeDialog()">返回</a>
</div>

<script type="text/javascript">
    //根据机构编号获取子机构列表
    function readButtons(index, row) {
        //parent.changeRight_but(row.menuIdE, row.name, row.menuId);
    }
    //添加机构
    function addMenu() {
        var parentId = '${param.id}';
        openDialog(
            '${basePath}page/ms/basics/sys_organization_add_update.jsp?parentId='+parentId,
            '添加机构');
    }

    //修改机构
    function updateMenu(pkField) {
        openDialog(
            '${basePath}page/ms/basics/sys_organization_add_update.jsp?isEdit=true&id=' + pkField, "修改机构");
    }

    //重新加载
    function reload() {
        $('#sysOrganizationGrid').datagrid('load', {
            name : encodeURI($('#user_rel_name').val())
        });
    }

    /**
     * 刷新所有机构缓存
     */
    function refreshRedisCache(){
        $.ajax({
            url : '${basePath}ms/sysOrganization/refreshRedisCache',
            success : function(data){
                if(data.code == 200)
                {
                    Ealert('刷新成功');
                }
                else
                {
                    EalertE('刷新失败');
                }
            }
        })
    }

    function deleteOrg(url){
        $.messager.confirm('提示框', '确认要删除吗?', function (r) {
            if (r) {
                $.ajax({
                    type: "GET",
                    url: url,
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 200 && data.result) {
                            Ealert("操作成功");
                            window.parent.document.getElementById('left').contentWindow.location.reload(true);
                        } else {
                            EalertE("操作失败");
						}
                    },
                });
            }
        });
	}
</script>
</html>
