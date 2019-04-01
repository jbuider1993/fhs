var modelConfig = {
    title: '机构管理表',
    pkey: 'id',
    type: 'uuid',
    orderBy: 'update_time Desc',
    namespace: "organization",
    table: 't_ucenter_ms_organization',
    trans: true,
    extendsParam: 'parent_id=${param.parent_id}',
};
var listPage = {
    listFieldSett: function () {
        return [
            {name: 'name', title: '机构名称', width: '10%', align: 'center'},
            {name: 'parent_id', title: '父机构id', width: '10%', align: 'center', hidden: "true"},
            {
                name: 'is_disable',
                title: '是否禁用',
                width: '10%',
                formart: 'formatRowColor',
                align: 'center',
                trans: 'book',
                key: 'is_disable',
                showField: 'transMap.is_disableName'
            },
            {
                name: 'create_user',
                title: '创建人',
                width: '8%',
                align: 'center',
                trans: 'user',
                showField: 'transMap.create_userUserName'
            },
            {name: 'create_time', title: '创建时间', width: '10%', align: 'center'},
            {
                name: 'update_user',
                title: '更新人',
                width: '8%',
                align: 'center',
                trans: 'user',
                showField: 'transMap.create_userUserName'
            },
            {name: 'update_time', title: '更新时间', width: '10%', align: 'center'},
        ]
    },
    isColumnButton: function () {
        return true;
    },
    filters: function () {
        return [{name: 'name', type: 'input', title: '机构名称',filterType:'like'}];
    },
    buttons: function () {
        return [];
    },
    disableButtons: function () {
        return [];
    },
    otherFunctions: function () {
        return {
            onListPageReady: function () {
                $('#listGrid').datagrid({'title':'${param.name}下的子部门列表'});
            }
        }
    }
};

var add = {
    formFields: function () {
        return [
            {name: 'name', type: 'input', title: '机构名称', required: true},
            {name: 'parent_id', type: 'hide'},
            {name: 'is_disable', type: 'switch', title: '是否禁用', dft: false, required: true},
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

            },
        }
    }
}

tree = {
    listUrlSett: function () {
        return {'baidu': 'http://baidu.com/'};
    },
    key: function () {
        return {
            namekey: 'name',
            fidkey: 'parent_id'
        }
    }
}