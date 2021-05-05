package com.wcm533.service.impl;

import com.wcm533.dao.BookDetailsMapper;
import com.wcm533.dao.BookListItemsMapper;
import com.wcm533.dao.BookListMapper;
import com.wcm533.dao.BookMapper;
import com.wcm533.pojo.*;
import com.wcm533.service.BookListService;

import java.util.ArrayList;
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
    private BookDetailsMapper bookDetailsMapper;

    public void setBookListMapper(BookListMapper bookListMapper) {
        this.bookListMapper = bookListMapper;
    }

    public void setBookListItemsMapper(BookListItemsMapper bookListItemsMapper) {
        this.bookListItemsMapper = bookListItemsMapper;
    }

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public void setBookDetailsMapper(BookDetailsMapper bookDetailsMapper) {
        this.bookDetailsMapper = bookDetailsMapper;
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
            Book book = bookMapper.queryBookById(cartItem.getBookId());
            //创建订单项
            BookListItems bookListItems = new BookListItems( cartItem.getBookId(),book.getName(), cartItem.getPoints(), cartItem.getCount(),  bookListId);
            //保存订单项到数据库
            bookListItemsMapper.addBookListItems(bookListItems);
            //更新库存和销量
            book.setStock(book.getStock()-cartItem.getCount());
            bookMapper.updateBook(book);
        }
        //清空购物车
        cart.clear();
        return bookListId;
    }

    @Override
    public boolean deleteBookList(String bookListId) {
        if(bookListMapper.deleteBookList(bookListId)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean readyBookList(String bookListId) {
        if(bookListMapper.changeBookListStatus(bookListId,BookList.READY)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean sendBookList(String bookListId) {
        if(bookListMapper.changeBookListStatus(bookListId,BookList.SEND)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean borrowBookList(String bookListId) {
        if(bookListMapper.changeBookListStatus(bookListId,BookList.BORROW)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBookList(String bookListId) {
        if(bookListMapper.changeBookListStatus(bookListId,BookList.RETURN)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelBookList(String bookListId) {
        if (bookListMapper.changeBookListStatus(bookListId,BookList.CANCEL)>0){
            return true;
        }
        return false;
    }

    @Override
    public List<ItemsDetails> queryBookListItems(String bookListId) {
        List<ItemsDetails> itemsDetails=new ArrayList<ItemsDetails>();
        List<BookListItems> bookListItems = bookListItemsMapper.queryBookListItemsByBookListId(bookListId);
        for (BookListItems bookListItem : bookListItems) {
            Book book = bookMapper.queryBookById(bookListItem.getBookId());
            BookDetails bookDetails = bookDetailsMapper.queryBookDetailsByBookId(bookListItem.getBookId());
            ItemsDetails iD = new ItemsDetails();
            iD.setBookId(bookListItem.getBookId());
            iD.setBookName(bookListItem.getBookName());
            iD.setBookAuthor(book.getAuthor());
            iD.setBookConcern(bookDetails.getBookConcern());
            iD.setEdition(bookDetails.getEdition());
            iD.setPoints(bookListItem.getPoints());
            iD.setCount(bookListItem.getCount());
            iD.setTotalPoints(bookListItem.getPoints()*bookListItem.getCount());
            itemsDetails.add(iD);
        }
        return itemsDetails;
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
