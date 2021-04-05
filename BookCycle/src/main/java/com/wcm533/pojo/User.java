package com.wcm533.pojo;

import java.util.Arrays;

/**
 * @ClassName User
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/05 11:54
 **/
public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private int points;
    private String headImgPath;
    private byte[] headImg;
    private int authority;

    public User(){

    }

    public User(int id, String username, String password, String email, int points, int authority) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.points = points;
        this.authority = authority;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getHeadImgPath() {
        return headImgPath;
    }

    public void setHeadImgPath(String headImgPath) {
        this.headImgPath = headImgPath;
    }

    public byte[] getHeadImg() {
        return headImg;
    }

    public void setHeadImg(byte[] headImg) {
        this.headImg = headImg;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", points=" + points +
                ", headImgPath='" + headImgPath + '\'' +
                ", headImg=" + headImg +
                ", authority=" + authority +
                '}';
    }
}
