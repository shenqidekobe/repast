<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/inc/admin/taglibs.jsp"%>
<fieldset class="layui-elem-field">
	<legend>用户列表</legend>
	<div class="layui-field-box">
		<table class="site-table table-hover">
			<thead>
				<tr>
					<th><input type="checkbox" id="selected-all"></th>
					<th>帐号</th>
					<th>昵称</th>
					<th>角色</th>
					<th>状态</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			    <c:forEach items="${dataPage.records}" var="it">
			         <tr>
						<td><input type="checkbox"></td>
						<td>${it.loginName}</td>
						<td>${it.nickName}</td>
						<td>${it.groupName}</td>
						<td style="text-align: center;">
							<c:if test="${it.status eq 1}"><i class="layui-icon" style="color: green;">&#xe616;</i></c:if>
							<c:if test="${it.status eq 0}"><i class="layui-icon" style="color: red;">&#x1007;</i></c:if>
						</td>
						<td><fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td id="${it.id}" status="${it.status }">
						    <c:if test="${it.status eq 1}">
							    <a href="javascript:void(0);" class="layui-btn layui-btn-mini" id="edit">编辑</a>
							</c:if>
							<a href="javascript:void(0);" class="layui-btn layui-btn-primary layui-btn-mini" id="toggle"><c:if test="${it.status eq 1}">禁用</c:if><c:if test="${it.status eq 0}">解禁</c:if></a>
							<c:if test="${it.status eq 1}">
								<a href="javascript:void(0);" class="layui-btn layui-btn-warm layui-btn-mini" id="reset">重置密码</a>
								<%-- <a href="${_PATH}/admin/admin/distribute/${it.id}" class="layui-btn layui-btn-normal layui-btn-mini">分配角色</a> --%>
								<a href="javascript:void(0);" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini" id="del">删除</a>
							</c:if>
						</td>
					</tr>
			    </c:forEach>
			</tbody>
		</table>
	</div>
</fieldset>
<input type="hidden" id="pageCount" value="${dataPage.pageCount}">
