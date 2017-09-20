function selectPicker (opts) {
	 opts = jQuery.extend({
         data: null,
         buttonId:null,
         textId: null,
         valueId: null,
         layer:1,
         callback: function () {}
     }, opts || {});
	 (function($, doc,opts) {
		$.init();
		$.ready(function() {
			//业务类型显示框
			var cityPicker = new $.PopPicker({
				layer : opts.layer
			});
			cityPicker.setData(opts.data);
			var showCityPickerButton = doc.getElementById(opts.buttonId);
			var showCityPickerButton1 = doc.getElementById(opts.textId);
			var cityResult = doc.getElementById(opts.textId);
			//setting default value
			var defaultVal=doc.getElementById(opts.valueId).value;
			if(defaultVal!=""){
				var arrs=defaultVal.split("-");
				for(var i=0;i<opts.layer;i++){
					cityPicker.pickers[i].setSelectedValue(arrs[i]);
				}
			}
			showCityPickerButton.addEventListener('tap', function(event) {
				cityPicker.show(function(items) {
					var text="";
					for(var i=0;i<opts.layer;i++){
						if(text==""){
							text=items[i].text;
						}else{
							text=text+"-"+items[i].text;
						}
					}
					cityResult.innerText = text;
					cityResult.style.color = '#000';
					//返回 false 可以阻止选择框的关闭
					//return false;
					doc.getElementById(opts.valueId).value=items[0].value;
					//cityPicker.dispose();
					opts.callback();
				});
			}, false);
			showCityPickerButton1.addEventListener('tap', function(event) {
				cityPicker.show(function(items) {
					var text="";
					for(var i=0;i<opts.layer;i++){
						if(text==""){
							text=items[i].text;
						}else{
							text=text+"-"+items[i].text;
						}
					}
					cityResult.innerText = text;
					cityResult.style.color = '#000';
					//返回 false 可以阻止选择框的关闭
					//return false;
					doc.getElementById(opts.valueId).value=text;
					//cityPicker.dispose();
				});
			}, false);
		});
	})(mui, document,opts);
}
var mtPicker;
function selectDtPicker (opts) {
	if(opts.hide&&mtPicker){
		mtPicker.hide();
		return;
	}
    opts = jQuery.extend({
        data: null,
        buttonId:null,
        textId: null,
        valueId: null,
        defaultValue:null,
        callback: function () {}
    }, opts || {});
	 (function($, doc,opts) {
		$.init();
		$.ready(function() {
			//业务类型显示框
			mtPicker = new $.DtPicker({
				 type: "date",//设置日历初始视图模式 
				 beginDate: new Date(2017, 09, 01),//设置开始日期 
				 endDate: new Date(2020, 12, 30),//设置结束日期 
				 labels: ['年', '月', '日'],//设置默认标签区域提示语 
				 value:opts.defaultValue
			});
			var showCityPickerButton = doc.getElementById(opts.buttonId);
			var showCityPickerButton1 = doc.getElementById(opts.textId);
			var cityResult = doc.getElementById(opts.textId);
			//setting default value
			var defaultVal=doc.getElementById(opts.valueId).value;
			if(defaultVal!=""){
				var arrs=defaultVal.split("-");
				for(var i=0;i<opts.layer;i++){
					mtPicker.pickers[i].setSelectedValue(arrs[i]);
				}
			}
			showCityPickerButton.addEventListener('tap', function(event) {
				mtPicker.show(function(items) {
					var text=items.text;
					cityResult.innerText = text;
					cityResult.style.color = '#000';
					//返回 false 可以阻止选择框的关闭
					//return false;
					doc.getElementById(opts.valueId).value=text;
					//cityPicker.dispose();
				});
			}, false);
			showCityPickerButton1.addEventListener('tap', function(event) {
				mtPicker.show(function(items) {
					var text=items.text;
					cityResult.innerText = text;
					cityResult.style.color = '#000';
					//返回 false 可以阻止选择框的关闭
					//return false;
					doc.getElementById(opts.valueId).value=text;
					//cityPicker.dispose();
				});
			}, false);
		});
	})(mui, document,opts);
}

