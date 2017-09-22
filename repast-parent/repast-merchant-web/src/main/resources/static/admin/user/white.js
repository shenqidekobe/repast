$(function () {
	 var dataTable=null;
	 $("#addBtn").click(function(){
		location.href='/user/white/edit'; 
	 });
	 window.operateEvents={
	    "click #edit":function(e,value, row, index){
	    	location.href='/user/white/edit?id='+row.id; 
		    return false;
	    },"click #toggle":function(e,value, row, index){
	    	var toggleTips=row.status=="normal"?"禁用":"启用";
	    	var status=row.status=="normal"?"disable":"normal";
	    	toastrC.confirm({ message: "确认要"+toggleTips+"该用户嘛？" }).on(function (e) {
	    		if (!e) { return; }
	    		$.ajax({
		    		url:'/user/white/toggle.do',
		    		type:'POST',
		    		data:{id:row.id,status:status},
		    		success:function(rsp){
		    			if(rsp.status==200){
		    				toastr.success(toggleTips+"成功");
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
	 var url = "/user/white/listData.do?random="+Math.random();
	 var columns= [
	        { field: '',title: '序号', formatter: function (value, row, index) { return index+1; }  },
	        { field : 'userName',title : '姓名',}, 
	        { field : 'company', title : '公司',},
	        { field : 'dept', title : '部门', }, 
	        { field : 'phone', title : '手机号',},
	        { field : 'typeName', title : '类型',},
	        { field : 'statusName',title : '状态',}, 
	        { field : 'createTime',title : '创建时间', formatter : function (value, row, index){ return new Date(value).Format('yyyy-MM-dd hh:mm:ss');}}, 
	        { field : 'opers',title: '操作',events:operateEvents, formatter : operateFormatter }]
	  function operateFormatter(value, row, index) {
		  var toggle=row.status=="normal"?"禁用":"启用";
	      return ['<button type="button" class="btn btn-primary btn-circle" id="edit">编辑</button>&nbsp;&nbsp;'+
	               '<button type="button" class="btn btn-warning btn-circle"  id="toggle">'+toggle+'</button>'].join("");
	  }
	  loadTable(url,columns,function(options){
		 options.rowStyle= function (row, index) {
             var strclass = "";
             if (row.statusName == "正常") {
                 strclass = 'success';
             } else if (row.statusName == "已禁用") {
                 strclass = 'danger';
             }
             return { classes: strclass }
          }
          dataTable=$('#dataTable').bootstrapTable(options);
	  });
});

