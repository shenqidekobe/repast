var myDate = new Date();
//nYear=myDate.getYear();        //获取当前年份(2位)
nYear=myDate.getFullYear();    //获取完整的年份(4位,1970-????)
nMoth=myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
nDate=myDate.getDate();        //获取当前日(1-31)
nDay=myDate.getDay();         //获取当前星期X(0-6,0代表星期天)
myDate.getTime();        //获取当前时间(从1970.1.1开始的毫秒数)
nhou=myDate.getHours();       //获取当前小时数(0-23)
nmin=myDate.getMinutes();     //获取当前分钟数(0-59)
nsec=myDate.getSeconds();     //获取当前秒数(0-59)
myDate.getMilliseconds();    //获取当前毫秒数(0-999)
ndat=myDate.toLocaleDateString();     //获取当前日期
var mytime=myDate.toLocaleTimeString();     //获取当前时间
myDate.toLocaleString( );        //获取日期与时间  

if(nMoth<10){nMoth='0'+nMoth}
if(nDate<10){nDate='0'+nDate}
if(nhou<10){nhou='0'+nhou}
if(nmin<10){nmin='0'+nmin}
nPdate=nDate-1;
nNdate=nDate+1;
uai=navigator.userAgent.toLowerCase();


Y={
	//计时器
	Stime:function(a){
		console.log('a:'+a)
		$(a).css('pointer-events','auto').attr('disabled',1).val('发送中...');
		var i=60;
		var k=setInterval(function(){
				if(i==0){
					$(a).val('重新发送').css('pointer-events','inherit').removeAttr('disabled');
					clearInterval(k);
				}else{
					i--;
					$(a).val(i+'秒');
				}
			},1E3); 
	},
	//手机号 
	rMob:function(a){
		return /^1[3|4|5|7|8]{1}[0-9]{9}$/.test(a);
	}
};

$(window).load(function(){
	
    //单选
    $(document).on('touchstart','.f-click-radio',function(){
    	var num=$(this).attr('data-num'),
    		show=$(this).attr('data-show');
    	
    	if(num==0){
    		if(show==0){
    			$(this).attr('data-show',1);
    			$(this).parents('.f-click-par').find('.f-click-radio').removeClass('none');
    		}else{
    			$(this).attr('data-show',0);
    			$(this).parents('.f-click-par').find('.f-click-radio:gt(10)').addClass('none');
    			$(this).removeClass('none');
    		}
    		
    	}else{
    			$(this).parents('.f-click-par').find('.f-click-radio').removeClass('on').end().end().addClass('on');
    	}
    });
    //数量+-
    $(document).on('touchstart','.f-click',function(){
    	var _this=$(this),
    		did=$(this).attr('data-id'),
    		num=$(this).siblings('.f-click-num').find('input');
    		console.log('did:'+did+';x:')
    		console.log($(this).siblings('.f-click-num').find('input').val())
    		switch (did){
    			case "del":
    				if(parseInt(num.val())>0){
    					num.val(parseInt(num.val())-1);
    				}
    				break;
    			case "add":
    				num.val(parseInt(num.val())+1);
    				break;
    			case "code":
	    			if($('#tel').val()==""){
						mui.toast('请先输入手机号');
						return false;
	    			}
	    			if(!Y.rMob($('#tel').val())){
	    				mui.toast('手机号格式有误');
						return false;
	    			}
    				_this.attr('disabled',true);
    				Y.Stime(_this);
    				break;
    			default:
    				break;
    		}
    });
    
    //监听
    $(document).on('input','.f-input',function(){
    	var _this=$(this),
    		did=$(this).attr('data-id'),
    		id=this.id;
    		switch (id){
    			case "tel":
    					if(_this.val().length==11){
    						$('#code').removeAttr('disabled');
    					}else{
    						$('#code').attr('disabled',true);
    					}
    				break;
    			default:
    				break;
    		}
    });
    try{
    	//轮播
	    var mySwiper1 = new Swiper('.swiper-container-1', {
			autoplay: 5000,
			pagination : '.swiper-pagination',
			paginationClickable :true
		})
    }catch(e){ }
    
   
});
