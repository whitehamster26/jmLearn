package ru.dsemenko.springboot.web.dao;


import ru.dsemenko.springboot.web.models.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    void remove(long id);
    void update(User user);
    User getById(long id);
    User getByName(String name);
    List<User> getAllUsers();
}
