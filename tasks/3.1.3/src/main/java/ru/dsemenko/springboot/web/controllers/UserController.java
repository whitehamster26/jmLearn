package ru.dsemenko.springboot.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dsemenko.springboot.web.models.User;
import ru.dsemenko.springboot.web.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getUserDetails(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "user";
    }
}
