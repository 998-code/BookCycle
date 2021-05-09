package com.wcm533.pojo;

import java.util.Arrays;

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
    private int classification;
    private byte[] bookImg;

    public Book(){

    }

    public Book(int id, String name, String author, int stock, int loan, int points, int classification, byte[] bookImg) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.stock = stock;
        this.loan = loan;
        this.points = points;
        this.classification = classification;
        this.bookImg = bookImg;
    }

    public Book(String name, String author, int stock, int loan, int points, int classification, byte[] bookImg) {
        this.name = name;
        this.author = author;
        this.stock = stock;
        this.loan = loan;
        this.points = points;
        this.classification = classification;
        this.bookImg = bookImg;
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

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    public byte[] getBookImg() {
        return bookImg;
    }

    public void setBookImg(byte[] bookImg) {
        this.bookImg = bookImg;
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
                ", classification=" + classification +
                ", bookImg=" + Arrays.toString(bookImg) +
                '}';
    }
}
