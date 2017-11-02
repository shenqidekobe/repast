<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/inc/admin/taglibs.jsp"%>
<div style="margin: 15px;">
    <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">商户名</label>
            <div class="layui-input-inline">
                <input type="hidden" name="id" id="id" value="${obj.id}">
                <input type="text" name="name" lay-verify="name" value="${obj.name}" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">商户类型</label>
            <div class="layui-input-inline">
                <input type="text" name="nickName" lay-verify="required" value="${obj.nickName}" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">商户地址</label>
            <div class="layui-input-inline">
                <input type="text" name="address" lay-verify="address" value="${obj.address}" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">联系电话</label>
            <div class="layui-input-inline">
                <input type="text" name="mobile" lay-verify="phone" value="${obj.mobile}" placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">角色</label>
            <div class="layui-input-inline">
                <select name="groupId" lay-verify="required">
                    <c:forEach items="${groupList}" var="it">
                        <option value="${it.id}" <c:if test="${obj.groupId eq it.id}">selected="selected"</c:if>>${it.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="button" class="layui-btn" lay-submit="" lay-filter="save">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script>
    layui.config({
        base : ctx+'/res/admin/plugins/layui/lay/modules/'
    });
    layui.use(['form'], function() {
        var form = layui.form(),
            layer = layui.layer,
            $ = layui.jquery;
        form.render('select');
        //自定义验证规则
        form.verify({
            loginName: function(value) {
                if(value.length < 4) {
                    return '登录名至少得4个字符啊';
                }
                var message = '';
                $.ajax({
                    async: false, //改为同步请求
                    url: ctx+'/admin/admin/validateLoginName',
                    data: {
                        loginName: value,
                        id:$("#id").val()
                    },
                    dataType: 'json',
                    type: 'post',
                    success: function(result) {
                        if (!result)
                            message = "登录名"+value+"已存在";
                    }
                });
                if (message !== '')
                    return message;
            },
            password: [/(.+){6,12}$/, '密码必须6到12位'],
        });

        //监听提交
        form.on('submit(save)', function(data) {
            var loading=layer.load(2);
            $.ajax({
                url:ctx+'/admin/admin/save',
                type:'POST',
                data:data.field,
                success:function(rsp){
                    if("success"==rsp){
                        layer.msg('保存成功');
                        setTimeout(function () {
                            layer.closeAll();
                        }, 1000);
                    }else if("fail"==rsp){
                        layer.msg('登录名已存在');
                    }else{
                        layer.msg('用户信息保存失败');
                    }
                    layer.close(loading);
                }
            });
            return false;
        });
    });
</script>