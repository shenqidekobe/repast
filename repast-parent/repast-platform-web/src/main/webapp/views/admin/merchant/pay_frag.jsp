<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/inc/admin/taglibs.jsp" %>
<fieldset class="layui-elem-field">
    <legend>用户列表</legend>
    <div class="layui-field-box">
        <table class="site-table table-hover">
            <thead>
            <tr>
                <th>商户id</th>
                <th>订单id</th>
                <th>金额</th>
                <th>支付渠道</th>
                <th>支付时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${dataPage.records}" var="it">
                <tr>
                    <td>${it.merchantId}</td>
                    <td>${it.orderId}</td>
                    <td>${it.amount}</td>
                    <td>${it.channel}</td>
                    <td><fmt:formatDate value="${it.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</fieldset>
<input type="hidden" id="pageCount" value="${dataPage.pageCount}">
