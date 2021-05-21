package com.wcm533.controller.bookList;

import com.wcm533.pojo.*;
import com.wcm533.service.impl.*;
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
    @Qualifier("UserServiceImpl")
    private UserServiceImpl userService;

    @Autowired
    HttpServletRequest request;


    @RequestMapping("/create")
    @ResponseBody
    public String createBookList(int userId,String bookId,String bookCount){
        String[] bookIdArr=bookId.split(",");
        String[] bookCountArr=bookCount.split(",");
        int totalCount = Integer.parseInt(bookCountArr[bookCountArr.length - 1]);
        User user = userService.getUserById(userId);
        if(user.getAuthority()==1&&totalCount>5){
            return "countError5";
        }
        if(user.getAuthority()==2&&totalCount>10){
            return "countError10";
        }
        if(user.getPoints()<Integer.parseInt(bookIdArr[bookIdArr.length-1])){
            return "InsufficientPoints";
        }
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
        User user = userService.getUserById(userId);
        if(user.getPoints()<Integer.parseInt(bookIdArr[bookIdArr.length-1])){
            return "InsufficientPoints";
        }
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
        BookList bookList = bookListService.queryBookListsByBookListId(bookListId);
        User user = (User)request.getSession().getAttribute("user");
        Points points = new Points(user.getId(),new Date(),bookList.getPoints(),Points.CANCEL_BORROW_BOOKS,bookListId);
        pointsService.addPoints(points);
        List<BookList> bookLists = bookListService.queryBookListsByUserId(user.getId(), 0, BookList.USER_PAGE_SIZE);
        User userById = userService.getUserById(user.getId());
        request.getSession().setAttribute("bookLists",bookLists);
        request.getSession().setAttribute("user",userById);
        return cancel;
    }

//    @RequestMapping("/bookDetails")
//    public List<BookListItems> bookDetails(String bookListId){
//
//
//        return bookListService.queryBookListItems(bookListId);
//    }
}
