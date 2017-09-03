<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/inc/admin/taglibs.jsp"%>
<fieldset class="layui-elem-field">
	<legend>菜单列表</legend>
	<div class="layui-field-box">
		<table class="site-table table-hover">
			<thead>
				<tr>
					<th><input type="checkbox" id="selected-all"></th>
					<th>ID</th>
					<th>名称</th>
					<th>上级ID</th>
					<th>类型</th>
					<th>地址</th>
					<th>顺序</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			    <c:forEach items="${dataPagination.records}" var="it">
			        <tr>
						<td><input type="checkbox"></td>
						<td>${it.id}</td>
						<td>${it.name}</td>
						<td>${it.pid}</td>
						<td style="text-align: center;">
							<c:if test="${it.type eq 1}">菜单</c:if>
							<c:if test="${it.type eq 2}">权限点</c:if>
						</td>
						<td>${it.url}</td>
						<td>${it.seq}</td>
						<td><fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td id="${it.id}">
						    <a href="javascript:void(0);" class="layui-btn layui-btn-mini" id="edit">编辑</a>
							<a href="javascript:void(0);" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini" id="del">删除</a>
						</td>
					</tr>
			    </c:forEach>
			</tbody>
		</table>
	</div>
</fieldset>
<input type="hidden" id="pageCount" value="${dataPagination.pager.pageCount}">
