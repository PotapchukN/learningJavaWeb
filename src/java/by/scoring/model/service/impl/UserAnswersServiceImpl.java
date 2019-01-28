package by.scoring.model.service.impl;

import by.scoring.model.repository.UserAnswersRepository;
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
    private UserAnswersRepository userAnswersRepository;

    @Override
    public void addUserAnswers(UserAnswers userAnswers) {
        userAnswersRepository.save(userAnswers);
    }

    @Override
    public void updateUserAnswers(UserAnswers userAnswers) {
        userAnswersRepository.save(userAnswers);
    }

    @Override
    public void removeUserAnswers(long id) {
        userAnswersRepository.delete(id);
    }

    @Override
    public void removeAllByUser(User user) {
        List<UserAnswers> l = userAnswersRepository.findAllByUser(user);
        System.out.println(l);
        for (UserAnswers x : l) {
            System.out.println("111");
            userAnswersRepository.delete((Long) x.getId());
        }
    }

    @Override
    public void removeAll() {
        userAnswersRepository.deleteAll();
    }

    @Override
    public List<UserAnswers> findAllByUser(User user) {
        return userAnswersRepository.findAllByUser(user);
    }

    @Override
    public List<UserAnswers> listUserAnswers() {
        return userAnswersRepository.findAll();
    }

    @Override
    public UserAnswers findByAnswers(Answers answers) {
        return userAnswersRepository.findByAnswers(answers);
    }


}
