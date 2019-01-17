package by.scoring.model.service.impl;

import by.scoring.model.dao.UserAnswersDao;
import by.scoring.model.entity.Answers;
import by.scoring.model.entity.User;
import by.scoring.model.entity.UserAnswers;
import by.scoring.model.service.IUserAnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAnswersServiceImpl implements IUserAnswersService {

    @Autowired
    private UserAnswersDao userAnswersDao;
    @Override
    public void addUserAnswers(UserAnswers userAnswers) {userAnswersDao.save(userAnswers);}

    @Override
    public void updateUserAnswers(UserAnswers userAnswers) {userAnswersDao.save(userAnswers);}

    @Override
    public void removeUserAnswers(long id) {userAnswersDao.delete(id);}

    @Override
    public void removeAllByUser(User user) {
        List<UserAnswers> l = userAnswersDao.findAllByUser(user);
        System.out.println(l);
        for (UserAnswers x:l) {
            System.out.println("111");
            userAnswersDao.delete((Long)x.getId());
//            System.out.println("12121212");
//            userAnswersDao.deleteAllInBatch();
        }
    }

    @Override
    public void removeAll(){userAnswersDao.deleteAll();}

    @Override
    public List<UserAnswers> findAllByUser(User user) {return userAnswersDao.findAllByUser(user);}

    @Override
    public List<UserAnswers> listUserAnswers() {return userAnswersDao.findAll();}

    @Override
    public UserAnswers findByAnswers(Answers answers) {return userAnswersDao.findByAnswers(answers);}

//    @Override
//    public UserAnswers findByQuestion_id(int id) {return userAnswersDao.findByQuestion_id(id);}

}
