window.addEventListener("load", function () {
    let href = location.href;//获取或设置整个URL
    let index = href.indexOf("user");
    let newHref = href.substr(0, index);

    $("#donateBook").click(function () {
        window.open(newHref + "clientBook/getDonate");
        return false;
    });

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
                    url: newHref + "bookList/receive",
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
        } else {
            if (confirm("你确定取消书单[" + bookListId + "]吗？")) {
                $.post({
                    url: newHref + "bookList/cancel",
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

    $(".endowCancel").click(function () {
        let status = $(this).data("status");
        let statusArr = ["待处理", "处理中", "已完成", "已取消", "已取消"];
        let bookListId = $(this).data("book-list-id");
        if (status != 0 && status != 1) {
            alert("书单[" + bookListId + "]" + statusArr[status] + "，不能取消！");
            return false;
        } else {
            if (confirm("你确定取消书单[" + bookListId + "]吗？")) {
                $.post({
                    url: newHref + "endowBookList/cancel",
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

    $(".details").click(function () {
        let ul = $(this).parent().parent();
        let bookListId = ul.find("td:first").text();
        let createTime = $.trim(ul.children("td").eq(1).text());
        let points = $.trim(ul.children("td").eq(2).text());
        let status = $.trim(ul.children("td").eq(3).text());
        $("#bookListId").text(bookListId);
        $("#createTime").text(createTime);
        $("#points").text(points);
        $("#status").text(status);
        $.post({
            url: newHref + "details/bookList",
            data: {"bookListId": bookListId},
            success: function (data) {
                var html = "";
                for (var i = 0; i < data.length; i++) {
                    html += "<tr>" +
                        "<td>" + data[i].bookId + "</td>" +
                        "<td>" + data[i].bookName + "</td>" +
                        "<td>" + data[i].bookAuthor + "</td>" +
                        "<td>" + data[i].bookConcern + "</td>" +
                        "<td>" + data[i].edition + "</td>" +
                        "<td>" + data[i].points + "</td>" +
                        "<td>" + data[i].count + "</td>" +
                        "<td>" + data[i].totalPoints + "</td>" +
                        "</tr>"
                }
                $("#items").html(html);
            }
        });
    });

    $("#cancelBorrowing").click(function () {
        let ul = $(this).parent().parent().parent().parent().parent();
        let bookListId = ul.find("td:first").text();
        let status = $.trim(ul.children("td").eq(3).text());
        if (status != "准备中") {
            alert("书单[" + bookListId + "]" + status + "，不能取消！");
        } else {
            if (confirm("你确定取消书单[" + bookListId + "]吗？")) {
                $.post({
                    url: newHref + "bookList/cancel",
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

    $("#confirmReceipt").click(function () {
        let ul = $(this).parent().parent().parent().parent().parent();
        let bookListId = ul.find("td:first").text();
        let status = $.trim(ul.children("td").eq(3).text());
        if (status != "已出库") {
            alert("书单[" + bookListId + "]" + status + "，不能接收！");
        } else {
            if (confirm("你确定接收书单[" + bookListId + "]吗？")) {
                $.post({
                    url: newHref + "bookList/receive",
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

    $(".endowDetails").click(function () {
        let ul = $(this).parent().parent();
        let bookListId = ul.find("td:first").text();
        let createTime = $.trim(ul.children("td").eq(1).text());
        let points = $.trim(ul.children("td").eq(2).text());
        let status = $.trim(ul.children("td").eq(3).text());
        $("#endowBookListId").text(bookListId);
        $("#endowCreateTime").text(createTime);
        $("#endowPoints").text(points);
        $("#endowStatus").text(status);
        $.post({
            url: newHref + "details/endowBookList",
            data: {"bookListId": bookListId},
            success: function (data) {
                var html = "";
                for (var i = 0; i < data.length; i++) {
                    html += "<tr>" +
                        "<td>" + data[i].bookId + "</td>" +
                        "<td>" + data[i].bookName + "</td>" +
                        "<td>" + data[i].bookAuthor + "</td>" +
                        "<td>" + data[i].bookConcern + "</td>" +
                        "<td>" + data[i].edition + "</td>" +
                        "<td>" + data[i].points + "</td>" +
                        "<td>" + data[i].count + "</td>" +
                        "<td>" + data[i].totalPoints + "</td>" +
                        "</tr>"
                }
                $("#endowItems").html(html);
            }
        });
    });

    $("#cancelDonation").click(function () {
        let ul = $(this).parent().parent();
        let status = $.trim(ul.children("td").eq(3).text());
        let bookListId = ul.find("td:first").text();
        if (status != "待处理" && status != "处理中") {
            alert("书单[" + bookListId + "]" + status + "，不能取消！");
        } else {
            if (confirm("你确定取消书单[" + bookListId + "]吗？")) {
                $.post({
                    url: newHref + "endowBookList/cancel",
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
})