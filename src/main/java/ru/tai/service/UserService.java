package ru.tai.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.tai.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAll();
    List<User> findAllWithRolesAndMessages();


//    User findById(Long Id);
//    User findByIdWithRoles(Long Id);
//    User findByIdWithRolesAndMessages(Long Id);

    User findByLogin(String login);
    User findByLoginWithRoles(String login);
    User findByLoginWithRolesAndMessages(String login);

    void add(User user);
    void addUserWithRole(User user, String roleName);

    void addMessageToUser(User user, String message);

    void update(Long oldUserId, User newUser);

    void deleteById(Long id);

}
