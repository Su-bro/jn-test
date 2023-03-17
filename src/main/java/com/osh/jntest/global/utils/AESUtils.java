package com.osh.jntest.global.utils;

import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class AESUtils {

    private static final String ALGORITHM_AES = "AES";
    private static final String SECRET_KEY = "DiuEjnfhduAHq19d";
    private static final String SECRET_KEY_WITH_CLIENT = "hdn19RkQd92jF120";

    public static String encrypt(String plainText) throws RuntimeException {
        return enc(plainText, SECRET_KEY);
    }

    public static String encrypt(String plainText, String key) throws RuntimeException {
        return enc(plainText, key);
    }

    public static String decrypt(String encryptedText) throws RuntimeException {
        return dec(encryptedText, SECRET_KEY);
    }

    public static String decrypt(String encryptedText, String key) throws RuntimeException {
        return dec(encryptedText, key);
    }

    public static String encryptWithClient(String plainText) throws RuntimeException {
        return enc(plainText, SECRET_KEY_WITH_CLIENT);
    }

    public static String decryptWithClient(String encryptedText) throws RuntimeException {
        return dec(encryptedText, SECRET_KEY_WITH_CLIENT);
    }

    private static String enc(String plainText, String secretKey) throws RuntimeException {
        String iv = secretKey.substring(0, 16);
        byte[] keyData = secretKey.getBytes();

        SecretKey secureKey = new SecretKeySpec(keyData, ALGORITHM_AES);

        String encStr = null;
        try {
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(iv.getBytes()));

            byte[] encrypted = c.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            encStr = new String(Base64.encodeBase64(encrypted));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return encStr;
    }

    private static String dec(String encryptedText, String secretKey) throws RuntimeException {
        String iv = secretKey.substring(0, 16);
        byte[] keyData = secretKey.getBytes();

        SecretKey secureKey = new SecretKeySpec(keyData, ALGORITHM_AES);

        byte[] bytes = new byte[0];
        try {
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));

            byte[] byteStr = Base64.decodeBase64(encryptedText.getBytes());
            bytes = c.doFinal(byteStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new String(bytes, StandardCharsets.UTF_8);
    }

}
