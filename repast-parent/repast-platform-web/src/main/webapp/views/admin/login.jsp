<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/inc/admin/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="shortcut icon" href="${_PATH}/res/admin/images/favicon.ico">
<link rel="stylesheet" href="${_PATH}/res/admin/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${_PATH}/res/admin/css/login.css" />
</head>
<body class="beg-login-bg">
	<div class="beg-login-box">
		<header>
			<h1>后台登录</h1>
		</header>
		<div class="beg-login-main">
			<form action="${_PATH}/admin/startLogin" class="layui-form" method="post">
				<div class="layui-form-item">
					<label class="beg-login-icon"> <i class="layui-icon">&#xe612;</i>
					</label> <input type="text" name="loginName" id="loginName" lay-verify="required"
						autocomplete="off" placeholder="这里输入登录名" class="layui-input">
				</div>
				<div class="layui-form-item">
					<label class="beg-login-icon"> <i class="layui-icon">&#xe642;</i>
					</label> <input type="password" name="password" id="password" lay-verify="required"
						autocomplete="off" placeholder="这里输入密码" class="layui-input">
				</div>
				<div class="layui-form-item">
					<div class="beg-pull-left beg-login-remember">
						<label style="color:#C0C0C0">记住帐号？</label>
						<input type="checkbox" name="rememberMe" id="rememberMe" value="1" lay-skin="switch" lay-filter="rememberMe" checked title="记住帐号">
					</div>
					<div class="beg-pull-right">
						<button type="button" class="layui-btn layui-btn-primary" lay-submit
							lay-filter="login">
							<i class="layui-icon">&#xe650;</i> 登录
						</button>
					</div>
					<div class="beg-clear"></div>
				</div>
			</form>
		</div>
		<footer></footer>
	</div>
	<script type="text/javascript" src="${_PATH}/res/admin/plugins/layui/layui.js"></script>
	<script>
		layui.use([ 'layer', 'form' ], function() {
			var layer = layui.layer, $ = layui.jquery, form = layui.form();
			$("#loginName").val(layui.data('loginObject').loginName);
			$("#password").val(layui.data('loginObject').password);
			$('body').keydown(function(event) {
				if (event.keyCode == 13){
					$(".layui-btn-primary").click();
				}
			});

			form.on('submit(login)', function(data) {
				var loading=layer.load(2);
				$.ajax({
					url:'${_PATH}/admin/startLogin',
					type:'POST',
					data:data.field,
					success:function(rsp){
						layer.close(loading);
						if("success"==rsp){
							//添加登录标识
							layui.data('menuFlag', {
								 key: 'refreshCount',
								 value: "0"
							});
							if($("#rememberMe").is(':checked')){
								 layui.data('loginObject', {
									 key: 'loginName',
									 value: data.field.loginName
								 });
							}else{
								 layui.data('loginObject',null);
							}
							layer.msg('登录成功');
							location.href='${_PATH}/admin/index';
						}else if("error"==rsp){
							layer.msg('您没有权限登录，请联系管理员');
						}else{
							layer.msg('用户名或密码错误');
						}
					}
				});
				return false;
			});
		});
	</script>
</body>
</html