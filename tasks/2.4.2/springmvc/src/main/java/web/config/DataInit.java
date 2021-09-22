package web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import web.models.Role;
import web.models.User;
import web.services.RoleService;
import web.services.UserService;

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
        u.setUsername("admin");
        u.setPassword("admin");
        u.setAge((byte) 69);
        u.setRoles(roles);
        userService.save(u);
    }
}
