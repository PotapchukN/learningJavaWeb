package by.scoring.model.service.impl;

import by.scoring.model.repository.UserMoneyRepository;
import by.scoring.model.entity.User;
import by.scoring.model.entity.UserMoney;
import by.scoring.model.service.IUserMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMoneyServiceImpl implements IUserMoneyService {

    @Autowired
    private UserMoneyRepository userMoneyRepository;
    @Override
    public void addUserMoney(UserMoney userMoney) {
        userMoneyRepository.save(userMoney);}

    @Override
    public void updateUserMoney(UserMoney userMoney) {
        userMoneyRepository.save(userMoney);}

    @Override
    public void removeUserMoney(long id) {
        userMoneyRepository.delete(id);}

    @Override
    public UserMoney getUserMoneyById(long id) {return userMoneyRepository.findOne(id);}

    @Override
    public List<UserMoney> listUserMoney() {return userMoneyRepository.findAll();}

    @Override
    public UserMoney findByConsumption(float consumption) {return userMoneyRepository.findByConsumption(consumption);}

    @Override
    public UserMoney findByIncome(float income) {return userMoneyRepository.findByIncome(income);}

    @Override
    public UserMoney findByUser(User user){return userMoneyRepository.findByUser(user);}
}
