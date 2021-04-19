package com.wcm533.dao;

import com.wcm533.pojo.User;
import com.wcm533.utils.FileUtils;
import com.wcm533.utils.MybatisUtils;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @ClassName UserMapperTest
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/05 14:04
 **/
public class UserMapperTest extends TestCase {

    public void testInsertUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User( "赵七", "123456", "zhaoliu@qq.com");
        int i = mapper.insertUser(user);
        System.out.println(i);
        sqlSession.close();
    }

    public void testDeleteUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.deleteUser(1919);
        System.out.println(i);
        sqlSession.close();
    }

    public void testUpdateUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(1918, "赵六", "666666", "zhaoliu@qq.com",200,1);
        int i = mapper.updateUser(user);
        System.out.println(i);
        sqlSession.close();
    }

    public void testReplaceHead() throws FileNotFoundException {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //File file = new File("web\\static\\img\\img5.png");
        byte[] headImg = FileUtils.fileToByte("web\\static\\img\\img5.png");

        int i = mapper.replaceHead(headImg, 1918);
        System.out.println(i);
        sqlSession.close();
    }

    public void testQueryAllUsers() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.queryAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    public void testQueryUsersByUsername() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.queryUsersByUsername("赵");
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    public void testQueryUserById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserById(1919);
        System.out.println(user);
        sqlSession.close();
    }

    public void testQueryUserByUsername() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserByUsername("赵六");
        System.out.println(user);
        sqlSession.close();
    }

    public void testQueryUserByEmail() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserByEmail("zhaoliu@qq.com");
        System.out.println(user);
        sqlSession.close();
    }

    public void testQueryUserByIdAndPassword() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserByIdAndPassword(1914, "123456");
        System.out.println(user);
        sqlSession.close();
    }

    public void testQueryUserByEmailAndPassword() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserByEmailAndPassword("zhaoliu@qq.com", "666666");
        System.out.println(user);
        sqlSession.close();
    }

    public void testQueryUserByUsernameAndPassword() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserByUsernameAndPassword("张三", "123456");
        if(user==null){
            System.out.println("用户名或密码错误！");
        }else{
            byte[] headImg = user.getHeadImg();
            File file = new File("web\\static\\img\\img" + user.getId() + user.getHeadImgPath());
            if(!file.exists()){
                FileUtils.byteToFile(headImg,"web\\static\\img\\","img"+user.getId()+user.getHeadImgPath());
            }else {
                System.out.println("文件已存在！");
            }
            System.out.println("登录成功！");
        }
        System.out.println(user);
        sqlSession.close();
    }

    public void testQueryUserCount() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.queryUserCount();
        System.out.println(i);
        sqlSession.close();
    }

    public void testQueryUserForPage() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.queryUserForPage(0,2);
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }
}