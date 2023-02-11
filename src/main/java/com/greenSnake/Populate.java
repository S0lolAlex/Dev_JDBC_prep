package com.greenSnake;

import java.sql.*;
import java.util.*;

public class Populate {
    private void insertClients(Connection conn, String req) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(req);
        String[] sql = Database.getRequestResult("./sql/populate_client.sql").split(";");
        for (String name : sql) {
            statement.setString(1, name);
            statement.executeUpdate();
        }

    }

    private void insertPrWork(Connection conn, String req) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(req);
        String[] sql = Database.getRequestResult("./sql/populate_pr_wor.sql").trim().split(";");
        for (String id : sql) {
            String[] set = id.trim().split(",");
            statement.setInt(1, Integer.parseInt(set[0]));
            statement.setInt(2, Integer.parseInt(set[1]));
            statement.addBatch();
        }
        statement.executeBatch();
    }

    private void insertProjects(Connection conn, String req) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(req);
        String[] sql = Database.getRequestResult("./sql/populate_project.sql").split(";");
        for (String id : sql) {
            String[] set = id.trim().replaceAll("\'", "").split(",");
            statement.setInt(1, Integer.parseInt(set[0]));
            statement.setString(2, set[1]);
            statement.setString(3, set[2]);
            statement.addBatch();
        }
        statement.executeBatch();
    }

    private void insertWorker(Connection conn, String req) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(req);
        String[] sql = Database.getRequestResult("./sql/populate_worker.sql").split(";");
        for (String name : sql) {
            String[] set = name.trim().replaceAll("\'", "").split(",");
            statement.setString(1, set[0]);
            statement.setString(2, set[1]);
            statement.setString(3, set[2]);
            statement.setInt(4, Integer.parseInt(set[3]));
            statement.addBatch();
        }
        statement.executeBatch();
    }

    public void populateDb() {
        List<String> list = Arrays.asList("insert into client (name) values (?)",
                "insert into project_worker (project_id, worker_id) values (?,?)",
                "insert into project (client_id, start_date, finish_date) values (?,?,?)",
                "insert into worker (name, birthday, level, salary) values (?,?,?,?)");
        try (Connection connection = Database.getConnection();
        ) {
            insertClients(connection, list.get(0));
            insertProjects(connection, list.get(2));
            insertWorker(connection, list.get(3));
            insertPrWork(connection, list.get(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
