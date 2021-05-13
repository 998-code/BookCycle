<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>书籍管理</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/bookimg1.jpg" type="image/x-icon"/>
    <%@include file="../common/head.jsp" %>
    <script>
        $(function () {
            let href = location.href;//获取或设置整个URL
            let index = href.indexOf("manager");
            let newHref = href.substr(0, index);
            let pathname = window.location.pathname;

            $("#search").click(function () {
                let info = $.trim($("#info").val());
                location.href=newHref+"manager/searchBook/"+info+"?pageNo=1";
                return false;
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
                if(pageSize>totalPage||pageSize<=0){
                    alert("该页码不存在?");
                    return false;
                }
                location.href = pathname + "?pageNo=" + pageSize;
                return false;
            });
        })
    </script>

</head>
<body>
<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small><a class="btn btn-primary" href="#">返回首页</a></small>
                    <small style="float: right;margin-top:15px;"><a href="#">书籍管理</a></small>
                    <small style="float: right;margin-top:15px;"><a href="#">书单管理&nbsp;</a></small>
                    <small style="float: right;margin-top:15px;"><a href="#">文章管理&nbsp;</a></small>
                    <small style="float: right;margin-top:15px;"><a href="#">用户管理&nbsp;</a></small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="">新增</a>
        </div>

        <div class="col-md-8 column">
            <a class="btn btn-primary" style="float: right;" href="#">刷新</a>
            <form class="form-inline" action="" method="POST" style="float: right;margin-right: 20px;">
                <span style="color: red;font-weight: bold;">ERROR</span>
                <input type="text" id="info" class="form-control" placeholder="请输入查询书籍的名称：" value="${requestScope.info}">
                <input type="submit" id="search" value="查询" class="btn btn-primary">
            </form>
        </div>
    </div>


    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped" style="font-size: 16px;">
                <thead>
                <tr>
                    <th>图书编号</th>
                    <th>图书名字</th>
                    <th>图书作者</th>
                    <th style="text-align: center">数量</th>
                    <th style="text-align: center">借出</th>
                    <th style="text-align: center">库存</th>
                    <th style="text-align: center">积分</th>
                    <th style="text-align: center">操作</th>
                </tr>
                </thead>

                <tbody>

                <c:forEach items="${requestScope.bookPage.pageItems}" var="book">
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.name}</td>
                        <td style="overflow: hidden">${book.author}</td>
                        <td style="text-align: center">${book.stock}</td>
                        <td style="text-align: center">${book.loan}</td>
                        <td style="text-align: center">${book.stock-book.loan}</td>
                        <td style="text-align: center">${book.points}</td>
                        <td style="text-align: center">
                            <a href="#">更改</a> |
                            <a href="#">删除</a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>

    <div class="row clearfix">

        <div class="col-md-12 colum">
            <nav aria-label="Page navigation" style="text-align: center;">
                <ul class="pagination" style="display: inline-block;float: none;margin: 0em;">
                    <c:if test="${requestScope.bookPage.pageNo>1}">
                        <li><a href="javascript:void(0);" id="goFirst">首页</a></li>
                        <li><a href="javascript:void(0);" class="previousPage"
                               data-page-no="${requestScope.bookPage.pageNo}">&laquo;</a></li>
                    </c:if>

                    <c:choose>
                        <c:when test="${requestScope.bookPage.pageTotal<=5}">
                            <c:set var="begin" value="1"></c:set>
                            <c:set var="end" value="${requestScope.bookPage.pageTotal}"></c:set>
                        </c:when>
                        <c:when test="${requestScope.bookPage.pageTotal>5}">
                            <c:choose>
                                <c:when test="${requestScope.bookPage.pageNo<=3}">
                                    <c:set var="begin" value="1"></c:set>
                                    <c:set var="end" value="5"></c:set>
                                </c:when>
                                <c:when test="${requestScope.bookPage.pageNo>=requestScope.bookPage.pageTotal-3}">
                                    <c:set var="begin" value="${requestScope.bookPage.pageTotal-4}"></c:set>
                                    <c:set var="end" value="${requestScope.bookPage.pageTotal}"></c:set>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="begin" value="${requestScope.bookPage.pageNo-2}"></c:set>
                                    <c:set var="end" value="${requestScope.bookPage.pageNo+2}"></c:set>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose>

                    <c:forEach begin="${begin}" end="${end}" var="i">
                        <c:if test="${requestScope.bookPage.pageNo==i}">
                            <li class="active"><a href="javascript:void(0);" class="pageBook">${i}</a></li>
                        </c:if>
                        <c:if test="${requestScope.bookPage.pageNo!=i}">
                            <li><a href="javascript:void(0);" class="pageBook">${i}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${requestScope.bookPage.pageNo<requestScope.bookPage.pageTotal}">
                        <li><a href="javascript:void(0);" class="nextPage"
                               data-page-no="${requestScope.bookPage.pageNo}">&raquo;</a>
                        </li>
                        <li><a href="javascript:void(0);" id="goLast"
                               data-last="${requestScope.bookPage.pageTotal}">末页</a>
                        </li>
                    </c:if>
                    <li><span>共有${requestScope.bookPage.pageTotal}页</span></li>
                    <li>
                        <form class="form-inline" style="white-space: nowrap;display:inline-block;">
                            <input class="form-control" style="width: 100px;" type="number" id="pageSize" value="${requestScope.bookPage.pageNo}"
                                   placeholder="查询页码">
                            <input class="btn btn-primary" type="submit" id="pageSizeSubmit" value="查询" data-page="${requestScope.bookPage.pageTotal}">
                        </form>

                    </li>
                </ul>
            </nav>
        </div>


    </div>

</div>

</body>
</html>