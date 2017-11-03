package com.processchecker.model.service;

import com.processchecker.model.entity.CreationBean;
import com.processchecker.model.utils.MySQLUtils;

/**
 * Created by ss on 2017/11/3.
 */
public class CreationHandler {

    private static final String address = "http://localhost:9000";

    public void create(CreationBean creationBean) {
        MySQLUtils.createInstance(creationBean.getDepartmentName(), address);
    }
}
