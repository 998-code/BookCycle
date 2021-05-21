<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>捐书单</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/bookimg1.jpg" type="image/x-icon"/>
    <%@include file="../common/head.jsp" %>
    <script src="${pageContext.request.contextPath }/static/js/myJS/conversion.js"></script>
    <script>
        let href = location.href;//获取或设置整个URL
        let index = href.indexOf("manager");
        let newHref = href.substr(0, index);
        $(function () {
            let pathname = window.location.pathname;
            $("#getBook").click(function () {
                window.open(newHref + "manager/getBook?pageNo=1");
                return false;
            });

            $("#search").click(function () {
                let info = $.trim($("#info").val());
                let regExp = /^[^0][0-9]{3,16}$/;
                if (regExp.test(info)) {
                    location.href = newHref + "manager/searchEndowBookList/" + info + "?pageNo=1";
                    return false;
                } else {
                    $(".book-alert").html("输入数据格式不匹配！").addClass("book-alert-danger").show().delay(5000).fadeOut();
                    return false;
                }

            });

            $("#refresh").click(function () {
                window.location.reload();
            });

            $(".startProcessing").click(function () {
                let status = $(this).data("status");
                let statusArr = ["待处理", "处理中", "已完成", "已取消", "已取消"];
                let bookListId = $(this).data("book-list-id");
                if (status != 0) {
                    alert("书单[" + bookListId + "]" + statusArr[status] + "，不能进行处理操作！");
                    return false;
                } else {
                    if (confirm("确定开始处理书单[" + bookListId + "]吗？")) {
                        $.post({
                            url: newHref + "manager/endowBookList/startProcessing",
                            data: {"bookListId": bookListId},
                            success: function (data) {
                                if (data) {
                                    $(".book-alert").html("书单号为[" + bookListId + "]的订单开始处理！").addClass("book-alert-success").show().delay(2500).fadeOut();
                                } else {
                                    $(".book-alert").html("操作失败！请重试！").addClass("book-alert-danger").show().delay(3000).fadeOut();
                                }
                            }
                        });
                    }
                }
            });

            $(".confirmComplete").click(function () {
                let status = $(this).data("status");
                let statusArr = ["待处理", "处理中", "已完成", "已取消", "已取消"];
                let bookListId = $(this).data("book-list-id");
                if (status != 1) {
                    alert("书单[" + bookListId + "]" + statusArr[status] + "，不能进行完成操作！");
                } else {
                    if (confirm("请确定书单[" + bookListId + "]的处理完成吗？")) {
                        $.post({
                            url: newHref + "manager/endowBookList/confirmComplete",
                            data: {"bookListId": bookListId},
                            success: function (data) {
                                if (data) {
                                    $(".book-alert").html("书单号为[" + bookListId + "]的订单处理完成！").addClass("book-alert-success").show().delay(2500).fadeOut();
                                } else {
                                    $(".book-alert").html("操作失败！请重试！").addClass("book-alert-danger").show().delay(3000).fadeOut();
                                }
                            }
                        });
                    }
                }

            });

            $("#startProcessing").click(function () {
                let ul = $(this).parent().parent().parent().parent().parent();
                let bookListId = ul.find("td:first").text();
                let status = $.trim(ul.children("td").eq(4).text());
                if (status != "待处理") {
                    alert("书单[" + bookListId + "]" + status + "，不能进行处理操作！");
                } else {
                    if (confirm("确定开始处理书单[" + bookListId + "]吗？")) {
                        $.post({
                            url: newHref + "manager/endowBookList/startProcessing",
                            data: {"bookListId": bookListId},
                            success: function (data) {
                                if (data) {
                                    ul.children("td").eq(4).text("处理中");
                                } else {
                                    alert("书单[" + bookListId + "]处理操作失败！");
                                }
                            }
                        });
                    }
                }
            });

            $("#confirmComplete").click(function () {
                let ul = $(this).parent().parent().parent().parent().parent();
                let bookListId = ul.find("td:first").text();
                let status = $.trim(ul.children("td").eq(4).text());
                if (status != "处理中") {
                    alert("书单[" + bookListId + "]" + status + "，不能进行完成操作！");
                } else {
                    if (confirm("请确定书单[" + bookListId + "]的处理完成吗？")) {
                        $.post({
                            url: newHref + "manager/endowBookList/confirmComplete",
                            data: {"bookListId": bookListId},
                            success: function (data) {
                                if (data) {
                                    ul.children("td").eq(4).text("已完成");
                                } else {
                                    alert("书单[" + bookListId + "]完成操作失败！");
                                }
                            }
                        });
                    }
                }
            });

            $(".details").click(function () {
                let ul = $(this).parent().parent();
                let bookListId = ul.find("td:first").text();
                let createTime = $.trim(ul.children("td").eq(1).text());
                let points = $.trim(ul.children("td").eq(2).text());
                let status = $.trim(ul.children("td").eq(3).text());
                let userId = $.trim(ul.children("td").eq(4).text());
                $("#bookListId").text(bookListId);
                $("#createTime").text(createTime);
                $("#points").text(points);
                $("#status").text(status);
                $("#userId").text(userId);
                $.post({
                    url: newHref + "manager/details/bookList",
                    data: {"bookListId": bookListId},
                    success: function (data) {
                        var html = "";
                        for (var i = 0; i < data.length; i++) {
                            html += "<tr>" +
                                "<td>" + data[i].bookId + "</td>" +
                                "<td>" + data[i].bookName + "</td>" +
                                "<td>" + data[i].bookAuthor + "</td>" +
                                "<td>" + data[i].bookConcern + "</td>" +
                                "<td>" + data[i].edition + "</td>" +
                                "<td>" + data[i].points + "</td>" +
                                "<td>" + data[i].count + "</td>" +
                                "<td>" + data[i].totalPoints + "</td>" +
                                "</tr>"
                        }
                        $("#items").html(html);
                    }
                });
            });

            $(".previousPage").click(function () {
                let No = $(this).data("page-no");
                let pageNo = No - 1;
                location.href = pathname + "?pageNo=" + pageNo;
                return false;
            });

            $(".nextPage").click(function () {
                let No = $(this).data("page-no");
                let pageNo = No + 1;
                location.href = pathname + "?pageNo=" + pageNo;
                return false;
            });

            $(".pageBook").click(function () {
                let pageNo = $.trim($(this).text());
                location.href = pathname + "?pageNo=" + pageNo;
                return false;
            });

            $("#goFirst").click(function () {
                location.href = pathname + "?pageNo=1";
                return false;
            });

            $("#goLast").click(function () {
                let last = $(this).data("last");
                location.href = pathname + "?pageNo=" + last;
                return false;
            });

            $("#pageSizeSubmit").click(function () {
                let totalPage = $(this).data("page");
                let pageSize = $("#pageSize").val();
                if (pageSize > totalPage || pageSize <= 0) {
                    alert("该页码不存在?");
                    return false;
                }
                location.href = pathname + "?pageNo=" + pageSize;
                return false;
            });

        });

        window.addEventListener("load",function () {
            let as = document.querySelectorAll(".byStatus");
            for(let i=0;i<as.length;i++){
                as[i].addEventListener("click",function () {
                    let status = this.dataset.status;
                    location.href = newHref + "manager/endowBookListByStatus/" + status + "?pageNo=1";
                });
            }
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
<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small><a class="btn btn-primary" href="#">返回首页</a></small>
                    <small style="float: right;margin-top:15px;"><a href="javascript:void(0);" id="getBook">书籍管理</a></small>
                    <small style="float: right;margin-top:15px;">书单管理&nbsp;</small>
                    <small style="float: right;margin-top:15px;"><a href="javascript:void(0)">文章管理&nbsp;</a></small>
                    <small style="float: right;margin-top:15px;"><a href="javascript:void(0)">用户管理&nbsp;</a></small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-4 column">
            <div class="btn-group">
                <div class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                        捐书单
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="javascript:void(0);" class="byStatus" data-status="0">
                            待处理</a></li>
                        <li><a href="javascript:void(0);" class="byStatus" data-status="1">
                            处理中</a></li>
                        <li><a href="javascript:void(0);" class="byStatus" data-status="2">
                            已完成</a></li>
                        <li><a href="javascript:void(0);" class="byStatus" data-status="4">
                            已取消</a></li>
                    </ul>
                </div>
                <div class="btn-group" style="margin-left: 5px">
                    <a type="button" class="btn btn-primary">
                        借书单
                    </a>

                </div>
            </div>
        </div>
        <div class="col-md-8 column">
            <a class="btn btn-primary" style="float: right;" href="javascript:void(0);" id="refresh">刷新</a>
            <form class="form-inline" action="" method="POST" style="float: right;margin-right: 20px;">
                <span style="color: red;font-weight: bold;">ERROR</span>
                <input type="text" id="info" class="form-control" placeholder="请输入书单ID或用户ID：" value="${requestScope.info}">
                <input type="submit" id="search" value="查询" class="btn btn-primary">
            </form>
        </div>
    </div>


    <div class="row clearfix" style="margin-top: 10px;">
        <div class="col-md-12 column">
            <table class="table table-bordered table-striped" style="font-size: 16px;text-align: center;">
                <thead>
                <tr>
                    <th style="text-align: center;">书单编号</th>
                    <th style="text-align: center;">创建时间</th>
                    <th style="text-align: center;">积分</th>
                    <th style="text-align: center;">状态</th>
                    <th style="text-align: center;">用户ID</th>
                    <th style="text-align: center;">备注</th>
                    <th style="text-align: center;">详情</th>
                    <th style="text-align: center;">操作</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${requestScope.endowBookListPage.pageItems}" var="bookList">
                    <tr>
                        <td>${bookList.bookListId}</td>
                        <td class="date">${bookList.createTime}</td>
                        <td>${bookList.points}</td>
                        <td>
                            <c:choose>
                                <c:when test="${bookList.status==0}">
                                    待处理
                                </c:when>
                                <c:when test="${bookList.status==1}">
                                    处理中
                                </c:when>
                                <c:when test="${bookList.status==2}">
                                    已完成
                                </c:when>
                                <c:when test="${bookList.status==4}">
                                    已取消
                                </c:when>
                            </c:choose>
                        </td>
                        <td>${bookList.userId}</td>
                        <td>捐书单</td>
                        <td>
                            <a href="javascript:void(0);" class="details" data-book-list-id="${bookList.bookListId}"
                               data-toggle="modal" data-target="#details">
                                详情
                            </a>
                        </td>
                        <td>
                            <a href="javascript:void(0);" class="startProcessing" data-book-list-id="${bookList.bookListId}"
                               data-status="${bookList.status}">
                                开始处理
                            </a> |
                            <a href="javascript:void(0);" class="confirmComplete"
                               data-book-list-id="${bookList.bookListId}" data-status="${bookList.status}">
                                确认完成
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <!-- 模态框（Modal） -->
            <div class="modal fade" id="details" tabindex="-1" role="dialog" aria-labelledby="detailsLabel"
                 aria-hidden="true">
                <div class="modal-dialog" style="width: 60%;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="detailsLabel">书单详情</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row clearfix">
                                <div class="col-md-12 column">
                                    <table class="table table-bordered table-striped"
                                           style="font-size: 16px;text-align: center;">
                                        <thead>
                                        <tr>
                                            <th style="text-align: center;">书单编号</th>
                                            <th style="text-align: center;">创建时间</th>
                                            <th style="text-align: center;">用户ID</th>
                                            <th style="text-align: center;">总积分</th>
                                            <th style="text-align: center;">状态</th>
                                            <th style="text-align: center;">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td id="bookListId"></td>
                                            <td id="createTime"></td>
                                            <td id="userId"></td>
                                            <td id="points"></td>
                                            <td id="status"></td>
                                            <td>
                                                <div class="btn-group">
                                                    <button type="button"
                                                            class="btn btn-primary dropdown-toggle"
                                                            data-toggle="dropdown">
                                                        修改状态
                                                        <span class="caret"></span>
                                                    </button>
                                                    <ul class="dropdown-menu">
                                                        <li><a href="javascript:void(0);" id="startProcessing">开始处理</a>
                                                        </li>
                                                        <li><a href="javascript:void(0);" id="confirmComplete">确认完成</a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <div class="row clearfix">
                                <div class="col-md-12 column">
                                    <table class="table table-bordered table-striped" style="font-size: 16px;">
                                        <thead>
                                        <tr>
                                            <th>图书编号</th>
                                            <th>图书名字</th>
                                            <th>图书作者</th>
                                            <th>出版社</th>
                                            <th>版本</th>
                                            <th>积分</th>
                                            <th>数量</th>
                                            <th>总积分</th>
                                        </tr>
                                        </thead>
                                        <tbody id="items">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>

        </div>
    </div>
    <!-- 分页条 -->
    <div class="row clearfix">

        <div class="col-md-12 colum">
            <nav aria-label="Page navigation" style="text-align: center;">
                <ul class="pagination" style="display: inline-block;float: none;margin: 0em;">
                    <c:if test="${requestScope.endowBookListPage.pageNo>1}">
                        <li><a href="javascript:void(0);" id="goFirst">首页</a></li>
                        <li><a href="javascript:void(0);" class="previousPage"
                               data-page-no="${requestScope.endowBookListPage.pageNo}">&laquo;</a></li>
                    </c:if>

                    <c:choose>
                        <c:when test="${requestScope.endowBookListPage.pageTotal<=5}">
                            <c:set var="begin" value="1"></c:set>
                            <c:set var="end" value="${requestScope.endowBookListPage.pageTotal}"></c:set>
                        </c:when>
                        <c:when test="${requestScope.endowBookListPage.pageTotal>5}">
                            <c:choose>
                                <c:when test="${requestScope.endowBookListPage.pageNo<=3}">
                                    <c:set var="begin" value="1"></c:set>
                                    <c:set var="end" value="5"></c:set>
                                </c:when>
                                <c:when test="${requestScope.endowBookListPage.pageNo>=requestScope.endowBookListPage.pageTotal-3}">
                                    <c:set var="begin" value="${requestScope.endowBookListPage.pageTotal-4}"></c:set>
                                    <c:set var="end" value="${requestScope.endowBookListPage.pageTotal}"></c:set>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="begin" value="${requestScope.endowBookListPage.pageNo-2}"></c:set>
                                    <c:set var="end" value="${requestScope.endowBookListPage.pageNo+2}"></c:set>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose>

                    <c:forEach begin="${begin}" end="${end}" var="i">
                        <c:if test="${requestScope.endowBookListPage.pageNo==i}">
                            <li class="active"><a href="javascript:void(0);" class="pageBook">${i}</a></li>
                        </c:if>
                        <c:if test="${requestScope.endowBookListPage.pageNo!=i}">
                            <li><a href="javascript:void(0);" class="pageBook">${i}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${requestScope.endowBookListPage.pageNo<requestScope.endowBookListPage.pageTotal}">
                        <li><a href="javascript:void(0);" class="nextPage"
                               data-page-no="${requestScope.endowBookListPage.pageNo}">&raquo;</a>
                        </li>
                        <li><a href="javascript:void(0);" id="goLast"
                               data-last="${requestScope.endowBookListPage.pageTotal}">末页</a>
                        </li>
                    </c:if>
                    <li><span>共有${requestScope.endowBookListPage.pageTotal}页</span></li>
                    <li>
                        <form class="form-inline" style="white-space: nowrap;display:inline-block;">
                            <input class="form-control" style="width: 100px;" type="number" id="pageSize"
                                   value="${requestScope.endowBookListPage.pageNo}"
                                   placeholder="查询页码">
                            <input class="btn btn-primary" type="submit" id="pageSizeSubmit" value="查询"
                                   data-page="${requestScope.endowBookListPage.pageTotal}">
                        </form>

                    </li>
                </ul>
            </nav>
        </div>


    </div>

</div>

</body>
</html>