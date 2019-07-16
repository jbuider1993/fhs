var modelConfig= {title:'学生',pkey:'id',type:'uuid',
    orderBy:'update_time Desc',
    namespace:"student_jack",table:'t_demo_student_jack',trans:true};

var listPage={
    listFieldSett:function(){
	  // 默认的formater - > 图片放大，tips
	  return [
		  {name:'name',title:'姓名',width:'25%',align:'center'},
          {name:'age',title:'年龄',width:'25%'},
	      {name:'sex',title:'性别(字典)',width:'25%',align:'center',trans:'book',key:'sex',showField:'transMap.sexName'}
  ]},
    isColumnButton:function(){
	  return  true;
  },
  filters:function(){
      return [
		  {name:'sex',type:'select',url:'${path.systemServiceUrl}/webApi/wordbook/getData?wordbookGroupCode=sex&jsonpCallback=?',valuefield:'wordbookCode',textfield:'wordbookDesc',title:'性别'},
		 //  {field:'sex',type:'book',key:'sex',title:'性别'},
		  {name:'name',type:'input',title:'姓名',filterType:'like'},
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
			 {name:'name',title:'姓名',required:true,type:'input',class:'imInputClass'},
             {name:'sex',title:'字典(性别)',required:true,type:'book',code:'sex'},
             {name:'age',title:'年龄',required:true,dataType:'n1-3',type:'input'},
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