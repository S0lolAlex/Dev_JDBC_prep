package com.greenSnake;
public class DatabasePopulateService {
    public static void main(String[] args){
        new SqlUtil().executeUpdateSql(Database.getRequestResult("./sql/populate_db.sql"));
    }
}
