package com.pmtemplate.utils;

import com.pmtemplate.model.utils.MySQLUtils;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by ss on 2017/10/21.
 */
public class MySQLUtilsTest {

    @Test
    @Ignore
    public void testGetTasksProcessor() {
        MySQLUtils mySQLUtils = new MySQLUtils();
        System.out.println(mySQLUtils.getTasksProcessor("Submit Request"));
    }

    @Test
    public void testAuthenticateUser() {
        MySQLUtils mySQLUtils = new MySQLUtils();
        System.out.println(mySQLUtils.authenticateUser("syt12345", "e72be384a9192d7409909b14b5a6e1257ed81cbd833e085c4d1eaf0c5ac49a63"));
    }
}