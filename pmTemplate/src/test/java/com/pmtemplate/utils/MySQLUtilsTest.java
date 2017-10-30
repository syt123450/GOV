package com.pmtemplate.utils;

import com.pmtemplate.model.utils.MySQLUtils;
import org.junit.Test;

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