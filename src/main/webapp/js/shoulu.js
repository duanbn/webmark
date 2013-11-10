// 收录框文本框变色
$(document).ready(function(){
	$('.input-url').val("http://veromoda.tmall.com/campaign.htm?spm=3.68707it7m/campaign.htm?spm=3.68707it7");
	$('.input-url').focus(function(){
		$(this).addClass('input-url-on');
	});
	$('.input-url').blur(function(){
		$(this).removeClass('input-url-on');
	});
}); 

$(document).ready(function(){
	$('.input-tit').val("FAN你的时尚顾问-YOKA时尚网");
	$('.input-tit').focus(function(){
		$(this).addClass('input-tit-on');
	});
	$('.input-tit').blur(function(){
		$(this).removeClass('input-tit-on');
	});
}); 

$(document).ready(function(){
	$('.input-des').focus(function(){
		$(this).addClass('input-des-on');
	});
	$('.input-des').blur(function(){
		$(this).removeClass('input-des-on');
	});
}); 

$(document).ready(function(){
	$('.youjiabox').hide();
	$('.input-youjia').click(function(){
		$('.youjiabox').show();
	});
	$('.youjiabox').mouseout(function(){
		$('.youjiabox').hide();
	});
}); 


$(document).ready(function(){
	$('.input-add').val("快速筛选/创建优夹");
}); 

