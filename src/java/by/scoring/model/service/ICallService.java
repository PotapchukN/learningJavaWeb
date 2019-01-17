package by.scoring.model.service;

import by.scoring.model.entity.Answers;
import by.scoring.model.entity.Calls;
import by.scoring.model.entity.Questions;

import java.util.List;

public interface ICallService {
    void addCall(Calls call);

    void updateCall(Calls call);

    void removeCall(long id);

    Calls getCallById(long id);

    List<Calls> listCalls();

    List<Calls> findAllByTopic(String topic);

}