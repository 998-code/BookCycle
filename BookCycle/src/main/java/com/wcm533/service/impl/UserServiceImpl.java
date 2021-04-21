package com.wcm533.service.impl;

import com.wcm533.dao.UserMapper;
import com.wcm533.pojo.User;
import com.wcm533.service.UserService;

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
    public int update(User user) {
        User userById = userMapper.queryUserById(user.getId());
        User updateUser = new User();
        updateUser.setUsername(user.getUsername());
        updateUser.setEmail(user.getEmail());
        updateUser.setId(userById.getId());
        updateUser.setPassword(userById.getPassword());
        updateUser.setAuthority(userById.getAuthority());
        updateUser.setPoints(userById.getPoints());
        updateUser.setHeadImg(userById.getHeadImg());
        updateUser.setHeadImgPath(userById.getHeadImgPath());
        int i = userMapper.updateUser(updateUser);
        return i;
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
        int i = userMapper.updateUser(updateUser);
        if(i==1){
            return true;
        }
        return false;
    }
}
