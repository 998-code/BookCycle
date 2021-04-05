package com.wcm533.dao;

import com.wcm533.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/05 12:01
 **/
public interface UserMapper {

    /**
     * 在注册时，将用户信息插入数据库
     * @param user
     * @return {@link null}
     * @throws
     * @author 吴超民
     * @date 2020/9/6 19:26
     */
    int insertUser(User user);

    /**
     * 通过id删除用户
     * @param id
     * @return
     */
    int deleteUser(@Param("id") int id);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 更换用户头像
     * @param headImg
     * @return
     */
    int replaceHead(@Param("headImg") byte[] headImg,@Param("id") int id);

    /**
     * 查询所有用户
     * @return
     */
    List<User> queryAllUsers();

    /**
     * 查询用户名包含该字段的所有用户
     * @param username
     * @return
     */
    List<User> queryUsersByUsername(@Param("username") String username);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User queryUserById(@Param("id") int id);

    /**
     * 在注册时，查询用户名是否存在
     * @param username
     * @return {@link User}
     * @throws
     * @author 吴超民
     * @date 2020/9/6 19:25
     */
    User queryUserByUsername(@Param("username") String username);

    /**
     * 在登录时，验证账号、密码正确性
     * @param username
     * @param password
     * @return {@link User}
     * @throws
     * @author 吴超民
     * @date 2020/9/6 19:27
     */
    User queryUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 查询用户总人数
     * @return
     */
    int queryUserCount();

    /**
     * 使用分页查询所有用户
     * @param begin
     * @param pageSize
     * @return
     */
    List<User> queryUserForPage(@Param("begin") int begin,@Param("pageSize") int pageSize);
}
