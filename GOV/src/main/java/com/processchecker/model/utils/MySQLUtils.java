package com.processchecker.model.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by ss on 2017/10/21.
 */
public class MySQLUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/ResourceManagement?serverTimezone=GMT&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "syt19930224";

    public String getDepartmentUrl(String departmentName) {

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
}
