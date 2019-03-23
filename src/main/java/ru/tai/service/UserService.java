package ru.tai.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.tai.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAll();

    User findByLogin(String login);

    void add(User user);

    void addMessageToUser(String login, String message);

    void update(Long oldUserId, User newUser);

    void deleteById(Long id);

}
