package com.wcm533.controller.manager;

import com.wcm533.pojo.Book;
import com.wcm533.pojo.Page;
import com.wcm533.service.impl.BookDetailsServiceImpl;
import com.wcm533.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName managerBook
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/05/13 11:34
 **/

@Controller
@RequestMapping("/manager")
public class managerBook {

    @Autowired
    @Qualifier("BookServiceImpl")
    private BookServiceImpl bookService;

    @Autowired
    @Qualifier("BookDetailsServiceImpl")
    private BookDetailsServiceImpl bookDetailsService;

    @GetMapping("/searchBook/{info}")
    public String search(@PathVariable String info, int pageNo, Model model){
        System.out.println(info);
        Page<Book> bookPage = bookService.queryBooksByInfo(pageNo, Page.PAGE_INDEX_SIZE, info);
        model.addAttribute("info",info);
        model.addAttribute("bookPage",bookPage);
        return "manager/manager_book";
    }
}
