package com.wcm533.dao;

import com.wcm533.pojo.Reservation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ReservationMapper
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/19 10:56
 **/
public interface ReservationMapper {

    /**
     * 添加预约
     * @param reservation
     * @return
     */
    int addReservation(Reservation reservation);

    /**
     * 取消预约
     * @param id
     * @return
     */
    int deleteReservation(@Param("id") int id);

    /**
     * 查询给用户的预约数量
     * @param userId
     * @return
     */
    int queryReservationCount(@Param("userId") int userId);

    /**
     * 分页查询用户的预约信息
     * @param begin
     * @param pageSize
     * @return
     */
    List<Reservation> queryReservationByPage(@Param("userId") int userId, @Param("begin") int begin,@Param("pageSize") int pageSize);
}
