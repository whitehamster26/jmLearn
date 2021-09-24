package ru.dsemenko.springboot.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dsemenko.springboot.web.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);
}
