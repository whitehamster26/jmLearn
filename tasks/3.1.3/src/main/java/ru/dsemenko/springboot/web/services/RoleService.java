package ru.dsemenko.springboot.web.services;



import ru.dsemenko.springboot.web.models.Role;

import java.util.List;

public interface RoleService {
    void save(Role role);
    void remove(long id);
    void update(Role role);
    Role getById(long id);
    Role getByName(String name);
    List<Role> getAllRoles();
}
