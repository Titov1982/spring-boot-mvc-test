package ru.tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tai.model.Role;
import ru.tai.model.User;
import ru.tai.service.UserService;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;


/**
 * Контроллер обрабатывает запросы по пользователям
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * GET метод, который показывает страницу регистрации нового пользователя registration.ftl.
     * На странице расположена форма регистрации.
     * @param model
     * @return
     */
    @GetMapping("/registration")
    public String registration(@AuthenticationPrincipal User user,
                               Model model){
        if (user != null){
            model.addAttribute("user", user);
        }
        return "registration";
    }

    /**
     * POST метод извлечения информации из формы регистрации пользователя со страницы registration.ftl
     * и отправки данных в контроллер. Далее по полученным данным создается новый объект пользователь и
     * записывается в базу данных. После регистрации переходим на страницу со списком пользователей.
     * @param login
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     * @param model
     * @return
     */
    @PostMapping("/registration")
    public String addUser(@RequestParam("login") String login,
                          @RequestParam("password") String password,
                          @RequestParam("firstName") String firstName,
                          @RequestParam("lastName") String lastName,
                          @RequestParam("email") String email,
                                      Model model){
        // Ищем пользователя с указанным логинов в БД
        User user = userService.findByLogin(login);
        // Если не нашли, то регистрируем нового пользователя
        if (user == null){
            User newUser = new User(login, password, firstName, lastName, email, true, null);
            userService.add(newUser);
            return "redirect:/login";
        }else{
            System.out.println("Пользователь с таким именем уже зарегистрирован");
            return "redirect:/registration";

        }
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
                    Model model){
        if (user != null){
            model.addAttribute("user", user);
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
        // Если не нашли, то регистрируем нового пользователя
        if (userFromDb == null){
            user.setLogin(login);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            userService.update(user.getId(), user);
        }
        return "redirect:/users";
    }

}
