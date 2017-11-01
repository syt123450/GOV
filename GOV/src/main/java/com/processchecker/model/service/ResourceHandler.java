package com.processchecker.model.service;

import com.processchecker.model.utils.MySQLUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ss on 2017/10/24.
 */

//use a hashMap to implement cache for resource
public class ResourceHandler {

    private static Map<String, String> cacheMap = new HashMap<>();

    public static String getResourceLocation(String departmentName) {

        if (cacheMap.containsKey(departmentName)) {
            return cacheMap.get(departmentName);
        } else {
            String departmentUrl = MySQLUtils.getDepartmentUrl(departmentName);
            if (departmentUrl != null) {
                cacheMap.put(departmentName, departmentUrl);
            }
            return departmentUrl;
        }
    }
}
