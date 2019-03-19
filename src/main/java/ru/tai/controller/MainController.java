package ru.tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tai.model.User;
import ru.tai.service.UserService;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(@RequestParam(value="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Map<String, Object> model){
        List<User> users = userService.findAll();

        model.put("users", users);

        return "users_list";
    }
}
