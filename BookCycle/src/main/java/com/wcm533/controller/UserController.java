package com.wcm533.controller;

import com.wcm533.pojo.User;
import com.wcm533.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

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
    HttpServletRequest reques;


    @RequestMapping("/getLogin")
    public String getLogin(Model model){

        return "user/user_login";
    }

    @PostMapping("/login")
    public String login(String key,String password,Model model){
        User user = userService.login(key, password);
        if(user!=null){
            reques.getSession().setAttribute("user",user);
            System.out.println("登录成功！");
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
        String token = (String) reques.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        reques.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
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
                    reques.getSession().setAttribute("user",user);
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
