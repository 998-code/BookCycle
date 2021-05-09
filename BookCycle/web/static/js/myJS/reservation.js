window.addEventListener("load", function () {
    let href = location.href;//获取或设置整个URL
    let index = href.indexOf("user");
    let newHref = href.substr(0, index);
    $(".borrowReservation").click(function () {
        let status = $(this).data("status");
        if (status == 1) {
            let bookId = $(this).data("book-id");
            let userId = $(this).data("user-id");
            $.post({
                url: newHref + "cart/addItem",
                data: {"bookId": bookId, "userId": userId},
                success: function (data) {

                }
            })
        }
    });

    $(".cancelReservation").click(function () {
        if(confirm("你确定要删除该预约吗？")){
            let id = $(this).data("id")
            $.post({
                url:newHref + "user/cancelReservation",
                data:{"id":id},
                success:function (data) {

                }
            })
        }
    });
    // console.log(href);
    // let host=location.host;//返回主机（域名）
    // console.log(host);
    // let port=location.port;//返回端口号
    // console.log(port);
    // let pathname=location.pathname;//返回路径
    // console.log(pathname);
    // let search=location.search;//返回参数
    //
    // let hash=location.hash;//返回片段 #后面的内容 链接或锚点
})