$(function () {
	 var dataTable=null;
	 $("#addBtn").click(function(){
		location.href='/role/edit'; 
	 });
	 window.operateEvents={
	    "click #edit":function(e,value, row, index){
	    	location.href='/role/edit?id='+row.id; 
		    return false;
	    },"click #pers":function(e,value, row, index){
	    	location.href='/role/pers?id='+row.id; 
		    return false;
	    }
     } 
	 var url = "/role/listData.do?random="+Math.random();
	 var columns= [
	        { field: '',title: '序号', formatter: function (value, row, index) { return index+1; }  },
	        { field : 'id',title : '编号',}, 
	        { field : 'name', title : '名称',},
	        { field : 'createTime',title : '创建时间',formatter : function (value, row, index){return new Date(value).Format('yyyy-MM-dd hh:mm:ss');} },
	        { field : 'opers',title: '操作',events:operateEvents, formatter : operateFormatter }]
	  function operateFormatter(value, row, index) {
	      return ['<button type="button" class="btn btn-primary btn-circle" id="edit">编辑</button>&nbsp;&nbsp;'+
	               '<button type="button" class="btn btn-warning"  id="pers">分配权限</button>'].join("");
	  }
	  loadTable(url,columns,function(options){
          dataTable=$('#dataTable').bootstrapTable(options);
	  });
});

