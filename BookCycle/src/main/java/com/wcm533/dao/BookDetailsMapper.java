package com.wcm533.dao;

import com.wcm533.pojo.BookDetails;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName BookDetailsMapper
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/05 16:18
 **/
public interface BookDetailsMapper {

    /**
     * 保存图书详情
     * @param bd
     * @return
     */
    int addBookDetails(BookDetails bd);

    /**
     * 删除图书详情
     * @param bookId
     * @return
     */
    int deleteBookDetails(@Param("bookId") int bookId);

    /**
     * 修改图书详情
     * @param bd
     * @return
     */
    int updateBookDetails(BookDetails bd);

    /**
     * 根据图书id查询图书详情
     * @param bookId
     * @return
     */
    BookDetails queryBookDetailsByBookId(@Param("bookId") int bookId);
}
