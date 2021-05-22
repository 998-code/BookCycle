package com.wcm533.service.impl;

import com.wcm533.dao.PointsMapper;
import com.wcm533.pojo.Points;
import com.wcm533.service.PointsService;

import java.util.List;

/**
 * @ClassName PointsServiceImpl
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/26 10:59
 **/
public class PointsServiceImpl implements PointsService {

    private PointsMapper pointsMapper;

    public void setPointsMapper(PointsMapper pointsMapper) {
        this.pointsMapper = pointsMapper;
    }

    @Override
    public boolean addPoints(Points points) {
        return pointsMapper.addPoints(points) > 0;
    }

    @Override
    public List<Points> queryPointsByUserId(int userId) {

        return pointsMapper.queryPointsByUserId(userId, 0, Points.USER_PAGE_SIZE);
    }
}
