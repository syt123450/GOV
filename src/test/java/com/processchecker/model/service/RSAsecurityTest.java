package com.processchecker.model.service;

import org.junit.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/10/21.
 */
public class RSAsecurityTest {

    @Test
    public void test() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        //秘钥长度
        keyPairGenerator.initialize(512);
        //初始化秘钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //公钥
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        //私钥
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        System.out.println(rsaPublicKey.getPublicExponent());
        System.out.println(rsaPrivateKey.getPrivateExponent());

        System.out.println(rsaPrivateKey);
        System.out.println(rsaPublicKey);
    }
}