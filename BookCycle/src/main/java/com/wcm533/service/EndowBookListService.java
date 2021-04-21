package com.wcm533.service;

import com.wcm533.pojo.BookList;
import com.wcm533.pojo.Cart;
import com.wcm533.pojo.EndowBookList;

import java.util.List;

/**
 * @ClassName EndowBookListService
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/20 14:18
 **/
public interface EndowBookListService {

    int createBookList(Cart cart, String userId);

    int deleteBookList(String bookListId);

    int readyBookList(String id);

    int borrowBookList(String id);

    int returnBookList(String id);

    int cancelBookList(String bookListId);

    List<EndowBookList> queryBookLists(int begin, int pageSize);

    List<EndowBookList> queryBookListsByUserId(int userId,int begin,int pageSize);
}
