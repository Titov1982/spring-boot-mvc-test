package ru.tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String registration(Model model){
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
        if (user == null){
            User newUser = new User(login, password, firstName, lastName, email, null);
            userService.addUserWithRole(newUser, "USER_R");
        }else{
            System.out.println("Пользователь с таким именем уже зарегистрирован");
        }
        return "redirect:/users";
    }


    @PostMapping("/delete_user")
    public  String deleteUser(@RequestParam("id") String id,
                              Model model){

        Long idFromPage = Long.parseLong(id);
        userService.deleteById(idFromPage);;
        return "redirect:/users";
    }
}
