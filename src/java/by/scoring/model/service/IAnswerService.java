package by.scoring.model.service;

import by.scoring.model.entity.Answers;
import by.scoring.model.entity.Questions;

import java.util.List;

public interface IAnswerService {
    void addAnswers(Answers answers);

    void updateAnswers(Answers answers);

    void removeAnswers(long id);

    Answers getAnswersById(long id);

    List<Answers> listAnswers();

    List<Answers> findByQuestions(Questions questions);
    Answers findByQuestionsAndAnswer(Questions questions, String answer);
}
