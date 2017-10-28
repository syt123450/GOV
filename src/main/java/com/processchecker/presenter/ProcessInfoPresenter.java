package com.processchecker.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.processchecker.model.service.ProcessDataProxy;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ss on 2017/10/20.
 */

@EnableAutoConfiguration
@RestController
@RequestMapping("/api")
public class ProcessInfoPresenter {

    private Gson gson = new GsonBuilder().create();

    private String processName = "Purchase Request";

    @RequestMapping("/processes")
    public String getProcessData() {

        ProcessDataProxy processDataProxy = new ProcessDataProxy();

        return processDataProxy.getProcessesInfo();
    }

    @RequestMapping("/tasks")
    public String getTasks() {

        ProcessDataProxy processDataProxy = new ProcessDataProxy();

        return processDataProxy.getTasksInfo(processName);
    }
}