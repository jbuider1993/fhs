<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/page/ms/common/form_include.jsp"></jsp:include>
<%@taglib prefix="hfs" uri="http://www.ylmo2o.com/tag/hfs"%>
<form id="addOrUpdateForm" method="post">

	<div class="fitem">
		<div class="fitemDiv">
			<label>子系统名称:</label><input id="name" class="easyui-validatebox"
										type="text" name="name" datatype="*" nullmsg="请填写子系统名称" />
			<span class="form-field-required">*</span>
		</div>
	</div>
	<div class="fitem">
		<div class="fitemDiv">
			<label>子系统排序:</label><input id="sort" class="easyui-validatebox"
										type="text" name="sort" datatype="*" nullmsg="请填写子系统排序" />
			<span class="form-field-required">*</span>
		</div>
	</div>

	<div class="fitem">
		<div class="fitemDiv">
			<label>&nbsp;</label> <img id="logoInputImg"
									   src="${staticPath}/images/upload_default_show.png" class="headerImg" />

			<input type="hidden" id="logoInputVal">
		</div>
	</div>

	<div class="fitem">
		<div class="fitemDiv">
			<div class="uploadLableDiv">
				<label class="uploadLable">子系统logo:</label>
			</div>
			<input type="button" id="logoInput" style="display: none;" />
		</div>
	</div>

	<input type="hidden" id="logo" name="logo" />

	<div class="fitem">
		<div class="bigLabelDiv">
			<label>&nbsp;</label>
		</div>
		<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;建议 60*60 像素或等比图片，图片支持 *.png, *.jpg, *.jpeg, *.gif</font></br>
	</div>

	<div class="fitem">
		<div class="fitemDiv">
			<label>状态:</label>
			<select id="isDisable" name="isDisable" class="easyui-combobox"
					data-options="
								valueField: 'wordbookCode',
								textField: 'wordbookDesc',
								editable : false,
								url: '${systemServiceUrl}webApi/wordbook/getData?wordbookGroupCode=is_disable&jsonpCallback=?',
								onLoadSuccess : function (rec){
									var peo = $(this).combobox('getValue');
									if(peo==''){
										peo = 0;
									}
									$(this).combobox('select', rec[peo].wordbookCode);
								},"
					datatype="*" nullmsg="请填写状态">
			</select>
			<span class="form-field-required">*</span>
		</div>
	</div>

	<div class="fitem">
		<div class="fitemDiv">
			<label>子系统类型:</label>
			<select id="type" name="type" class="easyui-combobox"
					data-options="
								valueField: 'wordbookCode',
								textField: 'wordbookDesc',
								editable : false,
								url: '${systemServiceUrl}webApi/wordbook/getData?wordbookGroupCode=system_type&jsonpCallback=?',"
					datatype="*" nullmsg="请填写子系统类型">
			</select>
			<span class="form-field-required">*</span>
		</div>
	</div>

	<div class="fitem">
		<div class="bigLabelDiv">
			<label>子系统URL:</label>
		</div>
		<div class="bigContent">
			<input id="url" name="url" class="big_text" type="text" />
		</div>
	</div>

	<div class="fitem">
		<div class="bigLabelDiv">
			<label>子系统首页URL:</label>
		</div>
		<div class="bigContent">
			<input id="indexUrl" name="indexUrl" class="big_text" type="text" />
		</div>
	</div>

	<script type="text/javascript">
        var url_save = "${basePath}ms/sysSystem/add";
        var addUpateUserForm = null;

        $(document)
            .ready(
                function() {
                    inituploadifyHeader("logoInput", "上传logo");
                    $('#subBtn').show();
                    //form验证。
                    addUpateUserForm = $('#addOrUpdateForm').Validform(
                        {
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
                        url_save = '${basePath}ms/sysSystem/update?id=${param.id}';
                        loadForm();
                        return;
                    }

                })

        // 初始化编辑的form
        function loadForm() {
            $('#addOrUpdateForm').form({
                onLoadSuccess : formLoadSuccess
            });
            $('#addOrUpdateForm')
                .form('load',
                    '${basePath}ms/sysSystem/info/${param.id}');
        }
        //表单数据
        function formLoadSuccess(info) {
            // 设置logo
            var logo = info.logo;
            $('#logoInputVal').val(logo);
            initHeader("logoInput", logo);
            if (isView) {
                renderView('addOrUpdateForm');
            }
        }

        function save() {
            $("#logo").val($("#logoInputVal").val());
            $('#addOrUpdateForm').form('submit', {
                url : url_save,
                onSubmit : function() {
                    return (addUpateUserForm.check());
                },
                success : function(d) {
                    d = $.parseJSON(d);
                    if (d && d.result) {
                        Ealert("操作成功！");
                        closeDialog();
                        reload();
                    } else {
                        Ealert('操作失败');
                    }
                }
            });
        };
	</script>
</form>