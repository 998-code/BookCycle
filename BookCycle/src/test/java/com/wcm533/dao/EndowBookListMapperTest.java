package com.wcm533.dao;


import com.wcm533.pojo.BookList;
import com.wcm533.pojo.EndowBookList;
import com.wcm533.utils.MybatisUtils;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;

/**
 * @ClassName EndowBookListMapperTest
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/06 15:07
 **/
public class EndowBookListMapperTest extends TestCase {

    public void testAddBookList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EndowBookListMapper mapper = sqlSession.getMapper(EndowBookListMapper.class);
        String bookListId=System.currentTimeMillis()+"1914";
        EndowBookList endowBookList = new EndowBookList(bookListId,new Date(),28,1914);
        int i = mapper.addBookList(endowBookList);
        System.out.println(i);
        sqlSession.close();
    }

    public void testDeleteBookList() {
    }

    public void testChangeBookListStatus() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EndowBookListMapper mapper = sqlSession.getMapper(EndowBookListMapper.class);
        int i = mapper.changeBookListStatus("16176935259661914", 2);
        System.out.println(i);
        sqlSession.close();
    }

    public void testQueryAllBookLists() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EndowBookListMapper mapper = sqlSession.getMapper(EndowBookListMapper.class);
        List<EndowBookList> endowBookLists = mapper.queryAllBookLists();
        for (EndowBookList endowBookList : endowBookLists) {
            System.out.println(endowBookList);
        }
        sqlSession.close();
    }

    public void testQueryBooksByUserId() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EndowBookListMapper mapper = sqlSession.getMapper(EndowBookListMapper.class);
        List<EndowBookList> endowBookLists = mapper.queryBookListsByUserId(1914,0,2);
        for (EndowBookList endowBookList : endowBookLists) {
            System.out.println(endowBookList);
        }
        sqlSession.close();
    }

    public void testQueryBookByBookListId() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EndowBookListMapper mapper = sqlSession.getMapper(EndowBookListMapper.class);
        EndowBookList endowBookList = mapper.queryBookByBookListId("16176935259661914");
        System.out.println(endowBookList);
        sqlSession.close();
    }
}