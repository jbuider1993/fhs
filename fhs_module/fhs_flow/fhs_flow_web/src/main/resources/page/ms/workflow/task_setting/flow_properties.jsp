<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<script type="text/javascript">
	//流程对象
	var line = workflow.getLine(nodeid);
	//author:zhangdaihao date:20140802 for:去掉空格
	if(line.condition != null && line.condition != "") {
		line.condition = line.condition.replace(/(^\s*)|(\s*$)/g, "");
	}

	//属性表格定义
	rows = [
		{ "name": "ID", "group": "节点属性", "value": line.lineId, "field": "id", "editor": "text" },
		{ "name": "Lable", "group": "节点属性", "value": line.lineName, "field": "name", "editor": "text" },
		{ "name": "表达式", "group": "分支条件", "value": line.condition, "field": "condition", "editor": "text" }

	];
	//保存属性
	function saveFlowProperties() {
		line.lineId = rows[0].value;
		line.lineName = rows[1].value;
		line.condition = rows[2].value;
		line.setLabel(rows[1].value);
	}
	//构建属性表格数据
	function populateFlowProperites() {
		rows[0].value = line.lineId;
		rows[1].value = line.lineName;
		rows[2].value = line.condition;
		flowpropertygrid();
	}
	//加载属性表格数据
	function flowpropertygrid() {
		$('#flow-properties').propertygrid('loadData', rows);
	}
	$(function() {
		//创建属性表格
		$('#flow-properties').propertygrid({
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
				saveFlowProperties(); //自动保存
			}
		});
		flowpropertygrid();
	});
</script>

<input type="hidden" id="listenerid"/>			
<input type="hidden" id="listenereven"/>		
<input type="hidden" id="listenertype"/>		
<input type="hidden" id="listenervalue"/>		
<input type="hidden" id="listenerName"/>		
<div id="flow-properties-layout" class="easyui-layout" fit="true">
	<div id="flow-properties-panel" region="center" border="true">
		<div id="flow-properties-accordion" class="easyui-accordion" fit="true" border="false">
			<div id="flow" style="padding:0px;border:0px" title="流程属性面板" class="properties-menu">
				<table id="flow-properties">
				</table>
			</div>
			<div id="flowlisteners" title="执行监听器" style="overflow: hidden; padding: 1px;">

				<script type="text/javascript">
					$(function() {
						storage = $.localStorage;
						if(!storage) storage = $.cookieStorage;
						$('#flowlistenerList').datagrid({
							idField: 'id',
							title: '',
							url: '${basePath}/workFlowListener/getWorkFlowListenerList.do',
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
								$("#flowlistenerList").datagrid("clearSelections");
								$(this).datagrid("fixRownumber");
								if(!false) {
									if(data.total && data.rows.length == 0) {
										var grid = $('#flowlistenerList');
										var curr = grid.datagrid('getPager').data("pagination").options.pageNumber;
										grid.datagrid({ pageNumber: (curr - 1) });
									}
								}
							},
							onClickRow: function(rowIndex, rowData) {
								rowid = rowData.id;
								gridname = 'flowlistenerList';
							}
						});
						$('#flowlistenerList').datagrid('getPager').pagination({ beforePageText: '', afterPageText: '/{pages}', displayMsg: '{from}-{to}共 {total}条', showPageList: true, showRefresh: true });
						$('#flowlistenerList').datagrid('getPager').pagination({
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

					function reloadflowlistenerList() { $('#flowlistenerList').datagrid('reload'); }

					function getflowlistenerListSelected(field) { return getSelected(field); }

					function getSelected(field) { var row = $('#' + gridname).datagrid('getSelected'); if(row != null) { value = row[field]; } else { value = ''; } return value; }

					function getflowlistenerListSelections(field) { var ids = []; var rows = $('#flowlistenerList').datagrid('getSelections'); for(var i = 0; i < rows.length; i++) { ids.push(rows[i][field]); } ids.join(','); return ids };

					function getSelectRows() { return $('#flowlistenerList').datagrid('getChecked'); }

					function saveHeader() {
						var columnsFields = null;
						var easyextends = false;
						try {
							columnsFields = $('#flowlistenerList').datagrid('getColumns');
							easyextends = true;
						} catch(e) { columnsFields = $('#flowlistenerList').datagrid('getColumnFields'); }
						var cols = storage.get('flowlistenerListhiddenColumns');
						var init = true;
						if(cols) { init = false; }
						var hiddencolumns = [];
						for(var i = 0; i < columnsFields.length; i++) { if(easyextends) { hiddencolumns.push({ field: columnsFields[i].field, hidden: columnsFields[i].hidden }); } else { var columsDetail = $('#flowlistenerList').datagrid("getColumnOption", columnsFields[i]); if(init) { hiddencolumns.push({ field: columsDetail.field, hidden: columsDetail.hidden, visible: (columsDetail.hidden == true ? false : true) }); } else { for(var j = 0; j < cols.length; j++) { if(cols[j].field == columsDetail.field) { hiddencolumns.push({ field: columsDetail.field, hidden: columsDetail.hidden, visible: cols[j].visible }); } } } } } storage.set('flowlistenerListhiddenColumns', JSON.stringify(hiddencolumns));
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

					function restoreheader() { var cols = storage.get('flowlistenerListhiddenColumns'); if(!cols) return; for(var i = 0; i < cols.length; i++) { try { if(cols.visible != false) $('#flowlistenerList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field); } catch(e) {} } }

					function resetheader() { var cols = storage.get('flowlistenerListhiddenColumns'); if(!cols) return; for(var i = 0; i < cols.length; i++) { try { $('#flowlistenerList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field); } catch(e) {} } }

					function flowlistenerListsearch() {
						try { if(!$("#flowlistenerListForm").Validform({ tiptype: 3 }).check()) { return false; } } catch(e) {}
						if(true) {
							var queryParams = $('#flowlistenerList').datagrid('options').queryParams;
							$('#flowlistenerListtb').find('*').each(function() { queryParams[$(this).attr('name')] = $(this).val(); });
							$('#flowlistenerList').datagrid({ url: 'processController.do?getNodelisteners&type=1&processNode=flow1501573349625&processId=process1501567070480&field=id,TPListerer_listenername,status,TPListerer_listenereven,TPListerer_listenertype,TPListerer_listenervalue,', pageNumber: 1 });
						}
					}

					function dosearch(params) {
						var jsonparams = $.parseJSON(params);
						$('#flowlistenerList').datagrid({ url: 'processController.do?getNodelisteners&type=1&processNode=flow1501573349625&processId=process1501567070480&field=id,TPListerer_listenername,status,TPListerer_listenereven,TPListerer_listenertype,TPListerer_listenervalue,', queryParams: jsonparams });
					}

					function flowlistenerListsearchbox(value, name) {
						var queryParams = $('#flowlistenerList').datagrid('options').queryParams;
						queryParams[name] = value;
						queryParams.searchfield = name;
						$('#flowlistenerList').datagrid('reload');
					}
					$('#flowlistenerListsearchbox').searchbox({ searcher: function(value, name) { flowlistenerListsearchbox(value, name); }, menu: '#flowlistenerListmm', prompt: '请输入查询关键字' });

					function EnterPress(e) { var e = e || window.event; if(e.keyCode == 13) { flowlistenerListsearch(); } }

					function searchReset(name) {
						$("#" + name + "tb").find(":input").val("");
						var queryParams = $('#flowlistenerList').datagrid('options').queryParams;
						$('#flowlistenerListtb').find('*').each(function() { queryParams[$(this).attr('name')] = $(this).val(); });
						$('#flowlistenerList').datagrid({ url: 'processController.do?getNodelisteners&type=1&processNode=flow1501573349625&processId=process1501567070480&field=id,TPListerer_listenername,status,TPListerer_listenereven,TPListerer_listenertype,TPListerer_listenervalue,', pageNumber: 1 });
					}
				</script>
				<table width="100%" id="flowlistenerList" toolbar="#flowlistenerListtb"></table>
				<div id="flowlistenerListtb" style="padding:3px; height: auto">
					<div style="height:0px;"><span style="float:left;"></span>
						<div style="clear:both"></div>
					</div>
					<div id="flowlistenerListtb" style="padding: 3px; height: 25px">
						<div style="float: left;">
							<div class="form">
								<input name="listenerid" type="hidden" value="" id="listenerid">
								<a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onClick="choose_ff8080815d550def015d9cbeaab719d4()">选择</a>
								<script type="text/javascript">
									var windowapi;
									try { windowapi = frameElement.api, W = windowapi.opener; } catch(e) {}

									function choose_ff8080815d550def015d9cbeaab719d4() {
										var url = '${basePath}/page/workflow/task_setting/listener_list.jsp?listenerType=1';
										if(typeof(windowapi) == 'undefined') { $.dialog({ content: 'url:' + url, zIndex: getzIndex(), title: '执行监听器', lock: true, width: 400, height: 350, left: '85%', top: '65%', opacity: 0.4, button: [{ name: '确定', callback: clickcallback_ff8080815d550def015d9cbeaab719d4, focus: true }, { name: '取消', callback: function() {} }] }); } else { $.dialog({ content: 'url:' + url, zIndex: getzIndex(), title: '执行监听器', lock: true, parent: windowapi, width: 400, height: 350, left: '85%', top: '65%', opacity: 0.4, button: [{ name: '确定', callback: clickcallback_ff8080815d550def015d9cbeaab719d4, focus: true }, { name: '取消', callback: function() {} }] }); }
									}

									function clickcallback_ff8080815d550def015d9cbeaab719d4() {
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
										if(id !== undefined && id != "") { $('#listenerid').val(id); } 
										saveFlowListener();
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
		function saveFlowListener() {
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
				var ls = process.getListener(listenerids[i]);
				addListener(listenerids[i], listenerevens[i], listenertypes[i], listenervalues[i]);
				if(!ls) {
					$('#flowlistenerList').datagrid('appendRow', {
						id: listenerids[i],
						listenerName: listenerNames[i],
						event: listenerevens[i],
						excuteType: listenertypes[i],
						excuteContent: listenervalues[i]
					});
				}
			}
		}
		//删除流程监听
		function delRow(id) {
			var rows = $('#flowlistenerList').datagrid('getSelections');
			for(var i = 0; i < rows.length; i++) {
				var row = rows[i];
				var index = $('#flowlistenerList').datagrid('getRowIndex', row);
				$('#flowlistenerList').datagrid('deleteRow', index);
				removeListener(row.id);
			}
		}
		
		function setFlowListener(index) {
			var row = $('#flowlistenerList').datagrid('getRows')[index];
			$.ajax({
				url: "processController.do?setProcessListener",
				type: 'POST',
				data: {
					id: row.id
				},
				dataType: 'json',
				success: function(data) {
					if(data.success) {

						var listener = new draw2d.DecoratedConnection.Listener();
						listener.id = row.id;
						listener.serviceType = row.TPListerer_listenertype;
						if(row.TPListerer_listenertype == "javaClass") {
							listener.serviceClass = row.TPListerer_listenervalue;
						} else {
							listener.serviceExpression = row.TPListerer_listenervalue;
						}

						line.listeners.add(listener);
					} else {
						line.deleteListener(row.id);
					}
					reloadflowlistenerList();
				}
			});

		}
	</script>