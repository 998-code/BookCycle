package com.wcm533.pojo;

import java.math.BigDecimal;

/**
 * @ClassName CartItem
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/06 15:31
 **/
public class CartItem {

    private Integer id;
    private String name;
    private Integer count;
    private int points;
    private int totalPoints;

    public CartItem() {
    }

    public CartItem(Integer id, String name, Integer count, int points, int totalPoints) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.points = points;
        this.totalPoints = totalPoints;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", points=" + points +
                ", totalPoints=" + totalPoints +
                '}';
    }
}
