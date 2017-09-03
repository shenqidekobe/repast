/** index.js By Beginner Emain:zheng_jinfan@126.com HomePage:http://www.zhengjinfan.cn */
layui.config({
	base: ctx+'/res/admin/js/'
}).use(['element', 'layer', 'navbar', 'tab'], function() {
	var element = layui.element(),
		$ = layui.jquery,
		layer = layui.layer,
		navbar = layui.navbar(),
		tab = layui.tab({
			elem: '.admin-nav-card' //设置选项卡容器
		});
	//iframe自适应
	$(window).on('resize', function() {
		var $content = $('.admin-nav-card .layui-tab-content');
		$content.height($(this).height() - 147);
		$content.find('iframe').each(function() {
			$(this).height($content.height());
		});
	}).resize();

	//设置navbar
	navbar.set({
		spreadOne: true,
		elem: '#admin-navbar-side',
		cached: true,
		/*data: navs*/
		cached:true,
		url: ctx+'/admin/navbar'
	});
	//渲染navbar
	navbar.render();
	//监听点击事件
	navbar.on('click(side)', function(data) {
		tab.tabAdd(data.field);
	});

	$('.admin-side-toggle').on('click', function() {
		var sideWidth = $('#admin-side').width();
		if(sideWidth === 200) {
			$('#admin-body').animate({
				left: '0'
			}); //admin-footer
			$('#admin-footer').animate({
				left: '0'
			});
			$('#admin-side').animate({
				width: '0'
			});
		} else {
			$('#admin-body').animate({
				left: '200px'
			});
			$('#admin-footer').animate({
				left: '200px'
			});
			$('#admin-side').animate({
				width: '200px'
			});
		}
	});
	//设置
	$('#setting').on('click', function() {
		setting($, layer);
	});

	//锁屏
	$(document).on('keydown', function() {
		var e = window.event;
		if(e.keyCode === 76 && e.altKey) {
			//alert("你按下了alt+l");
			lock($, layer);
		}
	});
	$('#lock').on('click', function() {
		lock($, layer);
	});

	//手机设备的简单适配
	var treeMobile = $('.site-tree-mobile'),
		shadeMobile = $('.site-mobile-shade');
	treeMobile.on('click', function() {
		$('body').addClass('site-mobile');
	});
	shadeMobile.on('click', function() {
		$('body').removeClass('site-mobile');
	});
});
function setting($, layer){
	var loading = layer.load();
	$.get(ctx+'/admin/setting', null, function(form) {
		layer.open({
			type: 1,
			title: '设置',
			content: form,
			area: ['480px', '330px'],
			maxmin: true,
			yes: function(index) {
				console.info(index);
			},
			success: function(layero, index){
				layer.close(loading);
			},
			end:function(){
			},
			full: function(elem) {
				var win = window.top === window.self ? window : parent.window;
				$(win).on('resize', function() {
					var $this = $(this);
					elem.width($this.width()).height($this.height()).css({
						top: 0,
						left: 0
					});
					elem.children('div.layui-layer-content').height($this.height() - 95);
				});
			}
		});
	});
}

function lock($, layer) {
	//自定页
	layer.open({
		title: false,
		type: 1,
		closeBtn: 0,
		anim: 6,
		content: $('#lock-temp').html(),
		shade: [0.9, '#393D49'],
		success: function(layero, lockIndex) {
			//屏蔽 Alt+ 方向键 ←/屏蔽 Alt+ 方向键 →/屏蔽退格删除键/屏蔽F5刷新键/屏蔽alt+R 
			$(document).keydown(function(event){
			     if ((event.altKey)&&((event.keyCode==37)||(event.keyCode==39))||event.keyCode==8
			    		 ||event.keyCode==116||((event.ctrlKey) && (event.keyCode==82))){ 
			        event.returnValue=false; 
			        return false;
			     }
			});
			var loginName=$("#lock").attr("data");
			//给显示用户名赋值
			layero.find('div#lockUserName').text(loginName);
			layero.find('input[name=lockPwd]').on('focus', function() {
					var $this = $(this);
					if($this.val() === '输入密码解锁..') {
						$this.val('').attr('type', 'password');
					}
				})
				.on('blur', function() {
					var $this = $(this);
					if($this.val() === '' || $this.length === 0) {
						$this.attr('type', 'text').val('输入密码解锁..');
					}
				});
			//在此处可以写一个请求到服务端删除相关身份认证，因为考虑到如果浏览器被强制刷新的时候，身份验证还存在的情况			
			//do something...
			//e.g. 
			/*
			 $.post(url,params,callback,'json');
			 */
			//绑定解锁按钮的点击事件
			layero.find('button#unlock').on('click', function() {
				var $lockBox = $('div#lock-box');

				var userName = $lockBox.find('div#lockUserName').text();
				var pwd = $lockBox.find('input[name=lockPwd]').val();
				if(pwd === '输入密码解锁..' || pwd.length === 0) {
					layer.msg('请输入密码..', {
						icon: 2,
						time: 1000
					});
					return;
				}
				unlock(userName, pwd);
			});
			/**
			 * 解锁操作方法
			 * @param {String} 用户名
			 * @param {String} 密码
			 */
			var unlock = function(un, pwd) {
				//这里可以使用ajax方法解锁
				$.post(ctx+'/admin/startLogin',{loginName:un,password:pwd},function(data){
					if("success"==data){
						//去除屏蔽的按键
						$(document).keydown(function(event){
						     if ((event.altKey)&&((event.keyCode==37)||(event.keyCode==39))||event.keyCode==8
						    		 ||event.keyCode==116||((event.ctrlKey) && (event.keyCode==82))){ 
						        event.returnValue=true; 
						        return true;
						     }
						});
						layer.close(lockIndex);
					}else{
						layer.msg('密码输入错误..',{icon:2,time:1000});
					}
				});
			};
		}
	});
};