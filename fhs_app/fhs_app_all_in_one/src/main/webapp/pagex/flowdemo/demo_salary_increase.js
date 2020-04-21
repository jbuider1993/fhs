var modelConfig = {
    title: '涨薪', pkey: 'id', type: 'uuid', orderBy: 'update_time Desc',processDefinitionKey:'demo_salary_increase',
    saveUrl:'${path.basePath}/ms/x/flow/demo_salary_increase/add',
    namespace: "demo_salary_increase", table: 't_demo_salary_increase',trans: true
};

var listPage = {
    listFieldSett: function () {
        return [
            {name: 'name', title: '姓名', width: '15%', align: 'center'},
            {name: 'increase', title: '涨幅', width: '15%', align: 'center'},
            //此字段为必须字段
            {name: 'instance_id', title: '实例id',hidden:true},
            //此字段可不显示,建议显示
            {name:'instance_status',title:'申请状态',width:'10%',align:'center',trans:'book',key:'workFlow_process_status',showField:'transMap.instance_statusName'},
            {name: 'create_time', title: '创建时间', width: '15%', align: 'center'},
            {name:'create_user',title:'创建人',width:'10%',align:'center',trans:'auto',showField:'transMap.create_userUserName'},
            {name: 'update_time', title: '更新时间', width: '14%', align: 'center'},
            {name:'update_user',title:'修改人',width:'10%',align:'center',trans:'auto',showField:'transMap.update_userUserName'}
        ]
    },
    isColumnButton: function () {
        return false;
    },
    filters: function () {
        return [
            {name: 'name', type: 'input', title: '姓名', filterType: 'like'}
        ];
    },
    buttons: function () {
        return [];
    },
    disableButtons: function () {
        return ['update','delete'];
    },
    otherFunctions: function () {
        return {}
    }
};

var add = {
    formFields: function () {
        return [
            {name: 'name', title: '姓名', required: true, type: 'input'},
            {name: 'increase', title: '涨幅', required: true, type: 'input',dataType:'n'},
            {name: 'instance_id', title: '实例id',type:'hide'},
            //此字段为必须字段
            {name: 'instance_status', title: '实例状态',type:'hide'},
            {name: 'title', title: '标题',type:'hide'},
        ];
    },
    otherFunctions: function () {
        return {
            ready: function () {
            },
            loadSuccess: function (info) {

            },
            onSave: function () {
                if(!isEdit){
                    $('#instanceStatus').val(0);
                    $('#title').val($('#name').val() +'申请涨薪' + $('#increase').val() + '元');
                }
            },
            saveSuccess: function () {

            },
            saveError: function () {

            }
        }
    }
};