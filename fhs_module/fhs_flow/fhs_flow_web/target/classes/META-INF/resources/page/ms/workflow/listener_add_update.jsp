<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/src/main/webapp/page/common/form_include.jsp"></jsp:include>
<form id="addOrUpdateForm" method="post">
	<div id="addUpateUserForm">
		<div class="fitem clearBR">
			<div class="fitem">
				<div class="fitemDiv">
					<label>监听器名称:</label><input id="listenerName" class="easyui-validatebox" type="text" value="${userName }" name="listenerName" datatype="*" nullmsg="监听器名称" />
				</div>

				<div class="fitemDiv">
					<label>监听类型:</label>
					<select id="typeselect" name="listenerType" class="easyui-combobox" data-options="
                                valueField: 'wordbookCode',
                                textField: 'wordbookDesc',
                                panelWidth: 155,
                                editable : false,
                                panelHeight: 'auto',
                                url: '${pub_service_url}wordbook/getData.do?wordbookGroupCode=workFlow_listener_type&jsonpCallback=?',
							" datatype="*" nullmsg="请选择监听类型">

						</select>
				</div>

			</div>

			<div class="fitem">

				<div class="fitemDiv">
					<label>事件:</label>
					<select id="typeselect" name="event" class="easyui-combobox" data-options="
                                valueField: 'wordbookCode',
                                textField: 'wordbookDesc',
                                panelWidth: 155,
                                editable : false,
                                panelHeight: 'auto',
                                url: '${pub_service_url}wordbook/getData.do?wordbookGroupCode=workFlow_event_type&jsonpCallback=?',
							" datatype="*" nullmsg="请选择事件">

						</select>
				</div>
				<div class="fitemDiv">
					<label>执行类型:</label>
					<select id="typeselect" datatype="*" nullmsg="请选择执行类型" name="excuteType" class="easyui-combobox" data-options="
                                valueField: 'wordbookCode',
                                textField: 'wordbookDesc',
                                panelWidth: 155,
                                editable : false,
                                panelHeight: 'auto',
                                url: '${pub_service_url}wordbook/getData.do?wordbookGroupCode=workFlow_excute_type&jsonpCallback=?',
							" >

						</select>
				</div>
			</div>

			<div class="fitem">
				<div class="bigLabelDiv">
					<label>执行内容:</label>
				</div>
				<div class="bigContent">
					<input id="excuteContent" class="easyui-validatebox" type="text" value="${userName }" name="excuteContent" datatype="*" nullmsg="请填写执行内容" />
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var url_save = "${basePath}workFlowListener/add.do";

			var addUpateUserForm = null;
			$(document).ready(function() {
				//form验证。
				addUpateUserForm = $('#addUpateUserForm').Validform({
					tiptype: 5
				});
				if(isView) {
					$('#subBtn').remove();
					url_save = '';
					loadForm();
					return;
				}
				initError('addOrUpdateDialog');
				// 如果是编辑页面，就初始化一些数据
				if(isEdit) {
					url_save = '${basePath}workFlowListener/update.do?id=${param.id}';
					loadForm();
					return;
				}
				
				
			})

			// 初始化编辑的form
			function loadForm()
			{
				$('#addOrUpdateForm').form({onLoadSuccess:formLoadSuccess});
				$('#addOrUpdateForm').form('load','${basePath}workFlowListener/getBeanData.do?id=${param.id}');
			}
			//表单数据
			function formLoadSuccess(info)
			{
				
				 if(isView)
				 {
					 renderView('addOrUpdateForm');
				 }
			}

			function save() {
				$('#addOrUpdateForm').form('submit', {
					url: url_save,
					onSubmit: function() {
						return(addUpateUserForm.check());
					},
					success: function(d) {
						d = $.parseJSON(d);
						if(d && d.result) {
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
	</div>
	</div>
</form>