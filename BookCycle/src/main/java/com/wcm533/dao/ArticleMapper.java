package com.wcm533.dao;

import com.wcm533.pojo.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ArticleMapper
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/06 18:59
 **/
public interface ArticleMapper {

    /**
     * 添加文章
     * @param article
     * @return
     */
    int addArticle(Article article);

    /**
     * 根据id删除文章
     * @param id
     * @return
     */
    int deleteArticle(@Param("id") int id);

    /**
     * 修改文章内容
     * @param article
     * @return
     */
    int updateArticle(Article article);

    /**
     * 查询所有文章
     * @return
     */
    List<Article> queryAllArticles();

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    Article queryArticleById(@Param("id") int id);

    /**
     * 根据书名查询文章
     * @param name
     * @return
     */
    Article queryArticleByName(@Param("name") String name);
}
