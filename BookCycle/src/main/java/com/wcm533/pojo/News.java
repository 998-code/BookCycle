package com.wcm533.pojo;

import java.util.Date;

/**
 * @ClassName News
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/06 19:50
 **/
public class News {

    private int id;
    private String title;
    private String author;
    private Date date;
    private String stemFrom;
    private String content;

    public News(){

    }

    public News(int id, String title, String author, Date date, String stemFrom, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.stemFrom = stemFrom;
        this.content = content;
    }

    public News(String title, String author, Date date, String stemFrom, String content) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.stemFrom = stemFrom;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getStemFrom() {
        return stemFrom;
    }

    public void setStemFrom(String stemFrom) {
        this.stemFrom = stemFrom;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", stemFrom='" + stemFrom + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
