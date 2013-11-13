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

    var inputAdd = $('.input-add');
    var displayVal = '快速筛选/创建优夹';
    inputAdd.val(displayVal);
    inputAdd.focus(function() {
        if (inputAdd.val() == displayVal) {
            inputAdd.val('');
        }
    });
    inputAdd.blur(function() {
        if ($.trim(inputAdd.val()) == '') {
            inputAdd.val(displayVal);
        }
    });

    $('#btn-add').click(function() {
        var clipName = $('#input-add').val();
        $.post("/favoriteclip/add.json", {"name":clipName}, function(result) {
            if (result == 'OK') {
                $('.input-youjia').text(clipName + '<div class="arrow"></div>');
                $('.youjiabox').hide();
            }
        }, "json");
    });
}); 
