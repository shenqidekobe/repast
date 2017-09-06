$(function() {
	$.dataTablesSettings = {
		    processing : false,//是否显示加载中提示
		    bAutoWidth : false,//是否自动计算表格各列宽度
		    bPaginate : true,//是否显示使用分页
		    bInfo : false,//是否显示页数信息
		    sPaginationType : "full_numbers",//分页样式
		    iDisplayLength : 10,//默认每页显示多少条记录
		    searching : false,//是否显示搜索框
		    bSort : false,//是否允许排序
		    serverSide : true,//是否从服务器获取数据
		    bStateSave : true,//页面重载后保持当前页
		    bLengthChange : false,//是否显示每页大小的下拉框
		    sServerMethod : "POST",
		    language: {
		        lengthMenu : "每页显示 _MENU_记录", 
		        zeroRecords : "没有匹配的数据", 
		        info : "第_PAGE_页/共 _PAGES_页", 
		        infoEmpty : "没有符合条件的记录", 
		        search : "搜索", 
		        infoFiltered : "(从 _MAX_条记录中过滤)", 
		        paginate : { "first" : "首页 ", "last" : "末页", "next" : "下一页", "previous" : "上一页"}
		    },
		    //这里是为ajax添加自定义参数，给它添加的属性，它会传给后台
		    fnServerParams : function (aoData) {
		        aoData._rand = Math.random();
		    }
		};
	$('#dataTable').DataTable(
		$.dataTablesSettings;
	);
});