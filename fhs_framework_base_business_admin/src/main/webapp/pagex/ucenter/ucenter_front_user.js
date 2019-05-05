var modelConfig= {title:'前端用户',pkey:'user_id',type:'uuid',
    orderBy:'update_time Desc',
    namespace:"ucenter_front_user",table:'t_ucenter_front_user',trans:true};
var listPage={
    listFieldSett:function(){
	  return [
	    {name:'user_name',title:'用户名',width:'10%',align:'center'},
        {name:'nick_name',title:'昵称',width:'10%',align:'center'},
        {name:'birthday',title:'生日',width:'10%',align:'center'},
        {name:'mobile',title:'手机号',width:'15%',align:'center'},
        {name:'sex',title:'性别',width:'10%',align:'center',trans:'book',key:'sex',showField:'transMap.sexName'},
        {name:'address',title:'地址',width:'25%',align:'center'},
        {name:'is_disable',title:'是否启用',width:'9%',formart:'formatRowColor',align:'center',trans:'book',key:'is_disable',showField:'transMap.is_disableName'},
        {name:'create_time',title:'创建时间',width:'10%',align:'center'},
    ]},
  isColumnButton:function(){
	  return  true;
  },
  filters:function(){
      return [
        {name:'nick_name',title:'昵称',type:'input',filterType:'like'},
        {name:'mobile',title:'手机号',type:'input',filterType:'like'},
        {name:'sex',title:'性别',type:'book',code:'sex'},
        {name:'is_disable',title:'是否启用',type:'book',code:'is_disable'},
	 ];
  },
  buttons:function(){
      return [
                  {title:'车辆绑定信息查看',fun:'userBindPlateView',permissionsCode:'ucenter_front_user_bind_plate:see',isRow:true},
                  {title:'启用',fun:'userEnable',permissionsCode:'ucenter_front_user:update',isRow:true},
                  {title:'禁用',fun:'userDisable',permissionsCode:'ucenter_front_user:update',isRow:true}
              ];
  },
  disableButtons:function(){
	    return ["add","update"];
  },
  otherFunctions:function(){
      return {
        userBindPlateView:function(row){
            var _openFrameMsg = {url:'http://park.sxpartner.com//ms/pagex/park/ucenter_front_user_bind_plate_list.jsp?front_user_id=' + row.userId,title:row.nickName + '绑定的车牌'}
            top.postMessage(_openFrameMsg, '*');
        },
        userEnable:function(row){
            if(row.isDisable == "0"){
                EalertE("已经在启用中");
                return;
            }
            $.ajax({
    　　　　　　url:'http://base.sxpartner.com//ms/x/ucenter_front_user/update/'+row.userId,
    　　　　　　data:{"isDisable":"0"},
    　　　　　　type:'POST',
    　　　　　　success:function(str){	//成功回调函数
                    Ealert("状态修改成功");
                    reload();
    　　　　　　},
    　　　　　　error:function (err){	//失败回调函数
                    EalertE("状态修改失败");
    　　　　　　}
    　　　　});
        },
        userDisable:function(row){
            if(row.isDisable == "1"){
                EalertE("已经在禁用中");
                return;
            }
            $.ajax({
    　　　　　　url:'http://base.sxpartner.com//ms/x/ucenter_front_user/update/'+row.userId,
    　　　　　　data:{"isDisable":"1"},
    　　　　　　type:'POST',
    　　　　　　success:function(str){	//成功回调函数
                    Ealert("状态修改成功");
                    reload();
    　　　　　　},
    　　　　　　error:function (err){	//失败回调函数
                    EalertE("状态修改失败");
    　　　　　　}
    　　　　});
        }
	  }
  }
};
var add={
	formFields:function(){
	     return [
            {name:'user_name',title:'用户名',type:'input'},
            {name:'nick_name',title:'昵称',type:'input'},
            {name:'birthday',title:'出生日期',type:'date'},
            {name:'mobile',title:'手机号',type:'input',dataType:'m'},
            {name:'sex',title:'性别',type:'book',code:'sex'},
            {name:'passwd',title:'密码',type:'password'},
            {name:'user_card',title:'身份证号',type:'input'},
            {name:'user_resource',title:'用户来源',type:'book',code:'user_resource'},
            {name:'language',title:'语种',type:'input'},
            {name:'image_path',type:'up',title:'头像',placeholder:'请上传头像图片'},
            {name:'is_disable',title:'是否启用',type:'book',code:'is_disable'},
            {name:'province_id',title:'省',type:'input'},
            {name:'city_id',title:'市',type:'input'},
            {name:'area_id',title:'区',type:'input'},
            {name:'address',title:'地址',type:'input'},
		 ];
	},
    otherFunctions:function(){
      return {
	     ready:function(){
	    },
	    loadSuccess:function(info){

	    },
	    onSave:function(){

	    },
		saveSucess:function(){
	    },
		saveError:function(){

	    },
	  }
   }
}