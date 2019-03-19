package ru.tai.service;

import ru.tai.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    List<User> findAllWithRolesAndMessages();


//    User findById(Long Id);
//    User findByIdWithRoles(Long Id);
//    User findByIdWithRolesAndMessages(Long Id);

    User findByLogin(String login);
    User findByLoginWithRoles(String login);
    User findByLoginWithRolesAndMessages(String login);

    void add(User user);
    void addRoleToUser(User user, String roleName);

    void addMessageToUser(User user, String message);

    void update(Long oldUserId, User newUser);

    void deleteById(Long id);

}
