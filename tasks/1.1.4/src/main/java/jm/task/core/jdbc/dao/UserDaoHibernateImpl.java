package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String queryString = "CREATE TABLE IF NOT EXISTS users(";
        queryString += "id INT NOT NULL AUTO_INCREMENT,";
        queryString += "name VARCHAR(50) NOT NULL,";
        queryString += "lastName VARCHAR(50) NOT NULL,";
        queryString += "age INT NOT NULL,";
        queryString += "PRIMARY KEY (id));";
        Session session = sessionFactory.openSession();
        session.createSQLQuery(queryString).executeUpdate();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        session.createSQLQuery("DROP TABLE IF EXISTS users;").executeUpdate();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("delete User where id = :ID");
        query.setDouble("ID", id);
        query.executeUpdate();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> users = (List<User>) session.createQuery("FROM User").list();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        session.createSQLQuery("TRUNCATE users;").executeUpdate();
        session.close();
    }
}
