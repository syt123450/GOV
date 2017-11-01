package com.processchecker.model.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ss on 2017/10/21.
 */
public class MySQLUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/ResourceManagement?serverTimezone=GMT&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "syt19930224";

    public static String getDepartmentUrl(String departmentName) {

        String sql = "select address from resource where name = '%s'";

        String formatSql = String.format(sql, departmentName);

        String departmentUrl = null;

        try {

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(formatSql);
            if (rs.next()) {
                departmentUrl = rs.getString(1);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return departmentUrl;
    }

    public static String getKey(String departmentName, String userName) {

        String sql = "select keyValue from encryption where department = '%s' and userName = '%s' and enable = true";

        String formatSql = String.format(sql, departmentName, userName);

        String key = null;

        try {

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(formatSql);
            if (rs.next()) {
                key = rs.getString(1);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return key;
    }

    public static void createKey(String departmentName, String userName, String keyValue) {

        String sql = "insert into encryption values ('%s', '%s', '%s', true) ON DUPLICATE KEY UPDATE keyValue = '%s', enable = true";

        String formatSql = String.format(sql, departmentName, userName, keyValue, keyValue);

        try {

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(formatSql);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeEncryption(String departmentName, String userName) {

        String sql = "update encryption set enable = false where department = '%s' and userName = '%s'";

        String formatSql = String.format(sql, departmentName, userName);

        try {

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(formatSql);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getDepartments() {

        String sql = "select name from resource";

        List<String> departments = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                departments.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return departments;
    }
}