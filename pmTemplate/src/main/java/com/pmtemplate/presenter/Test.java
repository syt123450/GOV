package com.pmtemplate.presenter;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ss on 2017/11/28.
 */

@EnableAutoConfiguration
@RestController
public class Test {

    @RequestMapping("/")
    public String hello() {

        return "Hello World!";
    }
}
