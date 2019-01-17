package by.scoring.model.service.impl;

import by.scoring.model.dao.CallsDao;
import by.scoring.model.entity.Calls;
import by.scoring.model.service.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallsServiceImpl implements ICallService{

    @Autowired
    CallsDao callsDao;

    @Override
    public void addCall(Calls call) {callsDao.save(call);}

    @Override
    public void updateCall(Calls call) {callsDao.save(call);}

    @Override
    public void removeCall(long id) {callsDao.delete(id);}

    @Override
    public Calls getCallById(long id) {return callsDao.findOne(id);}

    @Override
    public List<Calls> listCalls() {return callsDao.findAll();}

    @Override
    public List<Calls> findAllByTopic(String topic) {return callsDao.findAllByTopic(topic);}
}
