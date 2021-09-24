package ru.dsemenko.springboot.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String indexController() {
        return "redirect:/user";
    }
}
