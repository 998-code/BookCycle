package com.wcm533.controller.manager;

import com.wcm533.pojo.BookList;
import com.wcm533.pojo.Page;
import com.wcm533.pojo.User;
import com.wcm533.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ManagerUserController
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/05/18 22:09
 **/

@Controller
@RequestMapping("/manager")
public class ManagerUserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserServiceImpl userService;

    @GetMapping("/userByAuthority/{authority}")
    public String status(@PathVariable int authority, int pageNo, Model model){
        Page<User> userPage = userService.queryUsersByAuthority(pageNo, Page.PAGE_MANAGER_SIZE, authority);
        model.addAttribute("userPage",userPage);
        return "manager/manager_user";
    }

    @GetMapping("/searchUser/{info}")
    public String search(@PathVariable String info, int pageNo, Model model){
        Page<User> userPage = userService.queryUserssByInfo(pageNo, Page.PAGE_MANAGER_SIZE, info);
        model.addAttribute("info",info);
        model.addAttribute("userPage",userPage);
        return "manager/manager_user";
    }
}
