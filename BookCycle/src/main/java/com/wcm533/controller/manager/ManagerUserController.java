package com.wcm533.controller.manager;

import com.wcm533.pojo.BookList;
import com.wcm533.pojo.Page;
import com.wcm533.pojo.User;
import com.wcm533.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    HttpServletRequest request;

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

    @PostMapping("/updateUserAuthority")
    @ResponseBody
    public int updateUserAuthority(int userId,int authority){
        User user = (User) request.getSession().getAttribute("user");
        User userById = userService.getUserById(user.getId());
        if(userId==userById.getId()){
            return 501;
        }
        if(userById.getAuthority()<4&&authority>2){
            return 404;
        }
        if (userService.updateAuthority(userId, authority)){
            return 200;
        }
        return 500;
    }
}
