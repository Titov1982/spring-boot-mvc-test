package ru.tai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public User findByLogin(String login) {
        User user = userRepository.findByLogin(login);
        return user;
    }

    @Override
    @Transactional
    public void add(User user) {
        if (user != null){
            User userFromDb = userRepository.findByLogin(user.getLogin());
            if (userFromDb == null){
                user.getRoles().iterator();
                Role role = roleService.findByRole("USER_R");
                user.addRole(role);

                userRepository.save(user);
            }
        }
    }

    @Override
    @Transactional
    public void addMessageToUser(String login, String message) {
        User userFromDb = userRepository.findByLogin(login);
        if (userFromDb != null){
            userFromDb.getRoles().iterator();
            userFromDb.getMessages().iterator();
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

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user != null) return user;
        return null;
    }
}
