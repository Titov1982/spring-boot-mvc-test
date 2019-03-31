package ru.tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tai.model.Role;
import ru.tai.model.User;
import ru.tai.service.UserService;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getUsers(@AuthenticationPrincipal User user,
                           Model model){
        model.addAttribute("users", userService.findAll());
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
                             Model model){
        User userFromDb = userService.findByLogin(user.getLogin());
        // Преобразуем ID из String в Long
        Long idFromPage = Long.parseLong(id);
        // Если ID залогиненного пользователяь = ID выбранного для удаления пользователя,
        // то удаляем выбранного пользователя
        if (idFromPage == userFromDb.getId()){
            userService.deleteById(idFromPage);
            return "redirect:/login";
        }
        // Иначе просматриваем все роли зарегистрированного пользователя и если находим роль "ADMIN_R",
        // то удаляем выбранного пользователя
        List<Role> roles = userFromDb.getRoles();
        for (Role role: roles){
            if(role.getRole().equals("ADMIN_R")){
                userService.deleteById(idFromPage);
            }
        }
        return "redirect:/users";
    }

    @GetMapping("/userEdit")
    public String userEdit(@AuthenticationPrincipal User user,
                           @RequestParam(value="id", defaultValue="0") String id,
                           Model model){
        Long idLong = Long.parseLong(id);
        if (user != null){
            // Если приходит ID пользователя не равный 0, то получаем данные о редактируемом пользователе,
            // а это значит, что редактирование осуществляет не сам выбранный пользователь, а админисратор
            if(idLong != 0){
                User editableUser =  userService.findById(idLong);
                model.addAttribute("user", editableUser);
            // Иначе, пользователь редактирует сам себя
            }else{
                model.addAttribute("user", user);
            }
            User userFromDb = userService.findByLogin(user.getLogin());
            // Отправляем в шаблон данные, является ли пользователь администратором
            if(userFromDb.isAdmin()){
                model.addAttribute("adminUser", true);
            }else{
                model.addAttribute("adminUser", false);
            }

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
                             Model model){
        // Ищем пользователя с указанным логинов в БД
        User userFromDb = userService.findByLogin(login);
        // Если нашли, то регистрируем нового пользователя
        if (userFromDb != null){
            userFromDb.setLogin(login);
            userFromDb.setPassword(password);
            userFromDb.setFirstName(firstName);
            userFromDb.setLastName(lastName);
            userFromDb.setEmail(email);
            userService.update(userFromDb.getId(), userFromDb);
        }
        return "redirect:/users";
    }

}
