package com.wcm533.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @ClassName UserHome
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/19 16:17
 **/

@Controller
@RequestMapping("/user")
public class UserHomeController {

    @RequestMapping("/home")
    public String home(){

        return "user/user_homepage";
    }

    @RequestMapping("/information")
    public String information(){

        return "user/information";
    }

    @RequestMapping("/head")
    public String head(){

        return "user/head";
    }

    @RequestMapping("/bookList")
    public String bookList(){

        return "user/bookList";
    }

    @RequestMapping("/points")
    public String points(){

        return "user/points";
    }

    @RequestMapping("/bespeak")
    public String bespeak(){

        return "user/bespeak";
    }

}
