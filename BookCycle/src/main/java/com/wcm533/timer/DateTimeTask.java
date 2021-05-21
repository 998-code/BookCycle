package com.wcm533.timer;

import com.wcm533.pojo.BookList;
import com.wcm533.service.impl.BookListServiceImpl;
import com.wcm533.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @ClassName DateTimeTask
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/05/21 21:53
 **/

@Component
public class DateTimeTask {

    private static long OVER_TIME = 30 * 24 * 60 * 60 * 1000;
    private static long ABOUT_OF_TIME_OUT = 27 * 24 * 60 * 60 * 1000;

    @Autowired
    @Qualifier("BookListServiceImpl")
    private BookListServiceImpl bookListService;

    @Scheduled(cron = "0 0 0 * ?") // 每天0点执行
    public void taskCycle() {
        List<BookList> bookLists = bookListService.queryAllBookLists();
        for (BookList bookList : bookLists) {
            long date = WebUtils.parseString(bookList.getBookListId()).getTime();
            long newDate = new Date().getTime();
            if (newDate-date>ABOUT_OF_TIME_OUT&&newDate-date<OVER_TIME){
                bookListService.aboutOfTimeOutBookList(bookList.getBookListId());
            }else if(newDate-date>OVER_TIME){
                bookListService.overTimeBookList(bookList.getBookListId());
            }
        }
    }
}
