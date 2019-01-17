package by.scoring.model.service.impl;

import by.scoring.model.dao.UserMoneyDao;
import by.scoring.model.entity.Answers;
import by.scoring.model.entity.User;
import by.scoring.model.entity.UserMoney;
import by.scoring.model.service.IUserMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMoneyServiceImpl implements IUserMoneyService {

    @Autowired
    private UserMoneyDao userMoneyDao;
    @Override
    public void addUserMoney(UserMoney userMoney) {userMoneyDao.save(userMoney);}

    @Override
    public void updateUserMoney(UserMoney userMoney) {userMoneyDao.save(userMoney);}

    @Override
    public void removeUserMoney(long id) {userMoneyDao.delete(id);}

    @Override
    public UserMoney getUserMoneyById(long id) {return userMoneyDao.findOne(id);}

    @Override
    public List<UserMoney> listUserMoney() {return userMoneyDao.findAll();}

    @Override
    public UserMoney findByConsumption(float consumption) {return userMoneyDao.findByConsumption(consumption);}

    @Override
    public UserMoney findByIncome(float income) {return userMoneyDao.findByIncome(income);}

    @Override
    public UserMoney findByUser(User user){return userMoneyDao.findByUser(user);}
}
