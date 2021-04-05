package com.wcm533.pojo;

import java.util.Date;

/**
 * @ClassName BookList
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/05 18:35
 **/
public class BookList {
    
    private String bookListId;
    private Date createTime;
    private int points;
    private int status;
    private int userId;

    public BookList(){

    }

    public BookList(String bookListId, Date createTime, int points, int status, int userId) {
        this.bookListId = bookListId;
        this.createTime = createTime;
        this.points = points;
        this.status = status;
        this.userId = userId;
    }

    public BookList(String bookListId, Date createTime, int points, int userId) {
        this.bookListId = bookListId;
        this.createTime = createTime;
        this.points = points;
        this.userId = userId;
    }

    public String getBookListId() {
        return bookListId;
    }

    public void setBookListId(String bookListId) {
        this.bookListId = bookListId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
