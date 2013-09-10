<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>LYSOFT CMS</title>

<link href="<%=path%>/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=path%>/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=path%>/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%=path%>/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="<%=path%>/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<script src="<%=path%>/dwz/js/speedup.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/uploadify/scripts/swfobject.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/uploadify/scripts/jquery.uploadify.v2.1.0.js" type="text/javascript"></script>


<script src="<%=path%>/dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.ui.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="<%=path%>/dwz/js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="dwzbin/dwz.min.js" type="text/javascript"></script>
-->
<script src="<%=path%>/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("<%=path%>/dwz/dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<!-- <a class="logo" href="http://j-ui.com">标志</a> -->
				<ul class="nav">
					<li><a href="http://www.baidu.com/" target="_blank">博客</a></li>
					<li><a href="http://weibo.com/" target="_blank">微博</a></li>
					<li><a href="login.html">退出</a></li>
				</ul>
				
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>权限管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="<%=path%>/note/list" target="navTab" rel="note">测试1</a></li>
							<li><a href="<%=path%>/note/review" target="navTab" rel="note">测试2</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">		
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<p><span>FoolishMan Note</span></p>
							<p>FoolishMan Note,remember my note.</p>
							
						</div>

						
						<div style="width:100%;position: absolute;top:60px;right:0" layoutH="80">
							<p>Please click left menu.</p>
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2012 <a href="www.lysoft.com" target="dialog">六亿团队</a> Tel：028-12365478</div>


</body>
</html>