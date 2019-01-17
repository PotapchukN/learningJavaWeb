package by.scoring.model.dao;

import by.scoring.model.entity.CategoryQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryQuestionDao extends JpaRepository<CategoryQuestion, Long> {
    CategoryQuestion findByName(String name);
}
