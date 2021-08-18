package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Query implements AutoCloseable {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet resultSet;
    private boolean update;

    Query(Connection conn) {
        this.conn = conn;
    }

    public PreparedStatement getStatement(String query) {
        try {
            stmt = conn.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }

    public void apply(boolean update) {
        try {
            if (update) {
                this.update = update;
                stmt.execute();
            } else {
                resultSet = stmt.executeQuery();
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
        stmt.close();
    }
}
