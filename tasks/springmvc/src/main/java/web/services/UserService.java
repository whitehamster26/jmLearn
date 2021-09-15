package web.services;

import web.models.User;

import java.util.List;

public interface UserService {
    void save(User user);
    void remove(long id);
    void update(User user);
    User getById(long id);
    List<User> getAllUsers();
}
