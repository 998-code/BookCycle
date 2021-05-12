package com.wcm533.controller.bookList;

import com.wcm533.pojo.*;
import com.wcm533.service.impl.BookListServiceImpl;
import com.wcm533.service.impl.BookServiceImpl;
import com.wcm533.service.impl.PointsServiceImpl;
import com.wcm533.service.impl.ReservationServiceImpl;
import com.wcm533.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
    @Qualifier("BookServiceImpl")
    private BookServiceImpl bookService;

    @Autowired
    @Qualifier("BookListServiceImpl")
    private BookListServiceImpl bookListService;

    @Autowired
    @Qualifier("PointsServiceImpl")
    private PointsServiceImpl pointsService;

    @Autowired
    HttpServletRequest request;


    @RequestMapping("/create")
    @ResponseBody
    public String createBookList(int userId,String bookId,String bookCount){
        String[] bookIdArr=bookId.split(",");
        String[] bookCountArr=bookCount.split(",");
        String bookListId = bookListService.createBookList(userId, bookIdArr, bookCountArr);
        Date date = WebUtils.parseString(bookListId);
        Points points = new Points(userId,date,Integer.parseInt(bookIdArr[bookIdArr.length-1]),Points.BORROW_BOOKS,bookListId);
        pointsService.addPoints(points);
        return bookListId;
    }

    @RequestMapping("/borrowNow")
    @ResponseBody
    public String borrowNow(int userId,String bookId,String bookCount){
        String[] bookIdArr=bookId.split(",");
        String[] bookCountArr=bookCount.split(",");
        Book book = bookService.queryBookById(Integer.parseInt(bookIdArr[0]));
        if (book.getStock()>book.getLoan()){
            String bookListId = bookListService.createBookList(userId, bookIdArr, bookCountArr);
            Date date = WebUtils.parseString(bookListId);
            Points points = new Points(userId,date,Integer.parseInt(bookIdArr[bookIdArr.length-1]),Points.BORROW_BOOKS,bookListId);
            pointsService.addPoints(points);
            List<BookList> bookLists = bookListService.queryBookListsByUserId(userId, 0, BookList.USER_PAGE_SIZE);
            request.getSession().setAttribute("bookLists",bookLists);
            return bookListId;
        }else {
            return "bookListId";
        }
    }


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
