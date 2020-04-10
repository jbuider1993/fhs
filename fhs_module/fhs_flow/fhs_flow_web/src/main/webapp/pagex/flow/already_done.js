var modelConfig = {
    title: '办理历史', pkey: 'id', type: 'uuid',
    orderBy: 'update_time Desc',
    namespace: "already_done", table: 't_flow_task_history', trans: true,
    dataGridUrl: '${path.basePath}/ms/flowTaskHistory/getTaskHistoryList',
};
var listPage = {
    listFieldSett: function () {
        return [
            {name: 'processTitle', title: '流程实例标题', width: '19%', align: 'center'},
            {name: 'processName', title: '流程名称', width: '15%', align: 'center'},
            {name: 'taskName', title: '任务名称', width: '10%', align: 'center'},
            {name: 'processStatus', title: '流程状态', width: '10%', align: 'center',align: 'center',trans: 'book', showField: 'transMap.processStatusName'},
            {name: 'createUser', title: '发起人', width: '10%', align: 'center',trans: 'user', showField: 'transMap.userName'},
            {name: 'taskFinishTime', title: '办理时间', width: '15%', align: 'center'},
            {name: 'useTime', title: '处理用时', width: '10%', align: 'center'},
            {name: 'useStatus', title: '处理结果', width: '10%', align: 'center',align: 'center',trans: 'book', showField: 'transMap.useStatusName'},
        ]
    },
    isColumnButton: function () {
        return true;
    },
    filters: function () {
        return [
            {name: 'processTitle', type: 'input', title: '流程标题', filterType: 'like'},
            {name: 'processName', type: 'input', title: '任务名称', filterType: 'like'},
        ];
    },
    buttons: function () {
        return [
            {title: '详情', fun: 'details', permissionsCode: 'act_ru_task:see', isRow: true},
        ];
    },
    disableButtons: function () {
        return ["add", "update", "delete", "view", "export"];
    },
    otherFunctions: function () {
        return {
            details: function (row) {
                openDialog('${path.basePath}/b/page-ms-flow/handle?isView=true&isTaskHistory=true&taskId='+row.taskId + '&instanceId=' + row.instanceId,
                    '查看任务 {' + row.processTitle + "}");
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