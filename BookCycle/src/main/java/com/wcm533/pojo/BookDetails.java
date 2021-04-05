package com.wcm533.pojo;

/**
 * @ClassName BookDetails
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/05 16:12
 **/
public class BookDetails {

    private int id;
    private int bookId;
    private String edition;
    private String bookConcern;
    private String datePublication;
    private String summary;

    public BookDetails(){

    }

    public BookDetails( int bookId, String edition, String bookConcern, String datePublication, String summary) {
        this.bookId = bookId;
        this.edition = edition;
        this.bookConcern = bookConcern;
        this.datePublication = datePublication;
        this.summary = summary;
    }

    public BookDetails(int bookId, String edition, String bookConcern) {
        this.bookId = bookId;
        this.edition = edition;
        this.bookConcern = bookConcern;
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

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getBookConcern() {
        return bookConcern;
    }

    public void setBookConcern(String bookConcern) {
        this.bookConcern = bookConcern;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "BookDetails{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", edition='" + edition + '\'' +
                ", bookConcern='" + bookConcern + '\'' +
                ", datePublication='" + datePublication + '\'' +
                ", summary=" + summary +
                '}';
    }
}
