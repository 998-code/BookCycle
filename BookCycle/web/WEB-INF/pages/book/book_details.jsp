<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>${requestScope.book.name}</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/bookimg1.jpg" type="image/x-icon"/>
    <%@include file="../common/head.jsp" %>
    <script>
        $(function () {
            let href = location.href;//获取或设置整个URL
            let index = href.indexOf("book");
            let newHref = href.substr(0, index);

            $("#addBookList").click(function () {
                let bookId = $(this).data("book-id");
                let bookName = $.trim($("#bookName").html());
                let totalCount;
                $.get({
                    url:newHref+"cart/addItem",
                    data:{"bookId":bookId},
                    success:function (data) {
                        switch (data) {
                            case 0:
                                $(".book-alert").html("已将《"+bookName+"》添加到书单！").addClass("book-alert-success").show().delay(2500).fadeOut();
                                break;
                            case 1:
                                $(".book-alert").html("图书《"+bookName+"》添加出错，图书可能不存在！").addClass("book-alert-warning").show().delay(2500).fadeOut();
                                break;
                            case 2:
                                $(".book-alert").html("图书《"+bookName+"》已添加到书单！").addClass("book-alert-warning").show().delay(2500).fadeOut();
                                break;
                            case 3:
                                totalCount=${empty sessionScope.cart.totalCount ? 5:sessionScope.cart.totalCount};
                                $(".book-alert").html("您最多只能添加"+totalCount+"本书！").addClass("book-alert-danger").show().delay(5000).fadeOut();
                                break;
                        }
                    }
                });
            });

            $("#borrowNow").click(function () {
                let id = $(this).data("book-id");
                let bookName = $(this).data("book-name");
                let points = $(this).data("points");
                let userId = $("#userHome").data("user-id");
                if(userId==""||userId.length==0){
                    $(".book-alert").html("您还没有登录，请先登录！").addClass("book-alert-warning").show().delay(5000).fadeOut();
                    return false;
                }
                let bookId = new Array();
                let bookCount=new Array();
                bookId.push(id);
                bookId.push(points);
                bookCount.push(1);
                bookCount.push(1);
                $.get({
                    url:newHref+"bookList/borrowNow",
                    data: {"userId":userId,"bookId":bookId.toString(),"bookCount":bookCount.toString()},
                    success:function (data) {
                        if(data=="bookListId"){
                            $(".book-alert").html("图书《"+bookName+"》全部被借阅，您预约下次借阅！").addClass("book-alert-warning").show().delay(2500).fadeOut();
                        }else {
                            $(".book-alert").html("已为您生成订单号：" + data + "<br>您可以在个人主页查看详情！").addClass("book-alert-success").show().delay(2500).fadeOut();
                        }
                    }
                })
            });

            $("#reservationBook").click(function () {
                let bookId = $(this).attr("name");
                alert(bookId);
            });

        });
        window.onload = function () {
            conversionTime();
        }

        function conversionTime() {
            let time = $(".date").html();
            let date = new Date(time);
            let year = date.getFullYear();
            let month = date.getMonth() + 1;
            month = month < 10 ? "0" + month : month;
            let dates = date.getDate();
            dates = dates < 10 ? "0" + dates : dates;
            var s = year + " - " + month + " - " + dates;
            $(".date").html(s);
        }
    </script>
    <style>
        .book-alert {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            min-width: 200px;
            margin-left: -100px;
            z-index: 99999;
            padding: 15px;
            border: 1px solid transparent;
            border-radius: 4px;
        }
        .book-alert-success {
            color: #3c763d;
            background-color: #dff0d8;
            border-color: #d6e9c6;
        }
        .book-alert-info {
            color: #31708f;
            background-color: #d9edf7;
            border-color: #bce8f1;
        }
        .book-alert-warning {
            color: #8a6d3b;
            background-color: #fcf8e3;
            border-color: #faebcc;
        }
        .book-alert-danger {
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
        }
    </style>
</head>
<body>
<div class="book-alert"></div>
<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small><a class="btn btn-primary" href="#">返回上一页</a></small>
                    <small style="float: right;margin-top:15px;">书籍详情</small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-1 column">

        </div>
        <div class="col-md-5 column">
            <img src="${pageContext.request.contextPath}/static/img/bookimg1.jpg" class="img-thumbnail"
                 style="height: 600px; width: 400px;">
        </div>

        <div class="col-md-5 column">
            <div class="alert alert-warning">
                <p class="bookError"></p>
            </div>
            <h2>《${requestScope.book.name}》</h2>
            <div style="text-indent:2em;font-size: 16px;color: rgb(102, 100, 100);">
                <p>《三体2：黑暗森林》是刘慈欣创作的长篇科幻小说，是“地球往事三部曲系列”的第二部作品，
                    于2008年5月首次出版，该书总共有三个章节：“上部 面壁者”、“中部 咒语”和“下部 黑暗森林”。
                </p>
                <p>该书主要讲述庞大的三体舰队杀气腾腾直扑太阳系，
                    尖端科技被锁死的地球人面对前所未有的危局，
                    唯一的突破口在于三体人透明的思维。
                    于是，人类制订了神秘莫测的“面壁计划”，秘密展开对三体人的反击。
                    该小说通过展示异常黑暗的宇宙图景，在更加宏观的角度展示了“适者生存”宇宙进化理论，
                    直刺长期以来的人类中心主义之上的自恋情绪。
                </p>
            </div>
            <div>
                <ul class="list-group" style="border: none;">
                    <li class="list-group-item" style="border: none;">
                        <div style="display: inline-block;">书籍名称：</div>
                        <div id="bookName" style="display: inline-block;">${requestScope.book.name}</div>
                    </li>
                    <li class="list-group-item" style="border: none;border-top: 2px dashed #dddddd;">
                        <div style="display: inline-block;">书籍作者：</div>
                        <div style="display: inline-block;">${requestScope.book.author}</div>
                    </li>
                    <li class="list-group-item" style="border: none;border-top: 2px dashed #dddddd;">
                        <div style="display: inline-block;">书籍译者：</div>
                        <div style="display: inline-block;">无</div>
                    </li>
                    <li class="list-group-item" style="border: none;border-top: 2px dashed #dddddd;">
                        <div style="display: inline-block;">书籍版本：</div>
                        <div style="display: inline-block;">
                            <p class="date" style="display: inline-block">
                                ${requestScope.bookDetails.datePublication}
                            </p>
                            <p style="display: inline-block">

                            </p>
                        </div>
                    </li>
                    <li class="list-group-item" style="border: none;border-top: 2px dashed #dddddd;">
                        <div style="display: inline-block;">出版社：</div>
                        <div style="display: inline-block;">${requestScope.bookDetails.bookConcern}</div>
                    </li>
                    <li class="list-group-item"
                        style="font-size: 24px; font-weight: bolder;border: none;border-top: 2px dashed #dddddd;">
                        <div style="display: inline-block;">所需积分：</div>
                        <div style="display: inline-block;color: orangered;">${requestScope.book.points}</div>
                    </li>
                </ul>
            </div>

            <div id="userHome" data-user-id="${sessionScope.user.id}">
                <button type="button" class="btn btn-danger" id="addBookList" data-book-id="${requestScope.book.id}"
                        style="width: 30%;margin-left: 2%;">加入书单
                </button>
                <button type="button" class="btn btn-success" id="borrowNow" data-book-id="${requestScope.book.id}" data-book-name="${requestScope.book.name}" data-points="${requestScope.book.points}"
                        style="width: 30%;">立即借阅
                </button>
                <button type="button" class="btn btn-info" id="reservationBook" data-book-id="${requestScope.book.id}"
                        style="width: 30%;">预约书籍
                </button>
            </div>
        </div>
        <div class="col-md-1 column">

        </div>
    </div>

    <div class="row clearfix" style="margin-top: 20px;">

        <div class="col-md-12 column" style="height: 100px;background-color: #dddddd;">

        </div>

    </div>
</div>

</body>
</html>