package com.wcm533.service.impl;

import com.wcm533.dao.*;
import com.wcm533.pojo.*;
import com.wcm533.service.BookListService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        return bookListMapper.deleteBookList(bookListId) > 0;
    }

    @Override
    public boolean readyBookList(String bookListId) {
        return bookListMapper.changeBookListStatus(bookListId, BookList.READY) > 0;
    }

    @Override
    public boolean sendBookList(String bookListId) {
        return bookListMapper.changeBookListStatus(bookListId, BookList.SEND) > 0;
    }

    @Override
    public boolean borrowBookList(String bookListId) {
        return bookListMapper.changeBookListStatus(bookListId, BookList.BORROW) > 0;
    }

    @Override
    public boolean returnBookList(String bookListId) {
        BookList bookList = bookListMapper.queryBookByBookListId(bookListId);
        if(bookListMapper.changeBookListStatus(bookListId,BookList.RETURN)>0){
            List<BookListItems> bookListItems = bookListItemsMapper.queryBookListItemsByBookListId(bookListId);
            for (BookListItems bookListItem : bookListItems) {
                int bookId = bookListItem.getBookId();
                Book book = bookMapper.queryBookById(bookId);
                book.setLoan(book.getLoan() - 1);
                bookMapper.updateBook(book);
            }
            if(bookList.getStatus()!=BookList.TIME_OUT){
                User user = userMapper.queryUserById(bookList.getUserId());
                user.setPoints((int) (user.getPoints()+Math.floor(bookList.getPoints()*Points.POINTS_PROPORTION)));
                userMapper.updateUser(user);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelBookList(String bookListId) {
        if (bookListMapper.changeBookListStatus(bookListId,BookList.CANCEL)>0){
            List<BookListItems> bookListItems = bookListItemsMapper.queryBookListItemsByBookListId(bookListId);
            for (BookListItems bookListItem : bookListItems) {
                int bookId = bookListItem.getBookId();
                Book book = bookMapper.queryBookById(bookId);
                book.setLoan(book.getLoan() - 1);
                bookMapper.updateBook(book);
            }
            BookList bookList = bookListMapper.queryBookByBookListId(bookListId);
            User user = userMapper.queryUserById(bookList.getUserId());
            user.setPoints(user.getPoints()+bookList.getPoints());
            userMapper.updateUser(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean aboutOfTimeOutBookList(String bookListId) {
        return bookListMapper.changeBookListStatus(bookListId, BookList.ABOUT_TO_TIME_OUT) > 0;
    }

    @Override
    public boolean overTimeBookList(String bookListId) {
        return bookListMapper.changeBookListStatus(bookListId, BookList.TIME_OUT) > 0;
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
    public Page<BookList> queryBookLists(int pageNo, int pageSize) {
        Page<BookList> page = new Page<BookList>();
        page.setPageSize(pageSize);
        int pageTotalCount = bookListMapper.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        int begin=(page.getPageNo()-1)*pageSize;
        List<BookList> items=new ArrayList<BookList>();
        if (pageTotalCount>0){
            items= bookListMapper.queryAllBookListsByPage(begin,pageSize);
        }
        page.setPageItems(items);
        return page;
    }

    @Override
    public List<BookList> queryBookListsByUserId(int userId, int begin, int pageSize) {
        return bookListMapper.queryBookListsByUserId(userId,begin,pageSize);
    }

    @Override
    public Page<BookList> queryBookListsByInfo(int pageNo, int pageSize, String info) {
        Page<BookList> page = new Page<BookList>();
        page.setPageSize(pageSize);
        int userId=0;
        int pageTotalCount=1;
        if(info.length()<10){
            userId = Integer.parseInt(info);
            pageTotalCount=bookListMapper.queryForPageTotalCountByUserId(userId);
        }
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        int begin=(page.getPageNo()-1)*pageSize;
        List<BookList> items=new ArrayList<BookList>();
        if(info.length()<10){
            items= bookListMapper.queryBookListsByUserId(userId, begin, pageSize);
        }else {
            BookList bookList = bookListMapper.queryBookByBookListId(info);
            items.add(bookList);
        }
        page.setPageItems(items);
        return page;
    }

    @Override
    public Page<BookList> queryBookListsByStatus(int pageNo, int pageSize, String status) {
        Page<BookList> page = new Page<BookList>();
        page.setPageSize(pageSize);
        int bookListStatus = Integer.parseInt(status);
        int pageTotalCount = bookListMapper.queryForPageTotalCountByStatus(bookListStatus);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        int begin=(page.getPageNo()-1)*pageSize;
        List<BookList> items=new ArrayList<BookList>();
        if(pageTotalCount>0){
            items=bookListMapper.queryBookListsByStatus(bookListStatus,begin,pageSize);
        }
        page.setPageItems(items);
        return page;
    }

    @Override
    public BookList queryBookListsByBookListId(String bookListId) {
        return bookListMapper.queryBookByBookListId(bookListId);
    }

    @Override
    public List<BookList> queryAllBookLists() {
        return bookListMapper.queryAllBookLists();
    }


}
