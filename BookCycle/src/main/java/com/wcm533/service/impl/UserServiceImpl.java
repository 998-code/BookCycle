package com.wcm533.service.impl;

import com.wcm533.dao.UserMapper;
import com.wcm533.pojo.Page;
import com.wcm533.pojo.User;
import com.wcm533.service.UserService;
import com.wcm533.utils.WebUtils;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/19 12:01
 **/
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;
    public void setUserMapper(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    @Override
    public User login(String key,String password) {
//        if(userMapper.queryUserByIdAndPassword(Integer.parseInt(key),password)!=null){
//            return userMapper.queryUserByIdAndPassword(Integer.parseInt(key),password);
//        }
        if(userMapper.queryUserByUsernameAndPassword(key,password)!=null){
            return userMapper.queryUserByUsernameAndPassword(key,password);
        }
        if(userMapper.queryUserByEmailAndPassword(key,password)!=null){
            return userMapper.queryUserByEmailAndPassword(key,password);
        }
        return null;
    }

    @Override
    public boolean existsUsername(String username) {
        return userMapper.queryUserByUsername(username) != null;
    }

    @Override
    public boolean existsEmail(String email) {
        return userMapper.queryUserByEmail(email) != null;
    }

    @Override
    public int enrollUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public boolean update(User user) {
        user.setPoints(user.getPoints()-6);
        return userMapper.updateUser(user) > 0;
    }

    @Override
    public boolean updateAuthority(int userId, int authority) {
        User user = userMapper.queryUserById(userId);
        user.setAuthority(authority);
        return userMapper.updateUser(user) > 0;
    }

    @Override
    public User getUserById(int id) {
        return userMapper.queryUserById(id);
    }

    @Override
    public boolean updateUserPassword(String password, int userId) {
        User userById = userMapper.queryUserById(userId);
        User updateUser = new User();
        updateUser.setUsername(userById.getUsername());
        updateUser.setEmail(userById.getEmail());
        updateUser.setId(userById.getId());
        updateUser.setPassword(password);
        updateUser.setAuthority(userById.getAuthority());
        updateUser.setPoints(userById.getPoints());
        updateUser.setHeadImg(userById.getHeadImg());
        updateUser.setHeadImgPath(userById.getHeadImgPath());
        return userMapper.updateUser(updateUser) > 0;
    }

    @Override
    public boolean replaceHead(byte[] head,String headImgPath, int userId) {
//        FileUtils.byteToFile(head,"web\\static\\img\\","img"+userId+headImgPath);
        return userMapper.replaceHead(head, headImgPath, userId) > 0;
    }

    @Override
    public Page<User> queryUsersByPage(int pageNo, Integer pageSize) {
        Page<User> page = new Page<User>();

        //设置当前页数据个数
        page.setPageSize(pageSize);
        //设置图书总数
        int pageTotalCount = userMapper.queryUserCount();
        page.setPageTotalCount(pageTotalCount);
        //设置总页码
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        //设置当前页码
        page.setPageNo(pageNo);
        //设置页面图书数据
        int begin=(page.getPageNo()-1)*pageSize;
        List<User> items= userMapper.queryUserForPage(begin,pageSize);
        page.setPageItems(items);
        return page;
    }

    @Override
    public Page<User> queryUsersByAuthority(int pageNo, int pageSize, int authority) {
        Page<User> page = new Page<User>();
        page.setPageSize(pageSize);
        int pageTotalCount = userMapper.queryUserCountByAuthority(authority);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        int begin=(page.getPageNo()-1)*pageSize;
        List<User> items= userMapper.queryUserForPageByAuthority(begin,pageSize,authority);
        page.setPageItems(items);
        return page;
    }

    @Override
    public Page<User> queryUserssByInfo(int pageNo, int pageSize, String info) {
        Page<User> page = new Page<User>();
        page.setPageSize(pageSize);
        int pageTotalCount;
        if(WebUtils.isNum(info)){
            int userId = Integer.parseInt(info);
            pageTotalCount = userMapper.queryUserCountByUserId(userId);
        }else {
            pageTotalCount = userMapper.queryUserCountByUsername(info);
        }
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        int begin=(page.getPageNo()-1)*pageSize;
        List<User> items;
        if(WebUtils.isNum(info)){
            int userId = Integer.parseInt(info);
            items= userMapper.queryUserForPageByUserId(begin,pageSize,userId);
        }else {
            items= userMapper.queryUserForPageByUsername(begin,pageSize,info);
        }
        page.setPageItems(items);
        return page;
    }


}
