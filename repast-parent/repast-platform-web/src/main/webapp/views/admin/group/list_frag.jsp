<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/inc/admin/taglibs.jsp"%>
<fieldset class="layui-elem-field">
	<legend>角色列表</legend>
	<div class="layui-field-box">
		<table class="site-table table-hover">
			<thead>
				<tr>
					<th><input type="checkbox" id="selected-all"></th>
					<th>名称</th>
					<th>描述</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			    <c:forEach items="${dataPagination.records}" var="it">
			    <tr>
						<td><input type="checkbox"></td>
						<td>${it.name}</td>
						<td>${it.description}</td>
						<td><fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td id="${it.id}">
						    <a href="javascript:void(0);" class="layui-btn layui-btn-mini" id="edit">编辑</a>
							<a href="javascript:void(0);" id="distribute" class="layui-btn layui-btn-normal layui-btn-mini">分配权限</a>
							<a href="javascript:void(0);" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini" id="del">删除</a>
						</td>
					</tr>
			    </c:forEach>
			</tbody>
		</table>
	</div>
</fieldset>
<input type="hidden" id="pageCount" value="${dataPagination.pager.pageCount}">
