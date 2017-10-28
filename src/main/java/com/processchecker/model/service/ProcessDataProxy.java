package com.processchecker.model.service;

import org.apache.http.client.fluent.Request;

/**
 * Created by ss on 2017/10/19.
 */

public class ProcessDataProxy {

    private String processUrl = "%s/api/processes";
    private String taskUrl = "%s/api/tasks/%s";

    private String department = "";

    public String getProcessesInfo() {

        String requestUrl = String.format(processUrl, ResourceHandler.getResourceLocation(department));

        String responseContent = "";

        try {
            responseContent = Request.Get(requestUrl).execute().returnContent().asString();
            System.out.println(responseContent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseContent;
    }

    public String getTasksInfo(String processName) {

        String requestUrl = String.format(taskUrl, ResourceHandler.getResourceLocation(department), processName);

        String responseContent = "";

        try {
            responseContent = Request.Get(requestUrl).execute().returnContent().asString();
            System.out.println(responseContent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseContent;
    }
}
