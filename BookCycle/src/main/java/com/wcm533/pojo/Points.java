package com.wcm533.pojo;

import java.util.Date;

/**
 * @ClassName Points
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/18 16:31
 **/
public class Points {

    /**
     * 借书
     */
    public static final Integer BORROW_BOOKS=0;
    /**
     * 取消借书
     */
    public static final Integer CANCEL_BORROW_BOOKS=3;
    /**
     * 还书
     */
    public static final Integer RETURN_BOOKS=4;
    /**
     * 捐书
     */
    public static final Integer DONATE_BOOKS=1;
    /**
     * 修改昵称
     */
    public static final Integer CHANGE_USERNAME=2;
    /**
     * 还书返还积分比例
     */
    public static final Double POINTS_PROPORTION=0.8;
    /**
     * 用户积分变化数据
     */
    public static final Integer USER_PAGE_SIZE=8;

    private int id;
    private int userId;
    private Date date;
    private int points;
    private int status;
    private String other;

    public Points() {
    }

    public Points(int id, int userId, Date date, int points, int status, String other) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.points = points;
        this.status = status;
        this.other = other;
    }

    public Points(int userId, Date date, int points, int status, String other) {
        this.userId = userId;
        this.date = date;
        this.points = points;
        this.status = status;
        this.other = other;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "Points{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", points=" + points +
                ", status=" + status +
                ", other='" + other + '\'' +
                '}';
    }
}
