$(function () {
	 var dataTable=null;
	 window.operateEvents={
	    "click #edit":function(e,value, row, index){
	    	location.href=ctx+'/merchant/edit';
		    return false;
	    },"click #dwonload":function(e,value, row, index){
	    	location.href=ctx+'/merchant/qr';
		    return false;
	    }
     } 
	 var url = "/merchant/listData.do?random="+Math.random();
	 var columns= [
	        { field: '',title: '序号', formatter: function (value, row, index) { return index+1; }  },
	        { field : 'id',title : '编号',visible:false}, 
	        { field : 'name', title : '名称',},
	        { field : 'address', title : '地址', }, 
	        { field : 'createTime',title : '创建时间', formatter : function (value, row, index){ return new Date(value).Format('yyyy-MM-dd hh:mm:ss');}}, 
	        { field : 'opers',title: '操作',events:operateEvents, formatter : operateFormatter }]
	  function operateFormatter(value, row, index) {
	      return ['<button type="button" class="btn btn-primary" id="edit">详情</button>&nbsp;&nbsp;'+
	               '<button type="button" class="btn btn-danger"  id="dwonload">下载二维码</button>'].join("");
	  }
	  loadTable(url,columns,function(options){
		  options.showRefresh=false;
          dataTable=$('#dataTable').bootstrapTable(options);
	  });
});

