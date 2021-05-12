package com.wcm533.controller.manager;

import org.springframework.stereotype.Controller;
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

    @RequestMapping("/getBook")
    public String getBook(){

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
