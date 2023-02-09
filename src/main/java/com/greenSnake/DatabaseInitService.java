package com.greenSnake;
public class DatabaseInitService {
    public static void main(String[] args) {
        new SqlUtil().executeUpdateSql(Database.getRequestResult("./sql/init_db.sql"));
    }
}
