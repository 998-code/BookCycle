package com.wcm533.service.impl;

import com.wcm533.dao.UserMapper;
import com.wcm533.pojo.Book;
import com.wcm533.pojo.Page;
import com.wcm533.pojo.User;
import com.wcm533.service.UserService;
import com.wcm533.utils.FileUtils;

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
        if(userMapper.queryUserByUsername(username)!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean existsEmail(String email) {
        if(userMapper.queryUserByEmail(email)!=null){
            return true;
        }
        return false;
    }

    @Override
    public int enrollUser(User user) {
        int i = userMapper.insertUser(user);
        return i;
    }

    @Override
    public boolean update(User user) {
        user.setPoints(user.getPoints()-6);
        if (userMapper.updateUser(user)>0){
            return true;
        }
        return false;
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
        if(userMapper.updateUser(updateUser)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean replaceHead(byte[] head,String headImgPath, int userId) {
//        FileUtils.byteToFile(head,"web\\static\\img\\","img"+userId+headImgPath);
        if(userMapper.replaceHead(head,headImgPath, userId)>0){
            return true;
        }
        return false;
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

}
