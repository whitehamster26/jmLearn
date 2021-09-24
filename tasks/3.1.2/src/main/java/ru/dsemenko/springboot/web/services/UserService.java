package ru.dsemenko.springboot.web.services;

import ru.dsemenko.springboot.web.models.User;

import java.util.List;

public interface UserService {
    void save(User user);
    void remove(long id);
    void update(User user);
    User getById(long id);
    User getByEmail(String email);
    List<User> getAllUsers();
}
