package com.wcm533.service.impl;

import com.wcm533.dao.BookDetailsMapper;
import com.wcm533.pojo.BookDetails;
import com.wcm533.service.BookDetailsService;

/**
 * @ClassName BookDetailsServiceImpl
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/28 19:51
 **/
public class BookDetailsServiceImpl implements BookDetailsService {

    private BookDetailsMapper bookDetailsMapper;

    public void setBookDetailsMapper(BookDetailsMapper bookDetailsMapper) {
        this.bookDetailsMapper = bookDetailsMapper;
    }

    @Override
    public boolean addBookDetails(BookDetails bd) {
        if(bookDetailsMapper.addBookDetails(bd)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteBookDetails(int bookId) {
        if(bookDetailsMapper.deleteBookDetails(bookId)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateBookDetails(BookDetails bd) {
        BookDetails detailsByBookId = bookDetailsMapper.queryBookDetailsByBookId(bd.getBookId());
        bd.setId(detailsByBookId.getId());
        if(bookDetailsMapper.updateBookDetails(bd)>0){
            return true;
        }
        return false;
    }

    @Override
    public BookDetails queryBookDetailsByBookId(int bookId) {
        BookDetails bookDetails = bookDetailsMapper.queryBookDetailsByBookId(bookId);
        return bookDetails;
    }
}
