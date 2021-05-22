window.addEventListener("load", function () {
    let href = location.href;//获取或设置整个URL
    let index = href.indexOf("user");
    let newHref = href.substr(0, index);
    $("#username").change(function () {
        var username = $(this).val();
        let oldUsername = $(this).data("username");
        var usernamePatt = /^[(a-zA-Z0-9\u4e00-\u9fa5){1}_#]{2,12}$/;
        if (username === oldUsername) {
            return false;
        }
        $("span.errorUsername").text("");
        if (!usernamePatt.test(username)) {
            $("#username").val(oldUsername);
            $("span.errorUsername").text("用户名[" + username + "]不合法！");
            return false;
        }
        $.post({
            url: newHref + "user/ajaxUsername",
            data: {"username": username},
            success: function (data) {
                if (data) {
                    $("#username").val(oldUsername);
                    $("span.errorUsername").text("用户名[" + username + "]已存在！");
                } else {
                    $("span.errorUsername").text("用户名可用！");
                }
            }
        });
    });

    $("#email").change(function () {
        let email = $(this).val();
        let oldEmail = $(this).data("email");
        var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
        if (email === oldEmail) {
            return false;
        }
        $("span.errorEmail").text("");
        if (!emailPatt.test(email)) {
            $("#email").val(oldEmail);
            $("span.errorEmail").text("邮箱[" + email + "]格式不匹配！");
            return false;
        }
        $.post({
            url: newHref + "user/ajaxEmail",
            data: {"email": email},
            success: function (data) {
                if (data) {
                    $("#email").val(oldEmail)
                    $("span.errorEmail").text("该邮箱[" + email + "]已被注册！");
                } else {
                    $("span.errorEmail").text("该邮箱可用！");
                }
            }
        });
    });

    $("#save").click(function () {
        let username = $.trim($("#username").val());
        let email = $.trim($("#email").val());
        let address = $.trim($("#address").val());
        let oldUsername = $(this).data("username");
        let oldEmail = $(this).data("email");
        if (username === oldUsername && email === oldEmail) {
            alert("提交的用户信息与原信息相同！");
            return false;
        }
        if (confirm("你确定保存信息吗？")) {
            $.post({
                url: newHref + "user/updateUser",
                data: {"username": username, "email": email, "address": address},
                success: function (data) {
                    if (data) {
                        alert("修改成功！");
                    } else {
                        alert("修改失败！");
                    }
                }
            });
        }
    });

    $("#updatePassword").click(function () {
        var userId = $(this).data("user-id");
        let oldPassword = $("#oldPassword").val();
        let newPassword = $("#newPassword").val();
        var passwordPatt = /^\w{6,16}$/;
        $("span.errorOld").text("");
        $("span.errorNew").text("");
        $("span.errorConfirm").text("");
        $("span.errorUpdate").text("");
        if (!passwordPatt.test(newPassword)) {
            $("span.errorNew").text("密码不合法！");
            return false;
        }
        var confirm = $("#confirm").val();
        if (newPassword !== confirm) {
            $("span.errorConfirm").text("密码与确认密码不符！");
            return false;
        }
        $.post({
            url: newHref+"user/ajaxUpdatePassword",
            data: {"userId": userId, "oldPassword": oldPassword, "newPassword": newPassword},
            success: function (data) {
                if (data) {
                    $("span.errorUpdate").text("修改成功！");
                } else {
                    $("span.errorOld").text("密码错误！");
                }
            }
        });
    })

})

