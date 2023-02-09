package com.greenSnake;

import sqlRequestUsers.*;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;
import java.util.*;

public class DatabaseQueryService {
    private String getSqlRequest(String path) {
        return Database.getRequestResult(path);
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> list = new ArrayList<>();
        String sql = getSqlRequest("./sql/find_max_projects_client.sql");
        try (CachedRowSet result = new SqlUtil().executeQuerySql(sql)) {
            while (result.next()) {
                MaxProjectCountClient client = new MaxProjectCountClient();
                client.setName(result.getString("name"));
                client.setProjectCount(result.getInt("project_count"));
                list.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<LongestProject> findLongestProject() {
        List<LongestProject> list = new ArrayList<>();
        String sql = getSqlRequest("./sql/find_longest_project.sql");
        try (CachedRowSet result = new SqlUtil().executeQuerySql(sql)) {
            while (result.next()) {
                LongestProject project = new LongestProject();
                project.setName(result.getString("name"));
                project.setMonthCount(result.getInt("month_count"));
                list.add(project);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        List<MaxSalaryWorker> list = new ArrayList<>();
        String sql = getSqlRequest("./sql/find_max_salary_worker.sql");
        try (CachedRowSet result = new SqlUtil().executeQuerySql(sql)) {
            while (result.next()) {
                MaxSalaryWorker worker = new MaxSalaryWorker();
                worker.setName(result.getString("name"));
                worker.setSalary(result.getInt("salary"));
                list.add(worker);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<YoungestEldestWorkers> findYoungestEldestWorkers() {
        List<YoungestEldestWorkers> list = new ArrayList<>();
        String sql = getSqlRequest("./sql/find_youngest_eldest_workers.sql");
        try (CachedRowSet result = new SqlUtil().executeQuerySql(sql)) {
            while (result.next()) {
                YoungestEldestWorkers worker = new YoungestEldestWorkers();
                worker.setType(result.getString("type"));
                worker.setName(result.getString("name"));
                worker.setBirthday(result.getString("birthday"));
                list.add(worker);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static void main(String[] args) {
        List<MaxSalaryWorker> list = new DatabaseQueryService().findMaxSalaryWorker();
        list
                .forEach(System.out::println);
    }

}
