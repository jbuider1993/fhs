var modelConfig = {
    title: '任务管理', pkey: 'jobClassName', type: 'uuid', orderBy: 'update_time Desc',dataGridUrl:'${path.basePath}/ms/task/list',
    saveUrl:'${path.basePath}/ms/task/add',
    namespace: "task", table: 'task',trans: false};
var listPage = {
    listFieldSett: function () {
        return [
            {name: 'jobName', title: '任务名称', width: '15%', align: 'center'},
            {name: 'jobGroup', title: '任务分组', width: '15%', align: 'center'},
            {name: 'description', title: '任务描述', width: '15%', align: 'center'},
            {name:'jobClassName',title:'任务类',width:'20%',align:'center'},
            {name: 'cronExpression', title: '时间表达式', width: '20%', align: 'center'},
        ]
    },
    isColumnButton: function () {
        return false;
    },
    filters: function () {
        return [
            {name: 'jobName', type: 'input', title: '任务名称', filterType: 'like'},
            {name: 'jobGroup', type: 'input', title: '任务分组', filterType: 'like'},
            {name: 'description', type: 'input', title: '任务描述', filterType: 'like'},
            {name: 'jobClassName', type: 'input', title: '任务类名', filterType: 'like'}
        ];
    },
    buttons: function () {
        return [{title:'触发',fun:'trigger',permissionsCode:'task:update',isRow:true},
                {title:'暂停',fun:'pause',permissionsCode:'task:update',isRow:true},
                {title:'恢复',fun:'resume',permissionsCode:'task:update',isRow:true},
                {title:'删除',fun:'remove',permissionsCode:'task:del',isRow:true}];
    },
    disableButtons: function () {
        return ['update','delete','export','view'];
    },
    otherFunctions: function () {
        return {
            trigger:function(row){
                $.ajax({
                    url:'${path.basePath}/ms/task/trigger?jobName=' + row.jobName + '&jobGroup=' + row.jobGroup,
                    dataType:'json',
                    success:function(result){
                        if(result.code==200){
                            Ealert('操作成功');
                        }else{
                            EalertE(result.message);
                        }
                    }
                })
            },
            pause:function(row){
                $.ajax({
                    url:'${path.basePath}/ms/task/pause?jobName=' + row.jobName + '&jobGroup=' + row.jobGroup,
                    dataType:'json',
                    success:function(result){
                        if(result.code==200){
                            Ealert('操作成功');
                        }else{
                            EalertE(result.message);
                        }
                    }
                })
            },
            resume:function(row){
                $.ajax({
                    url:'${path.basePath}/ms/task/resume?jobName=' + row.jobName + '&jobGroup=' + row.jobGroup,
                    dataType:'json',
                    success:function(result){
                        if(result.code==200){
                            Ealert('操作成功');
                        }else{
                            EalertE(result.message);
                        }
                    }
                })
            },
            remove:function(row){

                swal({
                    title: '删除',
                    text: "您确定要删除么?这将无法恢复!",
                    type: 'warning',   //感叹号图标
                    showCancelButton: true,   //显示取消按钮
                    confirmButtonText: "是的，我要删除",
                    confirmButtonColor: "#ec6c62",
                    cancelButtonText: '取消',
                }, function () {    //大部分，then是通用的回调函数
                    $.ajax({
                        url:'${path.basePath}/ms/task/remove?jobName=' + row.jobName + '&jobGroup=' + row.jobGroup,
                        dataType:'json',
                        success:function(result){
                            if(result.code==200){
                                Ealert('操作成功');
                                reload();
                            }else{
                                EalertE(result.message);
                            }
                        }
                    })
                })

            },

        }
    }
};

var add = {
    formFields: function () {
        return [
            {name: 'jobName', title: '任务名称', required: true, type: 'bigInput'},
            {name: 'jobGroup', title: '任务分组', required: true, type: 'bigInput'},
            {name: 'description', title: '任务描述', required: true, type: 'bigInput'},
            {name: 'jobClassName', title: '任务类', required: true, type: 'bigInput'},
            {name: 'cronExpression', title: '时间表达式', required: true, type: 'bigInput'},
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