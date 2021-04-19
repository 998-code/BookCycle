package com.wcm533.controller.user;

import com.wcm533.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserHome
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/19 16:17
 **/

@Controller
@RequestMapping("/user")
public class UserHomeController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserServiceImpl userService;

    @Autowired
    HttpServletRequest reques;

    @RequestMapping("/home")
    public String home(Model model){

        return "user/user_homepage";
    }

    @RequestMapping("/information")
    public String information(Model model){

        return "user/information";
    }

    @RequestMapping("/hand")
    public String hand(Model model){

        return "user/hand";
    }

    @RequestMapping("/bookList")
    public String bookList(Model model){

        return "user/bookList";
    }

    @RequestMapping("/points")
    public String points(Model model){

        return "user/points";
    }

    @RequestMapping("/bespeak")
    public String bespeak(Model model){

        return "user/bespeak";
    }

}
