
var email_tip = "请填写邮箱";
var email_tip1 = "邮箱不存在";
var email_tip2 = "邮箱已经存在";
var email_tip3 = "请输入正确的邮箱地址";
var pwd1_tip = "请填写密码";
var pwd2_tip = "两次密码输入不一致哦";
var pwd2_tip1 = "请填写确认密码";

/**
 * 检查用户邮箱
 */
function checkEmail(email, opt) {
	if ($.trim(email) == '') {
		$("#email_tip").removeClass("span2").addClass("span2-error").text(email_tip);
		return;
	} else {
        var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        if(!reg.test(email)) {
            $("#email_tip").removeClass("span2").addClass("span2-error").text(email_tip3);
            return;
        }
    }
	
	$.post("/user/checkemail.json", {"email":email}, function(isExist) {
		if (!isExist) {
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

function checkForm() {
    var isOk = true;

    var email = $.trim($("#reg-email").val());
    if (email == "") {
        $("#email_tip").removeClass("span2").addClass("span2-error").text(email_tip);
        isOk = false;
    } else {
        var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        if(!reg.test(email)) {
            $("#email_tip").removeClass("span2").addClass("span2-error").text(email_tip3);
            isOk = false;
        }
    }
    if ($.trim($("#reg-pwd1").val()) == "") {
        $("#pwd1_tip").removeClass("span2").addClass("span2-error").text(pwd1_tip).show().show();
    }
    if ($.trim($("#reg-pwd2").val()) == "") {
        $("#pwd2_tip").removeClass("span2").addClass("span2-error").text(pwd2_tip1).show();
            isOk = false;
    }

    if ($.trim($("#reg-pwd1").val()) != $.trim($("#reg-pwd2").val())) {
        $("#pwd2_tip").text(pwd2_tip);
        isOk = false;
    }

    return isOk;
}