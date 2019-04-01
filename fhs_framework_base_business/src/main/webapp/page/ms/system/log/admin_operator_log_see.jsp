<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/page/common/form_include.jsp"></jsp:include>
<form id="addOrUpdateForm" method="get">

<div class="fitem">
	<div class="fitemDiv">
		<label>操作用户id:</label><input id="operatorId" name="operatorId" class="easyui-validatebox" type="text" readonly="readonly" />
	</div>
	<div class="fitemDiv">
		<label>创建时间:</label><input id="createTime" name="createTime" class="easyui-validatebox" type="text" readonly="readonly" />
	</div>
</div>

<div class="fitem">
	<div class="fitemDiv">
		<label>操作类型:</label>
		<select id="logType" name="logType"
				class="easyui-combobox"
				data-options="
                                valueField: 'wordbookCode',
                                textField: 'wordbookDesc',
                                editable : false,
                                panelHeight: 'auto',
                                url: '${pub_service_url}wordbook/getData?wordbookGroupCode=log_type&jsonpCallback=?'," readonly="readonly">
        </select>
	</div>
	<div class="fitemDiv">
		<label>ip地址:</label>
		<input id="networkIp" name="networkIp" class="easyui-validatebox" type="text" readonly="readonly" />
	</div>
</div>

<div class="fitem">
	<div class="bigLabelDiv">
		<label>请求的url:</label>
	</div>
	<div class="bigContent">
		<input id="url" name="url" class="big_text" type="text" readonly="readonly" />
	</div>
</div>

<div class="fitem">
	<div class="bigLabelDiv">
		<label>操作描述:</label>
	</div>
	<div class="bigContent">
		<input id="operatDesc" name="operatDesc" class="big_text" type="text" readonly="readonly" />
	</div>
</div>

<div class="fitem">
		<div class="bigLabelDiv">
			<label>请求参数:</label>
		</div>
		<div class="bigContent">
			<textarea id="reqParam" name="reqParam" style="margin-top:10px" class="big_text" rows="5" cols="20" readonly="readonly" />
		</div>
</div>

	<script type="text/javascript">
		$(function(){
			$('#addOrUpdateForm').form('load',
					'${basePath}logAdminOperatorLog/getBeanData?id=${param.id}');
		});

		function closeDialog(){
			$('#addOrUpdateDialog').dialog('close');
		}

	</script>
</form>