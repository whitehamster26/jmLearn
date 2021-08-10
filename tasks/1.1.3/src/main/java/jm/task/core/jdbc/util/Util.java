package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private final String DB_URL = "jdbc:mysql://localhost:3306/task_113?useSSL=false";
    private final String USERNAME = "jmlearn";
    private final String PASSWORD = "1234";

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
