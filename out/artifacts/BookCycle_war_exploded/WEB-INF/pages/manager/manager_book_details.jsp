<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>更新图书信息</title>
    <link rel="shortcut icon"
          href="${pageContext.request.contextPath}/static/img/bookImg/bookimg${requestScope.book.id}.jpg"
          type="image/x-icon"/>
    <%@include file="../common/head.jsp" %>
    <script>
        $(function () {
            $("#confirmSave").click(function () {
                let inputs=document.querySelectorAll(".bookInfo");
                let flag=false;
                for(let i=0;i<inputs.length;i++){
                    let value = $.trim(inputs[i].valueOf().value);
                    if(value==""||value.length==0){
                        alert("信息不全，请补全信息！");
                        return false;
                    }
                    let info = $.trim(inputs[i].valueOf().dataset.info);
                    if(value!=info){
                        let date = new Date(info);
                        let year = date.getFullYear();
                        let month = date.getMonth() + 1;
                        month = month < 10 ? "0" + month : month;
                        let dates = date.getDate();
                        dates = dates < 10 ? "0" + dates : dates;
                        let s = year + "-" + month + "-" + dates;
                        if(s!=value){
                            flag=true;
                            break;
                        }
                    }
                }
                return flag;
            });
        });
        window.addEventListener("load", function () {
            let summary = document.querySelector("#summary");
            let info = $.trim(summary.dataset.info);
            summary.innerHTML=info;
            conversionTime();
            let inputs = document.querySelectorAll(".bookInfo");
            for(let i=0;i<inputs.length;i++){
                inputs[i].onblur=function () {
                    if(this.value==""||this.value.length==0){
                        let p = this.parentElement.parentElement.firstElementChild;
                        p.style.color="red";
                    }else {
                        let p = this.parentElement.parentElement.firstElementChild;
                        p.style.color="snow";
                    }
                }
            }
        })

        function conversionTime() {
            let datePublication = $("#datePublication");
            let time = datePublication.data("date");
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
                 class="img-thumbnail" style="height: 400px; width: 400px;">
            <div style="margin-top: 50px">
                <form action="#" enctype="multipart/form-data" method="post" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">上传图片</label>
                        <input type="file" name="file">
                    </div>
                </form>
            </div>
        </div>

        <div class="col-md-5 column">
            <form action="${pageContext.request.contextPath}/manager/updateBook" method="post" class="form-horizontal" role="form">
                <input type="hidden" name="id" value="${requestScope.book.id}">
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">图书名称</label>
                    <div class="col-sm-10" style="width: 80%">
                        <input type="text" class="bookInfo form-control" name="name" data-info="${requestScope.book.name}" placeholder="请输入图书名称"
                               value="${requestScope.book.name}">
                    </div>
                </div>
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">图书作者</label>
                    <div class="col-sm-10" style="width: 80%">
                        <input type="text" class="bookInfo form-control" name="author" data-info="${requestScope.book.author}" placeholder="请输入图书作者"
                               value="${requestScope.book.author}">
                    </div>
                </div>
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">图书总数</label>
                    <div class="col-sm-10" style="width: 80%">
                        <input type="number" class="bookInfo form-control" name="stock" data-info="${requestScope.book.stock}" placeholder="请输入图书总数"
                               value="${requestScope.book.stock}">
                    </div>
                </div>
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">借出数目</label>
                    <div class="col-sm-10" style="width: 80%">
                        <input type="number" class="bookInfo form-control" name="loan" data-info="${requestScope.book.loan}" placeholder="请输入已借出的数目"
                               value="${requestScope.book.loan}">
                    </div>
                </div>
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">图书积分</label>
                    <div class="col-sm-10" style="width: 80%">
                        <input type="number" class="bookInfo form-control" name="points" data-info="${requestScope.book.points}" placeholder="请输入图书积分"
                               value="${requestScope.book.points}">
                    </div>
                </div>
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">图书版次</label>
                    <div class="col-sm-10" style="width: 80%">
                        <input type="text" class="bookInfo form-control" name="edition" data-info="${requestScope.bookDetails.edition}" placeholder="请输入图书版次"
                               value="${requestScope.bookDetails.edition}">
                    </div>
                </div>
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">出版时间</label>
                    <div class="col-sm-10" style="width: 80%">
                        <input type="date" class="bookInfo form-control" id="datePublication" name="datePublication" data-info="${requestScope.bookDetails.datePublication}" placeholder="请输入出版时间"
                               data-date="${requestScope.bookDetails.datePublication}">
                    </div>
                </div>
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">出版社</label>
                    <div class="col-sm-10" style="width: 80%">
                        <input type="text" class="bookInfo form-control" name="bookConcern" data-info="${requestScope.bookDetails.bookConcern}" placeholder="请输入出版社"
                               value="${requestScope.bookDetails.bookConcern}">
                    </div>
                </div>
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">简要描述</label>
                    <div class="col-sm-10" style="width: 80%">
                        <textarea class="bookInfo form-control" rows="7" id="summary" name="summary" data-info="${requestScope.bookDetails.summary}" placeholder="请输入图书概述">

                        </textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" id="confirmSave" class="btn btn-primary">确认保存</button>
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