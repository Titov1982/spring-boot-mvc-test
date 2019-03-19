package ru.tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/registration")
    public String registration(Map<String, Object> model){
        User newUser = new User("*", "*", "*", "*", "*", new Role());
        newUser.setId(1000L);
//        model.put("user", newUser);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
//            @RequestParam(value = "login", required=false) String login,
//                          @RequestParam(value = "password", required=false) String password,
//                          @RequestParam(value = "firstName", required=false) String firstName,
//                          @RequestParam(value = "lastName", required=false) String lastName,
//                          @RequestParam(value = "email", required=false) String email,
                                      Map<String, Object> model){

//        User user = userService.findByLogin(login);
        String log = ((User)model.get("user")).getLogin();
        User user = userService.findByLogin(log);
        if (user == null){
//            model.put("login", login);
//            model.put("password", password);
//            model.put("firstName", firstName);
//            model.put("lastName", lastName);
//            model.put("email", email);

//            User newUser = new User(login, password, firstName, lastName, email, null);
//            User newUser = new User((String)model.get("login"), (String)model.get("password"), (String)model.get("firstName"), (String)model.get("lastName"), (String)model.get("email"), null);
            User newUser = (User) model.get("user");
            userService.add(newUser);
            userService.addRoleToUser(newUser, "USER_R");
        }else{
            System.out.println("Пользователь с таким именем уже зарегистрирован");
        }

        return "users";
    }
}
