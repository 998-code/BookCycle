    //
    // // 页面加载完成之后
    // $(function () {
    //     // 给注册绑定单击事件
    //     $("#sub_btn").click(function () {
    //         // 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
    //         //1 获取用户名输入框里的内容
    //         var username = $("#username").val();
    //         //2 创建正则表达式对象
    //         var usernamePatt=/^\w{5,12}$/;
    //         //3 使用test方法验证
    //         if(!usernamePatt.test(username)){
    //             //4 提示用户结果
    //             $("span.errorMsg").text("用户名不合法！");
    //             return false;
    //         }
    //
    //
    //         // 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
    //         //1 获取用户名输入框里的内容
    //         var password = $("#password").val();
    //         //2 创建正则表达式对象
    //         var passwordPatt=/^\w{6,12}$/;
    //         //3 使用test方法验证
    //         if(!passwordPatt.test(password)){
    //             //4 提示用户结果
    //             $("span.errorMsg").text("密码不合法！");
    //             return false;
    //         }
    //
    //
    //
    //
    //         // 验证确认密码：和密码相同
    //         //1 获取确认密码内容
    //         var repwd = $("#repwd").val();
    //         //2 和密码相比较
    //         if(password!=repwd){
    //             //3 提示用户
    //             $("span.errorMsg").text("密码与确认密码不符！");
    //             return false;
    //         }
    //
    //
    //
    //         // 邮箱验证：xxxxx@xxx.com
    //         //1 获取邮箱里的内容
    //         var email = $("#email").val();
    //         //2 创建正则表达式对象
    //         var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
    //         //3 使用test方法验证是否合法
    //         if(!emailPatt.test(email)){
    //             //4 提示用户
    //             $("span.errorMsg").text("邮箱格式不匹配！");
    //             return false;
    //         }
    //
    //
    //         // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
    //         var code = $("#code").val();
    //
    //         //去掉验证码前后空格
    //         // alert("去空格前：["+codeText+"]")
    //         var code = $.trim(code);
    //         // alert("去空格后：["+codeText+"]")
    //
    //         if(code==null||code==""){
    //             //4 提示用户
    //             $("span.errorMsg").text("验证码错误！");
    //             return false;
    //         }
    //
    //
    //         // 去掉错误信息
    //         $("span.errorMsg").text("");
    //
    //     });
    //
    // });
