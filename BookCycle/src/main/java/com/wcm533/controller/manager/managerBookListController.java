package com.wcm533.controller.manager;

import com.wcm533.pojo.*;
import com.wcm533.service.impl.BookListServiceImpl;
import com.wcm533.service.impl.EndowBookListServiceImpl;
import com.wcm533.service.impl.PointsServiceImpl;
import com.wcm533.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
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
    @Qualifier("bookListServiceImpl")
    private BookListServiceImpl bookListService;

    @Autowired
    @Qualifier("endowBookListServiceImpl")
    private EndowBookListServiceImpl endowBookListService;

    @Autowired
    @Qualifier("pointsServiceImpl")
    private PointsServiceImpl pointsService;

    @GetMapping("/bookListByStatus/{status}")
    public String status(@PathVariable String status, int pageNo, Model model){
        Page<BookList> bookListPage = bookListService.queryBookListsByStatus(pageNo, Page.PAGE_INDEX_SIZE, status);
        model.addAttribute("bookListPage",bookListPage);
        return "manager/manager_bookList";
    }

    @GetMapping("/searchBookList/{info}")
    public String search(@PathVariable String info, int pageNo, Model model){
        Page<BookList> bookListPage = bookListService.queryBookListsByInfo(pageNo, Page.PAGE_INDEX_SIZE, info);
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

        return bookListService.sendBookList(bookListId);
    }

    @RequestMapping("/bookList/confirmReturn")
    @ResponseBody
    public boolean confirmReturn(String bookListId){
        boolean confirmReturn = bookListService.returnBookList(bookListId);
        BookList bookList = bookListService.queryBookListsByBookListId(bookListId);
        if(bookList.getStatus()!=BookList.TIME_OUT){
            Points points = new Points(bookList.getUserId(),new Date(), (int) (bookList.getPoints()*Points.POINTS_PROPORTION),Points.RETURN_BOOKS,bookListId);
            pointsService.addPoints(points);
        }
        return confirmReturn;
    }

    @RequestMapping("/getEndowBookList")
    public String getEndowBookList(String pageNo,Model model){
        int No = WebUtils.parseInt(pageNo, 1);
        Page<EndowBookList> endowBookListPage = endowBookListService.queryBookLists(No, Page.PAGE_MANAGER_SIZE);
        model.addAttribute("endowBookListPage",endowBookListPage);
        return "manager/manager_bookList_endow";
    }

    @GetMapping("/endowBookListByStatus/{status}")
    public String statusEndow(@PathVariable String status, int pageNo, Model model){
        Page<EndowBookList> endowBookListPage = endowBookListService.queryBookListsByStatus(pageNo, Page.PAGE_INDEX_SIZE, status);
        model.addAttribute("endowBookListPage",endowBookListPage);
        return "manager/manager_bookList_endow";
    }

    @GetMapping("/searchEndowBookList/{info}")
    public String searchEndow(@PathVariable String info, int pageNo, Model model){
        Page<EndowBookList> endowBookListPage = endowBookListService.queryBookListsByInfo(pageNo, Page.PAGE_INDEX_SIZE, info);
        model.addAttribute("info",info);
        model.addAttribute("endowBookListPage",endowBookListPage);
        return "manager/manager_bookList_endow";
    }

    @RequestMapping("/details/endowBookList")
    @ResponseBody
    public List<ItemsDetails> endowBookDetails(String bookListId){

        return endowBookListService.queryBookListItems(bookListId);
    }

    @RequestMapping("/endowBookList/startProcessing")
    @ResponseBody
    public boolean startProcessing(String bookListId){
        return endowBookListService.processingBookList(bookListId);
    }

    @RequestMapping("/endowBookList/confirmComplete")
    @ResponseBody
    public boolean confirmComplete(String bookListId){
        boolean confirmComplete = endowBookListService.completedBookList(bookListId);
        EndowBookList endowBookList = endowBookListService.queryBookListsByBookListId(bookListId);
        Points points = new Points(endowBookList.getUserId(),new Date(), endowBookList.getPoints(),Points.DONATE_BOOKS,bookListId);
        pointsService.addPoints(points);
        return confirmComplete;
    }
}
