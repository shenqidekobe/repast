layui.config({
	base : ctx+'/res/admin/plugins/layui/modules/'
});
layui.use(['icheck', 'laypage','layer','reuse','laydate','form'], function() {
	var $ = layui.jquery,
		laypage = layui.laypage,
		pageFlag=false,
		currPage=1;
	    laydate=layui.laydate,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		reuse=layui.reuse,
	    form = layui.form();
		
	 var pageSize=$("#uaFlag").val()=='1'?2:10;
	 var start = {
	    min: '2010-02-01 23:59:59',
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
		var type=$.trim($("#type").val());
		var name=$.trim($("#name").val());
		var phone=$.trim($("#phone").val());
		var startTime=$.trim($("#startTime").val());
		var endTime=$.trim($("#endTime").val());
		var status=$.trim($("#status").val());
		var departId=$.trim($("#departId").val());
		var reasonId=$.trim($("#reasonId").val());
		var loading = layer.load();
		$.ajax({
			url:ctx+'/admin/appointment/listData',
			type:'POST',
			data:{type:type,name:name,phone:phone,status:status,departId:departId,
				beginTime:startTime,endTime:endTime,reasonId:reasonId,
				page:page,pageSize:pageSize},
			success:function(data){
				$("#dataList").html(data);
				$("a[id=audit]").click(audit);
				$("a[id=edit]").click(edit);
				$("a[id=all_edit]").click(all_edit);
				$("a[id=ok]").click(update);
				$("a[id=sy]").click(update);
				$("a[id=qx]").click(update);
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
								loadList(currPage,pageSize);
							}
						}
					});
				}
			}
		});
	}
	if($("#type").val()=="success"){
		$("#startTime").val(laydate.now());
		$("#endTime").val(laydate.now());
	}
	loadList(1,pageSize);
	$('#search').on('click', function() {
		pageFlag=false;
		loadList(1,pageSize);
	});
	$('#print').on('click', function() {
		var type=$.trim($("#type").val());
		var name=$.trim($("#name").val());
		var phone=$.trim($("#phone").val());
		var startTime=$.trim($("#startTime").val());
		var endTime=$.trim($("#endTime").val());
		var status=$.trim($("#status").val());
		var departId=$.trim($("#departId").val());
		var url=ctx+'/admin/appointment/print?type='+type+'&name='+name+'&phone='+phone+'&startTime='+startTime+'&endTime='+endTime+'&status='+status+'&departId='+departId;
		window.open(url);
	});
	
	function audit(){
		var id=$(this).parent().attr("id");
		location.href=ctx+'/admin/appointment/view?id='+id;
	}
	function edit(){
		var id=$(this).parent().attr("id");
		location.href=ctx+'/admin/appointment/edit?id='+id;
	}
	function all_edit(){
		var id=$(this).parent().attr("id");
		location.href=ctx+'/admin/appointment/all_edit?id='+id;
	}
	function update(){
		var id=$(this).parent().attr("id");
		var status=$(this).attr("status");
		var tips="";
		if('treatment'==status){
			tips="你确认患者已就诊吗？";
		}else if('renege'==status){
			tips="您确认患者未按约定时间前来就诊吗？";
		}else if('cancels'==status){
			tips="你确认要撤消患者的预约安排吗？";
		}
		layer.confirm(tips, {icon: 3, title:'确认提示'}, function(index){
			var loading = layer.load();
			$.ajax({
				url:ctx+'/admin/appointment/update',
				type:'POST',
				data:{id:id,status:status},
				dataType:'json',
				success:function(data){
					if("success"==data){
						layer.msg("保存成功");
						loadList(currPage,10);
					}else{
						layer.msg("保存失败,请联系管理员");
					}
					layer.close(loading);
					layer.close(index);
				}
			});
		});
	}
});