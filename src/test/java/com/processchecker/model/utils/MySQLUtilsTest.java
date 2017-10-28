package com.processchecker.model.utils;

import org.junit.Test;

/**
 * Created by ss on 2017/10/24.
 */
public class MySQLUtilsTest {

    @Test
    public void testGetDepartmentUrl() {
        MySQLUtils mySQLUtils = new MySQLUtils();
        System.out.println(mySQLUtils.getDepartmentUrl("mockDepartment"));
    }
}