package ru.tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.tai.model.User;
import ru.tai.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class TestRestController {

    @Autowired
    private UserService userService;


    class Hello{
        private long id;
        private String content;

        public Hello(){}

        public Hello(long id, String content) {
            this.id = id;
            this.content = content;
        }

        public long getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        @Override
        public String toString() {
            return "Hello{" +
                    "id=" + id +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "hello")
    public Hello hello(){
        Hello hello = new Hello(123, "Hello world!");
        return hello;
    }

    @ResponseStatus(HttpStatus.OK)
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
