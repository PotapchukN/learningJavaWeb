package by.scoring.model.service.impl;

import by.scoring.model.dao.BidDao;
import by.scoring.model.entity.Bid;
import by.scoring.model.entity.CreditInfo;
import by.scoring.model.entity.User;
import by.scoring.model.service.IBidsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class BidServiceImpl implements IBidsService {

    @Autowired
    BidDao bidDao;

    @Override
    public void addBid(Bid bid) {bidDao.save(bid);}

    @Override
    public void updateBid(Bid bid) {bidDao.save(bid);}

    @Override
    public void removeBid(long id) {bidDao.delete(id);}

    @Override
    public Bid getBidById(long id) {return bidDao.getOne(id);}

    @Override
    public List<Bid> listBids() {return bidDao.findAll();}

    @Override
    public List<Bid> findByCredit(CreditInfo credit) {return bidDao.findByCredit(credit);}

    @Override
    public List<Bid> findByUser(User user) {return bidDao.findByUser(user);}

    @Override
    public List<Bid> findByDate(Date date) {return bidDao.findByDate(date);}

    @Override
    public List<Bid> findByDateAndTimeAndCredit_id(Date date,String time, Long id) {
        return bidDao.findByDateAndTimeAndCredit_id(date, time, id);
    }
}
