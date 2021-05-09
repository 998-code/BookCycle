package com.wcm533.service.impl;

import com.fasterxml.jackson.annotation.JsonTypeId;
import com.wcm533.dao.*;
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
    private UserMapper userMapper;

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

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public String createBookList(int userId, String[] bookIdArr, String[] bookCountArr) {
        String bookListId=System.currentTimeMillis()+""+userId;
        int points=Integer.parseInt(bookIdArr[bookIdArr.length-1]);
        BookList bookList = new BookList(bookListId, new Date(), points, userId);
        bookListMapper.addBookList(bookList);
        for(int i=0;i<bookIdArr.length-1;i++){
            Book book = bookMapper.queryBookById(Integer.parseInt(bookIdArr[i]));
            BookListItems items = new BookListItems();
            items.setBookId(book.getId());
            items.setBookName(book.getName());
            items.setCount(Integer.parseInt(bookCountArr[i]));
            items.setPoints(book.getPoints());
            items.setBookListId(bookListId);
            bookListItemsMapper.addBookListItems(items);
            book.setLoan(book.getLoan()+1);
            System.out.println(book);
            bookMapper.updateBook(book);
        }
        User user = userMapper.queryUserById(userId);
        user.setPoints(user.getPoints()-points);
        userMapper.updateUser(user);
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
