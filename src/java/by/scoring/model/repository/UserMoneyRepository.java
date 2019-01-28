package by.scoring.model.repository;

import by.scoring.model.entity.User;
import by.scoring.model.entity.UserMoney;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMoneyRepository extends JpaRepository<UserMoney, Long> {

    UserMoney findByConsumption(float consumption);
    UserMoney findByIncome(float income);
    UserMoney findByUser(User user);
}
