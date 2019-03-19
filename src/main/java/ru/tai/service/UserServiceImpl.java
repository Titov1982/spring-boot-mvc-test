package ru.tai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tai.model.Message;
import ru.tai.model.Role;
import ru.tai.model.User;
import ru.tai.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
//@Transactional // Если включить транзакции на весь класс, то все методы в нем будут транзакционными
                 // При работе с различными запросами к базе данных в рамках одной транзакции все запросы
                 // происходят в рамкох одной открытой сессии
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllWithRolesAndMessages() {
        List<User> users = findAll();
        for (User user: users) {
            user.getRoles().iterator();
            user.getMessages().iterator();
        }
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        User user = userRepository.findByLogin(login);
        if (user != null){
            user.setRoles(null);
        user.setMessages(null);
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByLoginWithRoles(String login) {
        User user = userRepository.findByLogin(login);
        if (user != null){
            user.getRoles().iterator(); // Заставляем загрузить данные из сущности ROLE в единой транзакции
            user.setMessages(null);
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByLoginWithRolesAndMessages(String login) {
        User user = userRepository.findByLogin(login);
        if (user != null){
            user.getRoles().iterator(); // Заставляем загрузить данные из сущности ROLE в единой транзакции
        user.getMessages().iterator();  // Заставляем загрузить данные из сущности MESSAGE в единой транзакции
        }
        return user;
    }

    @Override
    @Transactional
    public void add(User user) {
        if (user != null){
            User userFromDb = userRepository.findByLogin(user.getLogin());
            if (userFromDb == null){
                userRepository.save(user);
            }
        }
    }

    @Override
    @Transactional
    public void addRoleToUser(User user, String roleName) {
        Role role = roleService.findByRole(roleName);
        if (role == null) {
            user.addRole(role);
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void addMessageToUser(User user, String message) {
        User userFromDb = userRepository.findByLogin(user.getLogin());
        if (userFromDb != null){
            Message newMessage = new Message(message, userFromDb);
            userFromDb.addMessage(newMessage);
            userRepository.save(userFromDb);
        }
    }

    @Override
    @Transactional
    public void update(Long oldUserId, User newUser) {
        newUser.setId(oldUserId);
        userRepository.save(newUser);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            // Обнуляем привязку пользователя к ролям, чтобы при каскадном удалении был удален сам пользователь из
            // таблице пользователей и его привязка в промежуточной таблице, но сохранилась сама роль в таблице ролей
            user.get().setRoles(null);
            userRepository.delete(user.get());
        }
    }


}
