var modelConfig = {
    title: '抄送我的', pkey: 'id', type: 'uuid',
    orderBy: 'update_time Desc',
    namespace: "flow_instance", table: 't_flow_instance', trans: true,
    dataGridUrl: '${path.basePath}/ms/flow_instance/findPage',
};
var listPage = {
    listFieldSett: function () {
        return [
            {name: 'title', title: '流程实例标题', width: '35%', align: 'center'},
            {name: 'xmlName', title: '流程名称', width: '19%', align: 'center',showField: 'transMap.xmlName'},
            {name: 'status', title: '流程实例状态', width: '19%', align: 'center',showField: 'transMap.statusName'},
            {name: 'createUser', title: '发起人', width: '10%', align: 'center',trans: 'user', showField: 'transMap.create_userUserName'},
            {name: 'create_time', title: '发起时间', width: '15%', align: 'center'},
        ]
    },
    isColumnButton: function () {
        return true;
    },
    filters: function () {
        return [
            {name: 'title', type: 'input', title: '流程标题', filterType: 'like'}
        ];
    },
    buttons: function () {
        return [
            {title: '详情', fun: 'detail', permissionsCode: 'flow_instance:see', isRow: true},
        ];
    },
    disableButtons: function () {
        return ["add", "update", "delete", "view","export"];
    },
    otherFunctions: function () {
        return {
            detail: function (row) {
                openDialog('${path.basePath}/b/page-ms-flow/handle?isView=true&instanceId=' + row.activitiProcessInstanceId,
                    '处理任务 {' + row.title + "}");
            }
        };
    }
};
var add = {
    formFields: function () {
        return [

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