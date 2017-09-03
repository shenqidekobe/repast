layui.define(['layer'], function(exports) {
	"use strict";

	var $ = layui.jquery,
		layer = layui.layer;

	var reuse = {
		/**
		 * 表单icheck复用代码
		 * */
		tableCheck:function(){
			$('input').iCheck({
				checkboxClass: 'icheckbox_flat-green'
			});
			$('.site-table tbody tr').on('click', function(event) {
				var $this = $(this);
				var $input = $this.children('td').eq(0).find('input');
				$input.on('ifChecked', function(e) {
					$this.css('background-color', '#EEEEEE');
				});
				$input.on('ifUnchecked', function(e) {
					$this.removeAttr('style');
				});
				$input.iCheck('toggle');
			}).find('input').each(function() {
				var $this = $(this);
				$this.on('ifChecked', function(e) {
					$this.parents('tr').css('background-color', '#EEEEEE');
				});
				$this.on('ifUnchecked', function(e) {
					$this.parents('tr').removeAttr('style');
				});
			});
			$('#selected-all').on('ifChanged', function(event) {
				var $input = $('.site-table tbody tr td').find('input');
				$input.iCheck(event.currentTarget.checked ? 'check' : 'uncheck');
			});
		},
	     //加载省数据
		loadProvince :function ($form,form) {
			var areaData = Area;
            var proHtml = '';
            for (var i = 0; i < areaData.length; i++) {
                proHtml += '<option value="' + areaData[i].provinceName + '">' + areaData[i].provinceName + '</option>';
            }
            //初始化省数据
            $form.find('select[name=province]').append(proHtml);
            form.render();
            form.on('select(province)', function(data) {
                $form.find('select[name=area]').html('<option value="">请选择县/区</option>').parent().hide();
                var value = data.value;
                var d = value.split('_');
                var code = d[0];
                var count = d[1];
                var index = d[2];
                if (count > 0) {
                	reuse.loadCity($form,form,areaData[index].mallCityList);
                } else {
                    $form.find('select[name=city]').parent().hide();
                }
            });
        },
         //加载市数据
        loadCity:function($form,form,citys) {
            var cityHtml = '';
            for (var i = 0; i < citys.length; i++) {
                cityHtml += '<option value="' + cityName + '">' + citys[i].cityName + '</option>';
            }
            $form.find('select[name=city]').html(cityHtml).parent().show();
            form.render();
            //是否展示县
            /*form.on('select(city)', function(data) {
                var value = data.value;
                var d = value.split('_');
                var code = d[0];
                var count = d[1];
                var index = d[2];
                if (count > 0) {
                    reuse.loadArea(citys[index].mallAreaList);
                } else {
                    $form.find('select[name=area]').parent().hide();
                }
            });*/
        },
         //加载县/区数据
        loadArea:function(areas) {
            var areaHtml = '';
            for (var i = 0; i < areas.length; i++) {
                areaHtml += '<option value="' + areas[i].areaName + '">' + areas[i].areaName + '</option>';
            }
            $form.find('select[name=area]').html(areaHtml).parent().show();
            form.render();
            form.on('select(area)', function(data) {
                //console.log(data);
            });
        },
        /**
         * 点击图片查看大图
         * */
        lookBigImg:function(sourceSrc){
        	$("<img/>").attr("src", sourceSrc).load(function(){
				var windowW = $(window).width();
				var windowH = $(window).height();
				var w = this.width;
				var h = this.height;
				if(w>=windowW) {
					w=windowW-80;
				} if(h>=windowH) {
					h=windowH-100;
				} 
				layer.open({
					type: 1,
					title: '查看原图',
					skin: 'layui-layer-lan',
					content: '<img src="'+sourceSrc+'">',
					area: [w, h],
					maxmin: true,
					shadeClose: true,//开启遮罩关闭
					yes: function(index) {
						console.info(index);
					},
					success: function(layero, index){
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
        },
		/**
		 * 自定义Map
		 * */
		Map : function () {
			var struct = function(key, value) {
				this.key = key;
				this.value = value;
			};

			var put = function(key, value) {
				for ( var i = 0; i < this.arr.length; i++) {
					if (this.arr[i].key == key) {
						this.arr[i].value = value;
						return;
					}
				}
				this.arr[this.arr.length] = new struct(key, value);
			};

			var get = function(key) {
				for ( var i = 0; i < this.arr.length; i++) {
					if (this.arr[i].key == key) {
						return this.arr[i].value;
					}
				}
				return null;
			};

			var remove = function(key) {
				var v;
				for ( var i = 0; i < this.arr.length; i++) {
					v = this.arr.pop();
					if (v.key == key) {
						continue;
					}
					this.arr.unshift(v);
				}
			};

			var size = function() {
				return this.arr.length;
			};

			var isEmpty = function() {
				return this.arr.length <= 0;
			};

			this.arr = new Array();
			this.get = get;
			this.put = put;
			this.remove = remove;
			this.size = size;
			this.isEmpty = isEmpty;
		}
	};

	exports('reuse', reuse);
});