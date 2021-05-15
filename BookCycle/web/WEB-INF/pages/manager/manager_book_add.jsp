<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>添加图书</title>
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
                }
                return flag;
            });
        });
        window.addEventListener("load", function () {
            let inputs = document.querySelectorAll(".bookInfo");
            for(let i=0;i<inputs.length;i++){
                if(inputs[i].value==""||inputs[i].value.length==0){
                    let p = inputs[i].parentElement.parentElement.firstElementChild;
                    p.style.color="red";
                }else {
                    let p = inputs[i].parentElement.parentElement.firstElementChild;
                    p.style.color="snow";
                }
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
        });
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
            <form action="${pageContext.request.contextPath}/manager/addBook" method="post" class="form-horizontal" role="form">
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">图书名称</label>
                    <div class="col-sm-10" style="width: 80%">
                        <input type="text" class="bookInfo form-control" name="name" placeholder="请输入图书名称">
                    </div>
                </div>
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">图书作者</label>
                    <div class="col-sm-10" style="width: 80%">
                        <input type="text" class="bookInfo form-control" name="author" placeholder="请输入图书作者">
                    </div>
                </div>
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">图书总数</label>
                    <div class="col-sm-10" style="width: 80%">
                        <input type="number" class="bookInfo form-control" name="stock" placeholder="请输入图书总数">
                    </div>
                </div>
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">借出数目</label>
                    <div class="col-sm-10" style="width: 80%">
                        <input type="number" class="bookInfo form-control" name="loan" placeholder="请输入已借出的数目">
                    </div>
                </div>
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">图书积分</label>
                    <div class="col-sm-10" style="width: 80%">
                        <input type="number" class="bookInfo form-control" name="points" placeholder="请输入图书积分">
                    </div>
                </div>
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">图书版次</label>
                    <div class="col-sm-10" style="width: 80%">
                        <input type="text" class="bookInfo form-control" name="edition" placeholder="请输入图书版次">
                    </div>
                </div>
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">出版时间</label>
                    <div class="col-sm-10" style="width: 80%">
                        <input type="date" class="bookInfo form-control" id="datePublication" name="datePublication" placeholder="请输入出版时间">
                    </div>
                </div>
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">出版社</label>
                    <div class="col-sm-10" style="width: 80%">
                        <input type="text" class="bookInfo form-control" name="bookConcern" placeholder="请输入出版社">
                    </div>
                </div>
                <div class="form-group">
                    <p style="float: left;color: snow;font-size: 16px;">*</p>
                    <label class="col-sm-2 control-label">简要描述</label>
                    <div class="col-sm-10" style="width: 80%">
                        <textarea class="bookInfo form-control" rows="7" name="summary" placeholder="请输入图书概述">

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