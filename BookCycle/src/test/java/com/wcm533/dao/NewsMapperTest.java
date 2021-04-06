package com.wcm533.dao;

import com.wcm533.pojo.News;
import com.wcm533.utils.MybatisUtils;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName NewsMapperTest
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/06 20:02
 **/
public class NewsMapperTest extends TestCase {

    public void testAddNews() throws ParseException {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        NewsMapper mapper = sqlSession.getMapper(NewsMapper.class);
        String str="2012-06-25";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(str);
        News news = new News("《三体3》荣获银河奖","xxx",date,"文学报","");
        int i = mapper.addNews(news);
        System.out.println(i);
        sqlSession.close();
    }

    public void testDeleteNews() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        NewsMapper mapper = sqlSession.getMapper(NewsMapper.class);
        int i = mapper.deleteNews(4);
        System.out.println(i);
        sqlSession.close();
    }

    public void testUpdateNews() throws ParseException {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        NewsMapper mapper = sqlSession.getMapper(NewsMapper.class);
        String str="2012-06-25";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(str);
        News news = new News(1,"《三体》荣获银河奖","李华新",date,"文学报","");
        int i = mapper.updateNews(news);
        System.out.println(i);
        sqlSession.close();
    }

    public void testQueryAllNews() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        NewsMapper mapper = sqlSession.getMapper(NewsMapper.class);
        List<News> news = mapper.queryAllNews();
        for (News news1 : news) {
            System.out.println(news1);
        }
        sqlSession.close();
    }

    public void testQueryNewsById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        NewsMapper mapper = sqlSession.getMapper(NewsMapper.class);
        News news = mapper.queryNewsById(1);
        System.out.println(news);
        sqlSession.close();
    }

    public void testQueryNewsByTitle() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        NewsMapper mapper = sqlSession.getMapper(NewsMapper.class);
        List<News> news = mapper.queryNewsByTitle("三体");
        for (News news1 : news) {
            System.out.println(news1);
        }
        sqlSession.close();
    }
}