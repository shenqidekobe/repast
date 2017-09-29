$(function () {
	 var dataTable=null;
	 $("#addBtn").click(function(){
		location.href='/merchant/desk/edit'; 
	 });
	 window.operateEvents={
	    "click #edit":function(e,value, row, index){
	    	location.href='/merchant/desk/edit?id='+row.id; 
		    return false;
	    },"click #remove":function(e,value, row, index){
	    	toastrC.confirm({ message: "确认要删除该桌子嘛？" }).on(function (e) {
	    		if (!e) { return; }
	    		$.ajax({
		    		url:'/merchant/desk/remove.do',
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
	    },"click #dwonload":function(e,value, row, index){
	    	location.href='/merchant/qr?deskNum='+row.deskNum; 
		    return false;
	    }
     } 
	 var url = "/merchant/desk/listData.do?random="+Math.random();
	 var columns= [
	        { field: '',title: '序号', formatter: function (value, row, index) { return index+1; }  },
	        { field : 'id',title : '编号',visible:false}, 
	        { field : 'deskNum', title : '桌号',},
	        { field : 'seatCount', title : '座位数量',},
	        { field : 'createTime',title : '发布时间', formatter : function (value, row, index){ return new Date(value).Format('yyyy-MM-dd hh:mm:ss');}}, 
	        { field : 'opers',title: '操作',events:operateEvents, formatter : operateFormatter }]
	  function operateFormatter(value, row, index) {
	      return ['<button type="button" class="btn btn-primary" id="edit">编辑</button>&nbsp;&nbsp;'+
	               '<button type="button" class="btn btn-warning" id="remove">删除</button>&nbsp;&nbsp;'+
	               '<button type="button" class="btn btn-danger" id="dwonload">下载二维码</button>'].join("");
	  }
	  loadTable(url,columns,function(options){
          dataTable=$('#dataTable').bootstrapTable(options);
	  });
});

