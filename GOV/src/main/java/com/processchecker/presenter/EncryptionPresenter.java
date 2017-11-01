package com.processchecker.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.processchecker.model.entity.CipherOpRequestBean;
import com.processchecker.model.service.EncryptionConfigHandler;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ss on 2017/11/1.
 */

@EnableAutoConfiguration
@RestController
@RequestMapping("/api")
public class EncryptionPresenter {

    private Gson gson = new GsonBuilder().create();

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateCypherConfig(@RequestBody String body) {

        EncryptionConfigHandler encryptionConfigHandler = new EncryptionConfigHandler();
        CipherOpRequestBean cipherOpRequestBean = gson.fromJson(body, CipherOpRequestBean.class);
        encryptionConfigHandler.configEncryption(cipherOpRequestBean);
    }

    @RequestMapping("/disable/{department}/{userName}")
    public void disableEncryption(@PathVariable(name = "department") String departmentName,
                                    @PathVariable(name = "userName") String userName) {

        EncryptionConfigHandler encryptionConfigHandler = new EncryptionConfigHandler();
        encryptionConfigHandler.disableEncryption(departmentName, userName);
    }
}