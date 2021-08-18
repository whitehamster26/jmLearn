package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }

    public void createUsersTable() {
        String queryString = "CREATE TABLE IF NOT EXISTS users(";
        queryString += "id INT NOT NULL AUTO_INCREMENT,";
        queryString += "name VARCHAR(50) NOT NULL,";
        queryString += "lastName VARCHAR(50) NOT NULL,";
        queryString += "age INT NOT NULL,";
        queryString += "PRIMARY KEY (id));";

        try (Query query = new Query(connection)) {
            query.getStatement(queryString);
            query.apply(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Query query = new Query(connection)) {
            query.getStatement("DROP TABLE IF EXISTS users;");
            query.apply(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String queryString = "INSERT INTO users(name, lastName, age) VALUES (?, ?, ?);";
        try (Query query = new Query(connection)) {
            PreparedStatement stmt = query.getStatement(queryString);
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            query.apply(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String queryString = "DELETE FROM users WHERE id=?;";
        try (Query query = new Query(connection)) {
            PreparedStatement stmt = query.getStatement(queryString);
            stmt.setLong(1, id);
            query.apply(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        // Создаем LinkedList т.к. нам не нужен произвольный доступ, и его проще расширять.
        List<User> users = new LinkedList<>();
        try (Query query = new Query(connection)) {
            query.getStatement("SELECT * FROM users;");
            query.apply(false);
            ResultSet rs = query.getResultSet();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");
                users.add(new User(name, lastName, age));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Query query = new Query(connection)) {
            query.getStatement("TRUNCATE users;");
            query.apply(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
