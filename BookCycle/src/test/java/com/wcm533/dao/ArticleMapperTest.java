package com.wcm533.dao;

import com.wcm533.pojo.Article;
import com.wcm533.utils.MybatisUtils;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ArticleMapperTest
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/06 19:21
 **/
public class ArticleMapperTest extends TestCase {

    public void testAddArticle() throws ParseException {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        String str="2006-04-06";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(str);
        Article article = new Article("三体3","刘慈欣",date,"死神永生","");
        int i = mapper.addArticle(article);
        System.out.println(i);
        sqlSession.close();
    }

    public void testDeleteArticle(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        int i = mapper.deleteArticle(2);
        System.out.println(i);
        sqlSession.close();
    }

    public void testUpdateArticle() throws ParseException {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        String str="2006-04-06";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(str);
        Article article = new Article(1,"三体","刘慈欣",date,"红岸基地","web\\static\\img");
        int i = mapper.updateArticle(article);
        System.out.println(i);
        sqlSession.close();
    }

    public void testQueryAllArticles() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        List<Article> articles = mapper.queryAllArticles();
        for (Article article : articles) {
            System.out.println(article);
        }
        sqlSession.close();
    }

    public void testQueryArticleById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        Article article = mapper.queryArticleById(1);
        System.out.println(article);
        sqlSession.close();
    }

    public void testQueryArticleByName() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        Article article = mapper.queryArticleByName("三体");
        System.out.println(article);
        sqlSession.close();
    }
}