package com.wcm533.pojo;

/**
 * @ClassName Book
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/03 18:50
 **/
public class Book {

    private int id;
    private String name;
    private String author;
    private int stock;
    private int loan;
    private int points;
    private String imgPath;

    public Book(){

    }

    public Book(int id, String name, String author, int stock, int loan, int points, String imgPath) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.stock = stock;
        this.loan = loan;
        this.points = points;
        this.imgPath = imgPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getLoan() {
        return loan;
    }

    public void setLoan(int loan) {
        this.loan = loan;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", stock=" + stock +
                ", loan=" + loan +
                ", points=" + points +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
