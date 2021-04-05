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

    int addBookDetails(BookDetails bd);

    int deleteBookDetails(@Param("bookId") int bookId);

    int updateBookDetails(BookDetails bd);

    BookDetails queryBookDetailsByBookId(@Param("bookId") int bookId);
}
