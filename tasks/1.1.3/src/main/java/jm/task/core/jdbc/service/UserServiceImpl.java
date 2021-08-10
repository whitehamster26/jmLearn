package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao UserDaoImpl = new UserDaoJDBCImpl();

    public void createUsersTable() {
        UserDaoImpl.createUsersTable();
    }

    public void dropUsersTable() {
        UserDaoImpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoImpl.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserDaoImpl.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return UserDaoImpl.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDaoImpl.cleanUsersTable();
    }
}
