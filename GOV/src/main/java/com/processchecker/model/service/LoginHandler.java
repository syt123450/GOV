package com.processchecker.model.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.processchecker.model.entity.AuthenticationBean;
import com.processchecker.model.entity.DispatchMessageBean;
import com.processchecker.model.entity.IntermediateAuthBean;
import com.processchecker.model.entity.LoginRequestBean;
import com.processchecker.model.utils.MySQLUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Component;

/**
 * Created by ss on 2017/10/21.
 */

public class LoginHandler {

    private Gson gson = new GsonBuilder().create();
    private String authenticationUrl = "%s:6000/api/check";

    public AuthenticationBean authenticate(LoginRequestBean requestBean) {

        System.out.println(requestBean.getDepartment());

        String departmentUrl = ResourceHandler.getResourceLocation(requestBean.getDepartment());
        AuthenticationBean authenticationBean = new AuthenticationBean();
        if (departmentUrl == null) {

            System.out.println(departmentUrl);

            authenticationBean.setResult(false);

            return authenticationBean;
        } else {
            String requestUrl = String.format(authenticationUrl, departmentUrl);
            System.out.println(requestUrl);
            DispatchMessageBean dispatchMessageBean = new DispatchMessageBean();
            dispatchMessageBean.setName(requestBean.getName());
            dispatchMessageBean.setPassword(requestBean.getPassword());
            String message = gson.toJson(dispatchMessageBean);

            try {
                HttpEntity httpEntity = new StringEntity(message);
                String responseContent = Request.Post(requestUrl).body(httpEntity).execute().returnContent().asString();
                System.out.println(responseContent);
                IntermediateAuthBean intermediateAuthBean = gson.fromJson(responseContent, IntermediateAuthBean.class);
                if (intermediateAuthBean.isResult()) {
                    authenticationBean.setResult(true);
                    authenticationBean.setKeyValue(MySQLUtils.getKey(requestBean.getDepartment(), requestBean.getName()));
                    authenticationBean.setDepartmentResource(departmentUrl);
                } else {
                    authenticationBean.setResult(false);
                }
            } catch (Exception e) {
                authenticationBean.setResult(false);
                e.printStackTrace();
            }

            return authenticationBean;
        }
    }
}
