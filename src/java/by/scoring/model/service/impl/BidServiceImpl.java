package by.scoring.model.service.impl;

import by.scoring.model.repository.BidRepository;
import by.scoring.model.entity.Bid;
import by.scoring.model.entity.CreditInfo;
import by.scoring.model.entity.User;
import by.scoring.model.service.IBidsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BidServiceImpl implements IBidsService {

    @Autowired
    BidRepository bidRepository;

    @Override
    public void addBid(Bid bid) {
        bidRepository.save(bid);}

    @Override
    public void updateBid(Bid bid) {
        bidRepository.save(bid);}

    @Override
    public void removeBid(long id) {
        bidRepository.delete(id);}

    @Override
    public Bid getBidById(long id) {return bidRepository.getOne(id);}

    @Override
    public List<Bid> listBids() {return bidRepository.findAll();}

    @Override
    public List<Bid> findByCredit(CreditInfo credit) {return bidRepository.findByCredit(credit);}

    @Override
    public List<Bid> findByUser(User user) {return bidRepository.findByUser(user);}

    @Override
    public List<Bid> findByDate(Date date) {return bidRepository.findByDate(date);}

    @Override
    public List<Bid> findByDateAndTimeAndCredit_id(Date date,String time, Long id) {
        return bidRepository.findByDateAndTimeAndCredit_id(date, time, id);
    }
}
