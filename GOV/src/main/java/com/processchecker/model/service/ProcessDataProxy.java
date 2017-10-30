package com.processchecker.model.service;

import org.apache.http.client.fluent.Request;

/**
 * Created by ss on 2017/10/19.
 */

public class ProcessDataProxy {

    private String processUrl = "%s/api/processes";
    private String taskUrl = "%s/api/%s/tasks";

    public String getProcessesInfo(String departmentName) {

        String requestUrl = String.format(processUrl, ResourceHandler.getResourceLocation(departmentName));

        String responseContent = "";

        try {
            responseContent = Request.Get(requestUrl).execute().returnContent().asString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseContent;
    }

    public String getTasksInfo(String departmentName, String processName) {

        String requestUrl = String.format(taskUrl, ResourceHandler.getResourceLocation(departmentName), processName).replaceAll(" ", "%20");

        String responseContent = "";

        try {
            responseContent = Request.Get(requestUrl).execute().returnContent().asString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseContent;
    }
}
