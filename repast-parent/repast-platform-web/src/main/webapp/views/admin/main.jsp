<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/inc/admin/taglibs.jsp"%>
<link rel="stylesheet"
	href="${_PATH}/res/admin/plugins/layui/css/modules/bootstrap/bootstrap.min.css"
	media="all">
<link rel="stylesheet" href="${_PATH}/res/admin/css/iconfont.css"
	media="all">
<link rel="stylesheet" href="${_PATH}/res/admin/css/index-main.css"
	media="all">
<link rel="stylesheet"
	href="${_PATH}/res/admin/plugins/layui/css/layui.css" media="all" />
<div class="container-fluid larry-wrapper">
	<!--顶部统计数据预览 -->
	<div class="row">
		<div class="col-xs-6 col-sm-4 col-md-2">
			<section class="panel">
				<div class="symbol bgcolor-blue">
					<i class="iconfont"></i>
				</div>
				<div class="value">
					<a href="#">
						<h1>${allCount}</h1>
					</a>
					<p>订单总数</p>
				</div>
			</section>
		</div>
		<div class="col-xs-6 col-sm-4 col-md-2">
			<section class="panel">
				<div class="symbol bgcolor-commred">
					<i class="iconfont"></i>
				</div>
				<div class="value">
					<a href="#">
						<h1>${dayCount}</h1>
					</a>
					<p>今日订单</p>
				</div>
			</section>
		</div>

		<div class="col-xs-6 col-sm-4 col-md-2">
			<section class="panel">
				<div class="symbol bgcolor-dark-green">
					<i class="iconfont"></i>
				</div>
				<div class="value">
					<a href="#">
						<h1>${unpayCount}</h1>
					</a>
					<p>待支付</p>
				</div>
			</section>
		</div>
		<div class="col-xs-6 col-sm-4 col-md-2">
			<section class="panel">
				<div class="symbol bgcolor-yellow-green">
					<i class="iconfont"></i>
				</div>
				<div class="value">
					<a href="#">
						<h1>${payedCount}</h1>
					</a>
					<p>已支付</p>
				</div>
			</section>
		</div>
		<div class="col-xs-6 col-sm-4 col-md-2">
			<section class="panel">
				<div class="symbol bgcolor-yellow">
					<i class="iconfont"></i>
				</div>
				<div class="value">
					<a href="#">
						<h1>${finishedCount}</h1>
					</a>
					<p>已完成</p>
				</div>
			</section>
		</div>
		<div class="col-xs-6 col-sm-4 col-md-2">
			<section class="panel">
				<div class="symbol bgcolor-orange">
					<i class="iconfont"></i>
				</div>
				<div class="value">
					<a href="#">
						<h1>${cancelCount}</h1>
					</a>
					<p>已取消</p>
				</div>
			</section>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 col-md-6">
			<section class="panel">
				<div class="panel-heading">
					网站信息 <a href="javascript:;" class="pull-right panel-toggle"><i
						class="iconfont"></i></a>
				</div>
				<div class="panel-body">
					<table class="layui-table">
						<tbody>
							<tr>
								<td><strong>系统名称</strong>：</td>
								<td>${system.os_name}</td>
							</tr>
							<tr>
								<td><strong>系统版本号</strong>：</td>
								<td>${system.os_version}</td>
							</tr>
							<tr>
								<td><strong>系统时间</strong>：</td>
								<td><fmt:formatDate value="${system.os_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
							<tr>
								<td><strong>JDK版本</strong>：</td>
								<td>${system.java_version}</td>
							</tr>
							<tr>
								<td><strong>服务器名称</strong>：</td>
								<td>${system.server_name}</td>
							</tr>
							<tr>
								<td><strong>服务器地址</strong>：</td>
								<td>${system.server_addr}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</section>
			
		</div>
	</div>
</div>
