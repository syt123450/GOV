package com.pmtemplate.model.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ss on 2017/10/19.
 */
public class MySQLUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/bitnami_pm?serverTimezone=GMT&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "syt19930224";

    public List<String> getProcesses() {

        String sql = "select PRO_TITLE from process";

        List<String> processes = new ArrayList<>();

        try {

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                processes.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return processes;
    }

    public List<String> getTasks(String processName) {

        String sql = "select t.TAS_TITLE " +
                "from task AS t, process AS p " +
                "WHERE t.PRO_UID = p.PRO_UID " +
                "AND PRO_TITLE = '%s' " +
                "ORDER BY t.TAS_ID";

        String formatSql = String.format(sql, processName);

        List<String> tasks = new ArrayList<>();

        try {

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(formatSql);
            while (rs.next()) {
                tasks.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tasks;
    }

    public List<String> getTasksProcessor(String taskName) {

        String sql = "SELECT DISTINCT CONCAT(U.USR_FIRSTNAME, ' ', U.USR_LASTNAME) AS name " +
                "FROM users AS U, task AS T, group_user AS G, task_user AS TU " +
                "WHERE T.TAS_UID = TU.TAS_UID " +
                "AND (TU.USR_UID = U.USR_UID OR (TU.USR_UID = G.GRP_UID AND G.USR_UID = U.USR_UID)) " +
                "AND T.TAS_TITLE = '%s';";

        String formatSQL = String.format(sql, taskName);

        List<String> processes = new ArrayList<>();

        try {

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(formatSQL);
            while (rs.next()) {
                processes.add(rs.getString("name"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return processes;
    }

    public boolean authenticateUser(String userName, String userPassword) {

        String sql = "select USR_USERNAME FROM users WHERE USR_USERNAME = '%s'";
        String formatSQL = String.format(sql, userName);

        boolean result = false;

        try {

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(formatSQL);
            result = rs.next();
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
