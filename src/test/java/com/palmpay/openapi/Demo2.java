package com.palmpay.openapi;

import org.junit.Test;

import java.util.Map;

public class Demo2 {

    @Test
    public void test() throws Exception {
        // 1.生成密钥对
        Map<String, String> keyPair = RsaUtil.generateKeyPair();
        String privateKey = keyPair.get(Constants.PRIVATE_KEY);
        String publicKey = keyPair.get(Constants.PUBLIC_KEY);

        System.out.println("privateKey:");
        System.out.println(privateKey);
        System.out.println("publicKey:");
        System.out.println(publicKey);

    }

}