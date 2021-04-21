package com.wcm533.service.impl;

import com.wcm533.dao.BookMapper;
import com.wcm533.dao.EndowBookListItemsMapper;
import com.wcm533.dao.EndowBookListMapper;
import com.wcm533.pojo.Cart;
import com.wcm533.pojo.EndowBookList;
import com.wcm533.service.EndowBookListService;

import java.util.List;

/**
 * @ClassName EndowBookListServiceImpl
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/20 20:10
 **/
public class EndowBookListServiceImpl implements EndowBookListService {

    private EndowBookListMapper endowBookListMapper;
    private EndowBookListItemsMapper endowBookListItemsMapper;
    private BookMapper bookMapper;

    public void setEndowBookListMapper(EndowBookListMapper endowBookListMapper) {
        this.endowBookListMapper = endowBookListMapper;
    }

    public void setEndowBookListItemsMapper(EndowBookListItemsMapper endowBookListItemsMapper) {
        this.endowBookListItemsMapper = endowBookListItemsMapper;
    }

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public int createBookList(Cart cart, String userId) {
        return 0;
    }

    @Override
    public int deleteBookList(String bookListId) {
        return 0;
    }

    @Override
    public int readyBookList(String id) {
        return 0;
    }

    @Override
    public int borrowBookList(String id) {
        return 0;
    }

    @Override
    public int returnBookList(String id) {
        return 0;
    }

    @Override
    public int cancelBookList(String bookListId) {
        return 0;
    }

    @Override
    public List<EndowBookList> queryBookLists(int begin, int pageSize) {
        return null;
    }

    @Override
    public List<EndowBookList> queryBookListsByUserId(int userId, int begin, int pageSize) {
        return endowBookListMapper.queryBookListsByUserId(userId,begin,pageSize);
    }
}
