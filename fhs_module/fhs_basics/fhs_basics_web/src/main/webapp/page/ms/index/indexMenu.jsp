<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>太子湾后台管理系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<link rel="shortcut icon" href="${basePath}static/images/favicon.ico">
	<link rel="stylesheet" type="text/css"
		  href="${basePath}static/artIndex/assets/css/easyui.css">
	<!-- basic styles -->
	<link href="${basePath}static/artIndex/assets/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="${basePath}static/artIndex/assets/css/font-awesome.min.css" />
	<link rel="stylesheet" type="text/css" href="${staticPath}css/jquery.toast.css" />

	<!--[if IE 7]>
	<!--<link rel="stylesheet" href="${basePathForKaiYue}/artIndex/assets/css/font-awesome-ie7.min.css" />-->
	<![endif]-->

	<!-- page specific plugin styles -->
	<!-- fonts -->


	<!-- ace styles -->

	<link rel="stylesheet" href="${basePath}static/artIndex/assets/css/ace.min.css" />
	<link rel="stylesheet"
		  href="${basePath}static/artIndex/assets/css/ace-rtl.min.css" />
	<link rel="stylesheet"
		  href="${basePath}static/artIndex/assets/css/ace-skins.min.css" />

	<link rel="stylesheet" href="${staticPath}css/validform.css"
		  type="text/css" media="all" />
	<link rel="stylesheet" type="text/css" href="${staticPath}css/common.css">

	<!--[if lte IE 8]>
	<link rel="stylesheet" href="${basePathForKaiYue}/artIndex/assets/css/ace-ie.min.css" />
	<![endif]-->

	<!-- inline styles related to this page -->

	<!-- ace settings handler -->

	<script src="${basePath}static/artIndex/assets/js/ace-extra.min.js"></script>

</head>
<style>
	body {
		font-family: ""open sans", "Helvetica Neue", Helvetica, Arial, sans-serif";
	}
	.navbar-container{
		padding:0;
		margin:0;
	}
	.navbar{
		min-height:70px;
	}
	.icon_logo {
		width: 260px;
		height: 70px;
		display: block;
		float: left;
	}
	.navbar .navbar-brand {
		color: white;
		font-size: 24px;
		text-shadow: none;
		padding:0px;
	}
	.navbar-brand {
		float: left;
		font-size: 18px;
		line-height: 20px;
	}
	.navbar-container {
		padding: 0px;
	}
	.select_act{
		color:#a6b0c1 !important;
		padding: 22px 12px 24px 12px;
		background-color:#eee;
	}
	.select{
		font-size:16px;
		color:#666666;
		padding: 22px 12px 24px 12px;
	}
	.select:hover{
		color:#a6b0c1;
		padding: 22px 12px 24px 12px;
		background-color:#eee;
		cursor:pointer;
	}
	.icon_logo_p lable{
		float:left;
		color:#666666;
	}
	.logo_f{
		color:#999999;
		cursor:pointer;
	}
	.navbar_icon_logo{
		position:absolute;
		top:0;
		right:0;
		height:70px;
		background:#eeeeee;
	}
	.icon_logo_p{
		margin-top:12px;
		font-weight:normal;
	}
	.icon-users{
		width:35px;
		height:35px;
		margin:12px 10px 0 0 !important;
		border-radius:50%;
		background:#ffffff;
		overflow:hidden;
	}
	.icon-user{
		padding-top:4px;
		font-size:40px;
	}
	.logo-btn{
		/* display:none; */
		/* padding-top:10px; */
		/* background:#f1f1f1; */
	}
	.logo-btn p{
		height:30px;
		line-height:30px;
		padding-left:20px;
		font-size:14px;
		background:#ffffff;
		cursor:pointer;
	}
	.logo-btn p:hover{
		background:#eeeeee;
	}
	#navbar{
		background-color:#ffffff;
	}
	.nav-list>li>a>i{
		padding: 0 5px;
	}
	/* .nav>li>a{
	 	padding:3px 15px 3px 20px;
	} */
	.skin-1 .nav-list>li>a{
		font-size: 14px;
		height: 42px;
	}
	.nav-list>li .submenu>li>a {
		padding: 12px 0 12px 35px;
	}
	.submenu>li>a>i{
		/*display:block;*/
	}
	.nav-list>li>.submenu li>.submenu>li>a {
		margin-left: 0px;
		padding-left: 55px;
	}
	.skin-1 .nav-list>li .submenu li.open>a, .skin-1 .nav-list>li .submenu li>.submenu>li.open>a{
		color:#ffffff;
	}
	.l-btn-icon-left .l-btn-text {
		margin: 0 4px 0 15px !important;
	}
	.nav-list>li.open {
		border-bottom-color: none;
		border-left: 3px solid #1AB394;
	}
	.sidebar-collapse:before{
		border: none;
	}
	@media screen and (max-width: 1366px) {
		.sidebar,.sidebar:before,.icon_logo,#header_left{
			width:210px !important;
		}
	}


	/**(**********************/
	.panel{
		margin-bottom:0 !important;
	}
	.panel-body{
		padding:10px;
	}

	body{
		overflow: hidden;
	}
	#header_left{
		overflow: hidden;
	}
	@media only screen and (max-width: 991px){
		#header_left{
			width: 40px !important;
		}
	}

</style>
<body class="skin-1">
<div class="navbar navbar-default" id="navbar">
	<script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
	</script>
	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left" id="header_left">
			<small class="navbar-brand" style="background-color: #2f4050;">
				<img class="icon_logo" style="width:260px;height:70px;display:block;float:left;margin:0;" src="${staticPath}/js/easyui/themes/icons/logo.png">
			</small>
			<!-- <small  class="navbar-brand"> <img
						class="icon_logo"
						src="${basePath}/images/main_logo.png">

						<p class="icon_logo_p">
							<lable id="sys_name" style="font-size:24px;font-weight:bold;">智慧园区后台管理系统</lable>
							</br> <span class="icon_logo_f" >Smart Park backstage management system</span>
						</p>
				</small> -->
			<!-- /.brand -->
		</div>
		<div class="sidebar-collapse" id="sidebar-collapse" onclick="setTimeout(mainFrameResize,0)" style="top:0px !important;float:left;height:70px;width:60px;border:none;background-color:#eeeeee;padding:0;">
			<i class="icon-huodong" style="background-color:#eeeeee;border-radius:0px;border:none;padding-top:20px;">
				<img src="${staticPath}/js/easyui/themes/icons/zhaozu.png" style="width:16px; height:16px;">
			</i>
		</div>
		<!-- nav -->

		<div class="navbar-header clearfix" style="padding-right:120px;">
			<p class="icon_logo_p" style="margin:0;" id="topMenu">

			</p>
		</div>
		<!-- end -->

		<div class="navbar_icon_logo">
			<%-- <img class="icon-user" src="${basePath}/images/main_logo.png"> --%>
			<div class="clearfix" style="padding:5px 30px 13px 30px">
				 	<span class="pull-left icon-users">
						<span class="icon-user"></span>
					</span>
				<p class="icon_logo_p" style="line-height:15px;">
					<lable id="sys_name" style="font-size:14px;font-weight:bold;">${sessionUser.userName}</lable>
					</br> <span class="icon_logo_f logo_f" >编辑</span>
				</p>
			</div>

			<div class="logo-btn" style="display:none;">
				<p  onclick="updatePass()">修改密码</p>
				<p  onclick="updateUserInfo()">修改个人信息</p>
				<p onclick="if (confirm('您确定要退出吗?')) window.location.href='${applicationScope['shiro.cas.casServerUrlPrefix']}/logout';">退出</p>
			</div>

		</div>
	</div>
</div>

<div class="main-container" id="main-container">
	<script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
	</script>

	<div class="main-container-inner">
		<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
		</a>

		<div class="sidebar" id="sidebar">
			<script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'fixed')
                } catch (e) {
                }
			</script>

			<div class="sidebar-shortcuts" id="sidebar-shortcuts"></div>
			<!-- #sidebar-shortcuts -->

			<ul class="nav nav-list">

			</ul>
			<!-- /.nav-list -->

			<!-- <div class="sidebar-collapse" id="sidebar-collapse"  onclick="setTimeout(mainFrameResize,100)">
                <i class="icon-double-angle-left"
                    data-icon1="icon-double-angle-left"
                    data-icon2="icon-double-angle-right"></i>
            </div> -->

		</div>

		<div class="main-content">
			<div class="page-content"
				 style="background-color: #fff; min-height: 100%;">
				<div id="tabs" class="easyui-tabs"
					 data-options="border:false">
					<div title="主页" data-options="iconCls:'icon-house'"
						 style="padding: 0px; overflow: hidden;">
						<iframe id="mainIFrame"
								src=""
								width="100%" height="100%" frameborder="0" border="0" marginwidth="0"
								marginheight="0"  style="overflow-x:hidden;" allowtransparency="true"></iframe>
					</div>
				</div>
			</div>

		</div>
		<!-- /.main-content -->


		<!-- /#ace-settings-container -->
	</div>
	<!-- /.main-container-inner -->

</div>
<!-- /.main-container -->


<!-- 无下级菜单的li -->
<div style="display: none;" id="nHasItemMenu">
	<li>
		<a href="javascript:openFrame('{url}','{title}')">
			<i>{img}</i>
			<span class="menu-text">{name}</span>
		</a>
	</li>
</div>

<!-- 无下级菜单的li -->
<div style="display: none;" id="nHasItemMenuOpenNewWindow">
	<li>
		<a target="_blank" href="{url}">
			<i></i>
			<span class="menu-text">{name}</span>
		</a>
	</li>
</div>
<!-- 有下级菜单的li -->
<div style="display: none;" id="hasItemMenu">
	<li class="select_act">
		<a href="#" class="dropdown-toggle">
			<i class="">
				{img}
			</i>
			<span class="menu-text">{name}</span>
			<b class="arrow icon-angle-left"></b>
		</a>
		<ul class="submenu">{sunMenuHtml}
		</ul>
	</li>
</div>



<script src="${basePath}static/artIndex/assets/js/jquery-2.0.3.min.js"></script>

<script src="${staticPath}js/ecommon.js"></script>


<script type="text/javascript">
    if ("ontouchend" in document)
        document
            .write("<script src='${basePath}artIndex/assets/js/jquery.mobile.custom.min.js'>"
                + "<"+"script>");
</script>
<script src="${basePath}static/artIndex/assets/js/bootstrap.min.js"></script>
<script src="${basePath}static/artIndex/assets/js/typeahead-bs2.min.js"></script>
<script type="text/javascript" src="${staticPath}js/jquery.toast.js"></script>
<script type="text/javascript" src="${staticPath}js/ecommon.js"></script>
<!-- page specific plugin scripts -->

<script
		src="${basePath}static/artIndex/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script
		src="${basePath}static/artIndex/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${basePath}static/artIndex/assets/js/jquery.slimscroll.min.js"></script>
<script
		src="${basePath}static/artIndex/assets/js/jquery.easy-pie-chart.min.js"></script>
<script src="${basePath}static/artIndex/assets/js/jquery.sparkline.min.js"></script>
<script src="${basePath}static/artIndex/assets/js/flot/jquery.flot.min.js"></script>
<script src="${basePath}static/artIndex/assets/js/flot/jquery.flot.pie.min.js"></script>
<script
		src="${basePath}static/artIndex/assets/js/flot/jquery.flot.resize.min.js"></script>
<script type="text/javascript"
		src="${staticPath}js/easyui/jquery.easyui.min.js"></script>

<!-- ace scripts -->

<script src="${basePath}static/artIndex/assets/js/ace-elements.min.js"></script>
<script src="${basePath}static/artIndex/assets/js/ace.min.js"></script>

<script type="text/javascript" src="${basePath}js/systemConfig.js"></script>



<!-- inline scripts related to this page -->
<script type="text/javascript">
    //$(".url").attr("srcs",fileService+"/downLoad/downImgMin?fileId={image}&imgFileWidth=16&imgFileHeight=16");
    //获取单个菜单对象解析出来的html
    function getAMenuHtml(menuItemJson, fatherName) {
        var tempHtml = '';
        // 如果本菜单是最后一级菜单
        if (menuItemJson.sonMenu.length == 0) {
            tempHtml = $('#nHasItemMenu').html();
            if(menuItemJson.url.indexOf("newWindow")!=-1)
            {
                tempHtml = $('#nHasItemMenuOpenNewWindow').html();
            }
            tempHtml = tempHtml.replace('{url}',
                menuItemJson.serverUrl + "ms/redirect?call=" + menuItemJson.url)
                .replace('{name}', menuItemJson.name)
                .replace('{img}',replaceImg(menuItemJson.image))
                .replace('{title}',
                    fatherName + '>' + menuItemJson.name);

        } else {
            tempHtml = $('#hasItemMenu').html();
            fatherName += ('>' + menuItemJson.name);
            tempHtml = tempHtml.replace('{name}', menuItemJson.name).
            replace('{sunMenuHtml}',getManyMenuHtml(menuItemJson.sonMenu,fatherName)).
            replace('{img}',replaceImg(menuItemJson.image)).
            replace('src=""','').
            replace('srcs','src');
        }
        return tempHtml;
    }

    // 替换链接前面的图标
    function replaceImg(image){
        if(image != null && image != ''){
            return '<img src="'+fileService+'/downLoad/downImgMin?fileId='+image+'&imgFileWidth=16&imgFileHeight=16" style="margin-top:-3px;">';
        }else{
            return "";
        }
    }

    // 获取多个菜单对象解析出来的html
    function getManyMenuHtml(menuItemJson, fatherName) {
        var resultHtml = '';
        for (var i = 0; i < menuItemJson.length; i++) {
            resultHtml += getAMenuHtml(menuItemJson[i], fatherName);
        }
        return resultHtml;
    }

    // 初始化菜单
    function initMenu() {
        var html = getManyMenuHtml(menuJsonArray, '');
        $('.nav-list').html(html);
        try {
            ace.settings.check('sidebar', 'collapsed')
        } catch (e) {
        }
    }

    var topMenuMap = {};

    //初始化最顶部的菜单
    function initTopMenu(topMenuData){
        var _html = '';
        topMenuData.forEach(function (item) {
            if(item.menuServer>0)
            {
                /* _html+='<lable  class="select topMenu" menuId="'+ item.id + '">' + item.name + '</lable>'; */
                _html += '<span class="select topMenu" menuId="' + item.id + '" style="float:left;"><font style="transform: scale(0.9);display: inline-block;">' + item.name + '</font></span>';
            }
            else
            {
                /* _html+='<a href="' + item.url +'" target="_blank"><lable  class="select  notChangeLeft" menuId="'+ item.id + '">'
                + item.name + '</lable></a>'; */
                _html += '<a href="' + item.url + '" target="_blank"><span class="select notChangeLeft" menuId="' + item.id + '" style="float:left;"><font style="transform: scale(0.9);display: inline-block;">' + item.name + '</font></span></a>';
            }
            topMenuMap[item.id]=item.sonMenu;
        });
        $('#topMenu').html(_html);
        $('.select').click(changeLeft);
        $($('.select')[0]).trigger('click');

    }
    /* function initTopMenu(topMenuData){
        var _html = "";
        topMenuData.forEeach(
            function(item){
                if(item.menuServer > 0){
                    _html = '<span class="select topMenu" menuId="' + item.id + '">' + item.name + '</span>';
                }else{
                    _html = '<a href="' + item.url + '" target="_blank"></a>';
                }
            }
        );
    }
*/
    //更换一级菜单刷新左侧菜单
    function changeLeft()
    {
        $('.select_act').removeClass('select_act');
        $(this).addClass('select_act');
        if(!$(this).hasClass('notChangeLeft'))
        {
            menuJsonArray = topMenuMap[$(this).attr('menuId')];
            initMenu();
        }
        openResize();

    }

    var indexUrl = '${param.indexUrl}';

    $(document)
        .ready(
            function() {
                $
                    .get(
                        '${basePath}ms/sysUser/seachMenuByUser?systemId=${param.systemId}',
                        {},
                        function(data) {
                            //menuJsonArray = data;
                            initTopMenu(data);
                            $('body')
                                .height(
                                    document.documentElement.clientHeight);



                            $('.breadcrumb_p').text(
                                getCNDateName() + " "
                                + getCNWeek());
                            window.onresize = mainFrameResize;
                        }, 'json');
                $('.logo_f').click(function(){
                    if($('.logo-btn').css('display')=='none'){
                        $('.logo-btn').css('display','block');
                    }else{
                        $('.logo-btn').css('display','none');
                    }
                });
                $('.navbar_icon_logo').mouseleave(function(){
                    if($('.logo-btn').css('display')=='block'){
                        $('.logo-btn').css('display','none');
                    }
                });
                $('.nav-list>li').on('click',function(){
                    /* console.log($(this).attr('class'));
                    if($(this).hasClass('open')){
                        $(this).children('a').children('b').removeClass('icon-angle-left');
                        $(this).children('a').children('b').addClass('icon-angle-left');
                    } */
                });

                if(indexUrl != null && indexUrl != "" && indexUrl != "undefined"){
                    $('#mainIFrame').prop('src', indexUrl);
                }
                setCookie("ace.settings","","-1");
                initDefaultMainFrame();
                addOpenFrameMsgListener();
            });


    //初始化默认的子系统首页
    function initDefaultMainFrame(){
        $.ajax({
            url:'${basePath}/ms/sysSystem/systemInfo/${param.systemId}',
            dataType:'json',
            success:function (systemInfo) {
                if(systemInfo.indexUrl)
                {
                    openFrame(systemInfo.indexUrl, '首页')
                }
            }
        });
    }

    //监听子系统调用打开新的窗口事件
    function addOpenFrameMsgListener(){
        window.addEventListener('message',function(event){
            openFrame(event.data.url, event.data.title)
        },false);
    }

    function openResize() {
        var _main_content_height =   $(window)
            .height() - $('#navbar').height();
        $('#tabs').tabs('resize',{
            width : "auto",
            height : _main_content_height+ 'px'
        });
        $('#sidebar').height(_main_content_height);

        $('.main-content')
            .height(_main_content_height);

        $('.contentFrame')
            .height(
                $(window)
                    .height() - 142);
        if($('#sidebar').is(':hidden'))
        {
            $('.contentFrame')
                .width(
                    $(window)
                        .width() -  20);
        }
        else
        {
            $('.contentFrame')
                .width(
                    $(window)
                        .width() - $('#sidebar').width()-20);
        }

    }

    //处理浏览器改变事件
    function mainFrameResize(){
        logoShow();
        openResize();
    }
    //在mainIframe打开一个URL
    function _openFrame(url, title) {

        $('#mainIFrame').attr('src', '' + url);
        var titles = title.split('>');
        var html = '_$ta_$tag___________________________$ta_$tag____________________________________首页_$ta_$tag';
        for (i in titles) {
            if (i == 0) {
                continue;
            }
            html += '_$tag______________' + titles[i] + '_$tag';
        }
        $('.breadcrumb').html(html);
    }

    // mainIframe打开一个Tab
    function openFrame(url, title) {
        var titles = title.split('>');
        var subtitle = titles[titles.length - 1];
        addTab(subtitle, url, '');
    }

    //创建/移除tab
    function addTab(subtitle, url, icon) {
        if (!$('#tabs').tabs('exists', subtitle)) {
            $('#tabs').tabs('add', {
                title : subtitle,
                content : createFrame(url),
                closable : true,
                icon : ''
            });
            openResize();
            //mainFrameResize();
        } else {
            $('#tabs').tabs('select', subtitle);
        }
    }

    //创建Frame
    function createFrame(url) {
        //console.log("---" + url);
        var s = '<iframe  class="contentFrame"  height="100%" frameborder="0"  src="'
            + url
            + '"  border="0" marginwidth="0" marginheight="0" allowtransparency="yes"></iframe>';
        return s;
    }

    //显示系统中文日期
    function getCNDateName() {
        var now = new Date();
        var year = now.getFullYear();
        var month = now.getMonth() + 1;
        var day = now.getDate();

        return year + '年' + month + '月' + day + '日';
    }

    function setCookie(key,value,t)
    {
        var oDate=new Date();
        oDate.setDate(oDate.getDate()+t);
        document.cookie=key+"="+value+"; expires="+oDate.toDateString();
    }


    //显示系统中文星期
    function getCNWeek() {
        now = new Date()
        day = now.getDay();
        cnWeekName = '星期';

        switch (day) {
            case 0: {
                cnWeekName += '日'
            }
                ;
                break;
            case 1: {
                cnWeekName += '一'
            }
                ;
                break;
            case 2: {
                cnWeekName += '二'
            }
                ;
                break;
            case 3: {
                cnWeekName += '三'
            }
                ;
                break;
            case 4: {
                cnWeekName += '四'
            }
                ;
                break;
            case 5: {
                cnWeekName += '五'
            }
                ;
                break;
            case 6: {
                cnWeekName += '六'
            }
                ;
                break;

            default: {/* 异常处理*/
            }
        }

        return cnWeekName;

    }

    // 跳转到登陆页面
    function tologin() {
        alert('登陆超时，请重新登陆!');
        window.location.href = '${basePath}login.jsp';
    }
</script>

<script type="text/javascript" src="${staticPath}js/Validform_v5.3.2.js"></script>
<script type="text/javascript">

    //关闭dialog方法
    function closeDialog() {
        $('#UPpass').dialog('close');
    }
    //打开dialog
    function openDialog(url, title) {
        $('#UPpass').load(url, function() {
            $('#UPpass').dialog('open').dialog('setTitle', title);
            $.parser.parse($('#UPpass'));
        });

    }

    //修改密码
    function updatePass() {
        openDialog(
            '${basePath}page/ms/root/admin_pass_update.jsp',
            '修改密码');
    }

    //修改个人信息
    function updateUserInfo() {
        openDialog(
            '${basePath}page/ms/root/admin_info_update.html',
            '修改个人信息');
    }


    //logo隐藏、展示
    function logoShow(){
        var logo = $("#header_left").attr("style");
        var logo_width = $("#sidebar").width();
        $("#header_left").attr("style","width:"+logo_width+"px;overflow:hidden;");
        if(logo){
            $("#header_left").removeAttr("style");
        }else{
            $("#header_left").attr("style","width:"+logo_width+"px;overflow:hidden;")
        }
    }
</script>
<div id="UPpass" class="easyui-dialog" title="修改用户密码"
	 data-options="iconCls:'icon-save'" closed="true"
	 style="width: 500px; height: 400px; padding: 10px"></div>
</body>

</html>

