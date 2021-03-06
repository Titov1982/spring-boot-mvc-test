package ru.tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import ru.tai.model.Role;
import ru.tai.model.User;
import ru.tai.service.RoleService;
import ru.tai.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    // Получаем название роли администратора из конфигурационного файла
    @Value(value = "${admin.role.name}")
    private String adminRoleName;

    // Получаем название роли администратора из конфигурационного файла
    @Value(value = "${guest.role.name}")
    private String guestRoleName;


    @GetMapping("/users")
    public String getUsers(@AuthenticationPrincipal User user,
                           Model model){
        model.addAttribute("users", userService.findAll());
//        model.addAttribute("users", userService.findByOrderByLoginAsc());
        // Добавляем в модель страницы информацию о залогиненном пользователе
        if (user != null){
            model.addAttribute("user", user);
        }
        return "users_list";
    }

    /**
     * POST метод для удаления выбранного пользователя по пришедшему ID из формы на странице
     * @param id
     * @param model
     * @return
     */
    @PostMapping("/delete_user")
    public String deleteUser(@AuthenticationPrincipal User user,
                             @RequestParam("id") String id,
                             Model model,
                             // Добавлен входной параметр с информацией о текущей сессии пользователя
                             HttpSession session){
        User userFromDb = userService.findByLogin(user.getLogin());
        // Преобразуем ID из String в Long
        Long idFromPage = Long.parseLong(id);
        // Если ID залогиненного пользователяь = ID выбранного для удаления пользователя,
        // то удаляем выбранного пользователя
        if (idFromPage == userFromDb.getId()){
            userService.deleteById(idFromPage);

            // После удаления текущего пользователя из БД необходимо очистить его сессию
            // Получаем контекст безовасности из текущей сессии
            SecurityContext sc = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
            // Получаем данные текущего пользователя из сессии
//            User u = (User) ssc.getAuthentication().getPrincipal();
            // Еще один способ доступа к данным сессии
//            RequestContextHolder.currentRequestAttributes().getSessionId();
            // Сбрасываем его
            sc.setAuthentication(null);

            return "redirect:/login";
        }
        // Иначе просматриваем все роли зарегистрированного пользователя и если находим роль указанную в adminRoleName,
        // то удаляем выбранного пользователя
        List<Role> roles = userFromDb.getRoles();
        for (Role role: roles){
            if(role.getRole().equals(adminRoleName)){
                userService.deleteById(idFromPage);
            }
        }
        return "redirect:/users";
    }

    @GetMapping("/userEdit")
    public String userEdit(@AuthenticationPrincipal User user,
                           @RequestParam(value="id") String id,
                           Model model){
        // Если в ID пользователя приходит GUEST, то переходим на начальную страницу
        if (id.equals(guestRoleName)){
            return "index";
        }
        Long idLong = Long.parseLong(id);
        if (user != null){
            // Получаем данные пользователя из БД с ролями
            User userFromDb = userService.findByLogin(user.getLogin());
            // Проверяем, является ли залогиненный пользователь, осуществляющий редактирование администратором,
            // если да, то разрешаем редактировать данные любого пользователя ID, которого пришел в параметре id.
            // Если нет, то выдаем на редактирование данные самого пользователя, который запросил редактирование.
            if(userFromDb.isAdmin(adminRoleName)){
                User editableUser =  userService.findById(idLong);
                model.addAttribute("user", editableUser);
                // Отправляем в шаблон данные, что пользователь является администратором
                model.addAttribute("adminUser", true);
            // Иначе, пользователь редактирует сам себя
            }else{
                model.addAttribute("user", userFromDb);
                // Отправляем в шаблон данные, что пользователь не является администратором
                model.addAttribute("adminUser", false);
            }
            // Добавляем в модель список всех существующих в БД ролей
            model.addAttribute("roles", roleService.findAll());
        }
        return "userEdit";

    }

    @PostMapping("/userEdit")
    public String userUpdate(@AuthenticationPrincipal User user,
                             @RequestParam("login") String login,
                             @RequestParam("password") String password,
                             @RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("email") String email,
                             // В данном ассоциативном массиве мы получаем весь список входных парамеиров <input> со страницы.
                             // Список параметров является переменным, так как checkbox элементы попадают в массив
                             // только если они отмечены
                             @RequestParam Map<String, String> form,
                             Model model){
        // Ищем пользователя с указанным логинов в БД
        User userFromDb = userService.findByLogin(login);
        // Если  нашли, то редактируем пользователя
        if (userFromDb != null){
            userFromDb.setLogin(login);
            userFromDb.setPassword(password);
            userFromDb.setFirstName(firstName);
            userFromDb.setLastName(lastName);
            userFromDb.setEmail(email);

            // Получаем залогиненного пользователя из базы данных, для выборки имеющихся у него ролей,
            // так как во входном параметре user не подгружены роли
            User loggedUser= userService.findByLoginWithRoles(user.getLogin());

            // Если пользователь редактирующий параметры пользователя
            // является администратором, то редактируем роли
            if(loggedUser.isAdmin(adminRoleName)){
                // Предварительно очещаем список ролей пользователя
                userFromDb.getRoles().clear();
                // Получаем полный список возможных ролей из БД
                List<Role> roles = roleService.findAll();

                // Выбираем из списка <input> элементов формы те, ключи которых при создании объекта Role
                // образуют объект Role существующий в БД, и все совпадающие добавляем пользователю.
                for (String key: form.keySet()) {
                    if(roles.contains(new Role(key))){
                        userFromDb.addRole(roleService.findByRole(key));
                    }
                }
            }

            // Сохраняем изменения в БД
            userService.update(userFromDb.getId(), userFromDb);
        }
        return "redirect:/users";
    }

}
