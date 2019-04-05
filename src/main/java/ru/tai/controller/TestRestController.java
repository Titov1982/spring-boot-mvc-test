package ru.tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tai.model.User;
import ru.tai.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class TestRestController {

    @Autowired
    private UserService userService;


    class Hello{
        private final long id;
        private final String content;

        public Hello(long id, String content) {
            this.id = id;
            this.content = content;
        }

        public String getText() {
            return content;
        }

        public long getId() {
            return id;
        }
    }

    @GetMapping("hello")
    public Hello hello(){
        Hello hello = new Hello(123, "Hello world!");
//        return "Hello world!";
        return hello;
    }

    @GetMapping("users")
    public List<User> getUsers(@AuthenticationPrincipal User user){
//        List<User> users = userService.findAll();
        // Получение списка пользователей с обнуленным списком ролей и сообщений
//        users = users.stream().map((u) -> {
//            u.setRoles(null);
//            u.setMessages(null);
//            return u;
//        }).collect(Collectors.toList());

        List<User> users = userService.findAllWithRolesAndMessages();

        return users;
    }


}
