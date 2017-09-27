$(function () {
	 var dataTable=null;
	 window.operateEvents={
	    "click #edit":function(e,value, row, index){
	    	location.href='/logs/view/'+row.id; 
		    return false;
	    }
     } 
	 var url = "/logs/listData.do?random="+Math.random();
	 var columns= [
	        { field: '',title: '序号', formatter: function (value, row, index) { return index+1; }  },
	        { field : 'operUserName', title : '操作用户', }, 
	        { field : 'ip', title : '请求IP',},
	        { field : 'reqUrl', title : '请求地址',},
	        { field : 'createTime',title : '操作时间', formatter : function (value, row, index){ return new Date(value).Format('yyyy-MM-dd hh:mm:ss');}}, 
	        { field : 'opers',title: '操作',events:operateEvents, formatter : operateFormatter }]
	  function operateFormatter(value, row, index) {
		  var toggle=row.status=="normal"?"禁用":"启用";
	      return ['<button type="button" class="btn btn-primary btn-circle" id="edit">查看</button>&nbsp;&nbsp;'].join("");
	  }
	  loadTable(url,columns,function(options){
          dataTable=$('#dataTable').bootstrapTable(options);
	  });
});

