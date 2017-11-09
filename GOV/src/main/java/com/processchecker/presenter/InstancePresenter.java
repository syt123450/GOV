package com.processchecker.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.processchecker.model.entity.CreationBean;
import com.processchecker.model.service.CreationHandler;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ss on 2017/11/1.
 */

@EnableAutoConfiguration
@RestController
@RequestMapping("/api")
public class InstancePresenter {

    private Gson gson = new GsonBuilder().create();

    @RequestMapping("/create")
    public void createDepartment(@RequestBody String body) {
        CreationBean creationBean = gson.fromJson(body, CreationBean.class);
        CreationHandler creationHandler = new CreationHandler();
        creationHandler.create(creationBean);
    }
}
