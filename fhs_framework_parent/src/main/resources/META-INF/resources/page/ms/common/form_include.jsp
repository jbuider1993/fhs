<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<script type="text/javascript">
var isEdit = false;
var isView =  false;

if (eval("${!empty param.isEdit}")) {
	isEdit = true;
}

if (eval("${!empty param.isView}")) {
	isView = true;
}
//加载成功的时候执行的方法
var loadSuccessFuns=[];
//保存的时候执行的方法
var onSaveFuns=[];
//查看的时候
var renderViewFuns=[];
</script>
