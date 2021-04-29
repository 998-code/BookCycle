<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <meta charset="utf-8"> 
   <title>借书吧！</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/img7.png" type="image/x-icon" />
    <%@include file="../common/head.jsp"%>
    <script>
        $(function () {
            $(".login").click(function () {
                window.open("${pageContext.request.contextPath}/user/getLogin");
                return false;
            });

            $("#search").click(function () {
                let info = $.trim($("#info").val());
                location.href="${pageContext.request.contextPath}/book/search/"+info+"?pageNo=1";
                return false;
            });

            $(".userHome").click(function () {
                window.open("${pageContext.request.contextPath}/user/home");
                return false;
            });

            $(".getBookDetails").click(function () {
                let bookId = $(this).attr("name");
                window.open("${pageContext.request.contextPath}/book/bookDetails/"+bookId);
                return false;
            });

            $(".addBookList").click(function () {
                let bookId = $(this).attr("name");
                alert(bookId);
            });

            $(".borrowNow").click(function () {
                let bookId = $(this).attr("name");
                alert(bookId);
            });

            $(".pageBook").click(function () {
                let pageNo = $(this).text();
                let pathname = window.location.pathname;
                location.href=pathname+"?pageNo="+pageNo;
                return false;
            });
        });
    </script>
</head>
<body>
    <div style="margin-bottom:0;height: 30px;background-color:#f0e9e9f5 ;">
        <div class="container">
            <div class="row clearfix" style="margin-top: 5px;">
                <div class="col-md-12 column">
                    <div style="float: right;text-align: center;">
                        <c:if test="${sessionScope.user==null}">
                            欢迎光临借书吧！请<a href="javascript:void(0);" class="login" style="display: inline-block;color: orangered;">登录</a>成为会员！
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
            <div class="col-md-6 column" >
                <form class="form-inline">
                    <input type="text" id="info" value="${requestScope.info}" class="form-control" style="width: 75%;height:40px;" placeholder="请输入查询书籍的名称：">
                    <button type="submit" id="search" class="btn alert-danger" style="width: 10%; height: 40px;">
                        <span class="glyphicon glyphicon-search"></span> 
                      </button>
                </form>
            </div>
            <div class="col-md-4 column">
                <div class="btn-group btn-group-justified" style="height: 40px;">
                    <a href="javascript:void(0);" class="btn alert-danger" >
                        <span class="glyphicon glyphicon glyphicon-book">
                            借阅书单
                        </span>
                    </a>
                    <a href="javascript:void(0);" class="btn alert-info userHome">
                        <span class="glyphicon glyphicon-user">
                            个人中心
                        </span>
                    </a>
                    <a href="javascript:void(0);" class="btn alert-success" >
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
            <div class="col-md-12 column">
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
                    <p class="list-group-item" style="text-align: center;font-size: 18px;background-color: rgb(66, 161, 107);color: snow;">
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
                                    <a href="javascript:void(0);" class="getBookDetails" name="${book.id}"><img src="${pageContext.request.contextPath}/static/img/bookImg/bookimg${book.id}.jpg"></a>
                                    <div class="caption bookSubClass">
                                        <a href="javascript:void(0);" class="getBookDetails" name="${book.id}" style="display: block; width: 100%; font-size: 14px; font-weight: bold;margin: 0px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;">
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
                                            <a href="javascript:void(0);" class="addBookList" name="${book.id}">加入书单</a>
                                        </div>
                                        <div style="display: inline-block;">
                                            <a href="javascript:void(0);" class="borrowNow" name="${book.id}">立即借阅</a>
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
                            <c:forEach begin="1" end="${requestScope.pageBook.pageTotal}" var="i">
                                <c:if test="${requestScope.pageBook.pageNo==i}">
                                    <li class="active"><a href="javascript:void(0);" class="pageBook">${i}</a></li>
                                </c:if>
                                <c:if test="${requestScope.pageBook.pageNo!=i}">
                                    <li ><a href="javascript:void(0);" class="pageBook">${i}</a></li>
                                </c:if>
                            </c:forEach>

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