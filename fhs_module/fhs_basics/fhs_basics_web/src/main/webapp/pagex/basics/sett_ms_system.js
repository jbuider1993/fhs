var modelConfig = {
    title: '子系统管理', pkey: 'id', type: 'uuid', orderBy: 'update_time Desc',
    namespace: "sett_ms_system", table: 't_sett_ms_system',trans: true};
var listPage = {
    listFieldSett: function () {
        return [
            {name: 'name', title: '子系统名称', width: '20%', align: 'center'},
            {name: 'sort', title: '排序', width: '10%', align: 'center'},
            {name: 'is_enable', title: '状态', width: '10%', align: 'center',key:'is_enable',trans:'book',showField:'transMap.is_enableName'},
            {name: 'type', title: '类型', width: '10%', align: 'center',key:'system_type',trans:'book',showField:'transMap.typeName'},
            {name: 'create_time', title: '创建时间', width: '15%', align: 'center'},
            {name:'create_user',title:'创建人',width:'10%',align:'center',trans:'auto',showField:'transMap.create_userUserName'},
            {name: 'update_time', title: '更新时间', width: '14%', align: 'center'},
            {name:'update_user',title:'修改人',width:'10%',align:'center',trans:'auto',showField:'transMap.update_userUserName'},
        ]
    },
    isColumnButton: function () {
        return false;
    },
    filters: function () {
        return [
            {name: 'name', type: 'input', title: '子系统名称', filterType: 'like'}
        ];
    },
    buttons: function () {
        return [];
    },
    disableButtons: function () {
        return [];
    },
    otherFunctions: function () {
        return {}
    }
};

var add = {
    formFields: function () {
        return [
            {name: 'name', title: '子系统名称', required: true, type: 'input'},
            {name: 'sort', title: '排序', required: true, dataType:'n', type: 'input'},
            {name:'logo',title:'logo',type:'up',placeholder:'请上传图片'},
            {name: 'is_enable', title: '状态', type:'book',code:'is_enable', required: true,},
            {name: 'type', title: '类型', type:'book',code:'system_type', required: true,},
            {name: 'url', title: '跳转url',  type: 'input',dataType:'url'},
            {name: 'index_url', title: '首页url',  type: 'input',dataType:'url'},
        ];
    },
    otherFunctions: function () {
        return {
            ready: function () {
            },
            loadSuccess: function (info) {

            },
            onSave: function () {

            },
            saveSucess: function () {

            },
            saveError: function () {

            }
        }
    }
};
