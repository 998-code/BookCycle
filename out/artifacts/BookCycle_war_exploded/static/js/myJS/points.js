window.addEventListener("load", function () {
    $(".pointsDetails").click(function () {
        let ul = $(this).parent().parent();
        let bookListId = $.trim(ul.children("td").eq(3).html());
        if (bookListId.length === 0) {
            $(".pointsDetailsP").text("");
            $(".pointsDetailsP").text("修改昵称");
        } else {
            $(".pointsDetailsP").text("");
            $(".pointsDetailsP").text("书单编号为：" + bookListId);
        }
    });
    conversionTime();

})

function conversionTime() {
    let times = document.querySelectorAll(".date");
    for (let i = 0; i < times.length; i++) {
        let time = times[i].innerHTML;
        let date = new Date(time);
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        month = month < 10 ? "0" + month : month;
        let dates = date.getDate();
        dates = dates < 10 ? "0" + dates : dates;
        let hour = date.getHours();
        hour = hour < 10 ? "0" + hour : hour;
        let minutes = date.getMinutes();
        minutes = minutes < 10 ? "0" + minutes : minutes;
        let seconds = date.getSeconds();
        seconds = seconds < 10 ? "0" + seconds : seconds;
        times[i].innerHTML = year + "-" + month + "-" + dates + " " + hour + ":" + minutes + ":" + seconds;
    }
}

