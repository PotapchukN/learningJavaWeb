package by.scoring.model.service.impl;

import by.scoring.mail.Sender;
import by.scoring.model.repository.UserRepository;
import by.scoring.model.entity.User;
import by.scoring.model.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    private final static String HOST = "http://localhost:8080/";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setRole("ROLE_USER");
        user.setConfirm("N");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        user = userRepository.findByEmail(user.getEmail());
        Sender sender = new Sender("confirm.accont2@gmail.com", "12345678c");
        String subject = "Подтвердите ваш аккаунт";
        String link = HOST + "confirm/" + user.getId();
        String message = "Для подтверждения вашего аккаунта перейдите по ссылке: " + link +
                "  Если вы не регистрировались на сайте проигнорируйте данное сообщение.";
        String fromEmail = "confirm.accont2@gmail.com";
        sender.send(subject,message,fromEmail,user.getEmail());
    }

    @Override
    public void confirm(long id) {
        User user = userRepository.getOne(id);
        user.setConfirm("Y");
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getCurrentUser(){
        try {
            org.springframework.security.core.userdetails.User authUser =
                    (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                            .getAuthentication().getPrincipal();

            return findByEmail(authUser.getUsername());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findById(long id) {
        return userRepository.getOne(id);
    }
}

