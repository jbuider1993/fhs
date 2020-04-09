##工作流模块使用帮助
1.0版本支持的功能比较简单,只是一个表单,提交后后面都是审批.
如果牵扯到根据状态来决定工作流流向,请使用result字段判断.result有六种,请查看workFlow_use_status


如果不用pagex,自己写的表单,请保留isView和isEdit的支持,如果当前任务是工作流第一个任务,
引擎在处理的时候会传isEdit=true 以及taskid, 表单重新提交的时候请记得完成当前任务,成功后前端需要
调用parent.postMessage()来关闭当前页.



