<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>我要捐书</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/img7.png" type="image/x-icon" />
    <%@include file="../common/head.jsp"%>

    <%--<script type="text/html" id="tr">
        <tr>
            <td>
                <div>
                    <input type="text" class="form-control" placeholder="请输入图书名称：" style="float:left;width: 200px;">
                </div>
            </td>

            <td>
                <div style="margin-top:5px;">
                    <p style="float: left;">
                        12
                    </p>
                    <p style="float: left;">分</p>
                </div>
            </td>

            <td>
                <div>
                    <button class="lessCount"
                            style="float: left;background-color: #dddddd;border: none; height:30px;width:30px;">
                        -
                    </button>
                    <div style="float: left;">
                        <input type="text" class="count" value="1"
                               style="border:0.5px solid #dddddd;width: 40px;height: 30px;text-align:center;line-height:25px;">
                    </div>
                    <button class="plusCount"
                            style="float: left;background-color: #dddddd ;border: none;height:30px;width: 30px;">
                        +
                    </button>
                </div>

            </td>

            <td>
                <div style="margin-top:5px;">
                    <p class="totalPoints" style="float: left;">
                        12
                    </p>
                    <p style="float: left;">分</p>
                </div>
            </td>

            <td style="font-size: 13px;">
                <a href="javascript:void(0);" style="display: inline-block;">重置</a><br>
                <a href="javascript:void(0);" class="deleteTr" style="display: inline-block;margin-top: 5px;">删除</a>
            </td>
        </tr>
    </script>--%>
    <script>
        $(function () {
            let href = location.href;//获取或设置整个URL
            let index = href.indexOf("clientBook");
            let newHref = href.substr(0, index);

            $(".login").click(function () {
                window.open(newHref+"user/getLogin");
                return false;
            });

            $("#userHome").click(function () {
                window.open(newHref+"user/home");
                return false;
            });

            $("#donate").click(function () {
                let userId=$(this).data("user-id");
                let totalPoints=$.trim($("#totalPoints").html());
                let totalCount=$.trim($("#bookCount").html());
                if(userId==""||userId.length==0){
                    if(!confirm("您还没有登录，确定捐赠吗？")){
                        return false;
                    }
                    userId=0;
                }
                let bookId = new Array();
                let bookCount=new Array();
                $("input[name=bookName]").each(function(i){
                    bookId[i] = $(this).data("book-id");
                });
                bookId.push(totalPoints);
                $("input[class=count]").each(function(i){
                    bookCount[i] = $(this).val();
                });
                bookCount.push(totalCount);
                $.get({
                    url: newHref+"endowBookList/create",
                    data: {"userId":userId,"bookId":bookId.toString(),"bookCount":bookCount.toString()},
                    success:function(data){
                        if(userId=="userId"){
                            $(".book-alert").html("捐赠已发起！").addClass("book-alert-success").show().delay(2500).fadeOut();
                        }else {
                            $(".book-alert").html("已为您生成捐赠编号："+data+"<br>您可以在个人主页查看详情！").addClass("book-alert-success").show().delay(2500).fadeOut();
                        }
                    }
                });

            });

            $("table").on("change","input[name=bookName]",function () {
                let bookName =$.trim( $(this).val());
                let tr=$(this).parent().parent().parent();
                let p=tr.children("td").eq(1).children("div").children("p").eq(0);
                let count=tr.children("td").eq(2).children("div").children("div").children("input").val();
                let totalPoints=tr.children("td").eq(3).children("div").children("p:first");
                let input = this;
                $.post({
                    url:newHref+"book/bookPoints",
                    data:{"bookName":bookName},
                    success:function (data) {
                        p.html(data[1]);
                        totalPoints.html(data[1]*count);
                        input.setAttribute("data-book-id",data[0]);
                        totalPointss();
                    }
                });
            });

            $("table").on("click","button[class=lessCount]",function () {
                let tr = $(this).parent().parent().parent();
                let points = parseInt($.trim(tr.children("td").eq(1).children("div").children("p").eq(0)
                    .text()));
                let input = $(this).parent().children("div").eq(0).children("input");
                let count = input.val();
                if (count > 1) {
                    count--;
                }
                input.val(count);
                let totalPoints = count * points;
                tr.children("td").eq(3).children("div").children("p").eq(0).text(totalPoints);
                bookCount();
                totalPointss();
            });

            $("table").on("click","button[class=plusCount]",function () {
                let tr = $(this).parent().parent().parent();
                let points = parseInt($.trim(tr.children("td").eq(1).children("div").children("p").eq(0)
                    .text()));
                let input = $(this).parent().children("div").eq(0).children("input");
                let count = input.val();
                if (count < 100) {
                    count++;
                }
                input.val(count);
                let totalPoints = count * points;
                tr.children("td").eq(3).children("div").children("p").eq(0).text(totalPoints);
                bookCount();
                totalPointss();
            });

            $("table").on("click","a[class=deleteTr]",function () {
                let thisTr=$(this).parent().parent();
                let index=$("tbody tr").index(thisTr);
                if(index!=0){
                    $(this).parent().parent().remove();
                }
                bookCount();
                totalPointss();
            });

            // $("#addTr").click(function () {
            //     let newTr = $("#tr").html();
            //     $("#tb").append(newTr);
            //     bookCount();
            //     totalPointss();
            // });

            $("#addTr").click(function () {
                let tr=document.querySelector("#donate_tr").cloneNode(true);
                $("#tb").append(tr);
                bookCount();
                totalPointss();
            });
        });
        function bookCount(){
            var counts=new Array();
            $("input[class=count]").each(function(){
                counts.push($(this).val());
            });
            let bookCount=0;
            for(var i=0;i<counts.length;i++){
                bookCount+=parseInt(counts[i]);
            }
            $("#bookCount").text(bookCount);
        }

        function totalPointss(){
            var points=new Array();
            $("p[class=totalPoints]").each(function(){
                points.push($.trim($(this).text()));
            });
            let totalPoints=0;
            for(var i=0;i<points.length;i++){
                totalPoints+=parseInt(points[i]);
            }
            $("#totalPoints").text(totalPoints);
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

        a:link {
            color: black;
        }

        #userHome:link{
            color: tomato;
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

                <div style="width: 15%;float: right;color: rgb(240, 100, 100);">
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
                    我要捐书
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
                        您还没有登录！登录后您的捐赠信息将保存到您的帐号中，并且您将获得大量积分！
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
                <table id="ta" class="table table-striped" style="border:1px solid #dddddd ;">
                    <thead>
                    <tr>
                        <th>图书名称</th>
                        <th>积分</th>
                        <th>数量</th>
                        <th>总积分</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="tb">
                    <tr id="donate_tr">
                        <td>
                            <div>
                                <input type="text" class="form-control" name="bookName" placeholder="请输入图书名称："
                                       style="float:left;width: 200px;">
                            </div>
                        </td>

                        <td>
                            <div style="margin-top:5px;">
                                <p class="points" style="float: left;">
                                    12
                                </p>
                                <p style="float: left;">分</p>
                            </div>
                        </td>

                        <td>
                            <div>
                                <button class="lessCount"
                                        style="float: left;background-color: #dddddd;border: none; height:30px;width:30px;">
                                    -
                                </button>
                                <div style="float: left;">
                                    <input type="text" class="count" value="1"
                                           style="border:0.5px solid #dddddd;width: 40px;height: 30px;text-align:center;line-height:25px;">
                                </div>
                                <button class="plusCount"
                                        style="float: left;background-color: #dddddd ;border: none;height:30px;width: 30px;">
                                    +
                                </button>
                            </div>

                        </td>

                        <td>
                            <div style="margin-top:5px;">
                                <p class="totalPoints" style="float: left;">
                                    12
                                </p>
                                <p style="float: left;">分</p>
                            </div>
                        </td>

                        <td style="font-size: 13px;">
                            <a href="javascript:void(0);" style="display: inline-block;">重置</a><br>
                            <a href="javascript:void(0);" class="deleteTr" style="display: inline-block;margin-top: 5px;">删除</a>
                        </td>
                    </tr>
                    </tbody>
                </table>

                <div>
                    <a href="javascript:void(0);" id="addTr">添加</a>
                </div>

                <div style="margin-top: 30px;height: 50px; background-color: #f5ebeb;line-height:50px;">
                    <a href="javascript:void(0);" style="float: left; margin-left: 5%;">清空捐书单</a>
                    <p style="float: left;margin-left: 10%;">共</p>
                    <p id="bookCount" style="float: left;color:#ff0033">1</p>
                    <p style="float: left;">本图书</p>
                    <a href="javascript:void(0);" id="donate" class="btn btn-danger" data-user-id="${sessionScope.user.id}"
                       style="width: 15%; text-align: center; font-size: 16px; font-weight: bold; float: right;margin-right: 5%; margin-top: 6px; color: snow;">
                        立即捐书
                    </a>
                    <div style="float: right;margin-right: 5%;">
                        <p style="float: left;">本次捐书预计可获得积分：</p>
                        <p id="totalPoints" style="float: left;margin-left: 5px; margin-top: 1px;font-size: 24px; color: tomato;">
                            12
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