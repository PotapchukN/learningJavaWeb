package by.scoring.model.service.impl;

import by.scoring.model.repository.AnswersRepository;
import by.scoring.model.entity.Answers;
import by.scoring.model.entity.Questions;
import by.scoring.model.service.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswersServiceImpl implements IAnswerService {

    @Autowired
    private AnswersRepository answersRepository;

    @Override
    public void addAnswers(Answers answers) {
        answersRepository.save(answers);}

    @Override
    public void updateAnswers(Answers answers) {
        answersRepository.save(answers);}

    @Override
    public void removeAnswers(long id) {
        answersRepository.delete(id);}

    @Override
    public Answers getAnswersById(long id) {return answersRepository.getOne(id);}

    @Override
    public List<Answers> listAnswers() {return answersRepository.findAll();}

    @Override
    public List<Answers> findByQuestions(Questions questions) {return answersRepository.findByQuestions(questions);}

    @Override
    public Answers findByQuestionsAndAnswer(Questions questions, String answer) {return answersRepository.findByQuestionsAndAnswer(questions, answer);}
}
