package by.scoring.model.dao;

import by.scoring.model.entity.CategoryQuestion;
import by.scoring.model.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionsDao extends JpaRepository<Questions, Long> {
    Questions findByQuestion(String question);
    List<Questions> findByCategoryQuestion(CategoryQuestion cq);
}
