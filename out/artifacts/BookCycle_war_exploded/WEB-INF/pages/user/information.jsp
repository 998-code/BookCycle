<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <meta charset="utf-8"> 
   <title>个人中心</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/userImg/img${sessionScope.user.id}${sessionScope.user.headImgPath}" type="image/x-icon" />
    <%@include file="../common/head.jsp"%>
    <script src="${pageContext.request.contextPath }/static/js/myJS/homepage.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/myJS/information.js"></script>

</head>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-1 column">
                
            </div>
            <div class="col-md-10 column">                
                <h1>
                    <small><a class="btn btn-primary" href="#">首页</a></small>
                    <!-- <small style="float: right;margin-top:15px;">个人中心</small> -->
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
                        <a href="javascript:void(0);" onclick="myInformation()" class="list-group-item active"><span class="glyphicon glyphicon-user">&nbsp;我的信息</span></a>
                        <a href="javascript:void(0);" onclick="myHead()" class="list-group-item"><span class="glyphicon glyphicon-picture">&nbsp;我的头像</span></a>
                        <a href="javascript:void(0);" onclick="myBookList()" class="list-group-item"><span class="glyphicon glyphicon-book">&nbsp;我的书单</span></a>
                        <a href="javascript:void(0);" onclick="myPoints()" class="list-group-item"><span class="glyphicon glyphicon-usd">&nbsp;我的积分</span></a>
                        <a href="javascript:void(0);" onclick="myBespeak()" class="list-group-item"><span class="glyphicon glyphicon-time">&nbsp;我的预约</span></a>
                    </ul>
                </div>
                
                <div style="float: right; width: 80%;height: 620px; border: 1px solid #dddddd;">
                    <div style="width: 100%; height: 49px;padding-top:0px; border-bottom: 1px solid #dddddd;">
                        <div style="width: 5px;height: 20px;float: left; margin-left: 30px; margin-top: 15px; border-radius: 2px;background-color: rgb(63, 164, 247);"></div>
                        <span style="float: left; color: rgb(63, 164, 247);margin-top: 15px;margin-left: 5px;">我的信息</span>
                    </div>

                    <div style="margin-top: 20px;">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                              <label class="col-sm-2 control-label" style="color: #837d7d;">昵称：</label>
                              <div class="col-sm-10" style="width: 30%;display: inline-block;">
                                <input type="text" id="username" class="form-control" value="${sessionScope.user.username}" data-username="${sessionScope.user.username}">
                              </div>
                              <span class="errorUsername" style="display: inline-block; margin-top: 5px; color: #a19c9c;">
                                   注：修改一次昵称需要消耗6积分
                              </span>
                            </div>

                            <div class="form-group">
                              <label class="col-sm-2 control-label" style="color: #837d7d;">用户ID：</label>
                              <div class="col-sm-10" style="margin-top: 7px;font-size: 16px;color: #a19c9c;">
                                    <span id="userId">${sessionScope.user.id}</span>
                              </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="color: #837d7d;">邮箱：</label>
                                <div class="col-sm-10" style="width: 30%;display: inline-block;">
                                  <input type="text" id="email" class="form-control" value="${sessionScope.user.email}" data-email="${sessionScope.user.email}">
                                </div>
                                <span class="errorEmail" style="display: inline-block; margin-top: 5px; color: #a19c9c;">

                                </span>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="color: #837d7d;">我的地址：</label>
                                <div class="col-sm-10" style="width: 70%;display: inline-block;">
                                    <textarea class="form-control" id="address" rows="4" placeholder="设置你的接收地址。"></textarea>
                                </div>
                                
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="color: #837d7d;">密码：</label>
                                <div class="col-sm-10" style="width: 30%;margin-top: 10px;font-size: 16px;color: #a19c9c;">
                                    <span>********</span>
                                </div>
                                <div style="border:1px solid #dddddd;float: right; margin-top: 10px; margin-right: 40%;border-radius: 5px;padding-left:15px ;padding-right: 15px;">
                                    <a href="javascript:void(0);" data-toggle="modal" data-target="#myPasswordModal">
                                        <span style="color: #837d7d;">修改密码</span>
                                    </a>
                                </div>
                            </div>

                            <div class="form-group" style="margin: 0; margin-top: 20px; border-top: 1px solid #dddddd;">
                                <div class="col-sm-10" style="width: 100%;">
                                    <button type="submit" class="btn btn-primary" id="save" data-username="${sessionScope.user.username}" data-email="${sessionScope.user.email}" style="margin-top: 20px;width: 15%; margin-left: 42%;">
                                        保存
                                    </button>
                                </div>
                            </div>
                        </form>

                        <!-- 模态框（Modal） -->
                        <div class="modal fade" id="myPasswordModal" tabindex="-1" role="dialog" aria-labelledby="myPasswordModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="myPasswordModalLabel">修改密码</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form action="" method="post" class="form-horizontal" role="form" style="margin-left: 5%;">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label" style="color: #837d7d;">原密码：</label>
                                                <div class="col-sm-10" style="width: 50%;display: inline-block;">
                                                    <input type="password" class="form-control" name="oldPassword" id="oldPassword" placeholder="请输入原密码">
                                                </div>
                                                <span class="errorOld" style="display: inline-block; margin-top: 5px; color: red;">

                                                            </span>
                                            </div>
                                            <div class="form-group" style="margin-top: 30px;">
                                                <label class="col-sm-2 control-label" style="color: #837d7d;">新密码：</label>
                                                <div class="col-sm-10" style="width: 50%;display: inline-block;">
                                                    <input type="password" class="form-control" name="newPassword" id="newPassword" placeholder="请输入新密码">
                                                </div>
                                                <span class="errorNew" style="display: inline-block; margin-top: 5px; color: red;">

                                                            </span>
                                            </div>
                                            <div class="form-group" style="margin-top: 30px;">
                                                <label class="col-sm-2 control-label" style="color: #837d7d;">确认密码：</label>
                                                <div class="col-sm-10" style="width: 50%;display: inline-block;">
                                                    <input type="password" class="form-control" name="confirm" id="confirm" placeholder="确认密码">
                                                </div>
                                                <span class="errorConfirm" style="display: inline-block; margin-top: 5px; color: red;">

                                                            </span>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-10">
                                                    <a href="javascript:void(0);" type="submit" id="updatePassword" class="btn btn-primary" data-user-id="${sessionScope.user.id}" style="width: 30%;margin-left: 35%;text-align: center">保存</a>
                                                    <span class="errorUpdate" style="display: inline-block; margin-top: 5px; color: red;">

                                                                </span>
                                                </div>
                                            </div>
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