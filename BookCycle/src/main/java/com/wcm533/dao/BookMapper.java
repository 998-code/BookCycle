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
    List<Book> queryBookByAuthor(@Param("begin") int begin, @Param("pageSize") int pageSize,@Param("author") String author);

    /**
     * 根据id查询图书
     * @param id
     * @return
     */
    Book queryBookById(@Param("id") int id);

    /**
     * 根据图书名查询图书
     * @param bookName
     * @return
     */
    Book queryBookByBookName(@Param("bookName") String bookName);

    /**
     * 根据图书名称查询图书
     * @param name
     * @return
     */
    List<Book> queryBookByName(@Param("begin") int begin, @Param("pageSize") int pageSize,@Param("name") String name);

    /**
     * 根据图书信息查询所有符合该信息的图书
     * @param begin
     * @param pageSize
     * @param info
     * @return
     */
    List<Book> queryBooksByInfo(@Param("begin") int begin, @Param("pageSize") int pageSize,@Param("info") String info);
    /**
     * 根据图书信息查询符合该信息的图书数量
     * @param info
     * @return
     */
    int queryBookCountByInfo(@Param("info") String info);

    /**
     * 根据图书分类查询图书
     * @param classification
     * @return
     */
    List<Book> queryBookByClassification(@Param("classification") int classification);

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
    List<Book> queryForByPage(@Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * 根据积分区间查询图书总数
     * @param classification
     * @return
     */
    int queryForPageTotalCountByClassification(@Param("classification") int classification);

    /**
     * 根据积分区间查询图书分页
     * @param begin
     * @param pageSize
     * @param classification
     * @return
     */
    List<Book> queryForPageByClassification(@Param("begin") int begin,@Param("pageSize") int pageSize,@Param("classification") int classification);

}
