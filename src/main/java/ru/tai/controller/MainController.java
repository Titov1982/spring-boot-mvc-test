package ru.tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tai.model.Message;
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
    public String index(@AuthenticationPrincipal User user,
                        @RequestParam(value="name", required=false, defaultValue="World") String name,
                        Model model) {
        model.addAttribute("name", name);
        if (user != null){
            model.addAttribute("user", user);
        }
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(@AuthenticationPrincipal User user,
                           Model model){
        model.addAttribute("users", userService.findAll());
        if (user != null){
            model.addAttribute("user", user);
        }
        return "users_list";
    }

    @GetMapping("/messages")
    public String getMessages(@AuthenticationPrincipal User user,
                              Model model){
        model.addAttribute("messages", messageService.findAll());
        if (user != null){
            model.addAttribute("user", user);
        }
        return "messages_list";
    }

    @PostMapping("/add_message")
    public String addMessageToUser(@AuthenticationPrincipal User user,
                                   @RequestParam("message") String message,
                                   Model model){
        userService.addMessageToUser(user.getLogin(), message);
        return "redirect:/messages";
    }

    @PostMapping("/delete_message")
    public String delete_message(@AuthenticationPrincipal User user,
                          @RequestParam("id") String id,
                          Model model){
        User userFromDb = userService.findByLogin(user.getLogin());
        userFromDb.getRoles().iterator();
        Message message = messageService.findById(Long.parseLong(id));
        if (userFromDb.getId() == message.getUser().getId()){
            messageService.deleteById(Long.parseLong(id));
        }
        return "redirect:/messages";
    }
}
