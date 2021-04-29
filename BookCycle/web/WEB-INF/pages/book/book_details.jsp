<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <meta charset="utf-8"> 
   <title>${requestScope.book.name}</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/bookimg1.jpg" type="image/x-icon" />
    <%@include file="../common/head.jsp"%>
   <script>
       $(function () {
           $("#addBookList").click(function () {
               let bookId = $(this).attr("name");
               alert(bookId);
           });

           $("#borrowNow").click(function () {
               let bookId = $(this).attr("name");
               alert(bookId);
           });

           $("#reservationBook").click(function () {
               let bookId = $(this).attr("name");
               alert(bookId);
           });
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
                        <small style="float: right;margin-top:15px;">书籍详情</small>
                    </h1>
                </div>
            </div>
        </div>
     
        <div class="row clearfix">
            <div class="col-md-1 column">
        
            </div>
            <div class="col-md-5 column">
                <img src="${pageContext.request.contextPath}/static/img/bookimg1.jpg" class="img-thumbnail" style="height: 600px; width: 400px;">
            </div>
            
            <div class="col-md-5 column">
                <div class="alert alert-warning" >
                    <p class="bookError"></p>
                </div>
                <h2>《${requestScope.book.name}》</h2>
                <div style="text-indent:2em;font-size: 16px;color: rgb(102, 100, 100);">
                    <p>《三体2：黑暗森林》是刘慈欣创作的长篇科幻小说，是“地球往事三部曲系列”的第二部作品，
                        于2008年5月首次出版，该书总共有三个章节：“上部 面壁者”、“中部 咒语”和“下部 黑暗森林”。
                    </p>
                    <p>该书主要讲述庞大的三体舰队杀气腾腾直扑太阳系，
                        尖端科技被锁死的地球人面对前所未有的危局，
                        唯一的突破口在于三体人透明的思维。 
                        于是，人类制订了神秘莫测的“面壁计划”，秘密展开对三体人的反击。
                        该小说通过展示异常黑暗的宇宙图景，在更加宏观的角度展示了“适者生存”宇宙进化理论，
                        直刺长期以来的人类中心主义之上的自恋情绪。
                    </p>
                </div>
                <div>
                    <ul class="list-group" style="border: none;">
                        <li class="list-group-item" style="border: none;">
                            <div style="display: inline-block;">书籍名称：</div>
                            <div style="display: inline-block;">${requestScope.book.name}</div>
                        </li>
                        <li class="list-group-item" style="border: none;border-top: 2px dashed #dddddd;">
                            <div style="display: inline-block;">书籍作者：</div>
                            <div style="display: inline-block;">${requestScope.book.author}</div>
                        </li>
                        <li class="list-group-item" style="border: none;border-top: 2px dashed #dddddd;">
                            <div style="display: inline-block;">书籍译者：</div>
                            <div style="display: inline-block;">无</div>
                        </li>
                        <li class="list-group-item" style="border: none;border-top: 2px dashed #dddddd;">
                            <div style="display: inline-block;">书籍版本：</div>
                            <div style="display: inline-block;" class="date">${requestScope.bookDetails.datePublication}</div>
                        </li>
                        <li class="list-group-item" style="border: none;border-top: 2px dashed #dddddd;">
                            <div style="display: inline-block;">出版社：</div>
                            <div style="display: inline-block;">${requestScope.bookDetails.bookConcern}</div>
                        </li>
                        <li class="list-group-item" style="font-size: 24px; font-weight: bolder;border: none;border-top: 2px dashed #dddddd;">
                            <div style="display: inline-block;">所需积分：</div>
                            <div style="display: inline-block;color: orangered;">${requestScope.book.points}</div>
                        </li>
                    </ul>
                </div>

                <div>
                    <button type="button" class="btn btn-danger" id="addBookList" name="${requestScope.book.id}" style="width: 30%;margin-left: 2%;">加入书单</button>
                    <button type="button" class="btn btn-success" id="borrowNow" name="${requestScope.book.id}" style="width: 30%;">立即借阅</button>
                    <button type="button" class="btn btn-info" id="reservationBook" name="${requestScope.book.id}" style="width: 30%;">预约书籍</button>
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