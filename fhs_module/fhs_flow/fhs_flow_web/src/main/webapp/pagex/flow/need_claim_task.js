var modelConfig = {
    title: '待认领', pkey: 'id', type: 'uuid',
    orderBy: 'update_time Desc',
    namespace: "need_claim_task", table: 'need_claim_task', trans: true,
    dataGridUrl: '${path.basePath}/ms/myWorks/getNeedClaimTask',
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
            {title: '认领', fun: 'claim', permissionsCode: 'need_claim_task:see', isRow: true},
        ];
    },
    disableButtons: function () {
        return ["add", "update", "delete", "view","export"];
    },
    otherFunctions: function () {
        return {
            claim: function (row) {
                $.ajax({
                    url:'${path.basePath}/ms/myWorks/claimTask',
                    dataType:'json',
                    data:{taskId:row.taskId},
                    success:function(res){
                        if(res.code==200){
                            Ealert("认领成功");
                            reload();
                        }else{
                            EalertE(res.message)
                        }
                    }
                })
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