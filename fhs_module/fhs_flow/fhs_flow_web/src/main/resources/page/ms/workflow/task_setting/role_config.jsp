<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(function() { storage = $.localStorage; if(!storage) storage = $.cookieStorage;
		$('#groupList').datagrid({ idField: 'roleId', title: '角色列表', url: '${basePath}/adminRole/listData.do', fit: true, loadMsg: '数据加载中...', pageSize: 10, pagination: false, pageList: [10, 20, 30], sortOrder: 'asc', rownumbers: true, singleSelect: false, fitColumns: true, striped: true, showFooter: true, frozenColumns: [
				[{ field: 'ck', checkbox: 'true' }, ]
			], columns: [
				[{ field: 'roleName', title: '角色名', width: 40, sortable: true }, { field: 'roleId', title: '角色编码', width: 40, sortable: true }]
			], onLoadSuccess: function(data) { $("#groupList").datagrid("clearSelections");
				$(this).datagrid("fixRownumber"); if(!false) { if(data.total && data.rows.length == 0) { var grid = $('#groupList'); var curr = grid.datagrid('getPager').data("pagination").options.pageNumber;
						grid.datagrid({ pageNumber: (curr - 1) }); } } }, onClickRow: function(rowIndex, rowData) { rowid = rowData.id;
				gridname = 'groupList'; } });
		$('#groupList').datagrid('getPager').pagination({ beforePageText: '', afterPageText: '/{pages}', displayMsg: '{from}-{to}共 {total}条', showPageList: true, showRefresh: true });
		$('#groupList').datagrid('getPager').pagination({ onBeforeRefresh: function(pageNumber, pageSize) { $(this).pagination('loading');
				$(this).pagination('loaded'); } }); try { restoreheader(); } catch(ex) {} });

	function reloadTable() { try { $('#' + gridname).datagrid('reload');
			$('#' + gridname).treegrid('reload'); } catch(ex) {} }

	function reloadgroupList() { $('#groupList').datagrid('reload'); }

	function getgroupListSelected(field) { return getSelected(field); }

	function getSelected(field) { var row = $('#' + gridname).datagrid('getSelected'); if(row != null) { value = row[field]; } else { value = ''; } return value; }

	function getgroupListSelections(field) { var ids = []; var rows = $('#groupList').datagrid('getSelections'); for(var i = 0; i < rows.length; i++) { ids.push(rows[i][field]); } ids.join(','); return ids };

	function getSelectRows() { return $('#groupList').datagrid('getChecked'); }

	function saveHeader() { var columnsFields = null; var easyextends = false; try { columnsFields = $('#groupList').datagrid('getColumns');
			easyextends = true; } catch(e) { columnsFields = $('#groupList').datagrid('getColumnFields'); } var cols = storage.get('groupListhiddenColumns'); var init = true; if(cols) { init = false; } var hiddencolumns = []; for(var i = 0; i < columnsFields.length; i++) { if(easyextends) { hiddencolumns.push({ field: columnsFields[i].field, hidden: columnsFields[i].hidden }); } else { var columsDetail = $('#groupList').datagrid("getColumnOption", columnsFields[i]); if(init) { hiddencolumns.push({ field: columsDetail.field, hidden: columsDetail.hidden, visible: (columsDetail.hidden == true ? false : true) }); } else { for(var j = 0; j < cols.length; j++) { if(cols[j].field == columsDetail.field) { hiddencolumns.push({ field: columsDetail.field, hidden: columsDetail.hidden, visible: cols[j].visible }); } } } } } storage.set('groupListhiddenColumns', JSON.stringify(hiddencolumns)); }

	function isShowBut() { var isShowSearchId = $('#isShowSearchId').val(); if(isShowSearchId == "true") { $("#searchColums").hide();
			$('#isShowSearchId').val("false");
			$('#columsShow').remove("src");
			$('#columsShow').attr("src", "plug-in/easyui/themes/default/images/accordion_expand.png"); } else { $("#searchColums").show();
			$('#isShowSearchId').val("true");
			$('#columsShow').remove("src");
			$('#columsShow').attr("src", "plug-in/easyui/themes/default/images/accordion_collapse.png"); } }

	function restoreheader() { var cols = storage.get('groupListhiddenColumns'); if(!cols) return; for(var i = 0; i < cols.length; i++) { try { if(cols.visible != false) $('#groupList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field); } catch(e) {} } }

	function resetheader() { var cols = storage.get('groupListhiddenColumns'); if(!cols) return; for(var i = 0; i < cols.length; i++) { try { $('#groupList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field); } catch(e) {} } }

	function groupListsearch() { try { if(!$("#groupListForm").Validform({ tiptype: 3 }).check()) { return false; } } catch(e) {} if(true) { var queryParams = $('#groupList').datagrid('options').queryParams;
			$('#groupListtb').find('*').each(function() { queryParams[$(this).attr('name')] = $(this).val(); });
			$('#groupList').datagrid({ url: 'activitiController.do?getGroups&field=name,id,', pageNumber: 1 }); } }

	function dosearch(params) { var jsonparams = $.parseJSON(params);
		$('#groupList').datagrid({ url: 'activitiController.do?getGroups&field=name,id,', queryParams: jsonparams }); }

	function groupListsearchbox(value, name) { var queryParams = $('#groupList').datagrid('options').queryParams;
		queryParams[name] = value;
		queryParams.searchfield = name;
		$('#groupList').datagrid('reload'); } $('#groupListsearchbox').searchbox({ searcher: function(value, name) { groupListsearchbox(value, name); }, menu: '#groupListmm', prompt: '请输入查询关键字' });

	function EnterPress(e) { var e = e || window.event; if(e.keyCode == 13) { groupListsearch(); } }

	function searchReset(name) { $("#" + name + "tb").find(":input").val(""); var queryParams = $('#groupList').datagrid('options').queryParams;
		$('#groupListtb').find('*').each(function() { queryParams[$(this).attr('name')] = $(this).val(); });
		$('#groupList').datagrid({ url: 'activitiController.do?getGroups&field=name,id,', pageNumber: 1 }); }
</script>
<table width="100%" id="groupList" toolbar="#groupListtb"></table>
<div id="groupListtb" style="padding:3px; height: auto">
	<div style="height:0px;"><span style="float:left;"></span>
		<div style="clear:both"></div>
	</div>
	<script type="text/javascript">
		function xuanze() {
			var ids = getgroupListSelections("roleId");
			$('#expression').val(ids);
		}
	</script>