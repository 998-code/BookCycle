package com.wcm533.controller;

import com.wcm533.pojo.*;
import com.wcm533.service.BookListService;
import com.wcm533.service.impl.BookListServiceImpl;
import com.wcm533.service.impl.EndowBookListServiceImpl;
import com.wcm533.service.impl.ReservationServiceImpl;
import com.wcm533.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @ClassName UserController
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/19 12:09
 **/

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserServiceImpl userService;

    @Autowired
    @Qualifier("BookListServiceImpl")
    private BookListServiceImpl bookListService;

    @Autowired
    @Qualifier("ReservationServiceImpl")
    private ReservationServiceImpl reservationService;

    @Autowired
    HttpServletRequest request;


    @RequestMapping("/getLogin")
    public String getLogin(Model model){

        return "user/user_login";
    }

    @PostMapping("/login")
    public String login(String key,String password,Model model){
        User user = userService.login(key, password);
        if(user!=null){
            request.getSession().setAttribute("user",user);
            List<BookList> bookLists = bookListService.queryBookListsByUserId(user.getId(), 0, BookList.USER_PAGE_SIZE);
            List<ReservationDetails> reservations = reservationService.queryReservationByUserId(user.getId(), 0, 2);
            model.addAttribute("reservations",reservations);
            model.addAttribute("bookLists",bookLists);
            return "user/user_homepage";
        }else {
            model.addAttribute("msg","登录名或密码错误！");
            model.addAttribute("key",key);
            return "user/user_login";
        }

    }

    @RequestMapping("/getEnroll")
    public String getEnroll(){

        return "user/user_enroll";
    }

    /**
     *
     * @param user
     * @param code
     * @param model
     * @return
     */
    @PostMapping("/enroll")
    public String enroll(User user,String code,Model model){
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        if(token!=null&&token.equalsIgnoreCase(code)){
            if(userService.existsUsername(user.getUsername())){
                model.addAttribute("msg","用户名不可用！");
                model.addAttribute("password",user.getPassword());
                model.addAttribute("email",user.getEmail());
                user.setUsername("");
                return "/user/user_enroll";
            }else{
                if(userService.existsEmail(user.getEmail())){
                    model.addAttribute("msg","该 邮箱已被注册！");
                    model.addAttribute("username",user.getUsername());
                    model.addAttribute("password",user.getPassword());
                    user.setEmail("");
                    return "/user/user_enroll";
                }else {
                    userService.enrollUser(user);
                    request.getSession().setAttribute("user",user);
                    return "/user/user_homepage";
                }
            }
        }else {
            model.addAttribute("msg","验证码错误！");
            model.addAttribute("username",user.getUsername());
            model.addAttribute("password",user.getPassword());
            model.addAttribute("email",user.getEmail());
            return "/user/user_enroll";
        }
    }

    @PostMapping("/updateUser")
    public String updateUser(User user,Model model){
        User userById = userService.getUserById(user.getId());
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        String username = userById.getUsername();
        String email = userById.getEmail();
        if(!userService.existsUsername(user.getUsername())||user.getUsername().equals(username)){
            if(!userService.existsEmail(user.getEmail())||user.getEmail().equals(email)){
                userService.update(user);
            }else{
                model.addAttribute("errorEmail","邮箱已被注册！");
            }
        }else{
            model.addAttribute("errorUsername","该昵称已被使用！");
        }
        return "/user/information";
    }

    @RequestMapping("/ajaxUpdatePassword")
    @ResponseBody
    public boolean updateUserPassword(String password,int userId){
        return userService.updateUserPassword(password,userId);
    }

    @RequestMapping("/ajaxUsername")
    @ResponseBody
    public boolean ajaxUsername(String username){
        if(userService.existsUsername(username)){
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping("/ajaxEmail")
    @ResponseBody
    public boolean ajaxEmail(String email){
        if(userService.existsEmail(email)){
            return true;
        }else{
            return false;
        }
    }
}
