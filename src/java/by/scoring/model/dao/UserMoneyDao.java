package by.scoring.model.dao;

import by.scoring.model.entity.User;
import by.scoring.model.entity.UserMoney;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMoneyDao extends JpaRepository<UserMoney, Long> {

    UserMoney findByConsumption(float consumption);
    UserMoney findByIncome(float income);
    UserMoney findByUser(User user);
}
