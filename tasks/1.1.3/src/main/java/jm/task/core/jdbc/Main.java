package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        List<User> users = new LinkedList<>(Arrays.asList(
                new User("Mikhail", "Ivanov", (byte) 25),
                new User("Svetlana", "Ivanova", (byte) 23),
                new User("Timofei", "Zubov", (byte) 30),
                new User("Anton", "Zhukov", (byte) 30)
        ));
        for (User user : users) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.printf("User с именем – %s добавлен в базу данных\n", user.getName());
        }
        users.clear();
        users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
