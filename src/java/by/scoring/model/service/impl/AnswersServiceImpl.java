package by.scoring.model.service.impl;

import by.scoring.model.dao.AnswersDao;
import by.scoring.model.entity.Answers;
import by.scoring.model.entity.Questions;
import by.scoring.model.service.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswersServiceImpl implements IAnswerService {

    @Autowired
    private AnswersDao answersDao;

    @Override
    public void addAnswers(Answers answers) {answersDao.save(answers);}

    @Override
    public void updateAnswers(Answers answers) {answersDao.save(answers);}

    @Override
    public void removeAnswers(long id) {answersDao.delete(id);}

    @Override
    public Answers getAnswersById(long id) {return answersDao.getOne(id);}

    @Override
    public List<Answers> listAnswers() {return answersDao.findAll();}

//    @Override
//    public Answers findByAnswer(String name) { return answersDao.findByAnswer(name);}

    @Override
    public List<Answers> findByQuestions(Questions questions) {return answersDao.findByQuestions(questions);}

    @Override
    public Answers findByQuestionsAndAnswer(Questions questions, String answer) {return answersDao.findByQuestionsAndAnswer(questions, answer);}
}
