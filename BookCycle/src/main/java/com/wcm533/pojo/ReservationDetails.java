package com.wcm533.pojo;

/**
 * @ClassName ReservationDetails
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/20 21:15
 **/
public class ReservationDetails {

    public static final int STATUS_NO=0;
    public static final int STATUS_YES=1;

    private int bookId;
    private String bookName;
    private int status;
    private int points;

    public ReservationDetails() {
    }

    public ReservationDetails(int bookId, String bookName, int status, int points) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.status = status;
        this.points = points;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "ReservationDetails{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", status=" + status +
                ", points=" + points +
                '}';
    }
}
