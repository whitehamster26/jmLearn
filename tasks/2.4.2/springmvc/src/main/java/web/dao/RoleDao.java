package web.dao;

import web.models.Role;
import web.models.User;

import java.util.List;

public interface RoleDao {
    void save(Role role);
    void remove(long id);
    void update(Role role);
    Role getById(long id);
    Role getByName(String name);
    List<Role> getAllRoles();
}
