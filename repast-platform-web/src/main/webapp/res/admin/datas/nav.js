var navs = [{
	"title": "快件管理",
	"icon": "&#xe631;",
	"spread": true,
	"children": [{
		"title": "快件处理",
		"icon": "&#xe61d;",
		"href": ctx+"/admin/express"
	}, {
		"title": "人员管理",
		"icon": "&#xe612;",
		"href": ctx+"/admin/emp"
	}, {
		"title": "理件分析",
		"icon": "&#xe629;",
		"href": ctx+"/admin/statistics/collecter"
	}, {
		"title": "派件分析",
		"icon": "&#xe62d;",
		"href": ctx+"/admin/statistics/sender"
	}]
},{
	"title": "系统设置",
	"icon": "&#xe614;",
	"spread": false,
	"children": [{
		"title": "系统用户",
		"icon": "&#xe612;",
		"href": ctx+"/admin/user"
	}, {
		"title": "角色管理",
		"icon": "&#xe63c;",
		"href":  ctx+"/admin/group"
	}, {
		"title": "菜单管理",
		"icon": "&#xe628;",
		"href":  ctx+"/admin/catalog"
	}, {
		"title": "系统日志",
		"icon": "&#xe60a;",
		"href":  ctx+"/admin/systemlog"
	}]
}];