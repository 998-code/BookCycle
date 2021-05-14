package com.wcm533.controller.manager;

import com.wcm533.pojo.Book;
import com.wcm533.pojo.Page;
import com.wcm533.service.impl.BookDetailsServiceImpl;
import com.wcm533.service.impl.BookServiceImpl;
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
public class managerHome {

    @Autowired
    @Qualifier("BookServiceImpl")
    private BookServiceImpl bookService;

    @RequestMapping("/getBook")
    public String getBook(String pageNo,Model model){
        int No = WebUtils.parseInt(pageNo, 1);
        Page<Book> bookPage = bookService.queryBooksByPage(No, Page.PAGE_MANAGER_SIZE);
        bookPage.setPageUrl("/manager/getBook?pageNo=1");
        model.addAttribute("bookPage",bookPage);
        return "manager/manager_book";
    }

    @RequestMapping("/getBookList")
    public String getBookList(){

        return "manager/manager_bookList";
    }

    @RequestMapping("/getUser")
    public String getUser(){

        return "manager/manager_user";
    }

    @RequestMapping("/getArticle")
    public String getArticle(){

        return "manager/manager_article";
    }
}