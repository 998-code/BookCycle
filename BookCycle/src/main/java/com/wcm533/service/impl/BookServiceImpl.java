package com.wcm533.service.impl;

import com.wcm533.dao.BookMapper;
import com.wcm533.pojo.Book;
import com.wcm533.pojo.Page;
import com.wcm533.service.BookService;

import java.util.List;

/**
 * @ClassName BookServiceImpl
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/27 19:36
 **/
public class BookServiceImpl implements BookService {

    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public int addBook(Book book) {
        bookMapper.addBook(book);
        int newBookId = bookMapper.queryNewBookId(book.getName());
        System.out.println(newBookId);
        return newBookId;
    }

    @Override
    public boolean deleteBook(int bookId) {
        if(bookMapper.deleteBook(bookId)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateBook(Book book) {
        Book bookById = bookMapper.queryBookById(book.getId());
        book.setBookImg(bookById.getBookImg());
        if(bookMapper.updateBook(book)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateBookNumber(int bookId) {
        return false;
    }

    @Override
    public Book queryBookByName(String bookName) {
        return bookMapper.queryBookByBookName(bookName);
    }

    @Override
    public Book queryBookById(int bookId) {
        return bookMapper.queryBookById(bookId);
    }

    @Override
    public List<Book> queryBooks() {
        return null;
    }

    @Override
    public Page<Book> queryBooksByPage(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();

        //设置当前页数据个数
        page.setPageSize(pageSize);
        //设置图书总数
        int pageTotalCount = bookMapper.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //设置总页码
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        //设置当前页码
        page.setPageNo(pageNo);
        //设置页面图书数据
        int begin=(page.getPageNo()-1)*pageSize;
        List<Book> items= bookMapper.queryForByPage(begin,pageSize);
        page.setPageItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByClassification(int pageNo, int pageSize, int classification) {
        return null;
    }

    @Override
    public Page<Book> queryBooksByInfo(int pageNo, int pageSize, String info) {
        Page<Book> page = new Page<Book>();

        //设置当前页数据个数
        page.setPageSize(pageSize);
        //设置图书总数
        int pageTotalCount = bookMapper.queryBookCountByInfo(info);
        page.setPageTotalCount(pageTotalCount);
        //设置总页码
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        //设置当前页码
        page.setPageNo(pageNo);
        //设置页面图书数据
        int begin=(page.getPageNo()-1)*pageSize;
        List<Book> items= bookMapper.queryBooksByInfo(begin,pageSize,info);
        page.setPageItems(items);
        return page;
    }

}
