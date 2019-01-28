package by.scoring.model.service.impl;

import by.scoring.model.repository.QuestionsRepository;
import by.scoring.model.entity.CategoryQuestion;
import by.scoring.model.entity.Questions;
import by.scoring.model.service.IQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsServiceImpl implements IQuestionsService {

    @Autowired
    private QuestionsRepository questionsRepository;
    @Override
    public void addQuestions(Questions questions) {
        questionsRepository.save(questions);}

    @Override
    public void updateQuestions(Questions questions) {
        questionsRepository.save(questions);}

    @Override
    public void removeQuestions(long id) {
        questionsRepository.delete(id);}

    @Override
    public Questions getQuestionsById(long id) {return questionsRepository.findOne(id);}

    @Override
    public List<Questions> listQuestions() {return questionsRepository.findAll();}

    @Override
    public Questions findByQuestion(String question) {return questionsRepository.findByQuestion(question);}

    @Override
    public List<Questions> findByCategoryQuestion(CategoryQuestion cq) {return questionsRepository.findByCategoryQuestion(cq);}
}
