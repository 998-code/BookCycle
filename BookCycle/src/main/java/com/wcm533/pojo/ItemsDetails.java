package com.wcm533.pojo;

/**
 * @ClassName ItemsDetails
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/24 21:51
 **/
public class ItemsDetails {

    private int bookId;
    private String bookName;
    private String bookAuthor;
    private String bookConcern;
    private String edition;
    private int points;
    private int count;
    private int totalPoints;

    public ItemsDetails() {
    }

    public ItemsDetails(int bookId, String bookName, String bookAuthor, String bookConcern, String edition, int points, int count, int totalPoints) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookConcern = bookConcern;
        this.edition = edition;
        this.points = points;
        this.count = count;
        this.totalPoints = totalPoints;
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

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookConcern() {
        return bookConcern;
    }

    public void setBookConcern(String bookConcern) {
        this.bookConcern = bookConcern;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public String toString() {
        return "ItemsDetails{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookConcern='" + bookConcern + '\'' +
                ", edition='" + edition + '\'' +
                ", points=" + points +
                ", count=" + count +
                ", totalPoints=" + totalPoints +
                '}';
    }
}
