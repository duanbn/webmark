// 显示信息
var email_tip = "请填写邮箱";
var email_tip1 = "邮箱不存在";
var email_tip2 = "邮箱已经存在";
var email_tip3 = "请输入正确的邮箱地址";
var pwd1_tip = "请填写密码";
var pwd2_tip = "两次密码输入不一致哦";
var pwd2_tip1 = "请填写确认密码";

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

    /**
	 * 显示优夹列表
	 */
    $('.youjiabox').hide();
    $('.input-youjia').click(function(){
        $('.youjiabox').show();
        $.post('/favoriteclip/getFavoriteClip.json', function(resp) {
            if (resp.status == 'ok') {
                $('#youjia-list-item').empty();
                $.each(resp.data, function(i, clip) {
                    var item = '<li class="youjia-li';
                    if (clip.fc_isdefault) {
                        item += ' youjia-li-on">';
                    } else {
                        item += '">';
                    }
                    item += clip.fc_name + '</li>';
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
    });

    /**
	 * 输入优夹名称显示
	 */
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

    /**
	 * 输入优夹名称自动筛选.
	 */
    $('#input-add').keyup(function() {
        var input = $('#input-add').val();
        if ($.trim(input) != '') {
            $.post('/favoriteclip/getFavoriteClipByNamePrefix.json', {"name":input}, function(resp) {
                if (resp.status == 'ok') {
                    $('#youjia-list-item').empty();
                    $.each(resp.data, function(i, clip) {
                        var item = '<li class="youjia-li';
                        if (clip.fc_isdefault) {
                            item += ' youjia-li-on">';
                        } else {
                            item += '">';
                        }
                        item += clip.fc_name + '</li>';
                        $('#youjia-list-item').append(item);
                    });
                    $('#youjia-list-item li').click(function() {
                        $('.input-youjia').html(this.innerHTML + '<div class="arrow"></div>');
                        $('.youjiabox').hide();
                    });
                } else {
                    alert(resp.message);
                }
            }, 'json');
        } else {
            $.post('/favoriteclip/getFavoriteClip.json', function(resp) {
            if (resp.status == 'ok') {
                $('#youjia-list-item').empty();
                $.each(resp.data, function(i, clip) {
                    var item = '<li class="youjia-li';
                    if (clip.fc_isdefault) {
                        item += ' youjia-li-on">';
                    } else {
                        item += '">';
                    }
                    item += clip.fc_name + '</li>';
                    $('#youjia-list-item').append(item);
                });
                $('#youjia-list-item li').click(function() {
                    $('.input-youjia').html(this.innerHTML + '<div class="arrow"></div>');
                    $('.youjiabox').hide();
                });
            }
        }, 'json');
        }
    });

    /**
	 * 添加优夹
	 */
    $('#btn-add').click(function() {
        var clipName = $('#input-add').val();
        $.post("/favoriteclip/add.json", {"name":clipName}, function(resp) {
            if (resp.status == 'ok') {
                $('.input-youjia').html(clipName + '<div class="arrow"></div>');
                $('.youjiabox').hide();
                inputAdd.val(displayVal);
            } else {
                alert(resp.message);
            }
        }, "json");
    });

    /**
	 * 添加收录
	 */
    $('#btn-shoulu').click(function() {
        var screenshot = $('#screenshot').attr('screenshot');
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

        var param = {"clipName":clipName, "title":title, "url":url, "desc":desc, "screenshot":screenshot};
        $.post('/favorite/add.json', param, function(resp) {
            if (resp.status == 'ok') {
                alert("收录成功 :)");
                window.close();
            } else {
                alert(resp.message);
            }
        }, 'json');
    });
    
    /**
	 * 收录框登录操作.
	 */
    $('#btn-login').click(function () {
        var email = $('#login-email').val();
        if ($.trim(email) == '') {
            $('#email_tip').addClass('span2-error').html(email_tip);
            return;
        }
        var pwd = $('#login-pwd').val();
        if ($.trim(pwd) == '') {
            $('#password_tip').addClass('span2-error').html(pwd1_tip);
            return;
        }
        var autoLogin = $('#autologin').attr("checked") ? "on" : "off";
        $.post('/mark/dologin.json', {"email":email, "password":pwd, "autologin":autoLogin}, function(resp) {
            if (resp.status == 'ok') {
            	var title = resp.data.title;
            	var url = resp.data.url;
            	var desc = resp.data.desc;
            	var keywords = resp.data.keywords;
            	document.location.href = "http://localhost:8080/mark/showdlg.do?title=" + title + "&url=" + url + "&keyword=" + keywords + "&desc=" + desc;
            } else {
                if (resp.code == 'b1006') {
                    $('#password_tip').addClass('span2-error').html(resp.message);
                } else if (resp.code == 'b1005') {
                    $('#email_tip').addClass('span2-error').html(resp.message);
                }
            }
        }, 'json');
    });

    /**
     * 收录框注册
     */
    $('#btn-reg').click(function() {
        var email = $.trim($("#reg-email").val());
        if (email == "") {
            $("#email_tip").removeClass("span2").addClass("span2-error").text(email_tip);
            return;
        } else {
            var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
            if (!reg.test(email)) {
                $("#email_tip").removeClass("span2").addClass("span2-error").text(email_tip3);
                return;
            }

        }
        var password = $('#reg-pwd1').val();
        if ($.trim($("#reg-pwd1").val()) == "") {
            $("#pwd1_tip").removeClass("span2").addClass("span2-error").text(pwd1_tip).show().show();
            return;
        }
        if ($.trim($("#reg-pwd2").val()) == "") {
            $("#pwd2_tip").removeClass("span2").addClass("span2-error").text(pwd2_tip1).show();
            return;
        }

        if ($.trim($("#reg-pwd1").val()) != $.trim($("#reg-pwd2").val())) {
            $("#pwd2_tip").text(pwd2_tip);
            return;
        }

        $.post('/mark/reg.json', {"email":email, "password":password}, function(resp) {
            if (resp.status == 'ok') {
                var title = resp.data.title;
            	var url = resp.data.url;
            	var desc = resp.data.desc;
            	var keywords = resp.data.keywords;
            	document.location.href = "http://localhost:8080/mark/showdlg.do?title=" + title + "&url=" + url + "&keyword=" + keywords + "&desc=" + desc;
            } else if (resp.code == 'b1003') {
                $("#corrent_img").hide();
                $("#email_tip").removeClass("span2").addClass("span2-error").text(email_tip2).show();
            } else if (resp.code == 'b1001') {
                alert(resp.message);
            } else if (resp.code == 'b5001') {
                alert(resp.message);
            }
        }, 'json');
    });
}); 
