package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.services.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UsersController {
    private UserService userService;

    UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String printUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserConfirm(@PathVariable long id, ModelMap model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.remove(id);
        return "redirect:/users/list";
    }

    @GetMapping("/create")
    public String createUserPage(ModelMap model) {
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users/list";
    }

    @GetMapping("/update/{id}")
    public String createUserPage(@PathVariable long id, ModelMap model) {
        model.addAttribute("user", userService.getById(id));
        return "update";
    }

    @PostMapping("/update/{id}")
    public String createUser(@PathVariable long id, @ModelAttribute("user") User user) {
        //user.setId(id);
        userService.update(user);
        return "redirect:/users/list";
    }
}
