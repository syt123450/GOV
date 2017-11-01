package com.processchecker.model.service;

import com.processchecker.model.entity.CipherOpRequestBean;
import com.processchecker.model.utils.MySQLUtils;

/**
 * Created by ss on 2017/11/1.
 */

public class EncryptionConfigHandler {

    public void disableEncryption(String departmentName, String userName) {
        MySQLUtils.closeEncryption(departmentName, userName);
    }

    public void configEncryption(CipherOpRequestBean cipherOpRequestBean) {
        MySQLUtils.createKey(cipherOpRequestBean.getDepartment(),
                cipherOpRequestBean.getUserName(),
                cipherOpRequestBean.getCipherText());
    }
}
