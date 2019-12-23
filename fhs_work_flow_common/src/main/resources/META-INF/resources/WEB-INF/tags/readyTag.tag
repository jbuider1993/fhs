<%@ tag pageEncoding="utf-8"%>
<!-- 定义了两个标签属性 -->
<%@ attribute name="nameSpace" required="true" rtexprvalue="true"
	description="nameSpace"%>

<%@ attribute name="idField" required="false" rtexprvalue="false"
			  description="idField"%>

<script type="text/javascript">
	var url_save = "${basePath}ms/${nameSpace}/add";
	var id = '${param.id}';
	var addUpdateForm = null;
	//判断是否有UE文本编译器
	var ueArray = new Array();
	$("[tags]").each(function() {
		ueArray.push($(this).attr("id"));
	});
	$.each(ueArray, function(index, value) {
		window['ue_' + value];
		UE.delEditor(value);
		window['ue_' + value] = UE.getEditor(value);
	});
	$(function() {
		//初始化加载
		if (isExitsFunction("initHandler")) {
			initHandler();
		}
		$('#subBtn').show();
		addUpdateForm = $('#addUpdateForm').Validform({
			tiptype : 5
		});
		if (isView) {
			$('#subBtn').hide();
			url_save = '';
			loadForm();
			return;
		}
		initError('addOrUpdateDialog');
		// 如果是编辑页面，就初始化一些数据
		if (isEdit) {
		    var idField = "${idField}";
		    if(idField==='')
			{
                idField='id';
            }
			url_save = '${basePath}ms/${nameSpace}/update?'+ idField + '=' + id;
			loadForm();
			return;
		}
	});

	//初始化编辑的form
	function loadForm() {
		$('#addUpdateForm').form({
			onLoadSuccess : loadFormSuccess
		});
		$('#addUpdateForm').form('load',
				'${basePath}ms/${nameSpace}/info/'+id);
	}
	//加载表单数据
	function loadFormSuccess(info) {
        address_function(info.provinceId, info.cityId, info.areaId);
		//表单数据加载
		if (isExitsFunction("loadFormHandler")) {
			loadFormHandler(info);
		}
		if (isView) {
			renderView('addUpdateForm');
		}
        if (isExitsFunction("markPoint")) {
            markPoint(info);
        }
	}

	function save() {
	    var flag = true;
		//保存数据加载
		if (isExitsFunction("saveFormHandler")) {
			flag = saveFormHandler();
			if(typeof (flag) == 'undefined') {
			    flag = true;
			}
		}
		// 如果自定义校验方法没有校验正确，则也校验下form里面内容是否正确
		if(!flag)
		{
            addUpdateForm.check();
		}
		if(flag) {
            $('#addUpdateForm').form('submit', {
                url : url_save,
                onSubmit : function() {
                    return (addUpdateForm.check());
                },
                success : function(d) {
                    d = $.parseJSON(d);
                    if (d && d.result) {
                        Ealert("操作成功！");
                        closeDialog();
                        reload();
                        if (isExitsFunction("saveAfterSuccessHandler")) {
                            saveAfterSuccessHandler();
                        }
                    } else {

                        if(d.code == 300 || d.code==400 || d.code==500 )
						{
                            $.toast({
                                heading: '警告',
                                text: d.message,
                                showHideTransition: 'fade',
                                icon: 'error',
                                hideAfter: 2000,
                                position:{
                                    top:50,
                                    right:100
                                }
                            });
						}
						else
						{
                            Ealert('操作失败');
						}
                        if (isExitsFunction("saveAfterErrorHandler")) {
                            saveAfterErrorHandler();
                        }
                    }
                }
            });
		}

	};


	//判断变量是否存在
	function isExitsVariable(variableName) {
		try {
			if (typeof (variableName) == "undefined") {
				//alert("value is undefined"); 
				return false;
			} else {
				//alert("value is true"); 
				return true;
			}
		} catch (e) {
		}
		return false;
	}

	//UE文本编译器回显
	function ContentUE_function(ueArryCon) {
		$.each(ueArray, function(index, value) {
			var content = ueArryCon[index];
			window['ue_' + value].ready(function() {
				window['ue_' + value].setContent(content);
			});
		});
	}

	//地址回显
	function address_function(provinceId, cityId, address) {
	    try{
            var url = "${systemServiceUrl}webApi/area/getProvinceData?areaParentId=";
            var cityUrl = url + provinceId + "&jsonpCallback=?";
            $('#cityId').combobox('reload', cityUrl);
            var areaUrl = url + cityId + "&jsonpCallback=?";
            $('#areaId').combobox('reload', areaUrl);
		}catch (e)
		{
		    console.log(e);
		}

	}
</script>


