package com.wcm533.dao;

import com.wcm533.pojo.BookDetails;
import com.wcm533.utils.MybatisUtils;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;

/**
 * @ClassName BookDetailsMapperTest
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/05 16:38
 **/
public class BookDetailsMapperTest extends TestCase {

    public void testAddBookDetails() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookDetailsMapper mapper = sqlSession.getMapper(BookDetailsMapper.class);
        BookDetails bd = new BookDetails(53,"第五版","高等教育出版社");
        int i = mapper.addBookDetails(bd);
        System.out.println(i);
        sqlSession.close();
    }

    public void testDeleteBookDetails() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookDetailsMapper mapper = sqlSession.getMapper(BookDetailsMapper.class);
        int i = mapper.deleteBookDetails(53);
        System.out.println(i);
        sqlSession.close();
    }

    public void testUpdateBookDetails() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookDetailsMapper mapper = sqlSession.getMapper(BookDetailsMapper.class);
        BookDetails bd = new BookDetails(53,"第六版","高等教育出版社");
        int i = mapper.updateBookDetails(bd);
        System.out.println(i);
        sqlSession.close();
    }

    public void testQueryBookDetailsByBookId() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BookDetailsMapper mapper = sqlSession.getMapper(BookDetailsMapper.class);
        BookDetails bd = mapper.queryBookDetailsByBookId(53);
        System.out.println(bd);
        sqlSession.close();
    }
}