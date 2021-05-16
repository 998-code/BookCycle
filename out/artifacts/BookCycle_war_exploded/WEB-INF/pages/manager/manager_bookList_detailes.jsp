<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <meta charset="utf-8"> 
   <title>书单详情</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/bookimg1.jpg" type="image/x-icon"/>
    <%@include file="../common/head.jsp" %>
   
</head>
<body>
    <div class="container">

        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h1>
                        <small><a class="btn btn-primary" href="#">返回上一页</a></small>
                        <small style="float: right;margin-top:15px;">书单详情</small>
                    </h1>
                </div>
            </div>
        </div>
     
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-bordered table-striped" style="font-size: 16px;text-align: center;">
                    <thead>
                    <tr>
                        <th style="text-align: center;">书单编号</th>
                        <th style="text-align: center;">用户ID</th>
                        <th style="text-align: center;">创建时间</th>                       
                        <th style="text-align: center;">总积分</th>
                        <th style="text-align: center;">状态</th>
                        <th style="text-align: center;">操作</th>      
                    </tr>
                    </thead>    
                    <tbody>                       
                        <tr>
                            <td>16176247950501914</td>
                            <td>1914</td>
                            <td>2021-04-05 12:13:15</td>                            
                            <td>38</td>
                            <td>0</td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                                        修改状态
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">未出库</a></li>
                                        <li><a href="#">已出库</a></li>
                                        <li><a href="#">待确认</a></li>
                                        <li><a href="#">已接收</a></li>
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
                    </tr>
                    </thead>    
                    <tbody>                       
                        <tr>
                            <td>1901</td>
                            <td>Java核心技术</td>
                            <td>宋红康</td>
                            <td>机械工业出版社</td>
                            <td>第三版</td>                            
                            <td>10</td>
                        </tr>    
                        <tr>
                            <td>1901</td>
                            <td>Java核心技术</td>
                            <td>宋红康</td>
                            <td>机械工业出版社</td>
                            <td>第三版</td> 
                            <td>10</td>
                        </tr>
                        <tr>
                            <td>1901</td>
                            <td>Java核心技术</td>
                            <td>宋红康</td>
                            <td>机械工业出版社</td>
                            <td>第三版</td> 
                            <td>10</td>
                        </tr>
                        <tr>
                            <td>1901</td>
                            <td>Java核心技术</td>
                            <td>宋红康</td>
                            <td>机械工业出版社</td>
                            <td>第三版</td> 
                            <td>10</td>
                        </tr>                            
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</body>
</html>