package com.wcm533.controller.manager;

import com.wcm533.pojo.Book;
import com.wcm533.pojo.BookDetails;
import com.wcm533.pojo.Page;
import com.wcm533.service.impl.BookDetailsServiceImpl;
import com.wcm533.service.impl.BookServiceImpl;
import com.wcm533.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/getAddBook")
    public String getAddBook(){

        return "manager/manager_book_add";
    }

    @GetMapping("/searchBook/{info}")
    public String search(@PathVariable String info, int pageNo, Model model){
        System.out.println(info);
        Page<Book> bookPage = bookService.queryBooksByInfo(pageNo, Page.PAGE_INDEX_SIZE, info);
        model.addAttribute("info",info);
        model.addAttribute("bookPage",bookPage);
        return "manager/manager_book";
    }

    @RequestMapping("/bookDetails/{bookId}")
    public String bookDetails(@PathVariable int bookId,Model model){
        Book book = bookService.queryBookById(bookId);
        BookDetails bookDetails = bookDetailsService.queryBookDetailsByBookId(bookId);
        model.addAttribute("book",book);
        model.addAttribute("bookDetails",bookDetails);
        return "manager/manager_book_details";
    }

    @PostMapping("/updateBook")
    public String updateBook(Book book,BookDetails bookDetails,Model model){
        bookDetails.setBookId(book.getId());
        bookService.updateBook(book);
        bookDetailsService.updateBookDetails(bookDetails);
        Book newBook = bookService.queryBookById(book.getId());
        BookDetails newBookDetails = bookDetailsService.queryBookDetailsByBookId(book.getId());
        model.addAttribute("book",newBook);
        model.addAttribute("bookDetails",newBookDetails);
        return "manager/manager_book_details";
    }

    @GetMapping("/deleteBook")
    public String deleteBook(int bookId,String pageNo,Model model){
        System.out.println(bookId);
        System.out.println(pageNo);
        bookService.deleteBook(bookId);
        int No = WebUtils.parseInt(pageNo, 1);
        Page<Book> bookPage = bookService.queryBooksByPage(No, Page.PAGE_MANAGER_SIZE);
        model.addAttribute("bookPage",bookPage);
        return "redirect:getBook?pageNo="+pageNo;
    }
}
