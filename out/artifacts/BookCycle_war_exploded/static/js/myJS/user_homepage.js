window.addEventListener("load",function () {
    let href = location.href;//获取或设置整个URL
    let index = href.indexOf("user");
    let newHref = href.substr(0, index);

    $(".receive").click(function () {
        let status = $(this).data("status");
        let statusArr = ["准备中", "已出库", "借阅中", "已归还", "已取消"];
        let bookListId = $(this).data("book-list-id");
        if (status != 1) {
            alert("书单[" + bookListId + "]" + statusArr[status] + "，不能接收！");
            return false;
        } else {
            if (confirm("你确定接收书单[" + bookListId + "]吗？")) {
                $.post({
                    url: newHref+"bookList/receive",
                    data: {"bookListId": bookListId},
                    success: function (data) {
                        if (data) {
                            ul.children("td").eq(3).text("借阅中");
                        } else {
                            alert("书单[" + bookListId + "]接收失败！");
                        }
                    }
                });
            }
        }
    });

    $(".cancel").click(function () {
        let status = $(this).data("status");
        let statusArr = ["准备中", "已出库", "借阅中", "已归还", "已取消"];
        let bookListId = $(this).data("book-list-id");
        if (status != 0) {
            alert("书单[" + bookListId + "]" + statusArr[status] + "，不能取消！");
            return false;
        } else {
            if (confirm("你确定取消书单[" + bookListId + "]吗？")) {
                $.post({
                    url: newHref+"bookList/cancel",
                    data: {"bookListId": bookListId},
                    success: function (data) {
                        if (data) {
                            ul.children("td").eq(3).text("已取消");
                        } else {
                            alert("书单[" + bookListId + "]取消失败！");
                        }
                    }
                });
            }
        }

    });

    $(".borrowReservation").click(function () {
        let status = $(this).data("status");
        if (status == 1) {
            let bookId = $(this).data("book-id");
            let userId = $(this).data("user-id");
            $.post({
                url: newHref+"cart/addItem",
                data: {"bookId": bookId, "userId": userId},
                success: function (data) {

                }
            })
        } else {
            return false;
        }
    });

    $(".cancelReservation").click(function () {
        if (confirm("你确定要删除该预约吗？")) {
            let id = $(this).data("id");
            $.post({
                url: newHref+"user/cancelReservation",
                data: {"id": id},
                success: function (data) {

                }
            })
        }
    });
})