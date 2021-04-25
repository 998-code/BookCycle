package com.wcm533.controller.bookList;

import com.wcm533.pojo.EndowBookList;
import com.wcm533.pojo.User;
import com.wcm533.service.impl.EndowBookListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
