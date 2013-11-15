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
        $.post('/favoriteclip/getFavoriteClip.json', function(resp) {
            if (resp.status == 'ok') {
                $.each(resp.data, function(i, clip) {
                    var item = '<li class="youjia-li';
                    if (clip.default) {
                        item += ' youjia-li-on">';
                    } else {
                        item += '">';
                    }
                    item += clip.name + '</li>';
                    $('#youjia-list-item').append(item);
                });
                $('#youjia-list-item li').click(function() {
                    $('.input-youjia').html(this.innerHTML + '<div class="arrow"></div>');
                    $('.youjiabox').hide();
                });
            }
        }, 'json');
    });
    $('.youjiabox').mouseleave(function(){
        $('.youjiabox').hide();
        $('#youjia-list-item').empty();
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
        $.post("/favoriteclip/add.json", {"name":clipName}, function(resp) {
            if (resp.status == 'ok') {
                $('.input-youjia').html(clipName + '<div class="arrow"></div>');
                $('.youjiabox').hide();
                inputAdd.val(displayVal);
            }
        }, "json");
    });

    $('#btn-shoulu').click(function() {
        var clipName = $('.input-youjia').text();
        var url = $('#input-url').val();
        var title = $('#input-tit').val();
        var desc = $('#input-des').val();

        if ($.trim(title) == '') {
            alert("请给你的收录加一个标题");
            return;
        }
        if ($.trim(url) == '') {
            alert("url不能为空");
            return;
        }

        var param = {"clipName":clipName, "title":title, "url":url, "desc":desc};
        $.post('/favorite/add.json', param, function(resp) {
            if (resp.status == 'ok') {
                window.close();
            } else {
                alert(resp);
            }
        }, 'json');
    });
}); 
