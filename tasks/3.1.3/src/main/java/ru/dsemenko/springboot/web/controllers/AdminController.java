package ru.dsemenko.springboot.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.dsemenko.springboot.web.models.Role;
import ru.dsemenko.springboot.web.models.User;
import ru.dsemenko.springboot.web.services.RoleService;
import ru.dsemenko.springboot.web.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String redirectToCurrentPath() {
        return "redirect:/admin/";
    }

    @GetMapping("/")
    public String printUsers(ModelMap model, @AuthenticationPrincipal User user) {
        List<User> users = userService.getAllUsers();
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.remove(id);
        return "redirect:/admin/";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        List<Role> roles = new ArrayList<>();
        for (String role : checkBoxRoles) {
            roles.add(roleService.getByName(role));
        }
        user.setRoles(roles);
        userService.save(user);
        return "redirect:/admin/";
    }


    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        List<Role> roles = new ArrayList<>();
        for (String role : checkBoxRoles) {
            roles.add(roleService.getByName(role));
        }
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin/";
    }
}
