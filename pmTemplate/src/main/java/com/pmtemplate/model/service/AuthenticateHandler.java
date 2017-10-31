package com.pmtemplate.model.service;

import com.pmtemplate.model.entity.AuthenticationBean;
import com.pmtemplate.model.entity.LoginRequestBean;
import com.pmtemplate.model.utils.MySQLUtils;

/**
 * Created by ss on 2017/10/24.
 */
public class AuthenticateHandler {

    public AuthenticationBean authenticate(LoginRequestBean loginRequestBean) {

        MySQLUtils mySQLUtils = new MySQLUtils();
        AuthenticationBean authenticationBean = new AuthenticationBean();
        authenticationBean.setResult(mySQLUtils.authenticateUser(loginRequestBean.getName(), EncryptHandler.getSHA256StrJava(loginRequestBean.getPassword())));

        System.out.println(authenticationBean);

        return authenticationBean;
    }
}

