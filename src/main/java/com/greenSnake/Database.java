package com.greenSnake;

import java.io.*;
import java.sql.*;

public class Database {
    private static final Database SINGLE_DB = new Database();
    private static final String url = "jdbc:h2:./test";
    public Database() {
    }
    public static Database getSingleDb() {
        return SINGLE_DB;
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
    public static String getRequestResult(String fileWay) {
        StringBuilder query = new StringBuilder();
        try (FileInputStream init_db = new FileInputStream(fileWay);
             BufferedReader reader = new BufferedReader(new InputStreamReader(init_db))) {
            String s;
            while ((s = reader.readLine()) != null) {
                query.append(s).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return query.toString();
    }
}
