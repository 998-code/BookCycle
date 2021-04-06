package com.wcm533.pojo;

/**
 * @ClassName BookListItems
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/06 14:07
 **/
public class BookListItems {
    
    private int id;
    private String bookName;
    private int points;
    private String bookListId;
    
    public BookListItems(){
        
    }

    public BookListItems(int id, String bookName, int points, String bookListId) {
        this.id = id;
        this.bookName = bookName;
        this.points = points;
        this.bookListId = bookListId;
    }

    public BookListItems(String bookName, int points, String bookListId) {
        this.bookName = bookName;
        this.points = points;
        this.bookListId = bookListId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getBookListId() {
        return bookListId;
    }

    public void setBookListId(String bookListId) {
        this.bookListId = bookListId;
    }

    @Override
    public String toString() {
        return "BookListItems{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", points=" + points +
                ", bookListId='" + bookListId + '\'' +
                '}';
    }
}
