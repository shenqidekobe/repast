$(function () {
	 var dataTable=null;
	 $("#addBtn").click(function(){
		location.href=ctx+'/user/auth/edit'; 
	 });
	 window.operateEvents={
	    "click #edit":function(e,value, row, index){
	    	location.href=ctx+'/user/auth/edit?id='+row.id; 
		    return false;
	    },"click #toggle":function(e,value, row, index){
	    	toastrC.confirm({ message: "确认要删除此申请记录嘛？" }).on(function (e) {
	    		if (!e) { return; }
	    		$.ajax({
		    		url:ctx+'/user/auth/remove.do',
		    		type:'POST',
		    		data:{id:row.id},
		    		success:function(rsp){
		    			if(rsp.status==200){
		    				toastr.success("删除成功");
		    				doQuery();
		    			}else{
		    				toastr.error("服务器繁忙，请稍后再试");
		    			}
		    		}
		    	});
	    	});
		    return false;
	    }
     } 
	 var url = ctx+"/user/auth/listData.do?random="+Math.random();
	 var columns= [
	        { field: '',title: '序号', formatter: function (value, row, index) { return index+1; }  },
	        { field : 'userName', title : '申请人',},
	        { field : 'company', title : '公司',},
	        { field : 'dept', title : '部门', }, 
	        { field : 'count', title : '人数',},
	        { field : 'statusName', title : '状态',},
	        { field : 'repastTime',title : '就餐时间',}, 
	        { field : 'createTime',title : '申请时间', formatter : function (value, row, index){ return new Date(value).Format('yyyy-MM-dd hh:mm:ss');}}, 
	        { field : 'opers',title: '操作',events:operateEvents, formatter : operateFormatter }]
	  function operateFormatter(value, row, index) {
	      return ['<button type="button" class="btn btn-primary btn-circle" id="edit">编辑</button>&nbsp;&nbsp;'+
	               '<button type="button" class="btn btn-warning btn-circle"  id="toggle">删除</button>'].join("");
	  }
	  loadTable(url,columns,function(options){
          dataTable=$('#dataTable').bootstrapTable(options);
	  });
});

