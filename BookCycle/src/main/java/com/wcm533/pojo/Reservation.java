package com.wcm533.pojo;

/**
 * @ClassName Reservation
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/19 10:54
 **/
public class Reservation {

    private int id;
    private int bookId;
    private int userId;

    public Reservation() {
    }

    public Reservation(int id, int bookId, int userId) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                '}';
    }
}
