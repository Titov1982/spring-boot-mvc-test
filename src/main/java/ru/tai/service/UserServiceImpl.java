package ru.tai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.stream.Collectors;

@Service
//@Transactional // Если включить транзакции на весь класс, то все методы в нем будут транзакционными
                 // При работе с различными запросами к базе данных в рамках одной транзакции все запросы
                 // происходят в рамкох одной открытой сессии
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    // Получаем название роли пользователя из конфигурационного файла
    @Value(value = "${user.role.name}")
    private String userRoleName;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User findById(Long id) {
        User user = userRepository.getOne(id);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        User user = userRepository.findByLogin(login);
        return user;
    }

    @Override
    public User findByLoginWithRoles(String login) {
        User user = userRepository.findByLogin(login);
        user.getRoles().iterator();
        return user;
    }

    /**
     * Получить всех пользователей с их ролями и сообщениями
     * Так как у ролей и сообщений есть привязки к пользователям, то обнуляем их для разрыва цикличности
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> findAllWithRolesAndMessages() {
        List<User> users = userRepository.findAll();
        List<User> users_list = users.stream().map((u) -> {
            for (Role r: u.getRoles()) {
                r.setUsers(null);
            }
            for (Message m: u.getMessages()) {
                m.setUser(null);
            }
            return u;
        }).collect(Collectors.toList());
        return users_list;
    }

    /**
     * // Прямая сортировка всех пользователей по логину
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> findByOrderByLoginAsc() {
        return userRepository.findByOrderByLoginAsc();
    }

    @Override
    @Transactional
    public void add(User user) {
        if (user != null){
            User userFromDb = userRepository.findByLogin(user.getLogin());
            if (userFromDb == null){
                user.getRoles().iterator();
                Role role = roleService.findByRole(userRoleName);
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
