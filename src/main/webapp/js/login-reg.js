// 文本框变色
$(document).ready(function(){
	$('.input1').focus(function(){
		$(this).addClass('input1-on');
	});
	$('.input1').blur(function(){
		$(this).removeClass('input1-on');
	});
}); 