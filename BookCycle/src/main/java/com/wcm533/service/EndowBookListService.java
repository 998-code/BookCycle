package com.wcm533.service;

import com.wcm533.pojo.BookList;
import com.wcm533.pojo.Cart;
import com.wcm533.pojo.EndowBookList;
import com.wcm533.pojo.ItemsDetails;

import java.util.List;

/**
 * @ClassName EndowBookListService
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/20 14:18
 **/
public interface EndowBookListService {

    String createBookList(int userId,String[] bookIdArr,String[] bookCountArr);

    boolean deleteBookList(String bookListId);

    boolean readyBookList(String bookListId);

    boolean processingBookList(String bookListId);

    boolean completedBookList(String bookListId);

    boolean cancelBookList(String bookListId);

    List<ItemsDetails> queryBookListItems(String bookListId);

    List<EndowBookList> queryBookLists(int begin, int pageSize);

    List<EndowBookList> queryBookListsByUserId(int userId,int begin,int pageSize);
}
