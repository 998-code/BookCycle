package com.wcm533.service;

import com.wcm533.pojo.*;

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

    Page<EndowBookList> queryBookLists(int begin, int pageSize);

    List<EndowBookList> queryBookListsByUserId(int userId,int begin,int pageSize);

    Page<EndowBookList> queryBookListsByInfo(int pageNo, int pageSize, String info);

    Page<EndowBookList> queryBookListsByStatus(int pageNo, int pageSize, String status);

    EndowBookList queryBookListsByBookListId(String bookListId);
}
