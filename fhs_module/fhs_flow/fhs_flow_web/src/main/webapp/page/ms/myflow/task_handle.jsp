<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>工作处理</title>
		<jsp:include page="/src/main/webapp/page/common/js_css_include.jsp"></jsp:include>
	</head>

	<body>
		<!--
          	作者：wanglei
          	时间：2017-08-02
          	描述：主要用来显示历史的信息
          -->
		<div style="height: 70%;" id="historyDiv">

		</div>
		<!--
          	作者：wanglei
          	时间：2017-08-02
          	描述：主要用来显示当前步骤信息
          -->
		<form id="handleForm">
			<div style="height: 29%;" id="handleDiv">

			</div>
			<div id="btnDiv" style="display: none;">
				<center>
					<a id="subBtn" href="javascript:void(0);" class="easyui-linkbutton" onclick="complateTask()">提交</a>
					<a id="subBtn" href="javascript:void(0);" class="easyui-linkbutton" onclick="window.parent.parent.closeDialog()">取消</a>
				</center>
			</div>
		</form>
	</body>
	<script>
		var divNum = 0;
		var task = {};
		$(function() {
			task = window.parent.parent.getTaskInfo();
			getDivContent(task.creator + '于' + task.create_datetime + '创建', task.creator, task.form_id, task.form_key);
			if(!task.isView) {
				initHandleForm(task.FORM_KEY_, task.NAME_);
				$('#btnDiv').show();
			}
			if(task.extend_json) {
				task.extend_json = $.parseJSON(task.extend_json);;
				for(i = 0; i < task.extend_json.length; i++) {
					getDivContent(task.extend_json[i].taskName, task.extend_json[i].operator, task.extend_json[i].formId, task.extend_json[i].formKey);
				}
			}
		})

		//获取div
		function getDivContent(taskName, operator, formId, formKey) {
			$.ajax({
				type: "get",
				url: "${basePath}/pubWorkFlow/getFormDesignInfo.do?formId=" + formId,
				dataType: 'json',
				success: function(formData) {
					var divId = 'formDiv_' + divNum;
					divNum += 1;
					var div = '<div id="' + divId + '" class="easyui-panel" title="' + taskName + '" style="width:100%;height:auto;padding:20px;">';
					div += formData.parseHtml + '</div>';
					$('#historyDiv').append(div);
					$.parser.parse($('#' + divId).parent());
					initFormInfo(divId, formKey, formData.name);
				}
			});
		}

		//初始化form的信息
		function initFormInfo(divId, formKey, formName) {
			$.ajax({
				type: "get",
				url: "${basePath}/pubWorkFlow/getFormData.do?formName=" + formName + '&id=' + formKey,
				dataType: 'json',
				success: function(formData) {
					renderView(divId);
					loadForm4Json(divId, formData);
				}
			});
		}

		//初始化处理表单
		function initHandleForm(formName, taskName) {
			$.ajax({
				type: "get",
				url: "${basePath}/pubWorkFlow/getFormDesignInfo.do?formName=" + formName,
				dataType: 'json',
				success: function(formData) {
					var div = '<div  class="easyui-panel" title="您需要处理' + taskName + '" style="width:100%;height:auto;padding:10px;">';
					div += formData.parseHtml + '</div>';
					$('#handleDiv').append(div);
					$.parser.parse($('#handleDiv'))
				}
			});
		}

		//完成任务
		function complateTask() {
			$.post('${basePath}pubWorkFlow/complateTask.do?taskId=' + task.ID_ + '&businessKey=' + task.id, $('#handleForm').serializeObject(),
				function(data) {
					console.log(data);
				}, "json");
		}
	</script>

</html>