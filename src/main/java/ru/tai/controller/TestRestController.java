package ru.tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.tai.model.User;
import ru.tai.service.UserService;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/rest")
public class TestRestController {

    @Autowired
    private UserService userService;


    @GetMapping("hello")
    @ResponseBody
    public String hello(){
        return "Hello world!";
    }

    @GetMapping("users")
    @ResponseBody
    public String getUsers(){
        List<User> users = userService.findAll();
        String usersStr = "";

        for (User user: users) {
            usersStr += user.toString() + "  ";
        }
        return usersStr;
    }

}
