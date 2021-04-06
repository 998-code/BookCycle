package com.wcm533.dao;

import com.wcm533.pojo.BookListItems;
import com.wcm533.pojo.EndowBookListItems;
import com.wcm533.utils.MybatisUtils;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @ClassName EndowBookListItemsMapperTest
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/06 15:07
 **/
public class EndowBookListItemsMapperTest extends TestCase {

    public void testAddBookListItems() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EndowBookListItemsMapper mapper = sqlSession.getMapper(EndowBookListItemsMapper.class);
        EndowBookListItems endowBookListItems = new EndowBookListItems("Javascript从入门到精通",8,"16176935259661914");
        EndowBookListItems endowBookListItems1 = new EndowBookListItems("Java核心技术",10,"16176935259661914");
        EndowBookListItems endowBookListItems2 = new EndowBookListItems("MySQL技术内幕:InnoDB存储引擎",10,"16176935259661914");
        int i = mapper.addBookListItems(endowBookListItems);
        mapper.addBookListItems(endowBookListItems1);
        mapper.addBookListItems(endowBookListItems2);
        System.out.println(i);
        sqlSession.close();
    }

    public void testQueryBookListItemsByBookListId() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EndowBookListItemsMapper mapper = sqlSession.getMapper(EndowBookListItemsMapper.class);
        List<EndowBookListItems> endowBookListItems = mapper.queryBookListItemsByBookListId("16176935259661914");
        for (EndowBookListItems bookListItem : endowBookListItems) {
            System.out.println(bookListItem);
        }
        sqlSession.close();
    }
}