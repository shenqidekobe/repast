$(function () {
	 var dataTable=null;
	 $("#addBtn").click(function(){
		location.href='/user/edit'; 
	 });
	 window.operateEvents={
	    "click #edit":function(e,value, row, index){
	    	location.href='/user/edit?id='+row.id; 
		    return false;
	    }
     } 
	 var url = "/user/listData.do?random="+Math.random();
	 var columns= [
	        { field: '',title: '序号', formatter: function (value, row, index) { return index+1; }  },
	        { field : 'nickName', title : '帐号',},
	        { field : 'phone', title : '手机号', }, 
	        { field : 'openId', title : '微信号',},
	        { field : 'createTime',title : '注册时间', formatter : function (value, row, index){ return new Date(value).Format('yyyy-MM-dd hh:mm:ss');}}, 
	        { field : 'loginTime',title : '登录时间',formatter : function (value, row, index){return new Date(value).Format('yyyy-MM-dd hh:mm:ss');} },
	        { field : 'opers',title: '操作',events:operateEvents, formatter : operateFormatter }]
	  function operateFormatter(value, row, index) {
		  var toggle=row.status=="normal"?"禁用":"启用";
	      return ['<button type="button" class="btn btn-primary btn-circle" id="edit">编辑</button>&nbsp;&nbsp;'].join("");
	  }
	  loadTable(url,columns,function(options){
          dataTable=$('#dataTable').bootstrapTable(options);
	  });
});

