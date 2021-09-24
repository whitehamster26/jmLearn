package ru.dsemenko.springboot.web.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dsemenko.springboot.web.dao.RoleDao;
import ru.dsemenko.springboot.web.models.Role;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    @Transactional
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    @Transactional
    public void remove(long id) {
        roleDao.remove(id);
    }

    @Override
    @Transactional
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getById(long id) {
        return roleDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getByName(String name) {
        return roleDao.getByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}
