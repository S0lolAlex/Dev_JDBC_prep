package com.greenSnake;

import javax.sql.rowset.*;
import java.sql.*;
import java.util.*;

public class SqlUtil {
    public void executeUpdateSql(String sql) {
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CachedRowSet executeQuerySql(String sql) throws SQLException{
        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rest = statement.executeQuery();)
        {
            crs.populate(rest);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return crs;
    }


}
