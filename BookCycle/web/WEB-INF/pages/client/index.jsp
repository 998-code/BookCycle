<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>借书吧！</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/img7.png" type="image/x-icon"/>
    <%@include file="../common/head.jsp" %>
    <script>
        $(function () {
            let href = location.href;//获取或设置整个URL

            $("#login").click(function () {
                window.open(href + "user/getLogin");
                return false;
            });

            $("#search").click(function () {
                let info = $.trim($("#info").val());
                location.href = href + "book/search/" + info + "?pageNo=1";
                return false;
            });

            $("#borrowBookList").click(function () {
                window.open(href + "cart/getCart");
                return false;
            });

            $("#userHome").click(function () {
                window.open(href + "user/home");
                return false;
            });

            $("#donateBook").click(function () {
                window.open(href + "clientBook/getDonate");
                return false;
            });

            $(".getBookDetails").click(function () {
                let bookId = $(this).data("book-id");
                window.open(href + "book/bookDetails/" + bookId);
                return false;
            });

            $(".addBookList").click(function () {
                let bookId = $(this).data("book-id");
                let bookName = $(this).data("book-name");
                let totalCount;
                $.get({
                    url: href + "cart/addItem",
                    data: {"bookId": bookId},
                    success: function (data) {
                        switch (data) {
                            case 0:
                                $(".book-alert").html("已将《" + bookName + "》添加到书单！").addClass("book-alert-success").show().delay(2500).fadeOut();
                                break;
                            case 1:
                                $(".book-alert").html("图书《" + bookName + "》添加出错，图书可能不存在！").addClass("book-alert-warning").show().delay(2500).fadeOut();
                                break;
                            case 2:
                                $(".book-alert").html("图书《" + bookName + "》全部被借阅，您预约下次借阅！").addClass("book-alert-warning").show().delay(2500).fadeOut();
                                break;
                            case 3:
                                totalCount =${empty sessionScope.cart.totalCount ? 5:sessionScope.cart.totalCount};
                                $(".book-alert").html("您最多只能添加" + totalCount + "本书！").addClass("book-alert-danger").show().delay(5000).fadeOut();
                                break;
                        }
                    }
                });
            });

            $(".borrowNow").click(function () {
                let id = $(this).data("book-id");
                let bookName = $(this).data("book-name");
                let points = $(this).data("points");
                let userId = $("#userHome").data("user-id");
                if (userId == "" || userId.length == 0) {
                    $(".book-alert").html("您还没有登录，请先登录！").addClass("book-alert-warning").show().delay(5000).fadeOut();
                    return false;
                }
                let bookId = new Array();
                let bookCount = new Array();
                bookId.push(id);
                bookId.push(points);
                bookCount.push(1);
                bookCount.push(1);
                $.get({
                    url: href + "bookList/borrowNow",
                    data: {"userId": userId, "bookId": bookId.toString(), "bookCount": bookCount.toString()},
                    success: function (data) {
                        if (data == "bookListId") {
                            $(".book-alert").html("图书《" + bookName + "》全部被借阅，您预约下次借阅！").addClass("book-alert-warning").show().delay(2500).fadeOut();
                        } else if (data=="InsufficientPoints") {
                            $(".book-alert").html("您的积分不足！").addClass("book-alert-warning").show().delay(2500).fadeOut();
                        } else {
                            $(".book-alert").html("已为您生成订单号：" + data + "<br>您可以在个人主页查看详情！").addClass("book-alert-success").show().delay(2500).fadeOut();
                        }
                    }
                })
            });

            $(".previousPage").click(function () {
                let pathname = window.location.pathname;
                let No = ${requestScope.pageBook.pageNo};
                let pageNo = No - 1;
                location.href = pathname + "?pageNo=" + pageNo;
                return false;
            });

            $(".nextPage").click(function () {
                let pathname = window.location.pathname;
                let No = ${requestScope.pageBook.pageNo};
                let pageNo = No + 1;
                location.href = pathname + "?pageNo=" + pageNo;
                return false;
            });

            $(".pageBook").click(function () {
                let pageNo = $(this).text();
                let pathname = window.location.pathname;
                location.href = pathname + "?pageNo=" + pageNo;
                return false;
            });
        });
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

<div style="margin-bottom:0;height: 30px;background-color:#f0e9e9f5 ;">
    <div class="container">
        <div class="row clearfix" style="margin-top: 5px;">
            <div class="col-md-12 column">
                <div style="float: right;text-align: center;">
                    <c:if test="${sessionScope.user==null}">
                        欢迎光临借书吧！请<a href="javascript:void(0);" id="login" style="display: inline-block;color: orangered;">登录</a>成为会员！
                    </c:if>
                    <c:if test="${sessionScope.user!=null}">
                        您已登录，尊贵的会员:&nbsp;<p style="display: inline-block;color: tomato;font-weight: bold;">${sessionScope.user.username}</p>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="container">
    <div class="row clearfix" style="margin-top: 10px;height: 50px;">
        <div class="col-md-2 column">

        </div>
        <div class="col-md-6 column">
            <form class="form-inline">
                <input type="text" id="info" value="${requestScope.info}" class="form-control"
                       style="width: 75%;height:40px;" placeholder="请输入查询书籍的名称：">
                <button type="submit" id="search" class="btn alert-danger" style="width: 10%; height: 40px;">
                    <span class="glyphicon glyphicon-search"></span>
                </button>
            </form>
        </div>
        <div class="col-md-4 column">
            <div class="btn-group btn-group-justified" style="height: 40px;">
                <a href="javascript:void(0);" id="borrowBookList" class="btn alert-danger">
                        <span class="glyphicon glyphicon glyphicon-book">
                            借阅书单
                        </span>
                </a>
                <a href="javascript:void(0);" id="userHome" class="btn alert-info"
                   data-user-id="${sessionScope.user.id}">
                        <span class="glyphicon glyphicon-user">
                            个人中心
                        </span>
                </a>
                <a href="javascript:void(0);" id="donateBook" class="btn alert-success">
                        <span class="glyphicon glyphicon glyphicon-gift">
                            我要捐书
                        </span>
                </a>
            </div>
        </div>
    </div>
</div>
<!-- 头部分类栏 -->
<div class="container" style="height: 55px;">
    <div class="row clearfix" style="margin-top: 10px;">
        <div class="col-md-1 column"></div>

        <div class="col-md-10 column">
            <ul class="list-group-horizontal">
                <li class="list-group-item navFirst"><p>书籍分类</p></li>
                <li class="list-group-item navActive"><a href="#">教育</a></li>
                <li class="list-group-item navActive"><a href="#">小说</a></li>
                <li class="list-group-item navActive"><a href="#">文艺</a></li>
                <li class="list-group-item navActive"><a href="#">人文社科</a></li>
                <li class="list-group-item navActive"><a href="#">经管</a></li>
                <li class="list-group-item navActive"><a href="#">成功/励志</a></li>
                <li class="list-group-item navActive"><a href="#">科技</a></li>
                <li class="list-group-item navActive"><a href="#">英文原版</a></li>
            </ul>
        </div>

        <div class="col-md-1 column"></div>
    </div>
</div>

<div style="height: 100px;border-top:2px solid orangered;">
    <div class="container">
        <div class="row clearfix" style="margin-top: 10px;">
            <div class="col-md-3 column">

            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row clearfix" style="margin-top: 10px;">
        <!-- 左侧分类栏 -->
        <div class="col-md-2 column">
            <ul class="list-group">
                <p class="list-group-item"
                   style="text-align: center;font-size: 18px;background-color: #dff0d8;color: #3c763d;">
                    书籍分类
                </p>
                <div class="list-group-item">
                    <a href="#" class="pFont">
                        教育
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                    <div class="bookSubClass">
                        <a href="#">教材</a> |
                        <a href="#">外语</a> |
                        <a href="#">考试</a> |<br>
                        <a href="#">工具书</a>
                    </div>
                </div>
                <div class="list-group-item">
                    <a href="#" class="pFont">
                        小说
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                </div>
                <div class="list-group-item">
                    <a href="#" class="pFont">
                        文艺
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                    <div class="bookSubClass">
                        <a href="#">文学</a> |
                        <a href="#">艺术</a> |
                        <a href="#">传记</a> |
                        <a href="#">摄影</a>
                    </div>
                </div>
                <div class="list-group-item">
                    <a href="#" class="pFont">
                        人文社科
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                    <div class="bookSubClass">
                        <a href="#">历史</a> |
                        <a href="#">文化</a> |
                        <a href="#">政治/军事</a> |<br>
                        <a href="#">法律</a> |
                        <a href="#">社会科学</a> |
                        <a href="#">心理学</a>
                    </div>
                </div>
                <div class="list-group-item">
                    <a href="#" class="pFont">
                        经管
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                    <div class="bookSubClass">
                        <a href="#">经济</a> |
                        <a href="#">管理</a> |
                        <a href="#">理财投资</a>
                    </div>
                </div>
                <div class="list-group-item">
                    <a href="#" class="pFont">
                        成功/励志
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                </div>
                <div class="list-group-item">
                    <a href="#" class="pFont">
                        科技
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                    <div class="bookSubClass">
                        <a href="#">科普</a> |
                        <a href="#">建筑</a> |
                        <a href="#">医学</a> |<br>
                        <a href="#">计算机</a> |
                        <a href="#">农业</a> |<br>
                        <a href="#">自然科学</a> |
                        <a href="#">工业</a>
                    </div>
                </div>
                <div class="list-group-item">
                    <a href="#" class="pFont">
                        英文原版
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                </div>
            </ul>
        </div>

        <div class="col-md-7 column">

            <!-- 推荐书籍 -->
            <div style="margin-top: 0px;">
                <p style="font-size: 18px;color: dimgrey;">推荐书籍</p>
                <!-- 书籍组 -->
                <div class="row">

                    <c:forEach items="${requestScope.pageBook.pageItems}" var="book">
                        <div class="col-sm-6 col-md-3">
                            <div class="thumbnail" style="border: none;">
                                <a href="javascript:void(0);" class="getBookDetails" data-book-id="${book.id}"><img
                                        src="${pageContext.request.contextPath}/static/img/bookImg/bookimg${book.id}.jpg"></a>
                                <div class="caption bookSubClass">
                                    <a href="javascript:void(0);" class="getBookDetails" data-book-id="${book.id}"
                                       style="display: block; width: 100%; font-size: 14px; font-weight: bold;margin: 0px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;">
                                            ${book.name}
                                    </a>
                                    <p style="margin: 0px;font-size: 12px; color: silver;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;">
                                        著：${book.author}
                                    </p>
                                    <p style="font-weight: bold; font-size: 14px; color: tomato;">
                                        借阅积分：${book.points}
                                    </p>
                                    <p>
                                    <div style="display: inline-block;">
                                        <a href="javascript:void(0);" class="addBookList" data-book-id="${book.id}"
                                           data-book-name="${book.name}">加入书单</a>
                                    </div>
                                    <div style="display: inline-block;">
                                        <a href="javascript:void(0);" class="borrowNow" data-book-id="${book.id}"
                                           data-book-name="${book.name}" data-points="${book.points}">立即借阅</a>
                                    </div>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <!-- 分页 -->
                <nav aria-label="Page navigation" style="text-align: center;margin-top: 0px;">
                    <ul class="pagination" style="display: inline-block;float: none;margin: 0em;">
                        <c:if test="${requestScope.pageBook.pageNo>1}">
                            <li><a href="javascript:void(0);" class="previousPage">&laquo;</a></li>
                        </c:if>

                        <c:choose>
                            <c:when test="${requestScope.pageBook.pageTotal<=5}">
                                <c:set var="begin" value="1"></c:set>
                                <c:set var="end" value="${requestScope.pageBook.pageTotal}"></c:set>
                            </c:when>
                            <c:when test="${requestScope.pageBook.pageTotal>5}">
                                <c:choose>
                                    <c:when test="${requestScope.pageBook.pageNo<=3}">
                                        <c:set var="begin" value="1"></c:set>
                                        <c:set var="end" value="5"></c:set>
                                    </c:when>
                                    <c:when test="${requestScope.pageBook.pageNo>=requestScope.pageBook.pageTotal-3}">
                                        <c:set var="begin" value="${requestScope.pageBook.pageTotal-4}"></c:set>
                                        <c:set var="end" value="${requestScope.pageBook.pageTotal}"></c:set>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="begin" value="${requestScope.pageBook.pageNo-2}"></c:set>
                                        <c:set var="end" value="${requestScope.pageBook.pageNo+2}"></c:set>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                        </c:choose>

                        <c:forEach begin="${begin}" end="${end}" var="i">
                            <c:if test="${requestScope.pageBook.pageNo==i}">
                                <li class="active"><a href="javascript:void(0);" class="pageBook">${i}</a></li>
                            </c:if>
                            <c:if test="${requestScope.pageBook.pageNo!=i}">
                                <li><a href="javascript:void(0);" class="pageBook">${i}</a></li>
                            </c:if>
                        </c:forEach>

                        <c:if test="${requestScope.pageBook.pageNo<requestScope.pageBook.pageTotal}">
                            <li><a href="javascript:void(0);" class="nextPage">&raquo;</a></li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>

        <!-- 右侧 -->
        <div class="col-md-3 column">
            <div style="border: 1px solid #dddddd;">
                <p style="font-size: 16px;font-weight: bold;margin-top: 5px;margin-left: 5px;">
                    文学新闻
                </p>
            </div>
        </div>
    </div>
</div>

<div class="jumbotron text-center" style="margin-bottom:0">
    <p>底部内容</p>
</div>
</body>
</html>