$.dataTablesSettings = $.extend(true, $.fn.options, {});
$.dataTablesSettings = {
	method : 'POST',
	dataType : 'json',
	contentType: "application/x-www-form-urlencoded",
	toolbar : '#toolbar', // 工具按钮用哪个容器
	showRefresh: true,  //是否显示刷新按钮
    showColumns : false,
	showToggle: false,  // 分类
    search: false,   //是否显示表格搜索,客户端搜索
	striped : true, // 是否显示行间隔色
	cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	pagination : true, // 是否显示分页（*）
	sortable : false, // 是否启用排序
	sortOrder : "asc", // 排序方式
	queryParams : queryParams,// 传递参数（*）
	sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
	pageNumber : 1, // 初始化加载第一页，默认第一页
	pageSize : 10, // 每页的记录行数（*）
	pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
	strictSearch : true,
	clickToSelect : true, // 是否启用点击选中行
	//height : 460, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	uniqueId : "id", // 每一行的唯一标识，一般为主键列
	cardView : false, // 是否显示详细视图
	detailView : false, // 是否显示父子表
	width : $(window).width(),
	minimumCountColumns : 2,
	showExport : true,
	exportDataType : 'all',
};
function loadTable(url, colums, callback) {
	$.dataTablesSettings.url = url;
	$.dataTablesSettings.columns = colums;
	$.dataTablesSettings.queryParams = queryParams;
	callback($.dataTablesSettings);
}
function queryParams(params) {
	var search = $("#searchForm").serializeArray();
	var param = {
		page : params.offset,
		pageSize : params.limit
	}
    for (var item in search) {
    	var val=search[item].value;
    	param[search[item].name] = val==""?null:val;
    }
	return param;
}
function doQuery(params) {
	$('#dataTable').bootstrapTable('refresh');
 }
Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}
