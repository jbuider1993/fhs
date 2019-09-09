<%@ page language="java" contentType="text/html; charset=UTF-8"
		 isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ taglib prefix="hfs" uri="http://www.ylmo2o.com/tag/hfs"%>
	<jsp:include page="/page/ms/common/js_css_include.jsp"></jsp:include>
</head>
<body>

<table id="sysUserGrid" title="${param.organizationName}用户列表" class="easyui-datagrid"
	   fit="true" style="width: 90%;" url="${basePath}ms/sysUser/findPage/${param.organizationId}"
	   pagination="true" rownumbers="true" fitColumns="true"
	   singleSelect="true" pageSize="10" striped="true" toolbar="#toolbar">
	<thead>
	<tr>
		<th align='center' field="userId" hidden="true"></th>
		<th align='center' field="userName" width="15%">用户名</th>
		<th align='center' field="userLoginName" width="15%">登录名</th>
		<th align='center' field="transMap.sexName" width="10%">性别</th>
		<th align='center' field="transMap.organizationIdOrganizationName" width="19%">机构</th>
		<th align='center' field="mobile" width="10%">电话</th>
		<th align='center' field="email" width="20%">邮箱</th>
		<th align='center' field="transMap.isDisableName" width="10%">状态</th>
	</tr>
	</thead>
</table>


<div id="toolbar">
	<div style="margin-bottom: 5px;">
		<shiro:hasPermission name="sysUser:see">
			<input type="text" id="user_rel_name" placeholder="用户名">
			<a href="javascript:void(0)" class="easyui-linkbutton"
			   plain="true" iconCls="icon-search" onclick="reload()">查询</a>
		</shiro:hasPermission>

		<shiro:hasPermission name="sysUser:add">
			<a href="javascript:void(0)" class="easyui-linkbutton"
			   plain="true" iconCls="icon-add" onclick="addUser()">添加</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sysUser:update">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="addSelectRowFun('sysUserGrid',update);">修改</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sysUser:see">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-view" plain="true" onclick="addSelectRowFun('sysUserGrid',view);">查看</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sysUser:del">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="pubDel('sysUserGrid','${basePath}ms/sysUser/delSysUser?id=','userId');">删除</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sysUser:refreshRedisCache">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="refreshRedisCache()">刷新所有用户缓存</a>
		</shiro:hasPermission>

	</div>
</div>
<div id="dlg" class="easyui-dialog" title="添加/修改用户"
	 data-options="iconCls:'icon-save'"  closed="true"
	 style="width: 900px; height: 510px; padding: 10px;max-height: 95%" buttons="#dlg-buttons">
</div>
<div id="dlg-buttons">
	<a href="#" id="saveButten" class="easyui-linkbutton" onclick="save()">保存</a> <a
		href="#" class="easyui-linkbutton" onclick="closeDialog()">返回</a>
</div>
</body>

<script type="text/javascript">
    //默认关闭dialog
    $(function() {
        $('#dlg').dialog('close');
    });
    //关闭dialog方法
    function closeDialog() {
        $('#dlg').dialog('close');
    }
    //打开dialog
    function openDialog(url, title) {
        $('#dlg').load(url, function() {
            $('#dlg').dialog('open').dialog('setTitle', title);
            $.parser.parse($('#dlg'));
        });

    }
    /**
     * 添加用户
     */
    function addUser() {
        openDialog('${basePath}page/ms/ucenter/sys_user_add_update.jsp?organizationId=${param.organizationId}&isAdd=true', '添加用户');
    }

    /**
     * 修改用户
     */
    function update(row) {
        openDialog('${basePath}page/ms/ucenter/sys_user_add_update.jsp?isEdit=true&id=' + row.userId, '修改用户');
    }

    /**
     * 查看用户
     */
    function view(row) {
        openDialog('${basePath}page/ms/ucenter/sys_user_add_update.jsp?isView=true&id='+row.userId, '查看用户');
    }

    //重新加载
    function reload() {
        $('#sysUserGrid').datagrid('load', {
            'userName' : $('#user_rel_name').val()
        });
    }

    /**
     * 刷新所有用户缓存
     */
    function refreshRedisCache(){
        $.ajax({
            url : '${basePath}ms/sysUser/refreshRedisCache',
			dataType:'json',
            success : function(data){
                if(data.code == 200)
                {
                    Ealert('刷新成功');
                }
                else
                {
                    Ealert('刷新失败');
                }
            }
        })
    }
</script>
</html>
