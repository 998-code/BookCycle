package com.wcm533.service;

import com.wcm533.pojo.User;

/**
 * @ClassName UserService
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/18 16:48
 **/
public interface UserService {

    User login(String key,String password);

    boolean existsUsername(String username);

    boolean existsEmail(String email);

    int enrollUser(User user);


    int update(User user);

    User getUserById(int id);

    boolean updateUserPassword(String password, int userId);
}
