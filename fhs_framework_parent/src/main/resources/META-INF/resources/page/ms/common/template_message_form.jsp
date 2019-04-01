<%@ page language="java" contentType="text/html; charset=UTF-8"
         isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
<!-- 模板消息设置 -->
<div class="formInputGroup">
    模板消息设置
</div>
<div style="width:80%;margin:0 auto;padding:20px 0;" class="">
    <div style="font-size: 14px;">
        <div style="line-height: 32px;" class="disin">接收者：</div>
        <div style="" class="disin">
            <input id="receiverName" style="background:none;width:300px;line-height:30px;padding:0 10px;" class="bor-a-eee" autocomplete="off" type="text" placeholder="输入微信用户关键字模糊搜索" name="">
        </div>
        <div onclick="getPer()" class="disin poin searchPer">
            查询
        </div>
    </div>
    <div style="padding: 20px 56px;overflow: hidden; position: relative;">
        <div onclick="confirmPer()" class="poin confirm-div">
            <img src="${basePath}static/images/confirm.png" style="height:40px;width:40px;">
        </div>
        <div class="bor-a-bbb person-list" style="float: left;">
            <div style="padding:20px;" id="datalist">

            </div>
        </div>
        <div class="bor-a-bbb person-list" style="float: right;">
            <div style="padding:20px;" id="selectedlist">

            </div>
        </div>
        <div style="position:absolute;top:0px;right:66px;left: 0;line-height: 20px;text-align: right;font-size: 14px;z-index: 999;">
            最多10人，已选<span id="sums">0</span>人
        </div>
    </div>
</div>
<script type="text/javascript">

    $(function () {
        if (isView) {
            setTimeout(function(){
                $('#deletePng img').remove();
            }, 2000);
        }
    })
</script>
</body>
</html>
