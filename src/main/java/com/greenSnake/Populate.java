package com.greenSnake;

import java.sql.*;
import java.util.*;

public class Populate {
    private void insertClients(PreparedStatement prepare) throws SQLException {
        String[] sql = Database.getRequestResult("./sql/populate_client.sql").split(";");
        for(String name : sql){
            prepare.setString(1,name);
            prepare.addBatch();
        }
        prepare.executeBatch();
    }
    private void insertPrWork(PreparedStatement prepare) throws SQLException{
        String[] sql = Database.getRequestResult("./sql/populate_pr_wor.sql").trim().split(";");
        for(String id : sql){
            String[] set = id.trim().split(",");
            prepare.setInt(1,Integer.parseInt(set[0]));
            prepare.setInt(2,Integer.parseInt(set[1]));
            prepare.addBatch();
        }
        prepare.executeBatch();
    }

    private void insertProjects(PreparedStatement prepare) throws SQLException{
        String[] sql = Database.getRequestResult("./sql/populate_project.sql").split(";");
        for(String id : sql){
            String[] set = id.trim().replaceAll("\'","").split(",");
            prepare.setInt(1,Integer.parseInt(set[0]));
            prepare.setString(2,set[1]);
            prepare.setString(3,set[2]);
            prepare.addBatch();
        }
        prepare.executeBatch();
    }

    private void insertWorker(PreparedStatement prepare)throws SQLException{
        String[] sql = Database.getRequestResult("./sql/populate_worker.sql").split(";");

        for(String name : sql){
            String[] set = name.trim().replaceAll("\'","").split(",");
            prepare.setString(1,set[0]);
            prepare.setString(2,set[1]);
            prepare.setString(3,set[2]);
            prepare.setInt(4,Integer.parseInt(set[3]));
            prepare.addBatch();
        }
    }

    public void populateDb(){
        List<String> list = Arrays.asList("insert into client (name) values (?)",
                "insert into project_worker (project_id, worker_id) values (?,?)",
                "insert into project (client_id, start_date, finish_date) values (?,?,?)",
                "insert into worker (name, birthday, level, salary) values (?,?,?,?)");
        try (Connection connection = Database.getConnection();
        )
        {PreparedStatement statement;
            statement = connection.prepareStatement(list.get(0));
            insertClients(statement);
            statement = connection.prepareStatement(list.get(1));
            insertPrWork(statement);
            statement = connection.prepareStatement(list.get(2));
            insertProjects(statement);
            statement = connection.prepareStatement(list.get(3));
            insertWorker(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
