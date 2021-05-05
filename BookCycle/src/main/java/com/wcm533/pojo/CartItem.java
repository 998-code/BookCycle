package com.wcm533.pojo;

import java.math.BigDecimal;

/**
 * @ClassName CartItem
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/06 15:31
 **/
public class CartItem {

    private int bookId;
    private String bookName;
    private int count;
    private int points;
    private int totalPoints;
    private int userId;

    public CartItem() {
    }

    public CartItem(int bookId, String bookName, int count, int points, int totalPoints, int userId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.count = count;
        this.points = points;
        this.totalPoints = totalPoints;
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", count=" + count +
                ", points=" + points +
                ", totalPoints=" + totalPoints +
                ", userId=" + userId +
                '}';
    }
}
