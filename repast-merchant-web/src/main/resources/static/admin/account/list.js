$(function () {
	 var dataTable=null;
	 $("#addBtn").click(function(){
		location.href=ctx+'/account/edit'; 
	 });
	 window.operateEvents={
	    "click #edit":function(e,value, row, index){
	    	location.href=ctx+'/account/edit?id='+row.id; 
		    return false;
	    },"click #toggle":function(e,value, row, index){
	    	var toggleTips=row.status=="normal"?"禁用":"启用";
	    	var status=row.status=="normal"?"disable":"normal";
	    	toastrC.confirm({ message: "确认要"+toggleTips+"该用户嘛？" }).on(function (e) {
	    		if (!e) { return; }
	    		$.ajax({
		    		url:ctx+'/account/toggle.do',
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
	 var url = ctx+"/account/listData.do?random="+Math.random();
	 var columns= [
	        { field: '',title: '序号', formatter: function (value, row, index) { return index+1; }  },
	        { field : 'id',title : '编号',visible:false}, 
	        { field : 'loginName', title : '帐号',},
	        { field : 'role.name', title : '角色', }, 
	        { field : 'typeName', title : '类型',},
	        { field : 'statusName',title : '状态',}, 
	        { field : 'createTime',title : '入职时间', formatter : function (value, row, index){ return new Date(value).Format('yyyy-MM-dd hh:mm:ss');}}, 
	        { field : 'loginTime',title : '登录时间',formatter : function (value, row, index){return new Date(value).Format('yyyy-MM-dd hh:mm:ss');} },
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

