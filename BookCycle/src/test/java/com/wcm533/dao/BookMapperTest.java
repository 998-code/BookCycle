package com.wcm533.dao;

import com.wcm533.pojo.Book;
import com.wcm533.utils.MybatisUtils;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @ClassName BookMapperTest
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/03 19:48
 **/
public class BookMapperTest extends TestCase {

    public void testAddBook() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        Book book = new Book(0,"Java核心技术2","宋红康",12,8,5,null);
        int i = mapper.addBook(book);
        System.out.println(i);
        sqlSession.close();
    }

    public void testDeleteBook() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        int i = mapper.deleteBook(75);
        System.out.println(i);
        sqlSession.close();
    }

    public void testUpdateBook() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        Book book = new Book(75,"Java核心技术3","宋红康",12,8,5,null);
        int i = mapper.updateBook(book);
        System.out.println(i);
        sqlSession.close();
    }

    public void testQueryAllBooks() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        List<Book> books = mapper.queryAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
        sqlSession.close();
    }

    public void testQueryBooksByAuthor() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        List<Book> books = mapper.queryBookByAuthor("宋红康");
        for (Book book : books) {
            System.out.println(book);
        }
        sqlSession.close();
    }

    public void testQueryBookById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        Book book = mapper.queryBookById(75);
        System.out.println(book);
        sqlSession.close();
    }

    public void testQueryBookByName() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        List<Book> books = mapper.queryBookByName("Java");
        for (Book book : books) {
            System.out.println(book);
        }
        sqlSession.close();
    }

    public void testQueryForPageTotalCount() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        int i = mapper.queryForPageTotalCount();
        System.out.println(i);
        sqlSession.close();
    }

    public void testQueryForItems() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        List<Book> books = mapper.queryForItems(0, 8);
        for (Book book : books) {
            System.out.println(book);
        }
        sqlSession.close();
    }

    public void testQueryForPageTotalCountByPrice() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        int i = mapper.queryForPageTotalCountByPrice(5, 10);
        System.out.println(i);
        sqlSession.close();
    }

    public void testQueryForItemsByPrice() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        List<Book> books = mapper.queryForItemsByPrice(0, 8, 5, 10);
        for (Book book : books) {
            System.out.println(book);
        }
        sqlSession.close();
    }
}