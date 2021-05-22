<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>个人中心</title>
    <link rel="shortcut icon"
          href="${pageContext.request.contextPath}/static/img/userImg/img${sessionScope.user.id}${sessionScope.user.headImgPath}"
          type="image/x-icon"/>
    <%@include file="../common/head.jsp" %>
    <script src="${pageContext.request.contextPath }/static/js/myJS/homepage.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/myJS/conversion.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/myJS/bookList.js"></script>

</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-1 column">

        </div>
        <div class="col-md-10 column">
            <h1>
                <small><a class="btn btn-primary" href="#">首页</a></small>
            </h1>
        </div>
        <div class="col-md-1 column">

        </div>
    </div>


    <div class="row clearfix" style="margin-top: 20px;">
        <div class="col-md-1 column">

        </div>
        <div class="col-md-10 column">
            <div style="float: left; width: 20%">
                <ul class="list-group" id="homepageUl" style="border: 1px solid #dddddd;">
                    <span href="#" class="list-group-item ulSpan">个人中心</span>
                    <a href="javascript:void(0);" onclick="myHome()" class="list-group-item"><span
                            class="glyphicon glyphicon-home">&nbsp;首&nbsp;&nbsp;页</span></a>
                    <a href="javascript:void(0);" onclick="myInformation()" class="list-group-item"><span
                            class="glyphicon glyphicon-user">&nbsp;我的信息</span></a>
                    <a href="javascript:void(0);" onclick="myHead()" class="list-group-item"><span
                            class="glyphicon glyphicon-picture">&nbsp;我的头像</span></a>
                    <a href="javascript:void(0);" onclick="myBookList()" class="list-group-item active"><span
                            class="glyphicon glyphicon-book">&nbsp;我的书单</span></a>
                    <a href="javascript:void(0);" onclick="myPoints()" class="list-group-item"><span
                            class="glyphicon glyphicon-usd">&nbsp;我的积分</span></a>
                    <a href="javascript:void(0);" onclick="myBespeak()" class="list-group-item"><span
                            class="glyphicon glyphicon-time">&nbsp;我的预约</span></a>
                </ul>
            </div>

            <div style="float: right; width: 80%;height: 620px; border: 1px solid #dddddd;">
                <div style="width: 100%; height: 49px;padding-top:0; border-bottom: 1px solid #dddddd;">
                    <div style="width: 5px;height: 20px;float: left; margin-left: 30px; margin-top: 15px; border-radius: 2px;background-color: rgb(63, 164, 247);"></div>
                    <span style="float: left; color: rgb(63, 164, 247);margin-top: 15px;margin-left: 5px;">我的书单</span>
                    <div style="border:1px solid rgb(63, 164, 247);float: right;margin-right: 20%; margin-top:15px ;border-radius: 5px;padding-left:15px ;padding-right: 15px;">
                        <a href="javascript:void(0);" id="donateBook"><span
                                style="color: rgb(63, 164, 247);line-height: normal;">我要捐书</span></a>
                    </div>
                </div>
                <!-- 借书单 -->
                <div style="border-bottom: 1px solid #dddddd;">
                    <div style="width: 100%; height: 50px;padding-top:0;">
                        <div style="width: 5px;height: 20px;float: left; margin-left: 30px; margin-top: 15px; border-radius: 2px;"></div>
                        <span style="float: left;margin-top: 15px;margin-left: 5px;color: rgb(63, 164, 247);">借书单</span>
                    </div>

                    <table class="table table-condensed" style="text-align: center;">
                        <thead>
                        <tr>
                            <th style="text-align: center;">书单编号</th>
                            <th style="text-align: center;">借书时间</th>
                            <th style="text-align: center;">积分</th>
                            <th style="text-align: center;">状态</th>
                            <th style="text-align: center;">操作</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${sessionScope.bookLists}" var="bookList">
                            <tr>
                                <td>${bookList.bookListId}</td>
                                <td class="date">${bookList.createTime}</td>
                                <td>${bookList.points}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${bookList.status==0}">
                                            准备中
                                        </c:when>
                                        <c:when test="${bookList.status==1}">
                                            已出库
                                        </c:when>
                                        <c:when test="${bookList.status==2}">
                                            借阅中
                                        </c:when>
                                        <c:when test="${bookList.status==3}">
                                            已归还
                                        </c:when>
                                        <c:when test="${bookList.status==4}">
                                            已取消
                                        </c:when>
                                        <c:when test="${bookList.status==5}">
                                            即将超时
                                        </c:when>
                                        <c:when test="${bookList.status==6}">
                                            已超时
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="javascript:void(0);" class="details" data-toggle="modal" data-target="#details" data-book-list-id="${bookList.bookListId}">
                                        详情
                                    </a> |
                                    <a href="javascript:void(0);" class="receive" data-book-list-id="${bookList.bookListId}" data-status="${bookList.status}">
                                        接收
                                    </a> |
                                    <a href="javascript:void(0);" class="cancel" data-book-list-id="${bookList.bookListId}" data-status="${bookList.status}">
                                        取消
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
                                                    <th style="text-align: center;">总积分</th>
                                                    <th style="text-align: center;">状态</th>
                                                    <th style="text-align: center;">操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td id="bookListId"></td>
                                                    <td id="createTime"></td>
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
                                                                <li><a href="javascript:void(0);" id="cancelBorrowing">取消借阅</a>
                                                                </li>
                                                                <li><a href="javascript:void(0);" id="confirmReceipt">确认接收</a>
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

                    <div class="row clearfix" style="margin-bottom: 10px">
                        <div class="col-md-12 colum">
                            <nav aria-label="Page navigation" style="text-align: center;">
                                <ul class="pagination" style="display: inline-block;float: none;margin: 0em;">
                                    <li><a href="#">首页</a></li>
                                    <li>
                                        <a href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <li><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#">4</a></li>
                                    <li><a href="#">5</a></li>
                                    <li>
                                        <a href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                    <li><a href="#">末页</a></li>
                                    <li><span>共有10页</span></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
                <!-- 捐书单 -->
                <div>
                    <div style="width: 100%; height: 50px;padding-top:0;">
                        <div style="width: 5px;height: 20px;float: left; margin-left: 30px; margin-top: 15px; border-radius: 2px;"></div>
                        <span style="float: left;margin-top: 15px;margin-left: 5px;color: rgb(63, 164, 247);">捐书单</span>
                    </div>

                    <table class="table table-condensed" style="text-align: center;">
                        <thead>
                        <tr>
                            <th style="text-align: center;">书单编号</th>
                            <th style="text-align: center;">捐书时间</th>
                            <th style="text-align: center;">积分</th>
                            <th style="text-align: center;">状态</th>
                            <th style="text-align: center;">操作</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${sessionScope.endowBookLists}" var="endowBookList">
                            <tr>
                                <td>${endowBookList.bookListId}</td>
                                <td class="date">${endowBookList.createTime}</td>
                                <td>${endowBookList.points}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${endowBookList.status==0}">
                                            待处理
                                        </c:when>
                                        <c:when test="${endowBookList.status==1}">
                                            处理中
                                        </c:when>
                                        <c:when test="${endowBookList.status==2}">
                                            已完成
                                        </c:when>
                                        <c:when test="${endowBookList.status==4}">
                                            已取消
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="javascript:void(0);" class="endowDetails" data-toggle="modal" data-target="#endowDetails" data-book-list-id="${endowBookList.bookListId}">
                                        详情
                                    </a> |
                                    <a href="javascript:void(0);" class="endowCancel" data-book-list-id="${endowBookList.bookListId}" data-status="${endowBookList.status}">
                                        取消
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <!-- 模态框（Modal） -->
                    <div class="modal fade" id="endowDetails" tabindex="-1" role="dialog"
                         aria-labelledby="endowDetailsLabel" aria-hidden="true">
                        <div class="modal-dialog" style="width: 60%;">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4 class="modal-title" id="endowDetailsLabel">书单详情</h4>
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
                                                    <th style="text-align: center;">总积分</th>
                                                    <th style="text-align: center;">状态</th>
                                                    <th style="text-align: center;">操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td id="endowBookListId"></td>
                                                    <td id="endowCreateTime"></td>
                                                    <td id="endowPoints"></td>
                                                    <td id="endowStatus"></td>
                                                    <td>
                                                        <a href="javascript:void(0);" id="cancelDonation">取消捐书</a>
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
                                                <tbody id="endowItems">

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

                    <div class="row clearfix">
                        <div class="col-md-12 colum">
                            <nav aria-label="Page navigation" style="text-align: center;">
                                <ul class="pagination" style="display: inline-block;float: none;margin: 0em;">
                                    <li><a href="#">首页</a></li>
                                    <li>
                                        <a href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <li><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#">4</a></li>
                                    <li><a href="#">5</a></li>
                                    <li>
                                        <a href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                    <li><a href="#">末页</a></li>
                                    <li><span>共有10页</span></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
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