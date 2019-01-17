package by.scoring.model.service;

import by.scoring.model.entity.Answers;
import by.scoring.model.entity.User;
import by.scoring.model.entity.UserAnswers;
import by.scoring.model.entity.UserMoney;

import java.util.List;

public interface IUserMoneyService {

    void addUserMoney(UserMoney userMoney);

    void updateUserMoney(UserMoney userMoney);

    void removeUserMoney(long id);

    UserMoney getUserMoneyById(long id);

    List<UserMoney> listUserMoney();

    UserMoney findByConsumption(float consumption);
    UserMoney findByIncome(float income);
    UserMoney findByUser(User user);
}
