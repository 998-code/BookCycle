package com.wcm533.dao;

import com.wcm533.pojo.BookList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName BookListMapper
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/05 18:52
 **/
public interface BookListMapper {

    /**
     * 保存书单
     * @param bookList
     * @return
     */
    int addBookList(BookList bookList);

    /**
     * 删除书单
     * @param bookListId
     * @return
     */
    int deleteBookList(@Param("bookListId") String bookListId);

    /**
     * 修改书单状态
     * @param bookListId
     * @param status
     * @return
     */
    int changeBookListStatus(@Param("bookListId") String bookListId,@Param("status") int status);

    /**
     * 查询所有书单
     * @return
     */
    List<BookList> queryAllBookLists();

    /**
     * 通过分页查询书单
     * @param begin
     * @param pageSize
     * @return
     */
    List<BookList> queryAllBookListsByPage(@Param("begin") int begin,@Param("pageSize") int pageSize);

    /**
     * 根据用户id查询所有书单
     * @param userId
     * @param begin
     * @param pageSize
     * @return
     */
    List<BookList> queryBookListsByUserId(@Param("userId") int userId, @Param("begin") int begin,@Param("pageSize") int pageSize);

    /**
     * 根据书单id查询书单
     * @param bookListId
     * @return
     */
    BookList queryBookByBookListId(@Param("bookListId") String bookListId);
}
