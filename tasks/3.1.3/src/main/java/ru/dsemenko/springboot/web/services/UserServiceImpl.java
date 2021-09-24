package ru.dsemenko.springboot.web.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dsemenko.springboot.web.dao.RoleRepository;
import ru.dsemenko.springboot.web.dao.UserRepository;
import ru.dsemenko.springboot.web.models.User;

import java.util.HashSet;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void save(User user) {
        if (user.getRoles() == null || user.getRoles().size() == 0) {
            user.setRoles(new HashSet<>());
            user.addRole(roleRepository.findByRole("ROLE_USER"));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void remove(long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(long id) {
        return userRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
