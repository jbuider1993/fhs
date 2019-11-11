var modelConfig= {title:'流程列表-xml',pkey:'id',type:'uuid',
    orderBy:'update_time Desc',
    namespace:"t_flow_jbpm_xml请删除t_",table:'t_flow_jbpm_xml',trans:true};
var listPage={
    listFieldSett:function(){
	  return [
    ]},
    isColumnButton:function(){
	  return  true;
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