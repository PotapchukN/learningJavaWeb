package by.scoring.model.service;

import by.scoring.model.entity.Bid;
import by.scoring.model.entity.CreditInfo;
import by.scoring.model.entity.User;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface IBidsService {

    void addBid(Bid bid);

    void updateBid(Bid bid);

    void removeBid(long id);

    Bid getBidById(long id);

    List<Bid> listBids();

    List<Bid> findByCredit(CreditInfo credit);
    List<Bid> findByUser(User user);
    List<Bid> findByDate(Date date);
    List<Bid> findByDateAndTimeAndCredit_id(Date date, String time, Long id);
}
