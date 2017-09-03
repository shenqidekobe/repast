<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/inc/admin/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<c:import url="/inc/admin/link.jsp"></c:import>
<title>后台系统</title>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
        <c:import url="/inc/admin/header.jsp"></c:import>
		<div class="layui-side layui-bg-black" id="admin-side">
			<div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side"></div>
		</div>
		<div class="layui-body" style="bottom: 0; border-left: solid 2px #1AA094;" id="admin-body">
			<div class="layui-tab admin-nav-card layui-tab-brief" lay-filter="admin-tab">
				<ul class="layui-tab-title">
					<li class="layui-this"><i class="fa fa-dashboard" aria-hidden="true"></i> <cite>控制面板</cite></li>
				</ul>
				<div class="layui-tab-content" style="min-height: 150px; padding: 5px 0 0 0;">
					<div id="myBox" class="layui-tab-item layui-show" style="-webkit-overflow-scrolling:touch; overflow: scroll; height: 100%;width: 100%;">
						<iframe src="${_PATH}/admin/main" scrolling="yes" id="myFrame"></iframe>
					</div>
				</div>
			</div>
		</div>
		<c:import url="/inc/admin/footer.jsp"></c:import>
		
		<div class="site-tree-mobile layui-hide">
			<i class="layui-icon">&#xe602;</i>
		</div>
		<div class="site-mobile-shade"></div>
		
		<!--锁屏模板 start-->
		<script type="text/template" id="lock-temp">
				<div class="admin-header-lock" id="lock-box">
					<div class="admin-header-lock-img">
						<img src="${_PATH}/res/admin/images/0.jpg"/>
					</div>
					<div class="admin-header-lock-name" id="lockUserName"></div>
					<input type="text" class="admin-header-lock-input" value="输入密码解锁.." name="lockPwd" id="lockPwd" />
					<button class="layui-btn layui-btn-small" id="unlock">解锁</button>
				</div>
		</script>
		<!--锁屏模板 end -->
	</div>
	<script type="text/javascript" src="${_PATH}/res/admin/datas/nav.js"></script>
    <script src="${_PATH}/res/admin/js/index.js"></script>
    <script>
	    layui.use('layer', function() {
			var $ = layui.jquery,
				layer = layui.layer;
	    });			
	</script>
	
</body>
</html>