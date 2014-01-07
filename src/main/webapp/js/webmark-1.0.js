// 显示信息
var email_tip = "请填写邮箱";
var email_tip1 = "邮箱不存在";
var email_tip2 = "邮箱已经存在";
var email_tip3 = "请输入正确的邮箱地址";
var pwd1_tip = "请填写密码";
var pwd2_tip = "两次密码输入不一致哦";
var pwd2_tip1 = "请填写确认密码";

function doLogout() {
    $.post('/user/dologout.json', function(data) {
        if (data.status == 'ok') {
            window.location.href = '/view/login.html';
        } else {
            alert(data.message);
        }
    }, 'json')
}

function doLogin() {
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
    $.post('/user/dologin.json', {"email":email, "password":pwd, "autologin":autoLogin}, function(data) {
        if (data.status == 'ok') {
            window.location.href="/user/main.do";
        } else {
            if (data.code == 'b1006') {
                $('#password_tip').addClass('span2-error').html(data.message);
            } else if (data.code == 'b1005') {
                $('#email_tip').addClass('span2-error').html(data.message);
            }
        }
    }, 'json');
}

/**
 * 检查用户邮箱
 */
function checkEmail(email, opt) {
	if ($.trim(email) == '') {
		$("#email_tip").removeClass("span2").addClass("span2-error").text(email_tip);
		return;
	} else {
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if (!reg.test(email)) {
			$("#email_tip").removeClass("span2").addClass("span2-error").text(email_tip3);
			return;
		}
	}

	$.post("/user/checkemail.json", {
		"email" : email
	}, function(resp) {
		if (resp.status == 'ok' && !resp.data) {
			if (opt == 'login') {
				$("#email_tip").removeClass("span2").addClass("span2-error").text(email_tip1);
			}
			if (opt == 'reg') {
				$("#corrent_img").show();
				$("#email_tip").hide();
			}
		} else {
			if (opt == 'login') {
				$("#email_tip").removeClass("span2-error").addClass("span2").text("");
			}
			if (opt == 'reg') {
				$("#corrent_img").hide();
				$("#email_tip").removeClass("span2").addClass("span2-error").text(email_tip2).show();
			}
		}
	}, "json");
}

function pwd1_blur(pwd1) {
	if ($.trim(pwd1) == "") {
		$("#pwd1_tip").removeClass("span2").addClass("span2-error").text(pwd1_tip).show().show();
	} else {
		$("#pwd1_tip").hide();
	}
}
function pwd2_blur(pwd2) {
	if ($.trim(pwd2) == "") {
		$("#pwd2_tip").removeClass("span2").addClass("span2-error").text(pwd2_tip1).show();
		return;
	} else if ($.trim($("#reg-pwd1").val()) != $.trim($("#reg-pwd2").val())) {
		$("#pwd2_tip").removeClass("span2").addClass("span2-error").text(pwd2_tip).show();
		return;
	} else {
		$("#pwd2_tip").hide();
	}
}

function doReg() {
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

    $.post('/user/reg.json', {"email":email, "password":password}, function(data) {
        if (data.status == 'ok') {
            window.location.href = "/user/main.do";
        } else if (data.code == 'b1003') {
            $("#corrent_img").hide();
            $("#email_tip").removeClass("span2").addClass("span2-error").text(email_tip2).show();
        } else if (data.code == 'b1001') {
            alert(data.message);
        } else if (data.code == 'b5001') {
            alert(data.message);
        }
    }, 'json');
}
