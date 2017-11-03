package com.processchecker.model.utils;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by ss on 2017/10/24.
 */
public class MySQLUtilsTest {

    @Test
    @Ignore
    public void testGetDepartmentUrl() {
        System.out.println(MySQLUtils.getDepartmentUrl("mockDepartment"));
    }

    @Test
    @Ignore
    public void testGetKey() {
        System.out.println(MySQLUtils.getKey("department1", "syt123450"));
    }

    @Test
    @Ignore
    public void testCreateKey() {
        MySQLUtils.createKey("department1", "syt123450", "87654321");
    }

    @Test
    @Ignore
    public void testCloseEncryption() {
        MySQLUtils.closeEncryption("department1", "syt123450");
    }

    @Test
    @Ignore
    public void testGetDepartments() {
        System.out.println(MySQLUtils.getDepartments());
    }

    @Test
    @Ignore
    public void testCreateInstance() {
        MySQLUtils.createInstance("hello", "world");
    }
}