package com.wcm533.dao;

import com.wcm533.pojo.Reservation;
import com.wcm533.utils.MybatisUtils;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @ClassName ReservationMapperTest
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/19 11:14
 **/
public class ReservationMapperTest extends TestCase {

    public void testAddReservation() {
    }

    public void testDeleteReservation() {
    }

    public void testQueryReservationCount() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ReservationMapper mapper = sqlSession.getMapper(ReservationMapper.class);
        int i = mapper.queryReservationCount(1914);
        System.out.println(i);
        sqlSession.close();
    }

    public void testQueryReservationByPage() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ReservationMapper mapper = sqlSession.getMapper(ReservationMapper.class);
        List<Reservation> reservations = mapper.queryReservationByPage(1914, 0, 2);
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
        sqlSession.close();
    }
}