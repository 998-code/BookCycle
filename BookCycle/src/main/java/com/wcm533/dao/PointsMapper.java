package com.wcm533.dao;

import com.wcm533.pojo.Points;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName PointsMapper
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/18 16:33
 **/
public interface PointsMapper {

    /**
     * 新增用户积分记录
     * @param points
     * @return
     */
    int addPoints(Points points);

    /**
     * 用户通过分页查询积分的使用情况
     * @param userId
     * @param begin
     * @param pageSize
     * @return
     */
    List<Points> queryPointsByUserId(@Param("userId") int userId,@Param("begin") int begin,@Param("pageSize") int pageSize);
}
