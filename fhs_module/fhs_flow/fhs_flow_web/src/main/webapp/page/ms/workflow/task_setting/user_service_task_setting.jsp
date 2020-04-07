<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<script type="text/javascript">
	var task = workflow.getFigure(nodeid); //当前节点对象
	//属性表格定义
	rows = [

		{ "name": "ID", "group": "名称", "value": task.taskId, "field": "arraytaskId", "editor": "text" },
		{ "name": "描述", "group": "任务属性", "value": task.taskName, "field": "taskName", "editor": "text" },
		{ "name": "任务属性", "group": "任务属性", "value": task.documentation, "field": "documentation", "editor": "text" },
		{ "name": "表单key", "group": "表单属性", "value": task.formKey, "field": "formKey", "editor": "text" }

	];
	$(function() {
		$('#task_extend').val(task.task_extend);
		$('#isSequential').val(task.isSequential);
		$('#loopCardinality').val(task.loopCardinality);
		$('#collection').val(task.collection);
		$('#elementVariable').val(task.elementVariable);
		$('#completionCondition').val(task.completionCondition);
		$('#performerType').combobox({
			editable: false,
			onChange: function(newValue, oldValue) {
				$('#expression').val('');
				switchTaskCandidatesList(newValue);
			}
		});

		task_candidate_panel = $('#task-candidate-panel').panel({
			border: false
			//minimized:true,
		});
		var ptype = '';
		if($('#performerType').combobox('getValue') != '') {
			ptype = $('#performerType').combobox('getValue');
		}
		$('#performerType').combobox('setValue', ptype);
		switchTaskCandidatesList(ptype);
	});

	function switchTaskCandidatesList(performerType) {
		if(performerType == 'candidateUsers') {
			task_candidate_panel.panel("refresh", "${basePath}page/workflow/task_setting/candidate_users_config.jsp?processProperties&turn=candidateUsersConfig&checkbox=true");
		} else if(performerType == 'candidateGroups') {
			task_candidate_panel.panel("refresh", "${basePath}page/workflow/task_setting/role_config.jsp?processProperties&turn=candidateGroupsConfig&checkbox=true");
		} else if(performerType == 'assignee') {
			task_candidate_panel.panel("refresh", "${basePath}page/workflow/task_setting/user_config.jsp?processProperties&turn=candidateUsersConfig&checkbox=false");
		}
	}

	//保存
	function saveTaskProperties() {
		task.taskId = $.trim(rows[0].value);
		task.taskName = rows[1].value;
		task.formKey = rows[3].value;
		task.documentation = rows[2].value;
		task.setId($.trim(rows[0].value));
		task.setContent($.trim(rows[1].value));
		task.performerType = $('#performerType').combobox('getValue');
		task.expression = $.trim($('#expression').val());
		task.isUseExpression = true;
		task.task_extend = $.trim($('#task_extend').val());
		task.loopCardinality = $.trim($('#loopCardinality').val());
		task.isSequential = $.trim($('#isSequential').val());
		task.loopCardinality = $.trim($('#loopCardinality').val());
		task.collection = $.trim($('#collection').val());
		task.elementVariable = $.trim($('#elementVariable').val());
		task.completionCondition = $.trim($('#completionCondition').val());
	}
	//加载变量
	function populateTaskProperites() {
		$('#performerType').combobox('setValue', task.performerType);
		$('#expression').val(task.expression);
		rows[0].value = task.taskId;
		rows[1].value = task.taskName;
		rows[2].value = task.documentation;
		rows[3].value = task.formKey;

	}

	//加载属性表格数据
	function propertygrid() {
		$('#task-propertygrid').propertygrid('loadData', rows);
		populateTaskProperites();
	}
	//创建属性表格
	$('#task-propertygrid').propertygrid({
		width: 'auto',
		height: 'auto',
		showGroup: true,
		scrollbarSize: 0,
		border: 0,
		columns: [
			[
				{ field: 'name', title: '属性名', width: 30, resizable: false },
				{ field: 'value', title: '属性值', width: 100, resizable: false }
			]
		],
		onAfterEdit: function() {
			saveTaskProperties(); //自动保存
		}
	});
	propertygrid();
</script>

<script type="text/javascript">
	<!--
	//获取执行监听器id
	function getOldListenerIds() {
		var listeners = task.listeners;
		var listenersIds = new Array();
		for(var i = 0; i < listeners.getSize(); i++) {
			var listener = listeners.get(i);
			listenersIds.push(listener.getId());
		}
		return listenersIds.join(",");
	}
	//添加执行监听器
	function addListener(id, event, serviceType, value) {
		var ls = task.getListener(id);
		if(!ls) {
			var listener = new draw2d.Task.Listener();
			listener.id = id;
			listener.event = event;
			listener.serviceType = serviceType;
			listener.serviceClass = value;
			listener.serviceExpression = value;
			task.addListener(listener);
		}
	}
	//删除执行监听器
	function removeListener(id) {
		task.deleteListener(id);
	}

	//-->
</script>
<div id="task-properties-layout" class="easyui-layout" fit="true">
	<div id="task-properties-toolbar-panel" region="north" border="false" style="height:30px; background: #E1F0F2;">
		<a href="##" id="sb2" class="easyui-linkbutton" plain="true" iconCls="icon-save" onclick="saveTaskProperties()">保存</a>
	</div>
	<div id="task-properties-panel" region="center" border="true">
		<div id="task-properties-accordion" class="easyui-accordion" fit="true" border="false">
			<div id="task" title="任务属性" selected="true" class="properties-menu">
				<table id="task-propertygrid">
				</table>
			</div>
			<div id="main-config" title="人员配置" class="properties-menu">
				<div class="datagrid-toolbar" style="height:auto">
					<table id="main-properties">
						<tr>
							<td align="right">
								类型:
							</td>
							<td>
								<select id="performerType" name="performerType" style="width:100px;">
									<option value="assignee">处理人</option>
									<option value="candidateUsers">备选人员
									</option>
									<option value="candidateGroups">备选角色</option>
								</select>
								<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="xuanze();">选择</a>
							</td>
						</tr>
						<tr>
							<td align="right">
								表达式:
							</td>
							<td>
								<input type="text" id="expression" name="expression" style="width:190px" />
							</td>
						</tr>
					</table>
				</div>
				<div id="task-candidate-panel" class="easyui-panel" style="overflow: hidden; width: 280px; height: 450px; padding:1px;">
				</div>
			</div>

			<div id="taskHuiQianProperties" title="会签属性" selected="true">
				<table id="main-properties">
					<tr>
						<td align="right">
							状态:
						</td>
						<td>
							<select id="isSequential" name="isSequential" style="width:160px;">
								<option value="">不启动多实例</option>
								<option value="true">顺序</option>
								<option value="false">并行</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">
							循环数量:
						</td>
						<td>
							<input type="text" id="loopCardinality" name="loopCardinality" style="width:160px" />
						</td>
					</tr>
					<tr>
						<td align="right">
							循环集合:
						</td>
						<td>
							<input type="text" id="collection" name="collection" style="width:160px" />
						</td>
					</tr>
					<tr>
						<td align="right">
							元素名:
						</td>
						<td>
							<input type="text" id="elementVariable" name="elementVariable" style="width:160px" />
						</td>
					</tr>
					<tr>
						<td align="right">
							结束条件:
						</td>
						<td>
							<input type="text" id="completionCondition" name="completionCondition" style="width:160px" />
						</td>
					</tr>
				</table>
				<fieldset style="line-height: 21px;">
					<legend>说明</legend>
					<div>1.${flowUtil.stringToList(assigneeUserIdList)}，将字符串转换成集合，暴露的SpringBean方法</div>
					<div>2.多实例任务Activiti默认会创建3个流程变量，nrOfInstances:实例总数，nrOfActiveInstances:当前活跃的，也就是当前还未完成的，对于顺序的多实例，此值总是1,nrOfCompletedInstances:已完成的实例个数。</div>
					<div>3.状态:不启动多实例,则只会创建一个任务，默认不启动，不启动多实例，一下配置都无效，true:顺序执行，fasle:并行,同时执行。</div>
					<div>4.循环数量:指定创建多任务的数量。可使用表达式从流程变量获取。</div>
					<div>5.循环集合:流程变量中一个集合类型变量的变量名称。根据集合元素多少创建任务数量。可使用表达式。例:流程变量：assigneeUserIdList=[user1,user2]，可用assigneeUserIdList。</div>
					<div>6.集合元素:集合中每个元素的变量名称，可在每个任务中获取,可使用表达式。例：集合为当定义集合元素名称为:assigneeUserId,可在任务直接指派人员用表达式${assigneeUserId}获取，用于动态会签。</div>
					<div>7.结束条件:多实例活动结束的条件，默认为完成多全部实例，当表达式返回true时结束多实例活动。例：${nrOfCompletedInstances/nrOfInstances>=0.6} 说明当有60%的任务完成时，会完成此多实例，删除其他未完成的，继续下面的流程。</div>
				</fieldset>
			</div>

			<div id="listeners" title="任务监听" style="overflow: hidden;padding:1px;">

				<script type="text/javascript">
					$(function() {
						storage = $.localStorage;
						if(!storage) storage = $.cookieStorage;
						$('#listenerList').datagrid({
							idField: 'id',
							title: '添加，删除操作需点击保存',
							url: '${basePath}/workFlowListener/getWorkFlowListenerList.do',
							queryParams: { ids: getOldListenerIds() },
							fit: true,
							loadMsg: '数据加载中...',
							pageSize: 10,
							pagination: false,
							pageList: [10, 20, 30],
							sortOrder: 'asc',
							rownumbers: true,
							singleSelect: true,
							fitColumns: true,
							striped: true,
							showFooter: true,
							frozenColumns: [
								[]
							],
							columns: [
								[{ field: 'id', title: 'id', hidden: true, sortable: true }, { field: 'listenerName', title: '名字', width: 30, sortable: true }, { field: 'event', title: '事件', width: 30, sortable: true }, { field: 'excuteType', title: '类型', width: 30, sortable: true, formatter: function(value, rec, index) { if(value == undefined) return ''; var valArray = value.split(','); if(valArray.length > 1) { var checkboxValue = ''; for(var k = 0; k < valArray.length; k++) { if(valArray[k] == 'javaClass') { checkboxValue = checkboxValue + 'java类' + ','; } if(valArray[k] == 'expression') { checkboxValue = checkboxValue + '表达式' + ','; } } return checkboxValue.substring(0, checkboxValue.length - 1); } else { if(value == 'javaClass') { return 'java类'; } if(value == 'expression') { return '表达式'; } else { return value; } } } }, { field: 'excuteContent', title: '执行内容', width: 50, sortable: true }, {
									field: 'opt',
									title: '操作',
									width: 30,
									formatter: function(value, rec, index) {
										if(!rec.id) { return ''; }
										var href = '';
										href += "[<a href='#'   onclick=delRow('" + rec.id + "','" + index + "')>";
										href += "删除</a>]";
										return href;
									}
								}]
							],
							onLoadSuccess: function(data) {
								$("#listenerList").datagrid("clearSelections");
								$(this).datagrid("fixRownumber");
								if(!false) {
									if(data.total && data.rows.length == 0) {
										var grid = $('#listenerList');
										var curr = grid.datagrid('getPager').data("pagination").options.pageNumber;
										grid.datagrid({ pageNumber: (curr - 1) });
									}
								}
							},
							onClickRow: function(rowIndex, rowData) {
								rowid = rowData.id;
								gridname = 'listenerList';
							}
						});
						$('#listenerList').datagrid('getPager').pagination({ beforePageText: '', afterPageText: '/{pages}', displayMsg: '{from}-{to}共 {total}条', showPageList: true, showRefresh: true });
						$('#listenerList').datagrid('getPager').pagination({
							onBeforeRefresh: function(pageNumber, pageSize) {
								$(this).pagination('loading');
								$(this).pagination('loaded');
							}
						});
						try { restoreheader(); } catch(ex) {}
					});

					function reloadTable() {
						try {
							$('#' + gridname).datagrid('reload');
							$('#' + gridname).treegrid('reload');
						} catch(ex) {}
					}

					function reloadlistenerList() { $('#listenerList').datagrid('reload'); }

					function getlistenerListSelected(field) { return getSelected(field); }

					function getSelected(field) { var row = $('#' + gridname).datagrid('getSelected'); if(row != null) { value = row[field]; } else { value = ''; } return value; }

					function getlistenerListSelections(field) { var ids = []; var rows = $('#listenerList').datagrid('getSelections'); for(var i = 0; i < rows.length; i++) { ids.push(rows[i][field]); } ids.join(','); return ids };

					function getSelectRows() { return $('#listenerList').datagrid('getChecked'); }

					function saveHeader() {
						var columnsFields = null;
						var easyextends = false;
						try {
							columnsFields = $('#listenerList').datagrid('getColumns');
							easyextends = true;
						} catch(e) { columnsFields = $('#listenerList').datagrid('getColumnFields'); }
						var cols = storage.get('listenerListhiddenColumns');
						var init = true;
						if(cols) { init = false; }
						var hiddencolumns = [];
						for(var i = 0; i < columnsFields.length; i++) { if(easyextends) { hiddencolumns.push({ field: columnsFields[i].field, hidden: columnsFields[i].hidden }); } else { var columsDetail = $('#listenerList').datagrid("getColumnOption", columnsFields[i]); if(init) { hiddencolumns.push({ field: columsDetail.field, hidden: columsDetail.hidden, visible: (columsDetail.hidden == true ? false : true) }); } else { for(var j = 0; j < cols.length; j++) { if(cols[j].field == columsDetail.field) { hiddencolumns.push({ field: columsDetail.field, hidden: columsDetail.hidden, visible: cols[j].visible }); } } } } } storage.set('listenerListhiddenColumns', JSON.stringify(hiddencolumns));
					}

					function isShowBut() {
						var isShowSearchId = $('#isShowSearchId').val();
						if(isShowSearchId == "true") {
							$("#searchColums").hide();
							$('#isShowSearchId').val("false");
							$('#columsShow').remove("src");
							$('#columsShow').attr("src", "plug-in/easyui/themes/default/images/accordion_expand.png");
						} else {
							$("#searchColums").show();
							$('#isShowSearchId').val("true");
							$('#columsShow').remove("src");
							$('#columsShow').attr("src", "plug-in/easyui/themes/default/images/accordion_collapse.png");
						}
					}

					function restoreheader() { var cols = storage.get('listenerListhiddenColumns'); if(!cols) return; for(var i = 0; i < cols.length; i++) { try { if(cols.visible != false) $('#listenerList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field); } catch(e) {} } }

					function resetheader() { var cols = storage.get('listenerListhiddenColumns'); if(!cols) return; for(var i = 0; i < cols.length; i++) { try { $('#listenerList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field); } catch(e) {} } }

					function listenerListsearch() {
						try { if(!$("#listenerListForm").Validform({ tiptype: 3 }).check()) { return false; } } catch(e) {}
						if(true) {
							var queryParams = $('#listenerList').datagrid('options').queryParams;
							$('#listenerListtb').find('*').each(function() { queryParams[$(this).attr('name')] = $(this).val(); });
							$('#listenerList').datagrid({ url: 'processController.do?listenerGridYouXiao&field=id,listenerName,listenereven,listenertype,listenervalue,', pageNumber: 1 });
						}
					}

					function dosearch(params) {
						var jsonparams = $.parseJSON(params);
						$('#listenerList').datagrid({ url: 'processController.do?listenerGridYouXiao&field=id,listenerName,listenereven,listenertype,listenervalue,', queryParams: jsonparams });
					}

					function listenerListsearchbox(value, name) {
						var queryParams = $('#listenerList').datagrid('options').queryParams;
						queryParams[name] = value;
						queryParams.searchfield = name;
						$('#listenerList').datagrid('reload');
					}
					$('#listenerListsearchbox').searchbox({ searcher: function(value, name) { listenerListsearchbox(value, name); }, menu: '#listenerListmm', prompt: '请输入查询关键字' });

					function EnterPress(e) { var e = e || window.event; if(e.keyCode == 13) { listenerListsearch(); } }

					function searchReset(name) {
						$("#" + name + "tb").find(":input").val("");
						var queryParams = $('#listenerList').datagrid('options').queryParams;
						$('#listenerListtb').find('*').each(function() { queryParams[$(this).attr('name')] = $(this).val(); });
						$('#listenerList').datagrid({ url: 'processController.do?listenerGridYouXiao&field=id,listenerName,listenereven,listenertype,listenervalue,', pageNumber: 1 });
					}
				</script>
				<table width="100%" id="listenerList" toolbar="#listenerListtb"></table>
				<div id="listenerListtb" style="padding:3px; height: auto">
					<div style="height:0px;"><span style="float:left;"></span>
						<div style="clear:both"></div>
					</div>

					<div id="listenerListtb" style="padding:3px; height: 25px">
						<div style="float: left;">
							<div class="form">
								<input name="listenerid" type="hidden" id="listenerid">
								<input name="listenerName" type="hidden" id="listenerName">
								<input name="listenereven" type="hidden" id="listenereven">
								<input name="listenertype" type="hidden" id="listenertype">
								<input name="listenervalue" type="hidden" id="listenervalue">
								<a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onClick="choose_ff8080815d550def015d7280b453077b()">选择</a>
								<script type="text/javascript">
									var windowapi;
									try { windowapi = frameElement.api, W = windowapi.opener; } catch(e) {}

									function choose_ff8080815d550def015d7280b453077b() {
										var url = '${basePath}/page/workflow/task_setting/listener_list.jsp?listenerType=2';
										if(typeof(windowapi) == 'undefined') { $.dialog({ content: 'url:' + url, zIndex: getzIndex(), title: '监听列表', lock: true, width: 400, height: 350, left: '85%', top: '65%', opacity: 0.4, button: [{ name: '确定', callback: clickcallback_ff8080815d550def015d7280b453077b, focus: true }, { name: '取消', callback: function() {} }] }); } else { $.dialog({ content: 'url:' + url, zIndex: getzIndex(), title: '监听列表', lock: true, parent: windowapi, width: 400, height: 350, left: '85%', top: '65%', opacity: 0.4, button: [{ name: '确定', callback: clickcallback_ff8080815d550def015d7280b453077b, focus: true }, { name: '取消', callback: function() {} }] }); }
									}

									function clickcallback_ff8080815d550def015d7280b453077b() {
										iframe = this.iframe.contentWindow;
										var listenerName = iframe.getlistenerListSelections('listenerName');
										if($('#listenerName').length >= 1) {
											$('#listenerName').val(listenerName);
											$('#listenerName').blur();
										}
										if($("input[name='listenerName']").length >= 1) {
											$("input[name='listenerName']").val(listenerName);
											$("input[name='listenerName']").blur();
										}
										var listenereven = iframe.getlistenerListSelections('event');

										if($('#listenereven').length >= 1) {
											$('#listenereven').val(listenereven);
											$('#listenereven').blur();
										}
										if($("input[name='listenereven']").length >= 1) {
											$("input[name='listenereven']").val(listenereven);
											$("input[name='listenereven']").blur();
										}
										var listenertype = iframe.getlistenerListSelections('excuteType');
										if($('#listenertype').length >= 1) {
											$('#listenertype').val(listenertype);
											$('#listenertype').blur();
										}
										if($("input[name='listenertype']").length >= 1) {
											$("input[name='listenertype']").val(listenertype);
											$("input[name='listenertype']").blur();
										}
										var listenervalue = iframe.getlistenerListSelections('excuteContent');
										if($('#listenervalue').length >= 1) {
											$('#listenervalue').val(listenervalue);
											$('#listenervalue').blur();
										}
										if($("input[name='listenervalue']").length >= 1) {
											$("input[name='listenervalue']").val(listenervalue);
											$("input[name='listenervalue']").blur();
										}
										var id = iframe.getlistenerListSelections('id');
										if(id !== undefined && id != "") { $('#listenerid').val(id); } saveProcessListener();
									}
								</script>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		//保存监听
		function saveProcessListener() {
			var listenerid = $('#listenerid').val();
			var listenereven = $('#listenereven').val();
			var listenertype = $('#listenertype').val();
			var listenervalue = $('#listenervalue').val();
			var listenerName = $('#listenerName').val();
			var listenerids = listenerid.split(",");
			var listenerevens = listenereven.split(",");
			var listenertypes = listenertype.split(",");
			var listenervalues = listenervalue.split(",");
			var listenerNames = listenerName.split(",");
			for(var i = 0; i < listenerids.length; i++) {
				var ls = task.getListener(listenerids[i]);
				addListener(listenerids[i], listenerevens[i], listenertypes[i], listenervalues[i]);
				if(!ls) {
					$('#listenerList').datagrid('appendRow', {
						id: listenerids[i],
						listenerName: listenerNames[i],
						event: listenerevens[i],
						excuteType: listenertypes[i],
						excuteContent: listenervalues[i]
					});
				}
			}
		}

		function setProcessListener(index) {
			var row = $('#listenerList').datagrid('getRows')[index];
			$.ajax({
				url: "processController.do?setProcessListener",
				type: 'POST',
				data: {
					id: row.id
				},
				dataType: 'json',
				success: function(data) {
					if(data.success) {
						var listener = new draw2d.Task.Listener();
						listener.event = row.TPListerer_listenereven;
						listener.id = row.id;
						listener.serviceType = row.TPListerer_listenertype;
						if(row.TPListerer_listenertype == "javaClass") {
							listener.serviceClass = row.TPListerer_listenervalue;
						} else {
							listener.serviceExpression = row.TPListerer_listenervalue;
						}
						task.listeners.add(listener);
					} else {
						task.deleteListener(row.id);
					}
					reloadlistenerList();
				}
			});

		}
		//删除流程监听
		function delRow(id) {
			var rows = $('#listenerList').datagrid('getSelections');
			for(var i = 0; i < rows.length; i++) {
				var row = rows[i];
				var index = $('#listenerList').datagrid('getRowIndex', row);
				$('#listenerList').datagrid('deleteRow', index);
				removeListener(row.id);
			}
		}
	</script>