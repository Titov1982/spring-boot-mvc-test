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
