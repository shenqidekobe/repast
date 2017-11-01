<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/inc/admin/taglibs.jsp" %>
<fieldset class="layui-elem-field">
    <legend>商户订单列表</legend>
    <div class="layui-field-box">
        <table class="site-table table-hover">
            <thead>
            <tr>
                <th>商户ID</th>
                <th>订单号</th>
                <th>支付方式</th>
                <th>状态</th>
                <th>订单金额</th>
                <th>下单时间</th>
                <th>结算时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${dataPage.records}" var="it">
                <tr>
                    <td>${it.merchantId}</td>
                    <td>${it.orderId}</td>
                    <td>${it.payWay.name}</td>
                    <td>${it.status.name}</td>
                    <td>${it.amount}</td>
                    <td><fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${it.settleTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</fieldset>
<input type="hidden" id="pageCount" value="${dataPage.pageCount}">
