$(function () {
	 $("#addBtn").click(function(){
		location.href='/account/edit'; 
	 });
	 var operateEvents={
	    "click .btn-primary":function(e,value, row, index){
	    	location.href='/account/edit?id'+row.id; 
		   return false;
	    },"click #del":function(e,value, row, index){
		   alert(row.id); 
		   return false;
	    }
     } 
	 var url = "/account/listData.do?random="+Math.random();
	 var columns= [
	        { field: '',title: '序号', formatter: function (value, row, index) { return index+1; }  },
	        { field : 'id',title : '编号',}, 
	        { field : 'loginName', title : '帐号',},
	        { field : 'role.name', title : '角色', }, 
	        { field : 'typeName', title : '类型',},
	        { field : 'statusName',title : '状态',}, 
	        { field : 'createTime',title : '入职时间', formatter : function (value, row, index){ return new Date(value).Format('yyyy-MM-dd hh:mm:ss');}}, 
	        { field : 'loginTime',title : '登录时间',formatter : function (value, row, index){return new Date(value).Format('yyyy-MM-dd hh:mm:ss');} },
	        { field : '',title: '操作',events:operateEvents, formatter : operateFormatter }]
	  function operateFormatter(value, row, index) {
	        return ['<button type="button" class="btn btn-primary btn-circle" id="edit">编辑</button>&nbsp;&nbsp;'+
	               '<button type="button" class="btn btn-warning btn-circle"  id="del">删除</button>'].join("");
	  }
	  var dataTable=null;
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

