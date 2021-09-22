package web.services;

import web.models.Role;

import java.util.List;

public interface RoleService {
    void save(Role role);
    void remove(long id);
    void update(Role role);
    Role getById(long id);
    Role getByName(String name);
    List<Role> getAllRoles();
}
