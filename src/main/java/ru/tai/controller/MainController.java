package ru.tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tai.model.User;
import ru.tai.service.MessageService;
import ru.tai.service.UserService;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/")
    public String index(@RequestParam(value="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model){
        List<User> users = userService.findAll();

        model.addAttribute("users", users);
//        model.addAttribute("user", )
        return "users_list";
    }

    @GetMapping("/messages")
    public String getMessages(Model model){
        model.addAttribute("messages", messageService.findAll());
        return "messages_list";
    }


    @PostMapping("/add_message")
    public String addMessageToUser(@RequestParam("message") String message,
                                   Model model){
//        User user = (User) model.asMap().get("user");
        User user = userService.findByLogin("admin");   // Заглушка!!! Пользователя ноебходимо извлекать из модели или еще откуда
        userService.addMessageToUser(user, message);
        return "redirect:/messages_list";
    }
}
