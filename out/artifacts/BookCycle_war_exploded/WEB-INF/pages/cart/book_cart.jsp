<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>借阅书单</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/img7.png" type="image/x-icon" />
    <%@include file="../common/head.jsp"%>

    <script>
        $(function () {
            let href = location.href;//获取或设置整个URL
            let index = href.indexOf("cart");
            let newHref = href.substr(0, index);

            $(".login").click(function () {
                window.open(newHref+"user/getLogin");
                return false;
            });

            $("#userHome").click(function () {
                window.open(newHref+"user/home");
                return false;
            });

            $(".lessCount").click(function () {
                let points = parseInt($.trim($(this).parent().parent().children("td").eq(2).children(
                    "p").eq(0).text()));
                let input = $(this).parent().children("div").eq(0).children("input");
                let count = input.val();
                if (count > 1) {
                    count--;
                }
                input.val(count);
                let totalPoints = count * points;
                $(this).parent().parent().children("td").eq(4).children("p").eq(0).text(
                    totalPoints);
                bookCount();
                totalPointss();
            });
            $(".plusCount").click(function () {
                let points = parseInt($.trim($(this).parent().parent().children("td").eq(2).children(
                    "p").eq(0).text()));
                let input = $(this).parent().children("div").eq(0).children("input");
                let count = input.val();
                if (count < 100) {
                    count++;
                }
                input.val(count);
                let totalPoints = count * points;
                $(this).parent().parent().children("td").eq(4).children("p").eq(0).text(totalPoints);
                bookCount();
                totalPointss();
            });

            $("#checkAll").click(function(){
                let checkAll = document.getElementById("checkAll").checked;
                let checks=document.getElementsByClassName("check");
                for(var i=0;i<checks.length;i++){
                    checks[i].checked=checkAll;
                }
                bookCount();
                totalPointss();
                if(checkAll){
                    $("#borrow").removeClass("disabled");

                }else {
                    $("#borrow").addClass("disabled");
                }
            });

            $(".check").click(function(){
                let checkAll = document.getElementById("checkAll");
                let checks=document.getElementsByClassName("check");
                let flag=true;
                let borrowFlag=false;
                for(var i=0;i<checks.length;i++){
                    if(!checks[i].checked){
                        flag=false;
                        //break;
                    }
                    if(checks[i].checked){
                        borrowFlag=true;
                    }
                }
                checkAll.checked=flag;
                bookCount();
                totalPointss();
                if(!borrowFlag){
                    $("#borrow").addClass("disabled");
                }else {
                    $("#borrow").removeClass("disabled");
                }
            });

            $("#borrow").click(function(){
                let totalCount=$.trim($("#bookCount").html());
                let totalPoints=$.trim($("#totalPoints").html());
                let userId=$(this).data("user-id");
                if(userId==""||userId.length==0){
                    $(".book-alert").html("您还没有登录，请先登录！").addClass("book-alert-warning").show().delay(2500).fadeOut();
                    return false;
                }
                let bookId = new Array();
                let bookCount=new Array();
                $(".check:checked").each(function(i){
                    bookId[i] = $(this).val();
                });
                bookId.push(totalPoints);
                $(".count").each(function (i) {
                    bookCount[i]=$(this).val();
                });
                bookCount.push(totalCount);
                $.get({
                    url: newHref+"bookList/create",
                    data: {"userId":userId,"bookId":bookId.toString(),"bookCount":bookCount.toString()},
                    success:function(data){
                        $(".book-alert").html("已为您生成订单号："+data+"<br>您可以在个人主页查看详情！").addClass("book-alert-success").show().delay(2500).fadeOut();
                    }
                });

            });

        });

        function bookCount() {
            let checks=document.getElementsByClassName("check");
            var counts=new Array();
            $("input[class=count]").each(function(){
                counts.push($(this).val());
            });
            let bookCount=0;
            for(var i=0;i<checks.length;i++){
                if(checks[i].checked){
                    bookCount+=parseInt(counts[i]);
                }
            }
            $("#bookCount").text(bookCount);
        }

        function totalPointss(){
            let checks=document.getElementsByClassName("check");
            var points=new Array();
            $("p[class=totalPoints]").each(function(){
                points.push($.trim($(this).text()));
            });
            let totalPoints=0;
            for(var i=0;i<points.length;i++){
                if(checks[i].checked){
                    totalPoints+=parseInt(points[i]);
                }
            }
            $("#totalPoints").text(totalPoints);
        }
        // function bookCount(){
        //     var counts=new Array();
        //     $("input[class=count]").each(function(){
        //             counts.push($(this).val());
        //     });
        //     let bookCount=0;
        //     for(var i=0;i<counts.length;i++){
        //         bookCount+=parseInt(counts[i]);
        //     }
        //     $("#bookCount").text(bookCount);
        // }
        // function totalPointss(){
        //     var points=new Array();
        //     $("p[class=totalPoints]").each(function(){
        //         points.push($.trim($(this).text()));
        //     });
        //     let totalPoints=0;
        //     for(var i=0;i<points.length;i++){
        //         totalPoints+=parseInt(points[i]);
        //     }
        //     $("#totalPoints").text(totalPoints);
        // }
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

        .book-alert-warning {
            color: #8a6d3b;
            background-color: #fcf8e3;
            border-color: #faebcc;
        }

        .book-alert-success {
            color: #3c763d;
            background-color: #dff0d8;
            border-color: #d6e9c6;
        }

        input:focus {
            outline: none;
        }

        button:focus {
            outline: none;
        }
        .lessCount :hover{
            color: coral;
        }
        .plusCount :hover{
            color: coral;
        }
        a:link {
            color: black;
        }

        a:hover {
            color: #ff0033;
            text-decoration: underline;
            TEXT-DECORATION: none;
        }

        a:active {
            color: #ff0033;
            text-decoration: none;
        }
        #userHome:link{
            color: tomato;
        }

        /* 复选框 */
        input[type="checkbox"]:focus{
            /*-webkit-appearance: none; !*清除复选框默认样式*!*/
            outline: none;
        }
        input[type="checkbox"] {
            -webkit-appearance: none; /*清除复选框默认样式*/
            border: 1px solid red;
            vertical-align: middle;
            width: 14px;
            height: 14px;
            float: left;
            margin-top: 0;
            margin-right: 0 !important;
            border-radius: 0;
        }
        input[type="checkbox"]:checked{
            /*border: 6px solid mistyrose;*/
            border-radius: 7px;
            border:5px solid crimson;
            /*background: red;*/
        }

    </style>
</head>

<body>
<div class="book-alert"></div>
<div style="margin-bottom:0;height: 30px;background-color:#f0e9e9f5 ;">
    <div class="container">
        <div class="row clearfix" style="margin-top: 5px;">
            <div class="col-md-1 column"></div>
            <div class="col-md-10 column">
                <div style="float: left;text-align: center;">
                    <c:if test="${sessionScope.user==null}">
                        欢迎光临借书吧！请<a href="javascript:void(0);" class="login" style="display: inline-block;color: orangered;">登录</a>成为会员！
                    </c:if>
                    <c:if test="${sessionScope.user!=null}">
                        您已登录，尊贵的会员:&nbsp;<p style="display: inline-block;color: tomato;font-weight: bold;">${sessionScope.user.username}</p>
                    </c:if>
                </div>

                <div style="width: 15%;float: right;">
                    <a href="javascript:void(0);" id="userHome">
                        <span class="glyphicon glyphicon-user">
                            个人中心
                        </span>
                    </a>
                </div>
            </div>
            <div class="col-md-1 column"></div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row clearfix" style="margin-top: 10px;margin-bottom: 10px;">
        <div class="col-md-1 column"></div>
        <div class="col-md-10 column">
            <div>
                <div style="float: left;">
                    <img src="${pageContext.request.contextPath}/static/img/img7.png" height="80px">
                </div>
                <p
                        style="float:left;margin-left:20px;margin-top:40px;margin-bottom:0px;color: rgb(88, 84, 84);font-size: 30px;">
                    借阅书单
                </p>
            </div>
        </div>
        <div class="col-md-1 column"></div>
    </div>
</div>

<div style="height:10px;border-top:2px solid orangered;">

</div>

<div class="container">
    <div class="row clearfix" style="margin-top: 10px;margin-bottom: 10px;">
        <div class="col-md-1 column"></div>
        <div class="col-md-10 column" style="margin-bottom:0;height: 40px;background-color:#f0e9e9f5 ;">
            <div style="height: 30px;margin-top: 5px;font-size: 20px;">
                <span class="glyphicon glyphicon-exclamation-sign"
                      style="float:left;margin-top: 5px;color: darkorange;">
                </span>
                <c:if test="${sessionScope.user==null}">
                    <p style="float: left;font-size: 14px;margin-left:10px;margin-top: 6px;">
                        您还没有登录！登录后借阅书单中的图书信息将保存到您的帐号中
                    </p>
                    <a href="javascript:void(0);" class="btn btn-danger login"
                       style="height:30px;margin-left:10px;color: snow;">
                        立即登录
                    </a>
                </c:if>
                <c:if test="${sessionScope.user!=null}">
                    <p style="float: left;font-size: 14px;margin-left:10px;margin-top: 6px;">
                        您已登录，您可以在个人主页查看更多信息！
                    </p>
                </c:if>
            </div>

            <div style="margin-top: 10px;">
                <table class="table table-striped" style="border:1px solid #dddddd ;">
                    <thead>
                        <tr>
                            <th>
                                <div>
                                    <input type="checkbox" id="checkAll" checked="checked" style="display: inline-block;margin-top: 5px">
                                    <p style="display: inline-block;margin: 0px;margin-left: 5px;">全选</p>
                                </div>
                            </th>
                            <th>图书信息</th>
                            <th>积分</th>
                            <th>数量</th>
                            <th>总积分</th>
                            <th>操作</th>
                        </tr>
                    </thead>

                    <tbody id="bookBody">
                        <c:forEach items="${sessionScope.cart.items.values()}" var="item">
                            <tr>
                                <td>
                                    <div style="margin-top:30px ;">
                                        <input type="checkbox" class="check" checked="checked" value="${item.bookId}">
                                    </div>
                                </td>

                                <td>
                                    <div style="float: left;margin-bottom: 10px;">
                                        <img src="${pageContext.request.contextPath}/static/img/bookImg/bookimg${item.bookId}.jpg" height="80px">
                                    </div>
                                    <p style="float:left;margin-top:10px;margin-left:20px;margin-bottom:0px;color: rgb(88, 84, 84);font-size: 14px;">
                                        ${item.bookName}（第三版）
                                    </p>
                                </td>

                                <td>
                                    <p style="float: left;margin-top:10px;color: tomato;">
                                        ${item.points}
                                    </p>
                                    <p style="float: left;margin-top:10px;color: tomato;">分</p>
                                </td>

                                <td>
                                    <button class="lessCount"
                                            style="float: left;margin-top:10px;background-color: #f4f4f4;border: 1px solid #dddddd; height:25px;width:25px;">
                                        -
                                    </button>
                                    <div style="float: left;margin-top:10px;">
                                        <input type="text" class="count" value="1" size="1"
                                               style="border:0.5px solid #dddddd;width: 30px;height: 25px;text-align:center;line-height:25px;">
                                    </div>
                                    <button class="plusCount"
                                            style="float: left;margin-top:10px;background-color: #f4f4f4 ;border: 1px solid #dddddd;height:25px;width: 25px;">
                                        +
                                    </button>
                                </td>

                                <td>
                                    <p class="totalPoints" style="float: left;margin-top:10px;color: tomato;">
                                        ${item.totalPoints}
                                    </p>
                                    <p style="float: left;margin-top:10px;color: tomato;">分</p>
                                </td>

                                <td style="font-size: 13px;">
                                    <a href="javascript:void(0);"
                                       style="display: inline-block; margin-top:10px;">加入预约</a><br>
                                    <a href="javascript:void(0);" style="display: inline-block;margin-top: 10px;">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div style="margin-top: 30px;height: 50px; background-color: #f5ebeb;line-height:50px;">
                    <a href="javascript:void(0);" style="float: left; margin-left: 5%;">清空借阅书单</a>
                    <p style="float: left;margin-left: 10%;">共</p>
                    <p id="bookCount" style="float: left;color:#ff0033">${sessionScope.cart.totalCount}</p>
                    <p style="float: left;">本图书</p>
                    <a href="javascript:void(0);" id="borrow" class="btn btn-danger" data-user-id="${sessionScope.user.id}"
                       style="width: 10%; text-align: center; font-size: 16px; font-weight: bold; float: right;margin-right: 5%; margin-top: 6px; color: snow;">
                        结 算
                    </a>
                    <div style="float: right;margin-right: 5%;">
                        <p style="float: left;">共计（积分）：</p>
                        <p id="totalPoints" style="float: left;margin-left: 5px; margin-top: 1px;font-size: 24px; color: tomato;">
                            ${sessionScope.cart.totalPrice}
                        </p>
                        <p style="float: left; margin-top: 1px; font-size: 20px; color: tomato;">
                            分
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-1 column"></div>
    </div>
</div>

</body>

</html>