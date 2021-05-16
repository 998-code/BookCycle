<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <meta charset="utf-8"> 
   <title>登录</title>
    <%@include file="../common/head.jsp"%>
   
</head>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                
                <h1>
                    <small><a class="btn btn-primary" href="#">首页</a></small>
                </h1>
                
            </div>
        </div>
        <!-- <div class="row clearfix">
            <div class="col-md-12 column">
                <img src="${pageContext.request.contextPath }/static/img/img7.png" class="img-rounded" style="height: 100px; width:100%;">
            </div>
        </div> -->
        <div class="row clearfix">
            <div class="col-md-5 column">
                <div class="page-header">
                    
                </div>
            </div>
            <div class="col-md-2 column">
                <h1>
                    <p style="text-align: center;">登录</p>  
                </h1>
            </div>
            <div class="col-md-5 column">
                <div class="page-header">

                </div>
            </div>
        </div>
     
        <div class="row clearfix" style="margin-top: 40px;">

            <div class="col-md-1 column">
                
            </div>

            <div class="col-md-5 column" style="border-right:1px solid #dddddd;">
               <div style="margin-right: 5%;">
                <img src="${pageContext.request.contextPath }/static/img/img7.png" class="img-rounded" style="height: 350px; width:100%;">
               </div>
            </div>
            
            <div class="col-md-5 column">
                <div class="alert alert-warning" style="margin-top: 5%;margin-left: 5%;width: 78%; height: 40px;padding-top: 12px; text-align: center;">
                    <span>
                        ${empty requestScope.msg?"请输入用户名和密码":requestScope.msg}
                    </span>
                </div>
                <div style="margin-left: 5%;margin-bottom: 10px;margin-top: 10px;">
                    <a>密码登录</a>
                </div>
                <form action="${pageContext.request.contextPath}/user/login" method="post" class="form-horizontal" role="form" style="margin-left: 5%;">
                    <div class="form-group">
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="key" value="${requestScope.key}" placeholder="请输入账号或邮箱">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 30px;">
                        <div class="col-sm-10">
                            <input type="password" class="form-control" name="password" placeholder="密码">
                        </div>
                    </div>
                    <div style="margin: 10px auto;margin-top: 20px;">
                        <a>忘记密码？</a>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-primary" style="width: 45%;margin-right: 8%;">登录</button>
                            <a href="${pageContext.request.contextPath}/user/getEnroll" class="btn btn-default" style="width: 45%;">注册</a>
                        </div>
                    </div>
                </form>
            </div>

            <div class="col-md-1 column">
               
            </div>

        </div>

    </div>
    <div class="container" style="margin-top: 20px;">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <p style="text-align: center;">登录即代表你同意用户协议和隐私政策</p>
            </div>
        </div>
    </div>
    

</body>
</html>