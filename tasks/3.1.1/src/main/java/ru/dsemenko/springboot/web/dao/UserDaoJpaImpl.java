package ru.dsemenko.springboot.web.dao;

import org.springframework.stereotype.Repository;
import ru.dsemenko.springboot.web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoJpaImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(User user) {
       entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.createQuery("update User u set u.username = :name, u.age = :age where u.id = :id")
                .setParameter("name", user.getUsername())
                .setParameter("age", user.getAge())
                .setParameter("id", user.getId())
                .executeUpdate();
    }

    @Override
    public void remove(long id) {
      entityManager.createQuery("delete from User u where u.id = :id")
              .setParameter("id", id)
              .executeUpdate();
    }

    @Override
    public User getById(long id) {
        return entityManager.createQuery("select u from User u JOIN FETCH u.roles where u.id = :id ", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User getByName(String name) {
        User user = null;
        try {
            user = entityManager.createQuery("select u from User u JOIN FETCH u.roles where u.username = :name ", User.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return user;
    }


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }
}
