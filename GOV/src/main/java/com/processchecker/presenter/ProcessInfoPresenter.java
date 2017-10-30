package com.processchecker.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.processchecker.model.service.ProcessDataProxy;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/{department}/processes")
    public String getProcessData(@PathVariable(name = "department") String department) {

        ProcessDataProxy processDataProxy = new ProcessDataProxy();

        return processDataProxy.getProcessesInfo(department);
    }

    @RequestMapping("/{department}/{process}/tasks")
    public String getTasks(@PathVariable(name = "department") String departmentName,
                           @PathVariable(name = "process") String processName) {

        ProcessDataProxy processDataProxy = new ProcessDataProxy();

        return processDataProxy.getTasksInfo(departmentName, processName);
    }
}