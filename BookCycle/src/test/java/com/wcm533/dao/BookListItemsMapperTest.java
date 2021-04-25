package com.wcm533.dao;

import com.wcm533.pojo.BookListItems;
import com.wcm533.utils.MybatisUtils;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @ClassName BookListItemsMapperTest
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/06 14:30
 **/
public class BookListItemsMapperTest extends TestCase {

    public void testAddBookListItems() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookListItemsMapper mapper = sqlSession.getMapper(BookListItemsMapper.class);
        BookListItems bookListItems = new BookListItems(23,"Javascript从入门到精通",8,"16176247950501914");
        BookListItems bookListItems2 = new BookListItems(24,"编译原理",15,"16176247950501914");
        BookListItems bookListItems3 = new BookListItems(25,"数据库系统概念",15,"16176247950501914");
        int i = mapper.addBookListItems(bookListItems);
        mapper.addBookListItems(bookListItems2);
        mapper.addBookListItems(bookListItems3);
        System.out.println(i);
        sqlSession.close();
    }

    public void testQueryBookListItemsByBookListId() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookListItemsMapper mapper = sqlSession.getMapper(BookListItemsMapper.class);
        List<BookListItems> bookListItems = mapper.queryBookListItemsByBookListId("16176247950501914");
        for (BookListItems bookListItem : bookListItems) {
            System.out.println(bookListItem);
        }
        sqlSession.close();
    }
}