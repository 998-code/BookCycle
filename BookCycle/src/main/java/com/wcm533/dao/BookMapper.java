package com.wcm533.dao;

import com.wcm533.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName BookMapper
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/03 19:06
 **/
public interface BookMapper {

    /**
     * 添加图书
     * @param book
     * @return
     */
    int addBook(Book book);

    /**
     * 删除图书
     * @param id
     * @return
     */
    int deleteBook(@Param("id") int id);

    /**
     * 修改图书
     * @param book
     * @return
     */
    int updateBook(Book book);

    /**
     * 查询所有图书
     * @return
     */
    List<Book> queryAllBooks();

    /**
     * 根据作者查询图书
     * @param author
     * @return
     */
    List<Book> queryBookByAuthor(@Param("author") String author);

    /**
     * 根据id查询图书
     * @param id
     * @return
     */
    Book queryBookById(@Param("id") int id);

    /**
     * 根据图书名称查询图书
     * @param name
     * @return
     */
    List<Book> queryBookByName(@Param("name") String name);

    /**
     * 查询图书总数
     * @return
     */
    int queryForPageTotalCount();

    /**
     * 查询分页图书
     * @param begin
     * @param pageSize
     * @return
     */
    List<Book> queryForItems(@Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * 根据积分区间查询图书总数
     * @param min
     * @param max
     * @return
     */
    int queryForPageTotalCountByPrice(@Param("min") int min,@Param("max") int max);

    /**
     * 根据积分区间查询图书分页
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    List<Book> queryForItemsByPrice(@Param("begin") int begin,@Param("pageSize") int pageSize,@Param("min") int min,@Param("max") int max);
}
