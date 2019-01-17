package by.scoring.model.dao;

import by.scoring.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User,Long> {
    User findByEmail(String email);
}