<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/inc/admin/taglibs.jsp"%>
<div style="margin: 15px;">
	<form class="layui-form layui-form-pane" action="">
		<div class="layui-form-item">
			<label class="layui-form-label">昵称</label>
			<div class="layui-input-inline">
		    	<input type="hidden" name="id" id="id" value="${obj.id}">
				<input type="text" name="nickName" lay-verify="nickName" value="${obj.nickName}" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">旧密码</label>
			<div class="layui-input-inline">
				<input type="password" name="password" lay-verify="required" placeholder="请输入原密码" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">新密码</label>
			<div class="layui-input-inline">
				<input type="password" name="newPassword" id="newPassword" lay-verify="newPassword" placeholder="请输入新密码" autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">确认密码</label>
			<div class="layui-input-inline">
				<input type="password" name="newPassword1" lay-verify="newPassword1" placeholder="请输入确认密码" autocomplete="off" class="layui-input">
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
			nickName: function(value) {
				if(value.length < 4) {
					return '昵称至少得4个字符啊';
				}
			    if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
			        return '昵称不能有特殊字符';
			    }
			},
			newPassword: [/(.+){6,12}$/, '密码必须6到12位'],
			newPassword1:function(val){
				var newPassword=$("#newPassword").val();
				console.info(val+"---"+newPassword);
				if(val!=newPassword){
					return '确认密码不一致';
				}
			}
		});

		//监听提交
		form.on('submit(save)', function(data) {
			$.ajax({
				url:ctx+'/admin/setting/save',
				type:'POST',
				data:data.field,
				success:function(rsp){
					if("success"==rsp){
						layer.msg('更新成功');
						setTimeout(function () {
						   layer.closeAll();
				        }, 1000);
					}else{
						layer.msg('原密码错误,请重新填写');
					}
				}
			});
			return false;
		});
	});
</script>