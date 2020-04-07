<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<HTML><HEAD><META content="IE=11.0000"
http-equiv="X-UA-Compatible">
   <TITLE>流程设计器</TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<SCRIPT src="jscss/zh-cn.js" type="text/javascript"></SCRIPT>

<SCRIPT src="jscss/jquery-1.7.2.min.js" type="text/javascript"></SCRIPT>
<LINK id="easyuiTheme" href="jscss/easyui.css" rel="stylesheet" type="text/css"><LINK
href="jscss/icon.css" rel="stylesheet" type="text/css">
<SCRIPT src="jscss/jquery.easyui.min.1.3.0.js" type="text/javascript"></SCRIPT>

<SCRIPT src="" type="text/javascript"></SCRIPT>

<SCRIPT src="jscss/syUtil.js" type="text/javascript"></SCRIPT>

<SCRIPT src="jscss/jquery.bgiframe.min.js" type="text/javascript"></SCRIPT>

<SCRIPT src="jscss/jquery.ajaxQueue.js" type="text/javascript"></SCRIPT>

<SCRIPT src="jscss/jquery.autocomplete.min.js" type="text/javascript"></SCRIPT>
<LINK href="jscss/designer.css" rel="stylesheet" type="text/css">
<SCRIPT src="jscss/wz_jsgraphics.js"></SCRIPT>

<SCRIPT src="jscss/mootools.js"></SCRIPT>

<SCRIPT src="jscss/moocanvas.js"></SCRIPT>

<SCRIPT src="jscss/draw2d.js"></SCRIPT>

<SCRIPT src="jscss/MyCanvas.js"></SCRIPT>

<SCRIPT src="jscss/ResizeImage.js"></SCRIPT>

<SCRIPT src="jscss/Start.js"></SCRIPT>

<SCRIPT src="jscss/End.js"></SCRIPT>

<SCRIPT src="jscss/MyInputPort.js"></SCRIPT>

<SCRIPT src="jscss/MyOutputPort.js"></SCRIPT>

<SCRIPT src="jscss/DecoratedConnection.js"></SCRIPT>

<SCRIPT src="jscss/Task.js"></SCRIPT>

<SCRIPT src="jscss/UserTask.js"></SCRIPT>

<SCRIPT src="jscss/ManualTask.js"></SCRIPT>

<SCRIPT src="jscss/ServiceTask.js"></SCRIPT>

<SCRIPT src="jscss/ExclusiveGateway.js"></SCRIPT>
<SCRIPT src="jscss/html2canvas.js"></SCRIPT>

<SCRIPT src="jscss/ParallelGateway.js"></SCRIPT>

<SCRIPT src="jscss/TimerBoundary.js"></SCRIPT>

<SCRIPT src="jscss/ErrorBoundary.js"></SCRIPT>

<SCRIPT src="jscss/CallActivity.js"></SCRIPT>

<SCRIPT src="jscss/ScriptTask.js"></SCRIPT>

<SCRIPT src="jscss/MailTask.js"></SCRIPT>

<SCRIPT src="jscss/ReceiveTask.js"></SCRIPT>

<SCRIPT src="jscss/BusinessRuleTask.js"></SCRIPT>

<SCRIPT src="jscss/designer.js"></SCRIPT>

<SCRIPT src="jscss/mydesigner.js"></SCRIPT>
<LINK href="jscss/common.css" rel="stylesheet" type="text/css"><LINK
href="jscss/font-awesome.css" rel="stylesheet" type="text/css">
<SCRIPT src="jscss/lhgdialog.min.js" type="text/javascript"></SCRIPT>

<SCRIPT src="jscss/layer.js" type="text/javascript"></SCRIPT>

<SCRIPT src="jscss/curdtools_zh-cn.js" type="text/javascript"></SCRIPT>

<SCRIPT src="jscss/easyuiextend.js" type="text/javascript"></SCRIPT>

<SCRIPT src="jscss/jquery-hftable.js" type="text/javascript"></SCRIPT>

<SCRIPT src="jscss/json2.js" type="text/javascript"></SCRIPT>

<SCRIPT type="text/javascript">
	$(function() {
		try {
			_task_obj = $('#task');
			_task_context_menu = $('#task-context-menu').menu({});
			$('.easyui-linkbutton').draggable({
				proxy : function(source) {
					var n = $('<div class="draggable-model-proxy"></div>');
					n.html($(source).html()).appendTo('body');
					return n;
				},
				deltaX : 0,
				deltaY : 0,
				revert : true,
				cursor : 'auto',
				onStartDrag : function() {
					$(this).draggable('options').cursor = 'not-allowed';
				},
				onStopDrag : function() {
					$(this).draggable('options').cursor = 'auto';
				}
			});
			$('#paintarea').droppable({
				accept : '.easyui-linkbutton',
				onDragEnter : function(e, source) {
					$(source).draggable('options').cursor = 'auto';
				},
				onDragLeave : function(e, source) {
					$(source).draggable('options').cursor = 'not-allowed';
				},
				onDrop : function(e, source) {
					$(this).removeClass('over');
					var wfModel = $(source).attr('wfModel');
					var shape = $(source).attr('shape');
					if (wfModel) {
						var x = $(source).draggable('proxy').offset().left;
						var y = $(source).draggable('proxy').offset().top;
						var xOffset = workflow.getAbsoluteX();
						var yOffset = workflow.getAbsoluteY();
						var scrollLeft = workflow.getScrollLeft();
						var scrollTop = workflow.getScrollTop();
						addModel(wfModel, x - xOffset + scrollLeft, y - yOffset + scrollTop, shape);
					}
				}
			});
			//$('#paintarea').bind('contextmenu',function(e){
			//alert(e.target.tagName);
			//});

		} catch (e) {

		}
	});
//-->
</SCRIPT>

<META name="GENERATOR" content="MSHTML 11.00.10570.1001"></HEAD>
<BODY class="easyui-layout" id="designer">
<DIV title="流程元素" style="width: 110px;" iconcls="palette-icon" split="true"
region="west">
<DIV class="easyui-accordion" border="false" fit="true">
<DIV title="事件" class="palette-menu" id="event" iconcls="palette-menu-icon"><A
class="easyui-linkbutton" href=""
iconcls="start-event-icon" wfmodel="Start" plain="true">开始</A>      <BR><A
class="easyui-linkbutton" href=""
iconcls="end-event-icon" wfmodel="End" plain="true">结束</A>      <BR></DIV>
<DIV title="任务" class="palette-menu" id="task" iconcls="palette-menu-icon"
selected="true"><A class="easyui-linkbutton" href=""
iconcls="user-task-icon" wfmodel="UserTask" plain="true">用户任务</A>      <BR><A
class="easyui-linkbutton" href=""
iconcls="manual-task-icon" wfmodel="ManualTask" plain="true">手工任务</A>
<BR><A class="easyui-linkbutton" href=""
iconcls="service-task-icon" wfmodel="ServiceTask" plain="true">服务任务</A>
<BR><A class="easyui-linkbutton" href=""
iconcls="script-task-icon" wfmodel="ScriptTask" plain="true">脚本任务</A>
<BR><A class="easyui-linkbutton" href=""
iconcls="mail-task-icon" wfmodel="MailTask" plain="true">邮件任务</A>      <BR><A
class="easyui-linkbutton" href=""
iconcls="receive-task-icon" wfmodel="ReceiveTask" plain="true">接受任务</A>
<BR><A class="easyui-linkbutton" href=""
iconcls="business-rule-task-icon" wfmodel="BusinessRuleTask"
plain="true">业务规则</A>      <BR><A class="easyui-linkbutton" href=""
iconcls="subprocess-icon" wfmodel="CallActivity" plain="true">子流程</A>
<BR></DIV>
<DIV title="网关" class="palette-menu" id="gateway" iconcls="palette-menu-icon"><A
class="easyui-linkbutton" href=""
iconcls="parallel-gateway-icon" wfmodel="ParallelGateway" plain="true">同步</A>
  <BR><A class="easyui-linkbutton" href=""
iconcls="exclusive-gateway-icon" wfmodel="ExclusiveGateway" plain="true">分支</A>
    <BR></DIV>
<DIV title="边界事件" class="palette-menu" id="boundary-event" iconcls="palette-menu-icon"><A
class="easyui-linkbutton" href=""
iconcls="timer-boundary-event-icon" wfmodel="TimerBoundary"
plain="true">时间边界</A>      <BR><A class="easyui-linkbutton" href=""
iconcls="error-boundary-event-icon" wfmodel="ErrorBoundary"
plain="true">错误边界</A>      <BR></DIV></DIV></DIV>
<DIV title="流程" id="process-panel" style="padding: 1px;" iconcls="process-icon"
split="true" region="center">
<DIV id="process-definition-tab">
<DIV title="设计" id="designer-area" style="padding: 0px; border: currentColor; border-image: none; width: 100%; height: 100%; overflow: auto; position: absolute;">
<DIV id="paintarea"
style="width: 2000px; height: 2000px; position: absolute;"></DIV></DIV>
<DIV title="源码" id="xml-area" style="width: 100%; height: 100%; overflow: hidden; -ms-overflow-x: hidden; -ms-overflow-y: hidden;"><TEXTAREA id="descriptorarea" style="padding: 0px; border: currentColor; border-image: none; width: 100%; height: 100%; font-size: 12px;" rows="38" readonly="readonly"></TEXTAREA>
    </DIV></DIV></DIV><!-- toolbar -->   <!-- update-begin--Author:chenxu  Date:20130408 for：修改流程时，流程类型不能显示 -->

<DIV title="工具栏" id="toolbar-panel" style="background: rgb(216, 228, 254); height: 55px;"
region="north" border="false"><INPUT name="processId" id="processId" type="hidden" value="0">
   <IMG width="20" height="18" title="创建流程" class="buttonStyle" onclick="saveProcessDef()"
src="jscss/save.png">    <IMG width="20" height="18" title="上一步" class="buttonStyle"
onclick="undo()" src="jscss/back.png">    <IMG width="20" height="18"
title="下一步" class="buttonStyle" onclick="redo()" src="jscss/next.png">
  <IMG width="20" height="18" title="bpm.designer.toolbar.export" class="buttonStyle"
onclick="exportProcessDef(this)" src="jscss/printer.png">
类型:<select id="typeid" name="typeid" class="easyui-combobox" data-options="
                                valueField: 'wordbookCode',
                                textField: 'wordbookDesc',
                                panelWidth: 155,
                                editable : false,
                                panelHeight: 'auto',
                                url: '${pub_service_url}wordbook/getData.do?wordbookGroupCode=workFlow_xml_type&jsonpCallback=?',
							" datatype="*" nullmsg="请选择监听类型">

						</select>
</DIV>

<DIV title="流程属性" id="properties-panel" style="padding: 1px; width: 280px;"
iconcls="properties-icon" split="true" region="east" href="${basePath}page/workflow/task_setting/right_panel.jsp?processProperties&amp;turn=processProperties&amp;processId=0"></DIV><!-- update-end--Author:chenxu  Date:20130408 for：修改流程时，流程类型不能显示 -->
  <!-- task context menu -->
<DIV class="easyui-menu" id="task-context-menu" style="width: 120px;">
<DIV id="properties-task-context-menu" iconcls="properties-icon">属性    </DIV>
<DIV id="delete-task-context-menu" iconcls="icon-remove">    删除    </DIV></DIV><!-- form configuration window -->

<DIV title="表单配置" id="form-win" style="width: 720px; height: 300px;"></DIV><!-- form configuration window -->

<DIV title="变量配置" id="variable-win" style="width: 720px; height: 300px;"></DIV><!-- listener configuration window -->

<DIV title="监听配置" id="listener-win" style="width: 720px; height: 300px;"></DIV><!-- candidate configuration window -->

<DIV title="任务配置" id="task-candidate-win"
style="width: 720px; height: 300px;"></DIV>
<SCRIPT type="text/javascript">
	$('#process-definition-tab').tabs({
		fit : true,
		onSelect : function(title) {
			if (title == '设计') {

			} else if (title == '源码' || title == 'sourcecode') {
				$('#descriptorarea').val(workflow.toXML());

			}
		}
	});
</SCRIPT>

<SCRIPT type="text/javascript">
var imId = '0';
var isUpdate = false;
if('' != '${param.id}')
{
	imId = '${param.id}';
	isUpdate = true;
}
createCanvas(imId,false);
</SCRIPT>
  </BODY></HTML>
