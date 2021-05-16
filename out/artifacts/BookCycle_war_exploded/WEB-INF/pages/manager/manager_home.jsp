<%--
  Created by IntelliJ IDEA.
  User: 吴超民
  Date: 2021/05/12
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理中心</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/bookimg1.jpg" type="image/x-icon"/>
    <%@include file="../common/head.jsp" %>

    <script>
        $(function () {
            let href = location.href;//获取或设置整个URL
            let index = href.indexOf("user");
            let newHref = href.substr(0, index);
            $("#book").click(function () {
                window.open(newHref+"manager/getBook?pageNo=1");
                return false;
            });

            $("#bookList").click(function () {
                window.open(newHref+"manager/getBookList?pageNo=1");
                return false;
            });
        })
    </script>
</head>
<body>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">面板标题</h3>
    </div>
    <div class="panel-body">
        这是一个基本的面板
    </div>
</div>
<div class="panel panel-success">
    <div class="panel-heading">
        <h3 class="panel-title">面板标题</h3>
    </div>
    <div class="panel-body">
        <a href="javascript:void(0);" id="book">图书管理</a>
    </div>
</div>
<div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title">面板标题</h3>
    </div>
    <div class="panel-body">
        <a href="javascript:void(0);" id="bookList">书单管理</a>
    </div>
</div>
<div class="panel panel-warning">
    <div class="panel-heading">
        <h3 class="panel-title">面板标题</h3>
    </div>
    <div class="panel-body">
        这是一个基本的面板
    </div>
</div>
<div class="panel panel-danger">
    <div class="panel-heading">
        <h3 class="panel-title">面板标题</h3>
    </div>
    <div class="panel-body">
        这是一个基本的面板
    </div>
</div>
</body>
</html>
