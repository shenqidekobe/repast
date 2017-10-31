<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/inc/admin/taglibs.jsp"%>
<div style="margin: 15px;">
	<form class="layui-form" action="">
		<div class="layui-form-item">
		    <c:forEach items="${dataPage.records}" var="it">
			    <div class="layui-input-inline">
			        <input type="checkbox" name="catalogIds" value="${it.id}" title="${it.name}" <c:if test="${it.distributed}">checked</c:if> >
			    </div>
		    </c:forEach>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
			    <input type="hidden" name="id" id="id" value="${obj.id}">
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
					
		    form.render('checkbox');
			//监听提交
			form.on('submit(save)', function(data) {
				var catalogId=$('input:checkbox[name="catalogIds"]:checked').val();
				if(catalogId=='undefined'||catalogId==undefined||catalogId==''||catalogId==null){
					layer.msg('请至少选择一个菜单');
					return false;
				}
				var catalogIds=new Array();
				$("input[name='catalogIds']:checkbox").each(function() { 
					if($(this).is(":checked")) {
						catalogIds.push($(this).val());
					}
				});
				var loading = layer.load();
				var tmpCatalogIds = catalogIds.join(',');
				$.post(ctx+'/admin/group/distribute/save',{id:data.field.id,catalogIds:tmpCatalogIds},function(data){
					if("success"==data){
						layer.msg('保存成功');
						setTimeout(function () {
						   layer.closeAll();
				        }, 1000);
					}else{
						layer.msg('保存失败');
					}
			});
				return false;
			});
	});
</script>