<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/inc/admin/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <c:import url="/inc/admin/link.jsp"></c:import>
    <link rel="stylesheet" href="${_PATH}/res/admin/css/table.css"/>
</head>
<body>
<div class="admin-main">
    <blockquote class="layui-elem-quote">
        <a href="javascript:;" class="layui-btn layui-btn-small" id="add">
            <i class="layui-icon">&#xe608;</i> 添加商户
        </a>
    </blockquote>
    <div id="dataList"></div>
    <div class="admin-table-page">
        <div id="page" class="page"></div>
    </div>
</div>
<script type="text/javascript" src="${_PATH}/res/admin/js/merchant/list.js"></script>
</body>
</html>
