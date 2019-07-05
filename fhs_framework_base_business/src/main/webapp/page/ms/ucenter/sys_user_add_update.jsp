<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/page/ms/common/form_include.jsp"></jsp:include>
<script type="text/javascript" src="${staticPath}js/jQuery.md5.js"></script>
<%@taglib prefix="hfst" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="hfs" uri="http://www.ylmo2o.com/tag/hfs"%>
<style>
	input[type="text"], input[type="password"]{
		margin-top: 0;
	}
</style>
<form id="addUpdateForm" method="post">
	<div class="fitem">
		<input id="userId" name="userId" hidden="true"/>
		<hfs:input  maxLenth="20" name="userNameShow" dataType="s2-10" title="姓名" required="true" />
		<input type="hidden" id="userName" name="userName"/>
		<hfs:input  maxLenth="20" name="userLoginName" dataType="/^[a-zA-Z0-9_]{1,}$/" title="登录名" required="true" />
	</div>
	<div class="fitem">
		<hfs:input  maxLenth="20" name="mobile" dataType="*,m" title="手机号" required="true" />
		<hfs:wordBook name="sex" title="性别" code="sex"  />
	</div>
	<div class="fitem">
		<input type="hidden" id="passWd" name="passWd"/>
		<hfs:input  maxLenth="64" name="password" title="密码" required="true" type="password" />
		<hfs:wordBook name="isDisable" title="状态" code="is_disable" required="true" />
	</div>
	<div class="fitem">
		<hfs:input  maxLenth="255" name="email" dataType="e|empty" title="邮箱"  />
		<hfs:province name="provinceId" areaName="areaId" cityName="cityId" required="false" title="省份" />
	</div>
	<div class="fitem">
		<div class="fitemDiv">
			<label>所属机构:</label>
			<select class="easyui-combotree" id="organizationId" datatype="*" nullmsg="请选择机构" name="organizationId" data-options="
						url:'${basePath}ms/sysOrganization/getOrgIdComBoxData',
						method:'get',
						" >
			</select>
			<span class="form-field-required">*</span>
		</div>
	</div>
	<div class="fitem">
	       <div class="bigLabelDiv">
                <label>角色:</label>
	       </div>
	       <div class="bigContent" id="roleListDiv">
                <select id="roleList" class="easyui-combobox"
                					name="roleList" multiple="multiple" style="width: 200px;"
                					datatype="*" nullmsg="请选择角色"
                					data-options="
                									valueField: 'roleId',
                									textField: 'roleName',
                									multiple:true,
                									editable : false,
                									panelHeight: 'auto'">

                			</select>
                			<span class="form-field-required">*</span>
	       </div>
	</div>
</form>
<hfst:readyTag nameSpace="sysUser" idField="userId"/>
<script type="text/javascript">
    var isInitCityId = false;
    var isInitProvinceId = false;

    $(function() {
        $("#saveButten").show();
        $("#organizationId").combotree({
            onChange:function(){
                var organizationId = $("#organizationId").combotree("getValue");
                reloadRoles(organizationId);
            }
        });
        //先注释掉不知道哪天又要用到
        //$('#organizationId').combotree({readonly:true});
        if(isEdit || isView){
            $("#userLoginName").attr('readonly', true);
        }
        //如果是添加的话，就默认赋值
		if(!isEdit&& !isView)
		{
            $('#organizationId').combotree('setValue','${param.organizationId}');

		}
        // 剔除当前不存在的角色（重新选角色时，前面多）
        $("#roleList").combobox({
            onChange: function(n,o){
                $("#roleListDiv").find("input[type='hidden'][name='roleList'][value='']").remove();
            }
        });

        if(isView){
            $("#saveButten").hide();
        };
    });
    //保存数据
    var url_temp = "${basePath}ms/sysUser/addUser";
    function save() {
            $('#userName').val($('#userNameShow').val());

            $('#addUpdateForm').form('submit', {
                url : url_temp,
                onSubmit : function() {
                    if(addUpdateForm.check())
					{
                        //修改
                        if(isEdit && $("input[name='password']").val() != $("#passWd").val() ) {
                            $("input[name='password']").val($.md5($("input[name='password']").val()))
                        }
                        //新增
                        if(!isView && !isEdit) {
                            $("input[name='password']").val($.md5($("input[name='password']").val()))
                        }
					    return true;
					}
                    return false;
                },
                success : function(d) {
                    d = $.parseJSON(d);
                    if (d.code == 500) {
                        EalertE('登录名已经存在');
                        return;
                    }
                    if (d.code == 200) {
                        Ealert("操作成功！");
                        refreshRedisCache();
                        closeDialog();
                        reload();
                    } else {
                        Ealert('操作失败');
                    }
                }
            });
    };

    //重新加载角色列表
    function  reloadRoles(_orgId) {

        $("#roleList").combobox({
            nullmsg : '请选择角色',
            valueField: 'roleId',
            textField: 'roleName',
            multiple:true,
            editable : false,
            panelHeight: 'auto',
            url: '${basePath}ms/sysRole/getSelectOrganSysRoles/' + _orgId + '?isAdd=${param.isAdd}'
        });

    }
    
    /**
     * 刷新所有用户缓存
     */
    function refreshRedisCache(){
        $.ajax({
            url : '${basePath}ms/sysUser/refreshRedisCache',
            success : function(data){

            }
        })
    }

    function initHandler() {
        //初始化
    }
    function loadFormHandler(info) {
        //加载数据
        if(isEdit) {
			$("#passWd").val(info.password);
		}
        $('#userNameShow').val($('#userName').val());
        var sourceVals =$("#roleList").combobox('getValues');
        reloadRoles($('#organizationId').combotree('getValue'));
        $("#roleList").combobox('setValues',sourceVals);
    }
    function saveFormHandler(info) {
        //保存数据
    }
    function saveAfterSuccessHandler() {
        //保存成功处理
    }
    function saveAfterErrorHandler() {
        //保存失败后处理
    }
</script>