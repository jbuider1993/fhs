var modelConfig= {title:'出入口',pkey:'id',type:'uuid',orderBy:'update_time Desc',
    namespace:"parking_port",table:'t_park_parking_port',trans:true,
    joinColumns:JSON.stringify({port_name:'portName'}),db:"park"};
var listPage={
    listFieldSett:function(){
	  return [
		  {name:'port_name',title:'出入口名称',width:'20%',align:'center'},
          {name:'park_id',title:'停车场名称',width:'20%',isJoin:true,namespace:'parking',showField:'transMap.parkName',align:'center'},
          {name:'real_location',title:'位置',width:'20%',align:'center'},
          {name:'port_fun_type',title:'使用功能类别',width:'10%',align:'center',key:'port_fun_type',trans:'book',showField:'transMap.port_fun_typeName'},
          {name:'is_disable',title:'是否禁用',width:'6%',formart:'formatRowColor',align:'center',trans:'book',key:'is_disable',showField:'transMap.is_disableName'},
          {name:'is_main',title:'是否为主闸机',width:'6%',align:'center',trans:'book',key:'yesOrNo',showField:'transMap.is_mainName'},
          {name:'create_user',title:'创建人',width:'8%',align:'center',trans:'sysUser',showField:'transMap.create_userUserName'},
          {name:'is_sync',title:'是否已下发',width:'7%',align:'center',trans:'book',key:'yesOrNo',showField:'transMap.is_syncName'},
  ]},
  isColumnButton:function(){
	  return  false;
  },
  filters:function(){
      return [
          {name:'park_id',type:'select',url:'${path.basePath}/ms/x/parking/findListData',
              valuefield:'id',textfield:'parkName',title:'停车场'},
          {name:'port_name',type:'input',title:'出入口名称',filterType:'like'},
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
             {name:'park_id',type:'select',url:'${path.basePath}/ms/x/parking/findListData',
                 valuefield:'id',textfield:'parkName',title:'停车场',required:true,},
             {name:'port_name',title:'名称',required:true,type:'input'},
             {name:'port_type',title:'出入口类型',type:'book',code:'port_type'},
             {name:'port_ip',title:'摄像头ip',dataType:'ipv4|ipv6',type:'input'},
             {name:'camera_port',title:'摄像头端口',dataType:'n1-10',type:'input'},
             {name:'port_fun_type',title:'功能类别',required:true,type:'book',code:'port_fun_type'},
             {name:'is_main',title:'是否为主闸机',type:'book',code:'yesOrNo'},
             {name:'is_disable',title:'是否禁用',type:'switch',dft:false},
             {name:'description',title:'描述',type:'text'},
             {name:'real_location',title:'位置描述',type:'text',required:true},
             {name:'img',title:'图片',type:'up',placeholder:'请上传出入口图片'},
             {name:'is_sync',title:'是否下发',type:'hide'},
		 ];
	},
    otherFunctions:function(){
      return {
	     ready:function(){

	    },
	    loadSuccess:function(info){

	    },
	    onSave:function(){
            $('#isSync').val(0);
	    },
		saveSucess:function(){
	    },
		saveError:function(){
		    
	    },
	  }		
   }
}