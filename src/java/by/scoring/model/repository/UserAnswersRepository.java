package by.scoring.model.repository;

import by.scoring.model.entity.Answers;
import by.scoring.model.entity.User;
import by.scoring.model.entity.UserAnswers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAnswersRepository extends JpaRepository<UserAnswers,Long> {
    UserAnswers findByAnswers(Answers answers);
    List<UserAnswers> findAllByUser(User user);
    List<UserAnswers> findAllByUserAndRisk(User user, String risk);
    void removeAllByUser(User user);
}
