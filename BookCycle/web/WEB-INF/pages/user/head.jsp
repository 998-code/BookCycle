<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <meta charset="utf-8"> 
   <title>个人中心</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/userImg/img${sessionScope.user.id}${sessionScope.user.headImgPath}" type="image/x-icon" />
    <%@include file="../common/head.jsp"%>
    <script src="${pageContext.request.contextPath }/static/js/myJS/homepage.js"></script>

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
                        <span href="#" class="list-group-item ulSpan" >个人中心</span>
                        <a href="javascript:void(0);" onclick="myHome()" class="list-group-item"><span class="glyphicon glyphicon-home">&nbsp;首&nbsp;&nbsp;页</span></a>
                        <a href="javascript:void(0);" onclick="myInformation()" class="list-group-item"><span class="glyphicon glyphicon-user">&nbsp;我的信息</span></a>
                        <a href="javascript:void(0);" onclick="myHead()" class="list-group-item active"><span class="glyphicon glyphicon-picture">&nbsp;我的头像</span></a>
                        <a href="javascript:void(0);" onclick="myBookList()" class="list-group-item"><span class="glyphicon glyphicon-book">&nbsp;我的书单</span></a>
                        <a href="javascript:void(0);" onclick="myPoints()" class="list-group-item"><span class="glyphicon glyphicon-usd">&nbsp;我的积分</span></a>
                        <a href="javascript:void(0);" onclick="myBespeak()" class="list-group-item"><span class="glyphicon glyphicon-time">&nbsp;我的预约</span></a>
                    </ul>
                </div>
                
                <div style="float: right; width: 80%;height: 620px; border: 1px solid #dddddd;">
                    <div style="width: 100%; height: 49px;padding-top:0; border-bottom: 1px solid #dddddd;">
                        <div style="width: 5px;height: 20px;float: left; margin-left: 30px; margin-top: 15px; border-radius: 2px;background-color: rgb(63, 164, 247);"></div>
                        <span style="float: left; color: rgb(63, 164, 247);margin-top: 15px;margin-left: 5px;">
                            我的头像
                        </span>
                        <span class="errorHand" style="float: left; color: red;margin-top: 15px;margin-left: 50px;">
                            ${sessionScope.errorHand}
                        </span>
                    </div>

                    <div style="margin-top: 20px;">
                        <div style="width: 200px; height: 200px; margin: 0 auto; border-radius: 50%; border: 1px solid #dddddd;">
                            <img style=" height: 50%; width: 50%; margin-top: 50px; margin-left: 50px; border-radius: 50%; " src="${pageContext.request.contextPath}/static/img/userImg/img${sessionScope.user.id}${sessionScope.user.headImgPath}" alt="">
                            <a href="#" data-toggle="modal" data-target="#myModal">
                                <div style="height:35%;width: 35%; border-radius: 50%;background-color: skyblue;">
                                    <span style="float: left; margin-left: 20px;margin-top: 15px;color: snow;">更换<br>头像</span>
                                </div>
                            </a>
                            <!-- 模态框（Modal） -->
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="myModalLabel">更换头像</h4>
                                        </div>
                                        <div class="modal-body">
                                            <form action="${pageContext.request.contextPath}/user/updateHead" enctype="multipart/form-data" method="post">
                                                <input type="file" name="file"><br>
                                                <input type="submit" value="提交">
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                            <button type="button"  class="btn btn-primary" data-dismiss="modal">确定</button>
                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal -->
                            </div>
                        </div>
                    </div>

                    <div style="margin-top: 40px; border-top: 1px solid #dddddd;">
                        <div style="width: 100%; height: 50px;padding-top:0;">
                            <div style="width: 5px;height: 20px;float: left; margin-left: 30px; margin-top: 15px; border-radius: 2px;"></div>
                            <span style="float: left; color: rgb(63, 164, 247);margin-top: 15px;margin-left: 5px;">历史头像</span>
                        </div>

                        <div style="margin-top: 20px;">
                            <div class="col-sm-6 col-md-3" >
                                <a href="#" class="thumbnail" style="border-radius: 50%;">
                                    <img style="width: 150px;height: 150px; border-radius: 50%;" src="${pageContext.request.contextPath}/static/img/userImg/img${sessionScope.user.id}${sessionScope.user.headImgPath}" alt="">
                                </a>

                                <a class="btn btn-primary" style="display: inline-block; width: 100px; margin-left: 30px;">换上</a>
                            </div>
                            <div class="col-sm-6 col-md-3" >
                                <a href="#" class="thumbnail" style="border-radius: 50%;">
                                    <img style="width: 150px;height: 150px; border-radius: 50%;" src="${pageContext.request.contextPath}/static/img/userImg/img${sessionScope.user.id}${sessionScope.user.headImgPath}" alt="">
                                </a>

                                <a class="btn btn-primary" style="display: inline-block; width: 100px; margin-left: 30px;">换上</a>
                            </div>
                            <div class="col-sm-6 col-md-3" >
                                <a href="#" class="thumbnail" style="border-radius: 50%;">
                                    <img style="width: 150px;height: 150px; border-radius: 50%;" src="${pageContext.request.contextPath}/static/img/userImg/img${sessionScope.user.id}${sessionScope.user.headImgPath}" alt="">
                                </a>

                                <a class="btn btn-primary" style="display: inline-block; width: 100px; margin-left: 30px;">换上</a>
                            </div>
                            <div class="col-sm-6 col-md-3" >
                                <a href="#" class="thumbnail" style="border-radius: 50%;">
                                    <img style="width: 150px;height: 150px; border-radius: 50%;" src="${pageContext.request.contextPath}/static/img/userImg/img${sessionScope.user.id}${sessionScope.user.headImgPath}" alt="">
                                </a>

                                <a class="btn btn-primary" style="display: inline-block; width: 100px; margin-left: 30px;">换上</a>
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