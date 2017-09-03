<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/inc/admin/taglibs.jsp"%>
<div style="margin: 15px;">
	<form class="layui-form layui-form-pane" action="">
		<div class="layui-form-item">
			<label class="layui-form-label">角色名</label>
			<div class="layui-input-block">
		    	<input type="hidden" name="id" id="id" value="${obj.id}">
				<input type="text" name="name" lay-verify="name" value="${obj.name}" autocomplete="off" class="layui-input">
			</div>
		</div>
	    <div class="layui-form-item">
			<label class="layui-form-label">描述</label>
			<div class="layui-input-block">
				<textarea name="description" id="description" lay-verify="description" class="layui-textarea">${obj.description}</textarea>
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
			name: function(value) {
				if(value.length < 3) {
					return '角色名至少得3个字符啊';
				}
			    var message = '';
	            $.ajax({
	                async: false, //改为同步请求
	                url: ctx+'/admin/group/validateName',
	                data: {
	                	name: value,
	                	id:$("#id").val()
	                },
	                dataType: 'json',
	                type: 'post',
	                success: function(result) {
	                    if (!result) 
	                      	message = "角色名"+value+"已存在";
	                }
	            });
	            if (message !== '')
	              	return message;
			}
		});

		//监听提交
		form.on('submit(save)', function(data) {
			var loading=layer.load(2);
			$.ajax({
				url:ctx+'/admin/group/save',
				type:'POST',
				data:data.field,
				success:function(rsp){
					if("success"==rsp){
						layer.msg('保存成功');
						setTimeout(function () {
						   layer.closeAll();
				        }, 1000);
					}else if("fail"==rsp){
						layer.msg('角色名已存在');
					}else{
						layer.msg('角色信息保存失败');
					}
					layer.close(loading);
				}
			});
			return false;
		});
	});
</script>