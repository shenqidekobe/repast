<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/inc/admin/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <c:import url="/inc/admin/link.jsp"></c:import>
    <link rel="stylesheet" href="${_PATH}/res/admin/css/table.css" />
</head>
<body>
<div class="admin-main">
    <blockquote class="layui-elem-quote">
        <form action="" id="listForm" class="layui-form">
            <div class="layui-input-inline">
                <input type="text" name="name" id="name" lay-verify="name" placeholder="角色名" autocomplete="off" class="layui-input">
            </div>
            <a href="javascript:;" class="layui-btn layui-btn-small" id="search">
                <i class="layui-icon">&#xe615;</i> 搜索
            </a>
            <a href="javascript:;" class="layui-btn layui-btn-small" id="add">
                <i class="layui-icon">&#xe608;</i> 添加角色
            </a>
        </form>
    </blockquote>
    <div id="dataList"></div>
    <div class="admin-table-page">
        <div id="page" class="page"></div>
    </div>
</div>
</body>
</html>
