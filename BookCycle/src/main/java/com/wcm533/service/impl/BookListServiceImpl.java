package com.wcm533.service.impl;

import com.wcm533.dao.BookListItemsMapper;
import com.wcm533.dao.BookListMapper;
import com.wcm533.dao.BookMapper;
import com.wcm533.pojo.*;
import com.wcm533.service.BookListService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BookListServiceImpl
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/20 14:27
 **/
public class BookListServiceImpl implements BookListService {

    private BookListMapper bookListMapper;
    private BookListItemsMapper bookListItemsMapper;
    private BookMapper bookMapper;

    public void setBookListMapper(BookListMapper bookListMapper) {
        this.bookListMapper = bookListMapper;
    }

    public void setBookListItemsMapper(BookListItemsMapper bookListItemsMapper) {
        this.bookListItemsMapper = bookListItemsMapper;
    }

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public String createBookList(Cart cart, int userId) {
        //生成唯一订单号
        String bookListId=System.currentTimeMillis()+""+userId;
        //创建订单对象
        BookList bookList = new BookList(bookListId, new Date(), cart.getTotalPrice(), userId);
        //保存订单
        bookListMapper.addBookList(bookList);
        //遍历购物车
        for(Map.Entry<Integer, CartItem> entry:cart.getItems().entrySet()){
            //获取商品项
            CartItem cartItem = entry.getValue();
            //创建订单项
            BookListItems bookListItems = new BookListItems( cartItem.getId(), cartItem.getName(), cartItem.getPoints(),  bookListId);
            //保存订单项到数据库
            bookListItemsMapper.addBookListItems(bookListItems);
            //更新库存和销量
            Book book = bookMapper.queryBookById(cartItem.getId());
            book.setStock(book.getStock()-cartItem.getCount());
            bookMapper.updateBook(book);
        }
        //清空购物车
        cart.clear();
        return bookListId;
    }

    @Override
    public int deleteBookList(String bookListId) {
        return bookListMapper.deleteBookList(bookListId);
    }

    @Override
    public int readyBookList(String bookListId) {
        return bookListMapper.changeBookListStatus(bookListId,BookList.READY);
    }

    @Override
    public int sendBookList(String bookListId) {
        return bookListMapper.changeBookListStatus(bookListId,BookList.SEND);
    }

    @Override
    public int borrowBookList(String bookListId) {
        return bookListMapper.changeBookListStatus(bookListId,BookList.BORROW);
    }

    @Override
    public int returnBookList(String bookListId) {
        return bookListMapper.changeBookListStatus(bookListId,BookList.RETURN);
    }

    @Override
    public int cancelBookList(String bookListId) {
        return bookListMapper.changeBookListStatus(bookListId,BookList.CANCEL);
    }

    @Override
    public List<BookListItems> queryBookListItems(String bookListId) {
        return bookListItemsMapper.queryBookListItemsByBookListId(bookListId);
    }

    @Override
    public List<BookList> queryBookLists(int begin, int pageSize) {
        return bookListMapper.queryAllBookListsByPage(begin,pageSize);
    }

    @Override
    public List<BookList> queryBookListsByUserId(int userId, int begin, int pageSize) {
        return bookListMapper.queryBookListsByUserId(userId,begin,pageSize);
    }
}
