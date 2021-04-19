package com.wcm533.dao;

import com.wcm533.pojo.Points;
import com.wcm533.utils.MybatisUtils;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @ClassName PointsMapperTest
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/18 16:44
 **/
public class PointsMapperTest extends TestCase {

    public void testAddPoints() {
    }

    public void testQueryPointsByUserId() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PointsMapper mapper = sqlSession.getMapper(PointsMapper.class);
        List<Points> points = mapper.queryPointsByUserId(1914, 0, 4);
        for (Points point : points) {
            System.out.println(point);
        }
        sqlSession.close();
    }
}