package com.processchecker.model.entity;

import lombok.Data;

/**
 * Created by ss on 2017/10/21.
 */

@Data
public class LoginRequestBean {

    private String name;
    private String password;
    private String department;
}
