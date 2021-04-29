package com.wcm533.service;

import com.wcm533.pojo.BookDetails;

/**
 * @ClassName BookDetailsService
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/28 19:48
 **/
public interface BookDetailsService {

    boolean addBookDetails(BookDetails bd);

    boolean deleteBookDetails(int bookId);

    boolean updateBookDetails(BookDetails bd);

    BookDetails queryBookDetailsByBookId(int bookId);
}
