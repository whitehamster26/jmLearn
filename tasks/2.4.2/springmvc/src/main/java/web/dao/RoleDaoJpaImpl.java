package web.dao;

import org.springframework.stereotype.Repository;
import web.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoJpaImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void update(Role role) {
        entityManager.merge(role);
    }

    @Override
    public void remove(long id) {
        entityManager.createQuery("delete from Role r where r.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Role getById(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role getByName(String name) {
        name = "ROLE_" + name;
        return entityManager.createQuery("select r from Role r where r.role = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }


    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }
}
