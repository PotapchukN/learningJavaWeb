package by.scoring.model.service;

import by.scoring.model.entity.User;

/**
 * Service class for {@link net.logistic.model.entity.User}
 */
public interface IUserService {

    void save(User user);

    User findByEmail(String email);

    void confirm(long id);

    User findById(long id);

    User getCurrentUser();
}
