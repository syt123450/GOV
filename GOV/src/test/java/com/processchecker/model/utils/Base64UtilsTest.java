package com.processchecker.model.utils;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/10/31.
 */
public class Base64UtilsTest {

    @Test
    @Ignore
    public void testEncryption() {
        try {
            System.out.println(DESUtils.encryption("syt19930224", "12345678"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Ignore
    public void testDecryption() {
        String cipherText = "R5YshJhJ0EQ3VVuLYclOhw==";

        try {
            System.out.println(DESUtils.decryption(cipherText, "12345678"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}