package web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.models.Role;
import web.models.User;
import web.services.RoleService;
import web.services.RoleServiceImpl;
import web.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private UserService userService;

    AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String redirectToCurrentPath() {
        return "redirect:/admin/";
    }

    @GetMapping("/")
    public String printUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserConfirm(@PathVariable Long id, ModelMap model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.remove(id);
        return "redirect:/admin/";
    }

    @GetMapping("/create/")
    public String createUserPage(ModelMap model) {
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping("/create/")
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/";
    }

    @GetMapping("/update/{id}")
    public String updateUserPage(@PathVariable Long id, ModelMap model) {
        model.addAttribute("user", userService.getById(id));
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin/";
    }
}
