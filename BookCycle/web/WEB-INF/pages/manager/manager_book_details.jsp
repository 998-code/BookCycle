<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>书籍管理</title>
    <link rel="shortcut icon"
          href="${pageContext.request.contextPath}/static/img/bookImg/bookimg${requestScope.book.id}.jpg"
          type="image/x-icon"/>
    <%@include file="../common/head.jsp" %>
    <script>
        $(function () {

        });
        window.addEventListener("load", function () {
            conversionTime();
        })

        function conversionTime() {
            let datePublication = $("#datePublication");
            let time = datePublication.val();
            console.log(time);
            let date = new Date(time);
            let year = date.getFullYear();
            let month = date.getMonth() + 1;
            month = month < 10 ? "0" + month : month;
            let dates = date.getDate();
            dates = dates < 10 ? "0" + dates : dates;
            let s = year + "-" + month + "-" + dates;
            datePublication.val(s);

        }
    </script>

</head>
<body>
<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small><a class="btn btn-primary" href="#">返回上一页</a></small>
                    <small style="float: right;margin-top:15px;">书籍管理</small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-1 column">

        </div>
        <div class="col-md-5 column">
            <img src="${pageContext.request.contextPath}/static/img/bookImg/bookimg${requestScope.book.id}.jpg"
                 class="img-thumbnail" style="height: 600px; width: 400px;">
        </div>

        <div class="col-md-5 column">
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">图书名称</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" placeholder="请输入图书名称"
                               value="${requestScope.book.name}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="author" class="col-sm-2 control-label">图书作者</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="author" placeholder="请输入图书作者"
                               value="${requestScope.book.author}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="stock" class="col-sm-2 control-label">图书总数</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="stock" placeholder="请输入图书总数"
                               value="${requestScope.book.stock}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="loan" class="col-sm-2 control-label">借出数目</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="loan" placeholder="请输入已借出的数目"
                               value="${requestScope.book.loan}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="points" class="col-sm-2 control-label">图书积分</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="points" placeholder="请输入图书积分"
                               value="${requestScope.book.points}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="edition" class="col-sm-2 control-label">图书版次</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="edition" placeholder="请输入图书版次"
                               value="${requestScope.bookDetails.edition}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="datePublication" class="col-sm-2 control-label">出版时间</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" id="datePublication" placeholder="请输入出版时间"
                               value="${requestScope.bookDetails.datePublication}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="bookConcern" class="col-sm-2 control-label">出版社</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="bookConcern" placeholder="请输入出版社"
                               value="${requestScope.bookDetails.bookConcern}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="summary" class="col-sm-2 control-label">简要描述</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" rows="7" id="summary" placeholder="请输入图书概述"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="img" class="col-sm-2 control-label">上传图片</label>
                    <div class="col-sm-10">
                        <input type="file" id="img">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">确认保存</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-1 column">

        </div>
    </div>

</div>

</body>
</html>