<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/inc/admin/taglibs.jsp"%>
<div style="margin: 15px;">
	<form class="layui-form layui-form-pane" action="">
		<div class="layui-form-item">
			<label class="layui-form-label">菜单名</label>
			<div class="layui-input-inline">
		    	<input type="hidden" name="id" id="id" value="${obj.id}">
				<input type="text" name="name" lay-verify="name" value="${obj.name}" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">类型</label>
			<div class="layui-input-inline">
				<select name="type" lay-verify="required" lay-filter="type">
				   <option value="1" <c:if test="${obj.type eq 1}">selected="selected"</c:if>>菜单</option>
				   <option value="2" <c:if test="${obj.type eq 2}">selected="selected"</c:if>>权限点</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item" id="menuSelect">
			<label class="layui-form-label">上级菜单</label>
			<div class="layui-input-inline">
				<select name="pid">
				    <option value="">一级菜单</option>
				    <c:forEach items="${catalogList}" var="it">
				       <c:if test="${empty it.pid}">
					      <option value="${it.id}" <c:if test="${obj.pid eq it.id}">selected="selected"</c:if>>${it.name}</option>
				       </c:if>
				    </c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">访问地址</label>
			<div class="layui-input-inline">
				<input type="text" name="url" lay-verify="required" value="${obj.url}" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">菜单图标</label>
	        <div class="layui-input-inline" style="width:135px">
	            <input placeholder="请选择图标" type="text" name="icon" id="icon" value="${obj.icon}" class="layui-input">
	        </div>
	        <span class="layui-btn layui-btn-primary" style="padding:0 12px;min-width:45.2px">
	            <i id="icon-preview" style="font-size:1.2em" class="${obj.icon}"></i>
	        </span>
	        <button type="button" id="selectIcon" class="layui-btn layui-btn-primary">选择图标</button>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">序号</label>
			<div class="layui-input-inline">
				<input type="number" name="seq" lay-verify="seq" value="${obj.seq}" autocomplete="off" class="layui-input">
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
		
		$("#icon").change(function(){
		    $('#icon-preview').get(0).className = $(this).val();
		});
		
		$("#selectIcon").click(function(){
			layer.open({
				type: 2,
				title: '图标选择',
				content: '${_PATH}/admin/catalog/icon',
				area:['800px','600px'],
				success: function(layero, index){
					var body = layer.getChildFrame('body', index);
					body.find('li').click(function () {
                        var className = $(this).find('span.glyphicon,span.fa').get(0).className;
                        console.info(className);
                        if (className) {
                            $('#icon').val(className).trigger('change');
                            layer.close(index);
                        }
                    });
				}
			});
		});
		form.render('select');
		//自定义验证规则
		form.verify({
			name: function(value) {
				if(value.length < 4) {
					return '名称至少得4个字符啊';
				}
			   var message = '';
	            $.ajax({
	                async: false, //改为同步请求
	                url: ctx+'/admin/catalog/validateName',
	                data: {
	                	name: value,
	                	id:$("#id").val()
	                },
	                dataType: 'json',
	                type: 'post',
	                success: function(result) {
	                    if (!result) 
	                      	message = "菜单名"+value+"已存在";
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
				url:ctx+'/admin/catalog/save',
				type:'POST',
				data:data.field,
				success:function(rsp){
					if("success"==rsp){
						layer.msg('保存成功');
						setTimeout(function () {
						   layer.closeAll();
				        }, 1000);
					}else if("fail"==rsp){
						layer.msg('菜单名已存在');
					}else{
						layer.msg('菜单信息保存失败');
					}
					layer.close(loading);
				}
			});
			return false;
		});
	});
</script>