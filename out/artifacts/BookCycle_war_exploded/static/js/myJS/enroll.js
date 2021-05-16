window.addEventListener("load", function () {
    let href = location.href;//获取或设置整个URL
    let index = href.indexOf("user");
    let newHref = href.substr(0, index);
    alert(newHref);

    $("#username").blur(function () {
        var username = this.value;
        $.post({
            url: newHref+"user/ajaxUsername",
            data: {"username": username},
            success: function (data) {
                if (data) {
                    $("span.errorMsg").text("用户名已存在！");
                } else {
                    $("span.errorMsg").text("用户名可用！");
                }
            }
        });
    });

    $("#email").blur(function () {
        var email = this.value;
        $.post({
            url: newHref+"user/ajaxEmail",
            data: {"email": email},
            success: function (data) {
                if (data) {
                    $("span.errorMsg").text("该邮箱已被注册！");
                } else {
                    $("span.errorMsg").text("该邮箱可用！");
                }
            }
        });
    });

    $("#code_img").click(function () {
        this.src = newHref+"kaptcha.jpg?date=" + new Date();
    })

    $("#sub_btn").click(function () {
        var username = $("#username").val();
        var usernamePatt = /^[(a-zA-Z0-9\u4e00-\u9fa5){1}_#]{2,12}$/;
        if (!usernamePatt.test(username)) {
            $("span.errorMsg").text("用户名不合法！");
            return false;
        }
        var password = $("#password").val();
        var passwordPatt = /^\w{6,16}$/;
        if (!passwordPatt.test(password)) {
            $("span.errorMsg").text("密码不合法！");
            return false;
        }
        var repwd = $("#repwd").val();
        if (password != repwd) {
            $("span.errorMsg").text("密码与确认密码不符！");
            return false;
        }
        var email = $("#email").val();
        var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
        if (!emailPatt.test(email)) {
            $("span.errorMsg").text("邮箱格式不匹配！");
            return false;
        }
        var code = $("#code").val();
        var code = $.trim(code);
        if (code == null || code == "") {
            $("span.errorMsg").text("验证码错误！");
            return false;
        }
        $("span.errorMsg").text("");
        let form = $("#enrollForm");
        form.submit();
    });
})