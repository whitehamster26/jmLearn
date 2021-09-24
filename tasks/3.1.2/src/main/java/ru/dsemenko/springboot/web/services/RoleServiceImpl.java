package ru.dsemenko.springboot.web.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dsemenko.springboot.web.dao.RoleRepository;
import ru.dsemenko.springboot.web.models.Role;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void remove(long id) {
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getById(long id) {
        return roleRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getByName(String role) {
        return roleRepository.findByRole(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
