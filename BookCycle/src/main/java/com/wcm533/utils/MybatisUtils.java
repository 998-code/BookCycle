package com.wcm533.utils;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName MybatisUtils
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/02/08 15:48
 **/
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            /*获取sqlSessionFactory对象*/
            /*从配置文件中读取配置信息*/
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*从sqlSessionFactory中获取sqlSession实例
     * sqlSession实例中包含了对数据库的所有操作方法*/
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession(true);
    }
}