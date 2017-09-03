layui.config({
	base : ctx+'/res/admin/plugins/layui/modules/'
});
layui.use(['icheck', 'laypage','layer','reuse','laydate','form','element'], function() {
	var $ = layui.jquery,
		laypage = layui.laypage,
		element = layui.element(),
		pageFlag=false,
		currPage=1,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laydate = layui.laydate,
		reuse=layui.reuse,
	    form = layui.form();
	    
	form.render('select');
	
	var start = {
	    min: '2017-02-01 23:59:59',
	    max: '2099-06-16 23:59:59',
	    istoday: false,
	    choose: function(datas){
	      end.min = datas; //开始日选好后，重置结束日的最小日期
	      end.start = datas //将结束日的初始值设定为开始日
	    }
	  };
	 var end = {
	    min: laydate.now(),
	    max: '2099-06-16 23:59:59',
	    istoday: false,
	    choose: function(datas){
	      start.max = datas; //结束日选好后，重置开始日的最大日期
	    }
	  };
	  $("#startTime").click(function(){
	     start.elem = this;
	     laydate(start);
	  });
	  $("#endTime").click(function(){
		  end.elem = this;
		  laydate(end);
	  });

	function loadList(page,pageSize){
		var content=$.trim($("#content").val());
		var logType=$.trim($("#logType").val());
		var source=$.trim($("#source").val());
		var loading = layer.load();
		$.ajax({
			url:ctx+'/admin/systemlog/listData',
			type:'POST',
			data:{content:content,logType:logType,source:source,
				page:page,pageSize:pageSize},
			success:function(data){
				$("#dataList").html(data);
				$("a[id=view]").click(view);
				layer.close(loading);
				
				var pages=$("#pageCount").val();
				if(!pageFlag){
					pageFlag=true;
					laypage({
						cont: 'page',
						pages: pages, //总页数
						groups: 5 ,//连续显示分页数
						skin:'',
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
	element.on('tab(logSource)', function(){
		var source=this.getAttribute('lay-id');
		$("#source").val(source);
		pageFlag=false;
		loadList(1,10);
    });
	
	function view(){
		var id=$(this).parent().attr("id");
		var loading = layer.load();
		$.get(ctx+'/admin/systemlog/view', {id:id}, function(form) {
			layer.open({
				type: 1,
				title: '系统日志详情',
				content: form,
				area: ['770px', '650px'],
				maxmin: true,
				shadeClose: false,
				btn:['关闭'],
				yes: function(index) {
					layer.close(index);
				},
				success: function(layero, index){
					layer.close(loading);
				},
				end:function(){
					//loadList(currPage,10);
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
});