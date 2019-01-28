package by.scoring.model.repository;

import by.scoring.model.entity.CategoryQuestion;
import by.scoring.model.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    Questions findByQuestion(String question);
    List<Questions> findByCategoryQuestion(CategoryQuestion cq);
}
