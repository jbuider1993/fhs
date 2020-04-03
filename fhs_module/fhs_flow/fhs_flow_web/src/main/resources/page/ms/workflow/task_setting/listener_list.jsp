<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>

	<head>
		<title>监听列表</title>
		<script type="text/javascript" src="../plug-in/mutiLang/zh-cn.js"></script>
		<script type="text/javascript" src="../plug-in/jquery/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="../plug-in/jquery/jquery.cookie.js"></script>
		<script type="text/javascript" src="../plug-in/jquery-plugs/storage/jquery.storageapi.min.js"></script>
		<script type="text/javascript" src="../plug-in/tools/dataformat.js"></script>
		<link id="easyuiTheme" rel="stylesheet" href="../plug-in/easyui/themes/default/easyui.css" type="text/css"></link>
		<link id="easyuiTheme" rel="stylesheet" href="../plug-in/easyui/themes/icon.css" type="text/css"></link>
		<link rel="stylesheet" type="text/css" href="../plug-in/accordion/css/accordion.css">
		<link rel="stylesheet" type="text/css" href="../plug-in/accordion/css/icons.css">
		<script type="text/javascript" src="../plug-in/easyui/jquery.easyui.min.1.3.2.js"></script>
		<script type="text/javascript" src="../plug-in/easyui/locale/zh-cn.js"></script>
		<script type="text/javascript" src="../plug-in/tools/syUtil.js"></script>
		<script type="text/javascript" src="../plug-in/easyui/extends/datagrid-scrollview.js"></script>
		<link rel="stylesheet" href="../plug-in/tools/css/common.css" type="text/css"></link>
		<link rel="stylesheet" href="../plug-in/ace/css/font-awesome.css" type="text/css"></link>
		<script type="text/javascript" src="../plug-in/lhgDialog/lhgdialog.min.js"></script>
		<script type="text/javascript" src="../plug-in/layer/layer.js"></script>
		<script type="text/javascript" src="../plug-in/tools/curdtools_zh-cn.js"></script>
		<script type="text/javascript" src="../plug-in/tools/easyuiextend.js"></script>
		<script type="text/javascript" src="../plug-in/jquery-plugs/hftable/jquery-hftable.js"></script>
		<script type="text/javascript" src="../plug-in/tools/json2.js"></script>
	</head>

	<body style="overflow-y: hidden" scroll="no">

		<script type="text/javascript">
			$(function() {
				storage = $.localStorage;
				if(!storage) storage = $.cookieStorage;
				$('#listenerList').datagrid({
					idField: 'id',
					title: '',
					url: '${basePath}/workFlowListener/listData.do?listenerType=${param.listenerType}&rows=100',
					fit: true,
					loadMsg: '数据加载中...',
					pageSize: 10,
					pagination: false,
					pageList: [10, 20, 30],
					sortOrder: 'asc',
					rownumbers: true,
					singleSelect: false,
					fitColumns: true,
					striped: true,
					showFooter: true,
					frozenColumns: [
						[{ field: 'ck', checkbox: 'true' }, ]
					],
					columns: [
						[{ field: 'id', title: 'id', hidden: true, sortable: true }, { field: 'listenerName', title: '名称', width: 20, sortable: true }, { field: 'transMap.excuteTypeName', title: '事件', width: 40, sortable: true }, { field: 'transMap.listenerTypeName', title: '执行类型', width: 40, sortable: true }, { field: 'excuteContent', title: '执行内容', width: 40, sortable: true }]
					],
					onLoadSuccess: function(data) {
						$("#listenerList").datagrid("clearSelections");
						$(this).datagrid("fixRownumber");

					},
					onClickRow: function(rowIndex, rowData) {
						rowid = rowData.id;
						gridname = 'listenerList';
					}
				});
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

			function getlistenerListSelections(field) {
				var ids = []; var rows = $('#listenerList').datagrid('getSelections'); 
				for(var i = 0; i < rows.length; i++) {
					ids.push(rows[i][field]);
					console.log(rows[i]);
				} ids.join(','); 
				
				return ids };

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
					$('#listenerList').datagrid({ url: 'processController.do?listenerGrid&typeid=1&status=1&field=id,listenername,listenereven,listenertype,listenervalue,', pageNumber: 1 });
				}
			}

			function dosearch(params) {
				var jsonparams = $.parseJSON(params);
				$('#listenerList').datagrid({ url: 'processController.do?listenerGrid&typeid=1&status=1&field=id,listenername,listenereven,listenertype,listenervalue,', queryParams: jsonparams });
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
				$('#listenerList').datagrid({ url: 'processController.do?listenerGrid&typeid=1&status=1&field=id,listenername,listenereven,listenertype,listenervalue,', pageNumber: 1 });
			}
		</script>
		<table width="100%" id="listenerList" toolbar="#listenerListtb"></table>
		<div id="listenerListtb" style="padding:3px; height: auto">
			<div style="height:0px;"><span style="float:left;"></span>
				<div style="clear:both"></div>
			</div>
	</body>

</html>