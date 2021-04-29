package com.wcm533.service.impl;

import com.wcm533.dao.BookMapper;
import com.wcm533.dao.ReservationMapper;
import com.wcm533.pojo.Book;
import com.wcm533.pojo.Reservation;
import com.wcm533.pojo.ReservationDetails;
import com.wcm533.service.ReservationService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ReservationServiceImpl
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/20 20:30
 **/
public class ReservationServiceImpl implements ReservationService {

    private ReservationMapper reservationMapper;
    private BookMapper bookMapper;

    public void setReservationMapper(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public boolean addReservation(Reservation reservation) {
        return false;
    }

    @Override
    public boolean deleteReservation(int id) {
        if(reservationMapper.deleteReservation(id)>0){
            return true;
        }
        return false;
    }

    @Override
    public Reservation queryReservation(int id) {
        return null;
    }

    @Override
    public List<ReservationDetails> queryReservationByUserId(int userId, int begin, int pageSize) {
        List<ReservationDetails> reservationDetails=new ArrayList<ReservationDetails>();
        List<Reservation> reservations = reservationMapper.queryReservationByPage(userId, begin, pageSize);
        for (Reservation reservation : reservations) {
            Book book = bookMapper.queryBookById(reservation.getBookId());
            ReservationDetails details = new ReservationDetails();
            details.setId(reservation.getId());
            details.setBookId(book.getId());
            details.setBookName(book.getName());
            if(book.getStock()>book.getLoan()){
                details.setStatus(ReservationDetails.STATUS_YES);
            }else{
                details.setStatus(ReservationDetails.STATUS_NO);
            }
            details.setPoints(book.getPoints());
            reservationDetails.add(details);
        }
        return reservationDetails;
    }
}
