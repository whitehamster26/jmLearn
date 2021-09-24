package ru.dsemenko.springboot.web.dao;

import ru.dsemenko.springboot.web.models.Role;

import java.util.List;

public interface RoleDao {
    void save(Role role);
    void remove(long id);
    void update(Role role);
    Role getById(long id);
    Role getByName(String name);
    List<Role> getAllRoles();
}
