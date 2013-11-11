// 收录框文本框变色
$(document).ready(function(){
	$('.input-url').focus(function(){
		$(this).addClass('input-url-on');
	});
	$('.input-url').blur(function(){
		$(this).removeClass('input-url-on');
	});

    $('.input-tit').focus(function(){
		$(this).addClass('input-tit-on');
	});
	$('.input-tit').blur(function(){
		$(this).removeClass('input-tit-on');
	});

    $('.input-des').focus(function(){
		$(this).addClass('input-des-on');
	});
	$('.input-des').blur(function(){
		$(this).removeClass('input-des-on');
	});

    $('.youjiabox').hide();
	$('.input-youjia').click(function(){
		$('.youjiabox').show();
	});
	$('.youjiabox').mouseleave(function(){
		$('.youjiabox').hide();
	});

    $('.input-add').val("快速筛选/创建优夹");
}); 
