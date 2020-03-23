var $Common = {
		add:function(url) {
			alert(url);
		},
		del:function(id, url) {
			alert(id + "\n" + url);
			//$Utils.openDialog(url, '添加应用信息');
		},
		update:function(id, url) {
			//$Utils.openDialog(url, '修改应用信息');
			alert(id + "\n" + url);
		}
}
var $Utils = {
		openDialog:function(url, title){
			$('#dlg').load(url,function(){
				$('#dlg').dialog('open').dialog('setTitle', title);
				$.parser.parse($('#dlg'));
			});
		}
}