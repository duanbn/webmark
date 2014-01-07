$(document).ready(function(){

// 头部导航添加按钮
	
	$('.header-nav-add').click(function() {  
		var showDiv = $('.nav-add-menu');
		if (showDiv.is(":hidden")) {
 			showDiv.show();
			$('body').one('click', function() {
				showDiv.hide();
			});  
			return false;
		}  
		else {  
			showDiv.hide(); 
			$('body').one('click', function() { 
				showDiv.hide();
			});  
			return false;       
		}  
	});  
	$('.nav-add-menu').click(function() {
		return false;  
	});
	
// 头部导航用户按钮
	$('.header-nav-user').hover(function(){
		$('.nav-user-menu').show();	
	});
	$('.nav-user-menu').mouseleave(function(){
		$(this).hide();	
	});

// 关注人&取消关注人

	$('.followuser').toggle(function(){
		var _this = $(this);
		$(_this).removeClass('a-btn1');
		$(_this).addClass('a-btn3');
		$(_this).text('取消关注');
	},function(){
		var _this = $(this);
		$(_this).removeClass('a-btn3');
		$(_this).addClass('a-btn1');
		$(_this).text('关注');
	});
	
// 鼠标滑过出现举报用户按钮

	$('.user-card').mouseover(function(){
		$('.wrapper2').show();
	});
	$('.user-card').mouseleave(function(){
		$('.wrapper2').hide();
	});
	
// 滑块导航

	$('#topmenu').lavaLamp({
		fx: "backout", 
		speed: 700,
		click: function(event, menuItem) {
			return false;
		}
	});	

// 搜索
	
	$('.search-txt').focus(function(){
		$(this).addClass('search-txt-on');
	});
	$('.search-txt').blur(function(){
		$(this).removeClass('search-txt-on');
	});
	
// 关注优夹&取消关注优夹

	$('.followyj').toggle(function(){
		var _this = $(this);
		$(_this).removeClass('a-btn4');
		$(_this).addClass('a-btn3');
		$(_this).text('取消关注');
	},function(){
		var _this = $(this);
		$(_this).removeClass('a-btn3');
		$(_this).addClass('a-btn4');
		$(_this).text('关注');
	});


});

	
