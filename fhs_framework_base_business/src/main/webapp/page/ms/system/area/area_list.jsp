<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<jsp:include page="/page/ms/common/js_css_include.jsp"></jsp:include>
</head>

<body>
	<table id="voDataGrid" title="地区列表" class="easyui-datagrid"
		fit="true" style="width: 100%;"
		url="${basePath}ms/area/findPage" pagination="true"
		rownumbers="true" fitcolumns="true" singleselect="true" pagesize="10"
		toolbar="#toolbar" striped="true">
		<thead>
			<tr>
				<th align="center" field="id" width="24%">区域编号</th>
				<th align="center" field="areaName" width="25%">区域名称</th>
				<th align="center" field="areaCode" width="25%">区域代码</th>
				<th align="center" field="transMap.areaParentIdAreaName" sortable="false" width="25%">父级区域</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<div style="margin-bottom: 5px;">
			<shiro:hasPermission name="area:see">
				<input id="idF" name="idF" type="text" placeholder="区域编号">
				<input id="areaNameF" name="areaNameF" type="text" placeholder="区域名称">
				<input id="areaCodeF" name="areaCodeF" type="text" placeholder="区域代码">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-search" plain="true" onclick="reload()">查询</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="area:refreshRedisCache">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="refreshRedisCache()">刷新所有区域缓存</a>
			</shiro:hasPermission>
		</div>
	</div>

	<div id="addOrUpdateDialog" class="easyui-dialog" title="添加/修改应用"
		data-options="iconCls:'icon-save'" closed="true"
		style="width: 900px; height: 500px; padding: 10px" buttons="#dlg-buttons"></div>

	<div id="dlg-buttons">
		<a id="subBtn" href="#" class="easyui-linkbutton" onclick="save()">保存</a>
		<a id="cloBtn" href="#" class="easyui-linkbutton" onclick="closeDialog()">关闭</a>
	</div>

	<script>

		/** * 重新加载 */
		function reload() {
			$('#voDataGrid').datagrid('load', {
                id : trim($("#idF").val()),
                areaName : trim($("#areaNameF").val()),
                areaCode : trim($("#areaCodeF").val())
			});
		}

        /**
         * 刷新所有地区缓存
         */
        function refreshRedisCache(){
            $.ajax({
                url : '${basePath}ms/area/refreshRedisCache',
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
</body>
</html>