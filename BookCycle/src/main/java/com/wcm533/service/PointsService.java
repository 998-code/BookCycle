package com.wcm533.service;

import com.wcm533.pojo.Points;

import java.util.List;

/**
 * @ClassName PointsService
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/26 10:43
 **/
public interface PointsService {

    boolean addPoints(Points points);

    List<Points> queryPointsByUserId(int userId);
}
