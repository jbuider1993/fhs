var modelConfig = {
    title: '代办事项', pkey: 'id', type: 'uuid',
    orderBy: 'update_time Desc',
    namespace: "act_ru_task", table: 'act_ru_task', trans: true, db: "park",
    dataGridUrl: '${path.basePath}/ms/myWorks/getNeedComplateTask',
};
var listPage = {
    listFieldSett: function () {
        return [
            {name: 'title', title: '流程标题', width: '35%', align: 'center'},
            {name: 'taskName', title: '任务名称', width: '19%', align: 'center'},
            {
                name: 'createUser',
                title: '申请人',
                width: '15%',
                align: 'center',
                trans: 'user',
                showField: 'transMap.create_userUserName'
            },
            {name: 'instanceCreateTime', title: '申请时间', width: '15%', align: 'center'},
            {name: 'taskCreateTime', title: '任务创建时间', width: '15%', align: 'center'},
        ]
    },
    isColumnButton: function () {
        return true;
    },
    filters: function () {
        return [
            {name: 'title', type: 'input', title: '流程标题', filterType: 'like'},
            {name: 'taskName', type: 'input', title: '任务名称', filterType: 'like'},
        ];
    },
    buttons: function () {
        return [
            {title: '办理', fun: 'handle', permissionsCode: 'act_ru_task:see', isRow: true},
        ];
    },
    disableButtons: function () {
        return ["add", "update", "delete", "view","export"];
    },
    otherFunctions: function () {
        return {
            handle: function (row) {
                debugger;
                openDialog('${path.basePath}/b/page-ms/handle?taskId='+row.taskId,
                    '处理任务 {' + row.title + "}");
            }
        };
    }
};
var add = {
    formFields: function () {
        return [
            {name: 'free_type', type: 'input', title: '免费类型', type: 'book', code: 'free_type', required: true},
            {name: 'plate_number', type: 'input', title: '车牌号', required: true},
            {
                name: 'park_id',
                type: 'select',
                url: '${path.basePath}/ms/x/parking/findListData',
                valuefield: 'id',
                textfield: 'parkName',
                title: '停车场名称',
                required: true
            },
            {name: 'is_sync', type: 'hide'}
        ];
    },
    otherFunctions: function () {
        return {
            ready: function () {
            },
            loadSuccess: function (info) {
            },
            onSave: function () {
                $('#isSync').val(0);
            },
            saveSucess: function () {
            },
            saveError: function () {
            }
        }
    }
};