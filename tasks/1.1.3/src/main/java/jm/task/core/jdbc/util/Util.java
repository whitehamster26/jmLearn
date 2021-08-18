package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/task_113?useSSL=false";
    private static final String USER_NAME = "jmlearn";
    private static final String PASS_WORD = "1234";
    private static Connection conn;

    static public Connection getConnection() {
        try {
            conn = DriverManager.getConnection(
                    DB_URL, USER_NAME, PASS_WORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    static public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
