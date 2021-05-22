package com.wcm533.controller.book;

import com.wcm533.pojo.Book;
import com.wcm533.pojo.Page;
import com.wcm533.service.impl.BookServiceImpl;
import com.wcm533.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName indexController
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/26 20:21
 **/

@Controller
@RequestMapping("/clientBook")
public class indexController {

    @Autowired
    @Qualifier("bookServiceImpl")
    private BookServiceImpl bookService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping("/page")
    public String clientBook(String pageNo, Model model){
        int No = WebUtils.parseInt(pageNo, 1);
        Page<Book> pageBook = bookService.queryBooksByPage(No,Page.PAGE_INDEX_SIZE);
        pageBook.setPageUrl("/client/page?pageNo=1");
        model.addAttribute("pageBook",pageBook);
        return "client/index";
    }

    @RequestMapping("/getDonate")
    public String getDonate(){

        return "client/donate_book";
    }

   /* @RequestMapping("/getIndex")
    public String index(){

        return "client/index";
    }*/
}
