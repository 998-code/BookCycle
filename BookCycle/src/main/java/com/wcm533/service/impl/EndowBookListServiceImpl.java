package com.wcm533.service.impl;

import com.wcm533.dao.BookDetailsMapper;
import com.wcm533.dao.BookMapper;
import com.wcm533.dao.EndowBookListItemsMapper;
import com.wcm533.dao.EndowBookListMapper;
import com.wcm533.pojo.*;
import com.wcm533.service.EndowBookListService;

import java.util.ArrayList;
import java.util.Date;
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
    private BookDetailsMapper bookDetailsMapper;

    public void setEndowBookListMapper(EndowBookListMapper endowBookListMapper) {
        this.endowBookListMapper = endowBookListMapper;
    }

    public void setEndowBookListItemsMapper(EndowBookListItemsMapper endowBookListItemsMapper) {
        this.endowBookListItemsMapper = endowBookListItemsMapper;
    }

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public void setBookDetailsMapper(BookDetailsMapper bookDetailsMapper) {
        this.bookDetailsMapper = bookDetailsMapper;
    }

    @Override
    public String createBookList(int userId, String[] bookIdArr, String[] bookCountArr) {
        String bookListId;
        if(userId==0){
            bookListId=System.currentTimeMillis()+"1916";
        }else {
            bookListId=System.currentTimeMillis()+""+userId;
        }
        System.out.println(bookListId);
        int points=Integer.parseInt(bookIdArr[bookIdArr.length-1]);
        System.out.println(points);
        EndowBookList endowBookList = new EndowBookList(bookListId, new Date(), points, 1916);
        System.out.println(endowBookList);
        endowBookListMapper.addBookList(endowBookList);
        for(int i=0;i<bookIdArr.length-1;i++){
            Book book = bookMapper.queryBookById(Integer.parseInt(bookIdArr[i]));
            EndowBookListItems items = new EndowBookListItems();
            items.setBookId(book.getId());
            items.setBookName(book.getName());
            items.setCount(Integer.parseInt(bookCountArr[i]));
            items.setPoints(book.getPoints());
            items.setBookListId(bookListId);
            endowBookListItemsMapper.addBookListItems(items);
        }
        return bookListId;
    }

    @Override
    public boolean deleteBookList(String bookListId) {
        if(endowBookListMapper.deleteBookList(bookListId)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean readyBookList(String bookListId) {
        if(endowBookListMapper.changeBookListStatus(bookListId,EndowBookList.READY)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean processingBookList(String bookListId) {
        if(endowBookListMapper.changeBookListStatus(bookListId,EndowBookList.PROCESSING)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean completedBookList(String bookListId) {
        if(endowBookListMapper.changeBookListStatus(bookListId,EndowBookList.COMPLETED)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelBookList(String bookListId) {
        if(endowBookListMapper.changeBookListStatus(bookListId,EndowBookList.CANCEL)>0){
            return true;
        }
        return false;
    }

    @Override
    public List<ItemsDetails> queryBookListItems(String bookListId) {
        List<ItemsDetails> itemsDetails=new ArrayList<ItemsDetails>();
        List<EndowBookListItems> bookListItems = endowBookListItemsMapper.queryBookListItemsByBookListId(bookListId);
        for (EndowBookListItems bookListItem : bookListItems) {
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
        System.out.println(itemsDetails);
        return itemsDetails;
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
