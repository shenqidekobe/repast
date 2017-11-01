<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/inc/admin/taglibs.jsp" %>
<fieldset class="layui-elem-field">
    <legend>用户列表</legend>
    <div class="layui-field-box">
        <table class="site-table table-hover">
            <thead>
            <tr>
                <th>商户名</th>
                <th>类型</th>
                <th>地址</th>
                <th>状态</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${dataPage.records}" var="it">
                <tr>
                    <td>${it.name}</td>
                    <td>${it.type.name}</td>
                    <td>${it.address}</td>
                    <td>${it.status.name}</td>
                    <td><fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</fieldset>
<input type="hidden" id="pageCount" value="${dataPage.pageCount}">
