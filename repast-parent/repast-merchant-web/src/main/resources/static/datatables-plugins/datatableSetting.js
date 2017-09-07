$.dataTablesSettings=$.extend(true, $.fn.dataTable.defaults, {} );
$.dataTablesSettings = {
    processing : false,//是否显示加载中提示
    bAutoWidth : false,//是否自动计算表格各列宽度
    bPaginate : true,//是否显示使用分页
    bInfo : false,//是否显示页数信息
    sPaginationType : "full_numbers",//分页样式
    iDisplayLength : 10,//默认每页显示多少条记录
    searching : true,//是否显示搜索框
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
$.dataTablesSettings.ajax ={
		url:"/account/listData.do",
		dataSrc:function(rsp){
			var data=new Array();
			for (var i=0, ien=rsp.length ; i<ien ; i++ ) {
				var obj=rsp[i];
				var arr=new Array();
				arr[0]=obj.id;
				arr[1]=obj.loginName;
				arr[2]=obj.role.name;
				arr[3]=obj.typeName;
				arr[4]=obj.statusName;
				arr[5]=new Date(obj.createTime).Format("yyyy-MM-dd hh:mm:ss");
				arr[6]=new Date(obj.loginTime).Format("yyyy-MM-dd hh:mm:ss")
				data.push(arr);
	        }
			return data;
		}
} 
function deleteTabRow(id){
	console.info(id);
}
$.dataTablesSettings.columnDefs = [{
    targets : [7],
    render : function(data, type, row) {
        return "<a title='编辑' class='glyphicon glyphicon-edit nounderline' href='/account/edit?id='"+row[0]+"></a>&nbsp;"  + 
               "<a title='删除' class='glyphicon glyphicon-trash nounderline' href='javascript:deleteTabRow(" + row[0] + ");'></a>";
    }
}];
var dataTable=$('#dataTable').DataTable($.dataTablesSettings);

Date.prototype.Format = function(fmt) { //author: meizz 
    var o = { 
        "M+": this.getMonth() + 1, 
        "d+": this.getDate(), 
        "h+": this.getHours(), 
        "m+": this.getMinutes(), 
        "s+": this.getSeconds(), 
        "q+": Math.floor((this.getMonth() + 3) / 3), 
        "S": this.getMilliseconds() //毫秒 
    }; 
    if (/(y+)/.test(fmt)) { 
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length)); 
    } 
    for (var k in o) { 
        if (new RegExp("(" + k + ")").test(fmt)) { 
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length))); 
        } 
    } 
    return fmt; 
} 
