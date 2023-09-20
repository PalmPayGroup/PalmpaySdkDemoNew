package com.palmpay.openapi;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: yuanpei.liao
 * @date: 2022/12/5
 */

public class Demo {

    @Test
    public void test() throws Exception {
        // 1.生成密钥对
//        Map<String, String> keyPair = RsaUtil.generateKeyPair();
//        String privateKey = keyPair.get(Constants.PRIVATE_KEY);
//        String publicKey = keyPair.get(Constants.PUBLIC_KEY);

//        System.out.println("privateKey:");
//        System.out.println(privateKey);
//        System.out.println("publicKey:");
//        System.out.println(publicKey);

     // 2.加签
        String privateKey = null;
        String publicKey = null;
        HashMap<String, Object> newHashMap = new HashMap<>();
        newHashMap.put("requestTime",1662171389940L);
        newHashMap.put("version","V1.1");
        newHashMap.put("nonceStr","IBJGAeTa4ZJQv4Z2qufomVo9eI1YnJ9Y");
        newHashMap.put("amount", 200);
        newHashMap.put("currency", "NGN");
        newHashMap.put("notifyUrl", "https://xx.cn/callback/payment");
        newHashMap.put("orderId", "testc9ffae997fc4");
        newHashMap.put("title", "pay");
        newHashMap.put("transType", "24");
        newHashMap.put("userId", "110");
        newHashMap.put("country", "NG");
        newHashMap.put("orderType", "1");


        String sign = PalmPayUtil.generateSign(newHashMap, privateKey, SignType.RSA);
        System.out.println("sign： "+sign);
        // 3.验签
        boolean result = PalmPayUtil.verifySign(newHashMap, publicKey, sign, SignType.RSA);

        Assert.assertTrue(result);
    }
}
