package com.wcm533.service;

import com.wcm533.pojo.BookList;
import com.wcm533.pojo.BookListItems;
import com.wcm533.pojo.Cart;

import java.util.List;

/**
 * @ClassName BookListService
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/20 14:05
 **/
public interface BookListService {

    String createBookList(Cart cart,int userId);

    int deleteBookList(String bookListId);

    int readyBookList(String bookListId);

    int sendBookList(String bookListId);

    int borrowBookList(String bookListId);

    int returnBookList(String bookListId);

    int cancelBookList(String bookListId);

    List<BookListItems> queryBookListItems(String bookListId);

    List<BookList> queryBookLists(int begin,int pageSize);

    List<BookList> queryBookListsByUserId(int userId,int begin,int pageSize);
}
