var Server_Host = "http://localhost:8090";
var Config = {
    Url : {
        Save_Wf : Server_Host+"/bpm/api/process/deploy",
        Get_Candidate_Users : "http://base.sxpartner.com/ms/sysUser/findUsers?jsonpCallback=?",
        Get_Groups_4_Combox : "http://base.sxpartner.com/ms/sysOrganization/getOrgIdComBoxData?jsonpCallback=?",
        Get_Roles_4_Combox : "http://base.sxpartner.com/ms/sysRole/realRoleComBoxData?jsonpCallback=?",
        Get_Candidate_Groups : "http://localhost:8080/mock/getCandidateGroups.action",
        Get_Task_Listener_Classes : "http://localhost:8080/mock/task/getListenerClasses.action",
        Get_Execution_Listener_Classes:"http://localhost:8080/mock/execution/getListenerClasses.action",
        Get_Process_Listener_Classes:"http://localhost:8080/mock/process/getListenerClasses.action",
        Get_Flow_Listener_Classes:"http://localhost:8080/mock/process/getListenerClasses.action"
    }
};