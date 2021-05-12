package com.wcm533.controller.user;

import com.wcm533.pojo.*;
import com.wcm533.service.impl.*;
import com.wcm533.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
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
    @Qualifier("EndowBookListServiceImpl")
    private EndowBookListServiceImpl endowBookListService;

    @Autowired
    @Qualifier("ReservationServiceImpl")
    private ReservationServiceImpl reservationService;

    @Autowired
    @Qualifier("PointsServiceImpl")
    private PointsServiceImpl pointsService;

    @Autowired
    HttpServletRequest request;


    @RequestMapping("/getLogin")
    public String getLogin(){
        if(request.getSession().getAttribute("user")==null){
            return "user/user_login";
        }
        return "redirect:home";
    }

    @PostMapping("/login")
    public String login(String key,String password,Model model){
        User user = userService.login(key, password);
        if(user!=null){
            if(user.getAuthority()>2){
                return "redirect:manager/manager_home";
            }
            request.getSession().setAttribute("user",user);
            String realPath = request.getServletContext().getRealPath("/static/img/userImg/");
            System.out.println(realPath);
            FileUtils.byteToFile(user.getHeadImg(),realPath,"img"+user.getId()+user.getHeadImgPath());
            List<BookList> bookLists = bookListService.queryBookListsByUserId(user.getId(), 0, BookList.USER_PAGE_SIZE);
            List<EndowBookList> endowBookLists = endowBookListService.queryBookListsByUserId(user.getId(), 0, EndowBookList.USER_PAGE_SIZE);
            List<ReservationDetails> reservations = reservationService.queryReservationByUserId(user.getId(), 0, ReservationDetails.USER_HOMEPAGE_PAGE_SIZE);
            request.getSession().setAttribute("bookLists",bookLists);
            request.getSession().setAttribute("endowBookLists",endowBookLists);
            request.getSession().setAttribute("reservations",reservations);
            return "redirect:home";
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
                    return "redirect:/user/getLogin";
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

    @GetMapping("/signOut")
    public String signOut(){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("bookList");
        session.removeAttribute("endowBookList");
        session.removeAttribute("reservations");
        return "redirect:getLogin";
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public boolean updateUser(String username,String email,String address){
        User user = (User) request.getSession().getAttribute("user");
        user.setUsername(username);
        user.setEmail(email);
        boolean update = userService.update(user);
        Points points = new Points(user.getId(),new Date(),6,2,"");
        pointsService.addPoints(points);
        User userById = userService.getUserById(user.getId());
        request.getSession().setAttribute("user",userById);
        return update;
    }

    @RequestMapping("/ajaxUpdatePassword")
    @ResponseBody
    public boolean updateUserPassword(String oldPassword,String newPassword,String userId){
        if(userId!=null&&userId.length()!=0){
            if(userService.getUserById(Integer.parseInt(userId)).getPassword().equals(oldPassword)){
                return userService.updateUserPassword(newPassword,Integer.parseInt(userId));
            }
        }

        return false;
    }

    @PostMapping("/updateHead")
    public String updateHead(@RequestParam("file") MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        int lastIndexOf = originalFilename.lastIndexOf(".");
        String headImgPath = originalFilename.substring(lastIndexOf);
        User user =(User)request.getSession().getAttribute("user");
        if (file.isEmpty()) {
            request.getSession().setAttribute("errorHead","文件不存在");
            return "user/head";
        }
        try {
            byte[] head = file.getBytes();
            userService.replaceHead(head,headImgPath,user.getId());
            String path = request.getServletContext().getRealPath("/static/img/userImg");
            File realPath = new File(path);
            if (!realPath.exists()){
                realPath.mkdir();
            }
            file.transferTo(new File(realPath +"/"+ "img"+user.getId()+user.getHeadImgPath()));
            request.getSession().setAttribute("errorHead","头像上传成功！");
            request.getSession().setAttribute("user",user);
            return "redirect:head";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "user/head";
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
