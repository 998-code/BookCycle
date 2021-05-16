package com.wcm533.dao;

import com.wcm533.pojo.BookList;
import com.wcm533.pojo.EndowBookList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName EndowBookListMapper
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/06 14:57
 **/
public interface EndowBookListMapper {

    /**
     * 保存书单
     * @param endowBookList
     * @return
     */
    int addBookList(EndowBookList endowBookList);

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
    List<EndowBookList> queryAllBookLists();

    /**
     * 通过分页查询书单
     * @param begin
     * @param pageSize
     * @return
     */
    List<EndowBookList> queryAllBookListsByPage(@Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * 根据用户id查询所有书单
     * @param userId
     * @param begin
     * @param pageSize
     * @return
     */
    List<EndowBookList> queryBookListsByUserId(@Param("userId") int userId, @Param("begin") int begin,@Param("pageSize") int pageSize);

    /**
     * 根据书单id查询书单
     * @param bookListId
     * @return
     */
    EndowBookList queryBookByBookListId(@Param("bookListId") String bookListId);

    /**
     * 查询订单总数
     * @return
     */
    int queryForPageTotalCount();

    /**
     * 根据用户id查询订单总数
     * @return
     */
    int queryForPageTotalCountByUserId(@Param("userId") int userId);

    /**
     * 根据书单状态查询书单
     * @param status
     * @return
     */
    List<EndowBookList> queryBookListsByStatus(@Param("status") int status,@Param("begin") int begin,@Param("pageSize") int pageSize);

    /**
     * 根据书单状态查询订单总数
     * @return
     */
    int queryForPageTotalCountByStatus(@Param("status") int status);
}
