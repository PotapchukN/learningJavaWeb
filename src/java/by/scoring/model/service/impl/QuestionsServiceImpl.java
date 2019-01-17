package by.scoring.model.service.impl;

import by.scoring.model.dao.QuestionsDao;
import by.scoring.model.entity.Answers;
import by.scoring.model.entity.CategoryQuestion;
import by.scoring.model.entity.Questions;
import by.scoring.model.service.IQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsServiceImpl implements IQuestionsService {

    @Autowired
    private QuestionsDao questionsDao;
    @Override
    public void addQuestions(Questions questions) {questionsDao.save(questions);}

    @Override
    public void updateQuestions(Questions questions) {questionsDao.save(questions);}

    @Override
    public void removeQuestions(long id) {questionsDao.delete(id);}

    @Override
    public Questions getQuestionsById(long id) {return questionsDao.findOne(id);}

    @Override
    public List<Questions> listQuestions() {return questionsDao.findAll();}

    @Override
    public Questions findByQuestion(String question) {return questionsDao.findByQuestion(question);}

    @Override
    public List<Questions> findByCategoryQuestion(CategoryQuestion cq) {return questionsDao.findByCategoryQuestion(cq);}
}
