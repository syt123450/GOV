package com.processchecker.model.service;

import com.processchecker.model.utils.DESUtils;
import com.processchecker.model.utils.MySQLUtils;
import org.apache.http.client.fluent.Request;

/**
 * Created by ss on 2017/10/19.
 */

public class ProcessDataProxy {

    private String processUrl = "%s/api/processes";
    private String taskUrl = "%s/api/%s/tasks";

    public String getProcessesInfo(String departmentName, String userName) {

        String requestUrl = String.format(processUrl, ResourceHandler.getResourceLocation(departmentName));
        String keyValue = MySQLUtils.getKey(departmentName, userName);

        return getAndEncryptData(requestUrl, keyValue);
    }

    public String getTasksInfo(String departmentName, String processName, String userName) {

        String requestUrl = String.format(taskUrl, ResourceHandler.getResourceLocation(departmentName), processName).replaceAll(" ", "%20");
        String keyValue = MySQLUtils.getKey(departmentName, userName);

        return getAndEncryptData(requestUrl, keyValue);
    }

    private String getAndEncryptData(String requestUrl, String keyValue) {

        String responseContent = "";

        try {
            responseContent = Request.Get(requestUrl).execute().returnContent().asString();
            if (keyValue != null) {
                responseContent = DESUtils.encryption(responseContent, keyValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseContent;
    }
}
