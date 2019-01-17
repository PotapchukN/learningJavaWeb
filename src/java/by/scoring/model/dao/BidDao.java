package by.scoring.model.dao;

import by.scoring.model.entity.Bid;
import by.scoring.model.entity.CreditInfo;
import by.scoring.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface BidDao extends JpaRepository<Bid, Long> {

    List<Bid> findByCredit(CreditInfo credit);
    List<Bid> findByUser(User user);
    List<Bid> findByDate(Date date);
    List<Bid> findByDateAndTimeAndCredit_id(Date date, String time, Long id);
}
