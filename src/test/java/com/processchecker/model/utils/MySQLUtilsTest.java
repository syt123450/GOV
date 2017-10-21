package com.processchecker.model.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/10/21.
 */
public class MySQLUtilsTest {

    @Test
    public void testGetTasksProcessor() {
        MySQLUtils mySQLUtils = new MySQLUtils();
        System.out.println(mySQLUtils.getTasksProcessor("Submit Request"));
    }
}