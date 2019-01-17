package by.scoring.model.service;

import by.scoring.model.entity.Answers;
import by.scoring.model.entity.CategoryQuestion;
import by.scoring.model.entity.Questions;

import java.util.List;

public interface IQuestionsService {

    void addQuestions(Questions questions);

    void updateQuestions(Questions questions);

    void removeQuestions(long id);

    Questions getQuestionsById(long id);

    List<Questions> listQuestions();

    Questions findByQuestion(String question);
    List<Questions> findByCategoryQuestion(CategoryQuestion cq);
}
