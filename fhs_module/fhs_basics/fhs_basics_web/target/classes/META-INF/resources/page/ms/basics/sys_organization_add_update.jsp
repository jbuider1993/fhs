<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/page/ms/common/form_include.jsp"></jsp:include>
<form id="sysOrganizationForm" method="post">
	<div id="formulaCaclDiv">
		<div class="fitem clearBR">
			<input type="hidden" id="parentId" name="parentId"
				   value="${param.parentId}">

			<div class="fitem">
				<div class="fitemDiv">
					<label>机构名称:</label><input id="name" class="easyui-validatebox"
											   type="text" value="${name }" name="name" datatype="*"
											   nullmsg="请填写机构名称" /> <span class="form-field-required">*</span>
				</div>
			</div>

			<div class="fitem">
				<div class="fitemDiv">
					<label >状态:</label>
					<select id="isDisable" name="isDisable" class="easyui-combobox"
							prompt="状态" datatype="*" nullmsg="请选择状态"
							data-options="
		                                valueField: 'wordbookCode',
		                                textField: 'wordbookDesc',
		                                editable : false,
		                                panelHeight: 'auto',
		                                url: '${systemServiceUrl}webApi/wordbook/getData?wordbookGroupCode=is_disable&jsonpCallback=?',
		                                onLoadSuccess : function (rec){
											var peo = $(this).combobox('getValue');
		                                    if(peo==''){
		                                   		peo = 0;
		                                    }
		                                   	$(this).combobox('select', rec[peo].wordbookCode);
										},
		                            ">
					</select>
					<span class="form-field-required">*</span>
				</div>
			</div>

			<script type="text/javascript">
                var url_save = "${basePath}ms/sysOrganization/insertOrganization";
                var id = '${param.id}';
                var sysOrganizationForm = null;
                $(function() {
                    //form验证。
                    sysOrganizationForm = $('#sysOrganizationForm')
                        .Validform({
                            tiptype : 5
                        });

                    if (isView) {
                        $('#subBtn').remove();
                        url_save = '';
                        loadForm();
                        return;
                    }
                    initError('addOrUpdateDialog');
                    // 如果是编辑页面，就初始化一些数据
                    if (isEdit) {
                        url_save = '${basePath}ms/sysOrganization/update?id=' + id;
                        loadForm();
                        return;
                    }
                });

                // 初始化编辑的form
                function loadForm() {
                    $('#sysOrganizationForm').form({
                        onLoadSuccess : formLoadSuccess
                    });
                    $('#sysOrganizationForm').form(
                        'load',
                        '${basePath}ms/sysOrganization/info/'
                        + id);
                }
                //表单数据
                function formLoadSuccess(info) {
                    if (isView) {
                        renderView('sysOrganizationForm');
                    }
                }

                function save() {
                    $('#sysOrganizationForm').form('submit', {
                        url : url_save,
                        onSubmit : function() {
                            return (sysOrganizationForm.check());
                        },
                        success : function(d) {
                            d = $.parseJSON(d);
                            if (d.code == 200) {
                                Ealert("操作成功！");
                                closeDialog();
                                reload();
                                window.parent.document.getElementById('left').contentWindow.location.reload(true);
                            } else if(d.message != null && d.message != '') {
                                EalertE(d.message);
                            } else {
                                EalertE('操作失败');
							}
                        }
                    });
                };
			</script>
</form>
