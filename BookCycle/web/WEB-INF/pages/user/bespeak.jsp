<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <meta charset="utf-8"> 
   <title>个人中心</title>
    <link id="favicon" rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/img${sessionScope.user.id}.png" type="image/svg+xml" />
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
                        <a href="javascript:void(0);" onclick="myHand()" class="list-group-item"><span class="glyphicon glyphicon-picture">&nbsp;我的头像</span></a>
                        <a href="javascript:void(0);" onclick="myBookList()" class="list-group-item"><span class="glyphicon glyphicon-book">&nbsp;我的书单</span></a>
                        <a href="javascript:void(0);" onclick="myPoints()" class="list-group-item"><span class="glyphicon glyphicon-usd">&nbsp;我的积分</span></a>
                        <a href="javascript:void(0);" onclick="myBespeak()" class="list-group-item active"><span class="glyphicon glyphicon-time">&nbsp;我的预约</span></a>
                    </ul>
                </div>
                
                <div style="float: right; width: 80%;height: 620px; border: 1px solid #dddddd;">
                    <div style="width: 100%; height: 49px;padding-top:0px; border-bottom: 1px solid #dddddd;">
                        <div style="width: 5px;height: 20px;float: left; margin-left: 30px; margin-top: 15px; border-radius: 2px;background-color: rgb(63, 164, 247);"></div>
                        <span style="float: left; color: rgb(63, 164, 247);margin-top: 15px;margin-left: 5px;">我的预约</span>
                    </div>
                    
                    <div >
                        <div style="height: 50px;padding-top:0px;">
                            <span style="float: left;margin-top: 15px;margin-left: 30px;font-size: 20px;font-weight: bolder;">
                                预约信息：
                            </span>
                        </div>

                        <table class="table table-bordered" style="text-align: center;">
                            <thead>
                                <tr>
                                    <th style="text-align: center;">书籍编号</th>
                                    <th style="text-align: center;">书籍名称</th>
                                    <th style="text-align: center;">书籍状态</th>
                                    <th style="text-align: center;">积分</th>
                                    <th style="text-align: center;">操作</th>
                                </tr>
                            </thead>
                 
                            <tbody>
                                <tr>
                                    <td>1901</td>
                                    <td>Java核心技术</td>
                                    <td>可借阅</td>
                                    <td>8</td>
                                    <td>
                                        <a href="#">借阅</a>|
                                        <a href="#">取消</a>
                                    </td>
                                </tr>    
                                <tr>
                                    <td>1901</td>
                                    <td>Java核心技术</td>
                                    <td>可借阅</td>
                                    <td>8</td>
                                    <td>
                                        <a href="#">借阅</a>|
                                        <a href="#">取消</a>
                                    </td>
                                </tr>    
                                <tr>
                                    <td>1901</td>
                                    <td>Java核心技术</td>
                                    <td>可借阅</td>
                                    <td>8</td>
                                    <td>
                                        <a href="#">借阅</a>|
                                        <a href="#">取消</a>
                                    </td>
                                </tr>    
                                <tr>
                                    <td>1901</td>
                                    <td>Java核心技术</td>
                                    <td>借阅中</td>
                                    <td>8</td>
                                    <td>
                                        <a href="#">借阅</a>|
                                        <a href="#">取消</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>1901</td>
                                    <td>Java核心技术</td>
                                    <td>借阅中</td>
                                    <td>8</td>
                                    <td>
                                        <a href="#">借阅</a>|
                                        <a href="#">取消</a>
                                    </td>
                                </tr>    
                                <tr>
                                    <td>1901</td>
                                    <td>Java核心技术</td>
                                    <td>借阅中</td>
                                    <td>8</td>
                                    <td>
                                        <a href="#">借阅</a>|
                                        <a href="#">取消</a>
                                    </td>
                                </tr>              
                            </tbody>
                        </table>
                        
                        <div class="row clearfix">
                            <div class="col-md-12 colum">
                                <nav aria-label="Page navigation" style="text-align: center;">
                                    <ul class="pagination pagination-sm" style="display: inline-block;float: none;margin: 0em;">
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