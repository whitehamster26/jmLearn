package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.*;
import java.util.Properties;

public class Util {
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    Credentials.DB_URL, Credentials.USER_NAME, Credentials.PASS_WORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}