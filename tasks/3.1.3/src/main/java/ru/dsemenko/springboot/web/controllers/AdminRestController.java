package ru.dsemenko.springboot.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dsemenko.springboot.web.models.Role;
import ru.dsemenko.springboot.web.models.User;
import ru.dsemenko.springboot.web.services.RoleService;
import ru.dsemenko.springboot.web.services.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;


    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("list/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("authorities")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping(value = "admin")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("admin")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("admin/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        userService.remove(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}