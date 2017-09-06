$(function() {
	$.dataTablesSettings.ajax ={
			url:"/account/listData.do",
			dataSrc:function(rsp){
				var data=new Array();
				for ( var i=0, ien=rsp.length ; i<ien ; i++ ) {
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
	$.dataTablesSettings.columnDefs = [{
	    targets : [7],
	    data : "id",
	    render : function(data, type, row) {
	        return "<a title='编辑' class='glyphicon glyphicon-edit nounderline' href='javascript:editTabRow(" + data + ");'></a>&nbsp;"  + 
	               "<a title='删除' class='glyphicon glyphicon-trash nounderline' href='javascript:deleteTabRow(" + data + ");'></a>";
	    }
	}];
	var dataTable=$('#dataTable').DataTable($.dataTablesSettings);
});