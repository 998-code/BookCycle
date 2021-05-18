package com.wcm533.controller.manager;

import com.wcm533.pojo.Book;
import com.wcm533.pojo.BookList;
import com.wcm533.pojo.Page;
import com.wcm533.pojo.User;
import com.wcm533.service.impl.BookDetailsServiceImpl;
import com.wcm533.service.impl.BookListServiceImpl;
import com.wcm533.service.impl.BookServiceImpl;
import com.wcm533.service.impl.UserServiceImpl;
import com.wcm533.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName managerHome
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/05/12 22:28
 **/

@Controller
@RequestMapping("/manager")
public class ManagerHomeController {

    @Autowired
    @Qualifier("BookServiceImpl")
    private BookServiceImpl bookService;

    @Autowired
    @Qualifier("BookListServiceImpl")
    private BookListServiceImpl bookListService;

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserServiceImpl userService;

    @RequestMapping("/getBook")
    public String getBook(String pageNo,Model model){
        int No = WebUtils.parseInt(pageNo, 1);
        Page<Book> bookPage = bookService.queryBooksByPage(No, Page.PAGE_MANAGER_SIZE);
        bookPage.setPageUrl("/manager/getBook?pageNo=1");
        model.addAttribute("bookPage",bookPage);
        return "manager/manager_book";
    }

    @RequestMapping("/getBookList")
    public String getBookList(String pageNo,Model model){
        int No = WebUtils.parseInt(pageNo, 1);
        Page<BookList> bookListPage = bookListService.queryBookLists(No, Page.PAGE_MANAGER_SIZE);
        model.addAttribute("bookListPage",bookListPage);
        return "manager/manager_bookList";
    }

    @RequestMapping("/getUser")
    public String getUser(String pageNo,Model model){
        int No = WebUtils.parseInt(pageNo, 1);
        Page<User> userPage = userService.queryUsersByPage(No, Page.PAGE_MANAGER_SIZE);
        model.addAttribute("userPage",userPage);
        return "manager/manager_user";
    }

    @RequestMapping("/getArticle")
    public String getArticle(){

        return "manager/manager_article";
    }
}
