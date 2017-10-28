package com.processchecker.model.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.processchecker.model.entity.AuthenticationBean;
import com.processchecker.model.entity.DispatchMessageBean;
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
    private String authenticationUrl = "%s/api/check";

    public String authenticate(LoginRequestBean requestBean) {

        String departmentUrl = ResourceHandler.getResourceLocation(requestBean.getDepartment());
        if (departmentUrl == null) {
            AuthenticationBean authenticationBean = new AuthenticationBean();
            authenticationBean.setResult(false);

            return gson.toJson(authenticationBean);
        } else {
            String requestUrl = String.format(authenticationUrl, departmentUrl);
            DispatchMessageBean dispatchMessageBean = new DispatchMessageBean();
            dispatchMessageBean.setName(requestBean.getName());
            dispatchMessageBean.setPassword(requestBean.getPassword());
            String message = gson.toJson(dispatchMessageBean);
            String responseContent = null;
            try {
                HttpEntity httpEntity = new StringEntity(message);
                responseContent = Request.Post(requestUrl).body(httpEntity).execute().returnContent().asString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return responseContent;
        }
    }
}
