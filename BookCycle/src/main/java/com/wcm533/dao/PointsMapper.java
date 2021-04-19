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

    int addPoints(Points points);

    List<Points> queryPointsByUserId(@Param("userId") int userId,@Param("begin") int begin,@Param("pageSize") int pageSize);
}
