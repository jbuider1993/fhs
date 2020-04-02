var modelConfig = {
    title: '服务管理', pkey: 'id', type: 'uuid', orderBy: 'update_time Desc',
    namespace: "sett_ms_menu_server", table: 't_sett_ms_menu_server',trans: true};
var listPage = {
    listFieldSett: function () {
        return [
            {name: 'server_name', title: '服务名称', width: '25%', align: 'center'},
            {name: 'server_url', title: '服务链接', width: '25%', align: 'center'},
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
            {name: 'server_name', type: 'input', title: '服务名称', filterType: 'like'}
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
            {name: 'server_name', title: '服务名称', required: true, type: 'input'},
            {name: 'server_url', title: '服务链接', required: true, type: 'input'},
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