package com.wcm533.service;

import com.wcm533.pojo.BookList;
import com.wcm533.pojo.BookListItems;
import com.wcm533.pojo.Cart;
import com.wcm533.pojo.ItemsDetails;

import java.util.List;

/**
 * @ClassName BookListService
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/20 14:05
 **/
public interface BookListService {

    String createBookList(Cart cart,int userId);

    boolean deleteBookList(String bookListId);

    boolean readyBookList(String bookListId);

    boolean sendBookList(String bookListId);

    boolean borrowBookList(String bookListId);

    boolean returnBookList(String bookListId);

    boolean cancelBookList(String bookListId);

    List<ItemsDetails> queryBookListItems(String bookListId);

    List<BookList> queryBookLists(int begin,int pageSize);

    List<BookList> queryBookListsByUserId(int userId,int begin,int pageSize);
}
