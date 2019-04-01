var modelConfig= {title:'角色',pkey:'id',type:'uuid',db:'ucenter', namespace:"role",table:'t_demo_role',
    joinColumns:JSON.stringify({name:'roleName',dept:'roleDeptName'})};

var listPage={
    listFieldSett:function(){
        // 默认的formater - > 图片放大，tips
        return [
            {name:'name',title:'姓名',required:true,type:'input',class:'imInputClass'},
             ]},
    isColumnButton:function(){
        return  false;
    },
    filters:function(){
        return [
           ];
    },
    buttons:function(){
        return [];
    },
    disableButtons:function(){
        return [];
    },
    otherFunctions:function(){
        return {
        }
    }
};


var add={
    formFields:function(){
        return [

        ];
    },
    otherFunctions:function(){
        return {

        }
    }
}