layui.config({
	base : ctx+'/res/admin/plugins/layui/modules/'
});
layui.use(['icheck', 'laypage','layer','reuse','form'], function() {
	var $ = layui.jquery,
		laypage = layui.laypage,
		pageFlag=false,
		currPage=1,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		reuse=layui.reuse,
	    form = layui.form();
	    
	 form.render('select');
	
	function loadList(page,pageSize){
		var name=$("#name").val();
		var loading = layer.load();
		$.ajax({
			url:ctx+'/admin/catalog/listData',
			type:'POST',
			data:{name:name,page:page,pageSize:pageSize},
			success:function(data){
				$("#dataList").html(data);
				$("a[id=edit]").click(edit);
				$("a[id=del]").click(remove);
				layer.close(loading);
				
				reuse.tableCheck();
				
				var pages=$("#pageCount").val();
				if(!pageFlag){
					pageFlag=true;
					laypage({
						cont: 'page',
						pages: pages, //总页数
						groups: 5 ,//连续显示分页数
						jump: function(obj, first) {
							currPage = obj.curr;
							if(!first) {
								loadList(currPage,10);
							}
						}
					});
				}
			}
		});
	}
	loadList(1,10);
	$('#search').on('click', function() {
		pageFlag=false;
		loadList(1,10);
	});
	$('#add').on('click', function() {
		showForm(null);
	});
	function showForm(id){
		var loading = layer.load();
		$.get(ctx+'/admin/catalog/edit', {id:id}, function(form) {
			layer.open({
				type: 1,
				title: '菜单信息表单',
				content: form,
				area: ['500px', '480px'],
				maxmin: true,
				yes: function(index) {
					console.info(index);
				},
				success: function(layero, index){
					layer.close(loading);
				},
				end:function(){
					loadList(currPage,10);
				},
				full: function(elem) {
					var win = window.top === window.self ? window : parent.window;
					$(win).on('resize', function() {
						var $this = $(this);
						elem.width($this.width()).height($this.height()).css({
							top: 0,
							left: 0
						});
						elem.children('div.layui-layer-content').height($this.height() - 95);
					});
				}
			});
		});
	}
	
	function edit(){
		var id=$(this).parent().attr("id");
		showForm(id);
	}
	function remove(){
		var id=$(this).parent().attr("id");
		layer.confirm('确定要删除此菜单嘛？', {icon: 3, title:'确认提示'}, function(index){
			var loading = layer.load();
			$.ajax({
				url:ctx+'/admin/catalog/remove',
				type:'POST',
				data:{id:id},
				dataType:'json',
				success:function(rsp){
					if("success"==rsp){
						layer.close(loading);
						layer.msg("删除成功");
						loadList(currPage,10);
						layer.close(index);
					}else if("fail"==rsp){
						layer.close(loading);
						layer.msg('此菜单已被角色使用，不能直接删除');
						layer.close(index);
					}else{
						layer.close(loading);
						layer.msg('菜单存在下级菜单，不能直接删除');
						layer.close(index);
					}
				}
			});
		});
	}
});