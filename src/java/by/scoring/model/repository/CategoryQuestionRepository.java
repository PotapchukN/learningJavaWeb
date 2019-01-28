package by.scoring.model.repository;

import by.scoring.model.entity.CategoryQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryQuestionRepository extends JpaRepository<CategoryQuestion, Long> {
    CategoryQuestion findByName(String name);
}
