package com.processchecker.model.service;

/**
 * Created by ss on 2017/10/21.
 */
public class RSAtest {
    public static void main(String[] args) {
        RSAsecurity rsAsecurity = new RSAsecurity();
        System.out.println("私钥加密公钥解密例：");
        rsAsecurity.priENpubDE();
        System.out.println("公钥加密私钥解密例：");
        rsAsecurity.pubENpriDE();

    }
}
