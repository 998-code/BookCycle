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
     * 根据用户id查询所有书单
     * @param userId
     * @return
     */
    List<BookList> queryBooksByUserId(@Param("userId") int userId);

    /**
     * 根据书单id查询书单
     * @param bookListId
     * @return
     */
    BookList queryBookByBookListId(@Param("bookListId") String bookListId);
}
