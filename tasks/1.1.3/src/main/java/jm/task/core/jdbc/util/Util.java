package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/task_113?useSSL=false";
    private static final String USER_NAME = "jmlearn";
    private static final String PASS_WORD = "1234";

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    DB_URL, USER_NAME, PASS_WORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
