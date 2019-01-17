package by.scoring.model.service;

import by.scoring.model.entity.Answers;
import by.scoring.model.entity.Questions;
import by.scoring.model.entity.User;
import by.scoring.model.entity.UserAnswers;

import java.util.List;

public interface IUserAnswersService {

    void addUserAnswers(UserAnswers userAnswers);

    void updateUserAnswers(UserAnswers userAnswers);

    void removeUserAnswers(long id);

    List<UserAnswers> findAllByUser(User user);

    List<UserAnswers> listUserAnswers();

    void removeAll();
    void removeAllByUser(User user);

    UserAnswers findByAnswers(Answers answers);
//    UserAnswers findByQuestion_id(int id);
}
