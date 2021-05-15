package com.wcm533.service;

import com.wcm533.pojo.Book;
import com.wcm533.pojo.Page;

import java.util.List;

/**
 * @ClassName BookService
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/27 19:20
 **/
public interface BookService {

    boolean addBook(Book book);

    boolean deleteBook(int bookId);

    boolean updateBook(Book book);

    boolean updateBookNumber(int bookId);

    Book queryBookByName(String bookName);

    Book queryBookById(int bookId);

    List<Book> queryBooks();

    Page<Book> queryBooksByPage(int pageNo, int pageSize);

    Page<Book> pageByClassification(int pageNo, int pageSize, int classification);

    Page<Book> queryBooksByInfo(int pageNo, int pageSize,String info);
}
