//判断数组中是否包含字符串
function isArrayContainStr(array, str) {
	var flag = false;
	if (!isNullOrEnpty(array) && !isNullOrEnpty(str)) {
		for (var i = 0; i < array.length; i++) {
			if (array[i] == str) {
				flag = true;
				break;
			}
		}
	}
	return flag;
}
// 从数组中移除元素
function removeItemFromArray(array, str) {
	var arr = new Array();
	if (!isNullOrEnpty(array) && !isNullOrEnpty(str)) {
		for (var i = 0; i < array.length; i++) {
			if (array[i].split("-")[0] == str) {
				arr = array.remove(array.indexOf(array[i]));
			}
		}
	}
	return arr;
}
// 从数组中移除不在另一个数组中的元素(从returnArray中移除不在array中的元素)
function removeItemOfNotArray(array, returnArray, selectId) {
	var numArray = new Array();
	var arr = returnArray;
	for (var i = 0; i < array.length; i++) {
		numArray.push(array[i].split("-")[1]);
	}
	for (var j = 0; j < returnArray.length; j++) {
		if (!isArrayContainStr(numArray, returnArray[j].split("-")[1])) {
			arr = arr.remove(arr.indexOf(returnArray[j]));
			removeOption(selectId, returnArray[j].split("-")[0]);
		}
	}
	return arr;
}
// 从select中移除指定的option
function removeOption(selectId, val) {
	$("#" + selectId + " option[value='" + val + "']").remove();
}

function isExistence(array, str) {
	var flag = false;
	var numArray = new Array();
	for (var i = 0; i < array.length; i++) {
		numArray.push(array[i].split("-")[1]);
	}
	if (!isNullOrEnpty(array) && !isNullOrEnpty(str)) {
		flag = isArrayContainStr(numArray, str);
	}
	return flag;
}
// 将数组中的元素去掉重复项之后重新返回字符串
function unique(arr,reg) {
	var regx = ",";
	if(!isNullOrEnpty(reg)){
		regx = reg;
	}
	// 去除第一个空元素
	if (isNullOrEnpty(arr[0])) {
		arr.shift();
	}
	var result = [], hash = {};
	for (var i = 0, elem; (elem = arr[i]) != null; i++) {
		if (!hash[elem]) {
			result.push(elem);
			hash[elem] = true;
		}
	}
	return result.join(regx);

}
// 清除多选select中的值(select的id,清除所有(true,false))
// 移除的时候判断工作选择select中是否有不存在的对象种类,有--移除
// 判断对象选择select中是否有不存在的工作对象种类,有--移除
// isIf是专门为弹出框而判断的。
function clearMultiple(selectId, all, otherSelectId, isIf) {
	var reg = /,$/gi;
	if (all) {
		if ($("#" + selectId + " option:selected").length == 0 && isIf) {
			Ealert("请选择一条数据进行操作！");
			return false;
		}
		$("#" + selectId + " option")
				.each(
						function(i, n) {
							$("#" + selectId + " option[value='"+ $(this).val() + "']").remove();
						});
	} else {
		if ($("#" + selectId + " option:selected").length == 0 && isIf) {
			Ealert("请选择一条数据进行操作！");
			return false;
		}
		if (isNullOrEnpty(otherSelectId)) {
			$("#" + selectId + " option:selected")
					.each(
							function() {
				$("#" + selectId + " option[value='"+ $(this).val() + "']").remove();
				var array = $('#maintenanceWorkIdEs').val().replace(reg, "").split(",");
				var workArray = !isNullOrEnpty($('#workArray').val())?$('#workArray').val().replace(reg, "").split(";"):'';
				var workVal = removeItemFromArray(array, $(this).val());
				var val = removeItemFromArray(array, $(this).val());
				$('#maintenanceWorkIdEs').val(val);
				$('#workArray').val(workVal)
							});
			var array2 = $('#maintenanceCategoriesIds').val().replace(reg, "").split(",");
			var array1 = $('#maintenanceWorkIdEs').val().replace(reg, "").split(",");
			var val2 = removeItemOfNotArray(array1, array2, 'maintenance');
			$('#maintenanceCategoriesIds').val(val2)
		} else {
			$("#" + selectId + " option:selected").each(
					function() {
						// 获取隐藏域中的值
						var array = $('#maintenanceCategoriesIds').val().replace(reg, "").split(",");
						$("#" + selectId + " option[value='"+ $(this).val() + "']").remove();
						var val = removeItemFromArray(array, $(this).val());
						$('#maintenanceCategoriesIds').val(val)

					});
			var array2 = $('#maintenanceWorkIdEs').val().replace(reg, "").split(",");
			var array1 = $('#maintenanceCategoriesIds').val().replace(reg, "").split(",");
			var val2 = removeItemOfNotArray(array1, array2, otherSelectId);
			$('#maintenanceWorkIdEs').val(val2);
			var a_arry = $('#maintenanceWorkIdEs').val().replace(reg, "").split(",");
			var b_arry = $('#workArray').val().replace(reg, "").split(";");
			var arr = new Array();
			for(var i = 0;i<a_arry.length;i++){
				$.each(b_arry, function(j, o) {
					if(a_arry[i].split("-")[0] == o.split("-")[0]){
						arr.push(o);
					}
				})
			}
			var a_a = "";
			if(arr.length!=0){
				 a_a = arr.join(";");
			}
			$('#workArray').val(a_a);
		}
	}
	reloadContent();
}

// 将工作grid中的数据添加到multipleSelect中
function setWork(isNotNotify) {
	$('#maintenanceCategoriesIds').val();
	var reg = /,$/gi;
	var array = $('#maintenanceCategoriesIds').val().replace(reg, "")
			.split(",");
	var rows = $('#workGrid').datagrid('getSelections')
	for (var i = 0; i < rows.length; i++) {
		//只有手动选择的时候才去判断查看的时候就直接显示就行不用判断了
		if(isNotNotify){
			if (!isExistence(array, rows[i].maintenanceCategoriesId) && isFirstAdd) {
				Ealert("当前所选<span style='color:red;'>"
						+ rows[i].maintenanceCategoriesName
						+ "</span>维保对象种类下不存在维保对象或者当前所选对象不包括该种类,请重新选择!")
				continue;
			}
		}
		$('#work').append(
				"<option value='" + rows[i].maintenanceWorkIdE + "'>"
						+ rows[i].maintenanceCategoriesName + "("
						+ rows[i].maintenanceWorkName + ")["
						+ rows[i].maintenanceWorkCycleName + "]</option>");
		$('#maintenanceWorkIdEs').val(
				$('#maintenanceWorkIdEs').val() + ","
						+ rows[i].maintenanceWorkIdE + "-"
						+ rows[i].maintenanceCategoriesId + ",");
		$('#workArray').val(
				$('#workArray').val() 
						+ ";" + rows[i].maintenanceWorkIdE 
						+ "-" + rows[i].maintenanceWorkCycle 
						+ "-" + rows[i].workOrderExeMark.substring(1,rows[i].workOrderExeMark.length-1) 
						);
		var arrayEle = $('#maintenanceWorkIdEs').val().replace(reg, "").split(",");
		var workArrayEle = !isNullOrEnpty($('#workArray').val())?$('#workArray').val().replace(reg, "").split(";"):'';
		var newArray = unique(arrayEle);
		var newWorkArray = !isNullOrEnpty(workArrayEle)?unique(workArrayEle,";"):'';
		$('#workArray').val(newWorkArray);
		$('#maintenanceWorkIdEs').val(newArray);
	}

	cancelEcho('work');
}
// ztree中调用的方法将树的节点传送到多选select中
function setMaintenance(id, name, maintenanceIdE, maintenanceCategoriesId,
		maintenanceCategoriesName) {
	var reg = /,$/gi;
	$('#maintenance').append(
			"<option value='" + maintenanceIdE + "'>" + name + "</option>");
	$('#maintenanceCategoriesIds').val(
			$('#maintenanceCategoriesIds').val() + "," + maintenanceIdE + "-"+ maintenanceCategoriesId + ",");
	var arrayEle = $('#maintenanceCategoriesIds').val().replace(reg, "").split(
			",");
	var newArrayStr = unique(arrayEle);
	$('#maintenanceCategoriesIds').val(newArrayStr)
	cancelEcho('maintenance');

}
// 展开panel
function expandPanel(panelId1, panelId2) {
	// 以window方式打开dialog并重新布局
	$('#addOrUpdateDialog').window('open').window('resize', {
		width : '1100px',
		height : '400px'
	});
	$('#' + panelId1).show();
	$('#' + panelId2).hide();
	reloadGrid();
	reloadContent();
}
// 根据选择的维保对象的种类加载对应的工作grid
function reloadContent() {
	var maintenanceCategoriesIdsVal = $('#maintenanceCategoriesIds').val();
	var maintenanceCategoriesIdsArrayVal = maintenanceCategoriesIdsVal
			.split(",")
	var valArray = new Array();
	$(maintenanceCategoriesIdsArrayVal).each(function(i, n) {
		valArray.push("'" + n.split("-")[1] + "'")
	})
	var maintenanceCategoriesIdsVals = valArray.distinct().join(",");
	$('#workGrid').datagrid('load', {
		categoriesIdArrays : maintenanceCategoriesIdsVals
	})
}
function next() {
	return true;
}

//判断选择的维保工作的生成周期是什么？然后附加参数再做限制
//每半月附加参数5
//每半年附加参数6
//每周附加参数1
//每季度附加参数7
//每年附加参数3
//每月附加参数2
//每两年附加参数4
function blurReg(combId,obj){
	if($('#'+combId).combobox('getValue')==1){
		var reg = /^[1-7]$/,
		regText="<br><br><br>输入格式:<span style='color:red'>1-7之间的正整数</span>";
		 test(reg,obj,regText);
	}
	if($('#'+combId).combobox('getValue')==2){
		var reg = /^(1[0-9]|2[0-8]|[0-9])$/,
		regText="<br><br><br>输入格式:<br><span style='color:red'>1-28之间的正整数</span>";
		test(reg,obj,regText);
	}
	if($('#'+combId).combobox('getValue')==3){
		var reg = /^(1[0-2]|[0-9])\.(0[1-9]|1[0-9]|2[0-9]|3[0-1])$/,
		regText="<br><br><br>输入格式:<br><span style='color:red'>月.日 如:1.01</span>";
		 test(reg,obj,regText);
	}
	if($('#'+combId).combobox('getValue')==4){
		var reg = /^(1[0-2]|[0-9])\.(0[1-9]|1[0-9]|2[0-9]|3[0-1])$/,
		regText="<br><br><br>输入格式:<br><span style='color:red'>月.日 如:1.01</span>";
		 test(reg,obj,regText);
	}
	if($('#'+combId).combobox('getValue')==5){
		var reg = /^([1-9]|1[0-5])\,(1[0-6]|2[0-8])$/,
		regText="<br><br><br>输入格式:<br><span style='color:red''>前半月1-14之间的正整数,</span><br><span style='color:red''>后半月15-28之间的正整数中间用','隔开</span>";
		 test(reg,obj,regText);
	}
	if($('#'+combId).combobox('getValue')==6){
		var reg = /^[1-6]\.([1-3][0-1]|[1-2][0-9]|[0-0][1-9])\,([7-9]|1[0-2])\.([1-3][0-1]|[1-2][0-9]|[0-0][1-9])$/,
		regText="<br><br><br>输入格式:<br><br><span style='color:red'>前半年:月.日 1.01-6.30,</span><br><span style='color:red'>后半年:月.日 7.01-12.31</span><br><span style='color:red'>中间用','隔开</span>";
		 test(reg,obj,regText);
	}
	if($('#'+combId).combobox('getValue')==7){
		var reg = /^([1-3])\.(3[0-1]|2[0-9]|1[0-9]|0[1-9])\,([4-6])\.(0[1-9]|1[0-9]|2[0-9]|3[0-1])\,([7-9])\.(0[1-9]|1[0-9]|2[0-9]|3[0-1])\,(12|1[0-1])\.(0[1-9]|1[0-9]|2[0-9]|3[0-1])$/,
		regText="<br><br><br>输入格式:<br><br><span style='color:red'>依次输入月.日<br>1.01-3.31,<br>4.01-6.30,<br>7.01-9.30,<br>10.01-12.31</span><br><span style='color:red'>中间用','隔开</span>";
		 test(reg,obj,regText);
	}
}
//测试函数
function test(reg,obj,regText){
if(!reg.test(obj.value)){
	Ealert('输入不合法'+regText)
	obj.value=''
	}
}