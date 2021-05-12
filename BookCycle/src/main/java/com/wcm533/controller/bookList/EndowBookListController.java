package com.wcm533.controller.bookList;

import com.wcm533.pojo.EndowBookList;
import com.wcm533.pojo.Points;
import com.wcm533.pojo.User;
import com.wcm533.service.impl.EndowBookListServiceImpl;
import com.wcm533.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @ClassName EndowBookListController
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/24 19:23
 **/

@Controller
@RequestMapping("/endowBookList")
public class EndowBookListController {

    @Autowired
    @Qualifier("EndowBookListServiceImpl")
    private EndowBookListServiceImpl endowBookListService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping("/create")
    @ResponseBody
    public String createBookList(int userId,String bookId,String bookCount){
        String[] bookIdArr=bookId.split(",");
        String[] bookCountArr=bookCount.split(",");
        String bookListId = endowBookListService.createBookList(userId, bookIdArr, bookCountArr);
        List<EndowBookList> endowBookLists = endowBookListService.queryBookListsByUserId(userId, 0, EndowBookList.USER_PAGE_SIZE);
        request.getSession().setAttribute("endowBookLists",endowBookLists);
        return bookListId;
    }

    @RequestMapping("/cancel")
    @ResponseBody
    public boolean cancel(String bookListId){
        boolean cancel = endowBookListService.cancelBookList(bookListId);
        User user = (User) request.getSession().getAttribute("user");
        List<EndowBookList> endowBookLists = endowBookListService.queryBookListsByUserId(user.getId(), 0, EndowBookList.USER_PAGE_SIZE);
        request.getSession().setAttribute("endowBookLists",endowBookLists);
        return cancel;
    }
}
