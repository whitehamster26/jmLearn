package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util connectionUtil;

    public UserDaoJDBCImpl() {
        connectionUtil = new Util();
    }

    private class Query implements AutoCloseable {
        private Connection conn = connectionUtil.getConnection();
        private Statement stmt;
        private ResultSet resultSet;
        private String query;
        private boolean update;

        Query(String query, boolean update) {
            this.query = query;
            this.update = update;

            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void apply() {
            try {
                if (update) {
                    stmt.execute(query);
                } else {
                    resultSet = stmt.executeQuery(query);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public ResultSet getResultSet() {
            if (update) {
                throw new RuntimeException("There's no ResultSet in Update query");
            } else {
                return resultSet;
            }
        }


        @Override
        public void close() throws Exception {
            conn.close();
            stmt.close();
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }


    private void updateQuery(String query) {
        try (Query dbQuery = new Query(query, true)) {
            dbQuery.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users(";
        query += "id INT NOT NULL AUTO_INCREMENT,";
        query += "name VARCHAR(50) NOT NULL,";
        query += "lastName VARCHAR(50) NOT NULL,";
        query += "age INT NOT NULL,";
        query += "PRIMARY KEY (id));";
        updateQuery(query);
    }

    public void dropUsersTable() {
        updateQuery("DROP TABLE IF EXISTS users;");
    }

    public void saveUser(String name, String lastName, byte age) {
        String query;
        query = "INSERT INTO users(name, lastName, age) VALUES";
        query += String.format("('%s', '%s', %d);", name, lastName, age);
        updateQuery(query);
    }

    public void removeUserById(long id) {
        updateQuery(String.format("DELETE FROM users WHERE id=%d", id));
    }

    public List<User> getAllUsers() {
        List<User> users = new LinkedList<>();
        try (Query query = new Query("SELECT * FROM users;", false)) {
            query.apply();
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
        updateQuery("TRUNCATE users;");
    }
}
