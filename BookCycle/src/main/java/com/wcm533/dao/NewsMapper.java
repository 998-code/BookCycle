package com.wcm533.dao;

import com.wcm533.pojo.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName NewsMapper
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/06 19:53
 **/
public interface NewsMapper {

    /**
     * 添加新闻
     * @param news
     * @return
     */
    int addNews(News news);

    /**
     * 根据id删除新闻
     * @param id
     * @return
     */
    int deleteNews(@Param("id") int id);

    /**
     * 修改新闻内容
     * @param news
     * @return
     */
    int updateNews(News news);

    /**
     * 查询所有新闻
     * @return
     */
    List<News> queryAllNews();

    /**
     * 根据id查询新闻
     * @param id
     * @return
     */
    News queryNewsById(@Param("id") int id);

    /**
     * 根据标题模糊查询新闻
     * @param title
     * @return
     */
    List<News> queryNewsByTitle(@Param("title") String title);
}
