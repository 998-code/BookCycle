package com.wcm533.controller.bookList;

import com.wcm533.pojo.BookList;
import com.wcm533.pojo.BookListItems;
import com.wcm533.pojo.User;
import com.wcm533.service.impl.BookListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BookListController
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/24 15:39
 **/

@Controller
@RequestMapping("/bookList")
public class BookListController {

    @Autowired
    @Qualifier("BookListServiceImpl")
    private BookListServiceImpl bookListService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping("/receive")
    @ResponseBody
    public boolean receive(String bookListId){
        boolean receive = bookListService.borrowBookList(bookListId);
        User user = (User)request.getSession().getAttribute("user");
        List<BookList> bookLists = bookListService.queryBookListsByUserId(user.getId(), 0, BookList.USER_PAGE_SIZE);
        request.getSession().setAttribute("bookLists",bookLists);
        return receive;
    }

    @RequestMapping("/cancel")
    @ResponseBody
    public boolean cancel(String bookListId){
        boolean cancel = bookListService.cancelBookList(bookListId);
        User user = (User)request.getSession().getAttribute("user");
        List<BookList> bookLists = bookListService.queryBookListsByUserId(user.getId(), 0, BookList.USER_PAGE_SIZE);
        request.getSession().setAttribute("bookLists",bookLists);
        return cancel;
    }

//    @RequestMapping("/bookDetails")
//    public List<BookListItems> bookDetails(String bookListId){
//
//
//        return bookListService.queryBookListItems(bookListId);
//    }
}
