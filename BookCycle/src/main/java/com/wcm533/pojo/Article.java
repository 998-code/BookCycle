package com.wcm533.pojo;

import java.util.Date;

/**
 * @ClassName Article
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/06 18:56
 **/
public class Article {

    private int id;
    private String name;
    private String author;
    private Date date;
    private String summary;
    private String imgPath;

    public Article(){

    }

    public Article(int id, String name, String author, Date date, String summary, String imgPath) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.date = date;
        this.summary = summary;
        this.imgPath = imgPath;
    }

    public Article(String name, String author, Date date, String summary, String imgPath) {
        this.name = name;
        this.author = author;
        this.date = date;
        this.summary = summary;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", summery='" + summary + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
