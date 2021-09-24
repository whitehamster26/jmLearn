package ru.dsemenko.springboot.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.dsemenko.springboot.web.models.Role;
import ru.dsemenko.springboot.web.models.User;
import ru.dsemenko.springboot.web.services.RoleService;
import ru.dsemenko.springboot.web.services.UserService;

import java.util.List;

@Configuration
public class DataInit {
    private RoleService roleService;
    private UserService userService;

    DataInit (RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
        fillRoles();
        createAdmin();
    }

    private void fillRoles() {
        roleService.save(new Role("ROLE_USER"));
        roleService.save(new Role("ROLE_ADMIN"));
    }

    private void createAdmin() {
        User u = new User();
        List<Role> roles = roleService.getAllRoles();
        u.setUsername("Admin");
        u.setLastName("Adminov");
        u.setEmail("admin@admin.ru");
        u.setPhoneNumber("+79999999999");
        u.setPassword("admin");
        u.setRoles(roles);
        userService.save(u);
    }
}
