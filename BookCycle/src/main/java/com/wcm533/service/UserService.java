package com.wcm533.service;

import com.wcm533.pojo.User;
import org.omg.CORBA.portable.InputStream;

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

    boolean update(User user);

    User getUserById(int id);

    boolean updateUserPassword(String password, int userId);

    boolean replaceHead(byte[] head,String headImgPath,int userId);
}
