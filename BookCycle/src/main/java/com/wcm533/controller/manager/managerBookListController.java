package com.wcm533.controller.manager;

import com.wcm533.pojo.Book;
import com.wcm533.pojo.BookList;
import com.wcm533.pojo.ItemsDetails;
import com.wcm533.pojo.Page;
import com.wcm533.service.impl.BookListServiceImpl;
import com.wcm533.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName managerBookListController
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/05/16 15:56
 **/

@Controller
@RequestMapping("/manager")
public class managerBookListController {

    @Autowired
    @Qualifier("BookServiceImpl")
    private BookServiceImpl bookService;

    @Autowired
    @Qualifier("BookListServiceImpl")
    private BookListServiceImpl bookListService;

    @GetMapping("/searchBookList/{info}")
    public String search(@PathVariable String info, int pageNo, Model model){
        System.out.println(info);
        Page<BookList> bookListPage = bookListService.queryBooksByInfo(pageNo, Page.PAGE_INDEX_SIZE, info);
        model.addAttribute("info",info);
        model.addAttribute("bookListPage",bookListPage);
        return "manager/manager_bookList";
    }

    @RequestMapping("/details/bookList")
    @ResponseBody
    public List<ItemsDetails> bookDetails(String bookListId){

        return bookListService.queryBookListItems(bookListId);
    }

    @RequestMapping("/bookList/outOfStock")
    @ResponseBody
    public boolean outOfStock(String bookListId){
        boolean outOfStock = bookListService.sendBookList(bookListId);

        return outOfStock;
    }

    @RequestMapping("/bookList/confirmReturn")
    @ResponseBody
    public boolean confirmReturn(String bookListId){
        boolean confirmReturn = bookListService.returnBookList(bookListId);

        return confirmReturn;
    }
}
