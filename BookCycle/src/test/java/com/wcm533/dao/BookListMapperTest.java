package com.wcm533.dao;

import com.wcm533.pojo.BookList;
import com.wcm533.utils.MybatisUtils;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;

/**
 * @ClassName BookListMapperTest
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/05 19:19
 **/
public class BookListMapperTest extends TestCase {

    public void testAddBookList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookListMapper mapper = sqlSession.getMapper(BookListMapper.class);
        String bookListId=System.currentTimeMillis()+"1914";
        BookList bookList = new BookList(bookListId,new Date(),38,1914);
        int i = mapper.addBookList(bookList);
        System.out.println(i);
        sqlSession.close();
    }

    public void testDeleteBookList() {
    }

    public void testChangeBookListStatus() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookListMapper mapper = sqlSession.getMapper(BookListMapper.class);
        int i = mapper.changeBookListStatus("16176247950501914", 2);
        System.out.println(i);
        sqlSession.close();
    }

    public void testQueryAllBookLists() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookListMapper mapper = sqlSession.getMapper(BookListMapper.class);
        List<BookList> bookLists = mapper.queryAllBookLists();
        for (BookList bookList : bookLists) {
            System.out.println(bookList);
        }
        sqlSession.close();
    }

    public void testQueryBooksByUserId() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookListMapper mapper = sqlSession.getMapper(BookListMapper.class);
        List<BookList> bookLists = mapper.queryBooksByUserId(1914);
        for (BookList bookList : bookLists) {
            System.out.println(bookList);
        }
        sqlSession.close();
    }

    public void testQueryBookByBookListId() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookListMapper mapper = sqlSession.getMapper(BookListMapper.class);
        BookList bookList = mapper.queryBookByBookListId("16176247950501914");
        System.out.println(bookList);
        sqlSession.close();
    }
}