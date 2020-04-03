<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(function() { storage = $.localStorage; if(!storage) storage = $.cookieStorage;
		$('#userList').datagrid({ idField: 'userLoginName', title: '用户列表', url: '${basePath}adminUser/listData.do.do', fit: true, loadMsg: '数据加载中...', pageSize: 10, pagination: false, pageList: [10, 20, 30], sortOrder: 'asc', rownumbers: true, singleSelect: true, fitColumns: true, striped: true, showFooter: true, frozenColumns: [
				[]
			], columns: [
				[{ field: 'userName', title: '用户名', width: 40, sortable: true }, { field: 'userLoginName', title: '用户账号', width: 40, sortable: true }]
			], onLoadSuccess: function(data) { $("#userList").datagrid("clearSelections");
				$(this).datagrid("fixRownumber"); if(!false) { if(data.total && data.rows.length == 0) { var grid = $('#userList'); var curr = grid.datagrid('getPager').data("pagination").options.pageNumber;
						grid.datagrid({ pageNumber: (curr - 1) }); } } }, onClickRow: function(rowIndex, rowData) { rowid = rowData.id;
				gridname = 'userList'; } });
		$('#userList').datagrid('getPager').pagination({ beforePageText: '', afterPageText: '/{pages}', displayMsg: '{from}-{to}共 {total}条', showPageList: true, showRefresh: true });
		$('#userList').datagrid('getPager').pagination({ onBeforeRefresh: function(pageNumber, pageSize) { $(this).pagination('loading');
				$(this).pagination('loaded'); } }); try { restoreheader(); } catch(ex) {} });

	function reloadTable() { try { $('#' + gridname).datagrid('reload');
			$('#' + gridname).treegrid('reload'); } catch(ex) {} }

	function reloaduserList() { $('#userList').datagrid('reload'); }

	function getuserListSelected(field) { return getSelected(field); }

	function getSelected(field) { var row = $('#' + gridname).datagrid('getSelected'); if(row != null) { value = row[field]; } else { value = ''; } return value; }

	function getuserListSelections(field) { var ids = []; var rows = $('#userList').datagrid('getSelections'); for(var i = 0; i < rows.length; i++) { ids.push(rows[i][field]); } ids.join(','); return ids };

	function getSelectRows() { return $('#userList').datagrid('getChecked'); }

	function saveHeader() { var columnsFields = null; var easyextends = false; try { columnsFields = $('#userList').datagrid('getColumns');
			easyextends = true; } catch(e) { columnsFields = $('#userList').datagrid('getColumnFields'); } var cols = storage.get('userListhiddenColumns'); var init = true; if(cols) { init = false; } var hiddencolumns = []; for(var i = 0; i < columnsFields.length; i++) { if(easyextends) { hiddencolumns.push({ field: columnsFields[i].field, hidden: columnsFields[i].hidden }); } else { var columsDetail = $('#userList').datagrid("getColumnOption", columnsFields[i]); if(init) { hiddencolumns.push({ field: columsDetail.field, hidden: columsDetail.hidden, visible: (columsDetail.hidden == true ? false : true) }); } else { for(var j = 0; j < cols.length; j++) { if(cols[j].field == columsDetail.field) { hiddencolumns.push({ field: columsDetail.field, hidden: columsDetail.hidden, visible: cols[j].visible }); } } } } } storage.set('userListhiddenColumns', JSON.stringify(hiddencolumns)); }

	function isShowBut() { var isShowSearchId = $('#isShowSearchId').val(); if(isShowSearchId == "true") { $("#searchColums").hide();
			$('#isShowSearchId').val("false");
			$('#columsShow').remove("src");
			$('#columsShow').attr("src", "plug-in/easyui/themes/default/images/accordion_expand.png"); } else { $("#searchColums").show();
			$('#isShowSearchId').val("true");
			$('#columsShow').remove("src");
			$('#columsShow').attr("src", "plug-in/easyui/themes/default/images/accordion_collapse.png"); } }

	function restoreheader() { var cols = storage.get('userListhiddenColumns'); if(!cols) return; for(var i = 0; i < cols.length; i++) { try { if(cols.visible != false) $('#userList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field); } catch(e) {} } }

	function resetheader() { var cols = storage.get('userListhiddenColumns'); if(!cols) return; for(var i = 0; i < cols.length; i++) { try { $('#userList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field); } catch(e) {} } }

	function userListsearch() { try { if(!$("#userListForm").Validform({ tiptype: 3 }).check()) { return false; } } catch(e) {} if(true) { var queryParams = $('#userList').datagrid('options').queryParams;
			$('#userListtb').find('*').each(function() { queryParams[$(this).attr('name')] = $(this).val(); });
			$('#userList').datagrid({ url: 'activitiController.do?getUsers&field=firstName,id,', pageNumber: 1 }); } }

	function dosearch(params) { var jsonparams = $.parseJSON(params);
		$('#userList').datagrid({ url: 'activitiController.do?getUsers&field=firstName,id,', queryParams: jsonparams }); }

	function userListsearchbox(value, name) { var queryParams = $('#userList').datagrid('options').queryParams;
		queryParams[name] = value;
		queryParams.searchfield = name;
		$('#userList').datagrid('reload'); } $('#userListsearchbox').searchbox({ searcher: function(value, name) { userListsearchbox(value, name); }, menu: '#userListmm', prompt: '请输入查询关键字' });

	function EnterPress(e) { var e = e || window.event; if(e.keyCode == 13) { userListsearch(); } }

	function searchReset(name) { $("#" + name + "tb").find(":input").val(""); var queryParams = $('#userList').datagrid('options').queryParams;
		$('#userListtb').find('*').each(function() { queryParams[$(this).attr('name')] = $(this).val(); });
		$('#userList').datagrid({ url: 'activitiController.do?getUsers&field=firstName,id,', pageNumber: 1 }); }
</script>
<table width="100%" id="userList" toolbar="#userListtb"></table>
<div id="userListtb" style="padding:3px; height: auto">
	<div style="height:0px;"><span style="float:left;"></span>
		<div style="clear:both"></div>
	</div>
	<script type="text/javascript">
		function xuanze() {
			var ids = getuserListSelections("userLoginName");
			$('#expression').val(ids);
		}
	</script>