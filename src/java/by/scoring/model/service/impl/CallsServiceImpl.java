package by.scoring.model.service.impl;

import by.scoring.model.repository.CallsRepository;
import by.scoring.model.entity.Calls;
import by.scoring.model.service.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallsServiceImpl implements ICallService{

    @Autowired
    CallsRepository callsRepository;

    @Override
    public void addCall(Calls call) {
        callsRepository.save(call);}

    @Override
    public void updateCall(Calls call) {
        callsRepository.save(call);}

    @Override
    public void removeCall(long id) {
        callsRepository.delete(id);}

    @Override
    public Calls getCallById(long id) {return callsRepository.findOne(id);}

    @Override
    public List<Calls> listCalls() {return callsRepository.findAll();}

    @Override
    public List<Calls> findAllByTopic(String topic) {return callsRepository.findAllByTopic(topic);}
}
