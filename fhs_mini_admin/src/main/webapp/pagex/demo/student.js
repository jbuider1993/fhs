var modelConfig= {title:'学生',pkey:'student_id',type:'uuid',
    orderBy:'student_id Desc',db:'ucenter',
    namespace:"student",table:'t_demo_student',trans:true};

var listPage={
    listFieldSett:function(){
	  // 默认的formater - > 图片放大，tips
	  return [
		  {name:'name',title:'姓名',width:'25%',align:'center'},
          {name:'role_id',title:'关联其他表字段1',width:'25%',isJoin:true,namespace:'role',showField:'transMap.roleName'},
          {name:'role_id',title:'关联其他表字段2',showField:'transMap.roleDeptName'},
	      {name:'sex',title:'性别(字典)',width:'25%',align:'center',trans:'book',key:'sex',showField:'transMap.sexName'}
  ]},
    isColumnButton:function(){
	  return  true;
  },
  filters:function(){
      return [
		  {name:'sex',type:'select',url:'${path.systemServiceUrl}/webApi/wordbook/getData?wordbookGroupCode=sex&jsonpCallback=?',valuefield:'wordbookCode',textfield:'wordbookDesc',title:'分类'},
		 //  {field:'sex',type:'book',key:'sex',title:'性别'},
		  {name:'name',type:'input',title:'姓名',filterType:'like'},
		  //  {field:'startDate',type:'date',title:'开始日期',dateSett:'{formart:"yyyy-MM-dd"}',filterType:'>='},
	  ];      
  }, 
  buttons:function(){
      return [{title:'审核',fun:'shenhe',permissionsCode:'student:add',isRow:true}];
  },
  disableButtons:function(){
	    return [];
  },
  otherFunctions:function(){
      return {
        testFormart:function(index,val){
	        return '<font size="10">' + row.val + '</font>'
	    },
        shenhe:function(row){
	    },
        extendsParam:function(){
           return {sex:1};
        }
	  }		
  }
};


var add={ 
	formFields:function(){
	     return [
			 {name:'name',title:'姓名',required:true,type:'input',class:'imInputClass'},
             {name:'sex',title:'字典(性别)',required:true,type:'book',code:'sex'},
             {name:'title',title:'标题(单行)',required:true,type:'bigInput'},
             {name:'select_id',title:'下拉',required:true,type:'select',url:'${path.systemServiceUrl}/webApi/wordbook/getData?wordbookGroupCode=sex&jsonpCallback=?',valuefield:'wordbookCode',textfield:'wordbookDesc'},
             {name:'role_id',title:'表关联',required:true,type:'select',url:'${path.basePath}/ms/x/role/findListData',valuefield:'id',textfield:'name'},
             {name:'tree_id',title:'下拉树',required:true,type:'selectTree',url:'${path.basePath}//webApi/pagex/demo/tree'},
             {name:'grid_id',title:'下拉列表',required:true,type:'selectGrid',url:'${path.basePath}/webApi/pagex/demo/grid',columns:JSON.stringify([[
                     {field:'itemid',title:'Item ID',width:80},
                     {field:'productname',title:'Product',width:120},
                 ]]),idField: 'itemid',
                 textField: 'productname'},
               {type:'divStart',id:'imDIV'},
			   {name:'is_disable',title:'是否禁用',type:'switch',dft:true},
               {name:'content',title:'内容',type:'um',required:true,placeholder:'请输入富文本'},
               {type:'divEnd',id:'imEnd'},
               {name:'logo',title:'图片',type:'up',required:true,placeholder:'请上传50*50的logo'},
               {name:'files',title:'多视频上传',type:'ups',required:true,placeholder:'请上传视频',max:2,fileType:'video/*'},
               {name:'start_time',title:'日期',type:'date',required:true,placeholder:'请选择日期'},
               {name:'text_a',title:'多行文本框',type:'text',required:true,placeholder:'多行文本'},
               {name:'longitude,latitude,address,provinceid,cityid,areaid',type:'address'},
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