package by.scoring.model.dao;

import by.scoring.model.entity.Answers;
import by.scoring.model.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswersDao extends JpaRepository<Answers, Long>{

    List<Answers> findByQuestions(Questions questions);
    Answers findByQuestionsAndAnswer(Questions questions, String answer);
}
