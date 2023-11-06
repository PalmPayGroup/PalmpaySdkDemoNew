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
        Map<String, String> keyPair = RsaUtil.generateKeyPair();
        String privateKey = keyPair.get(Constants.PRIVATE_KEY);
        String publicKey = keyPair.get(Constants.PUBLIC_KEY);

        System.out.println("privateKey:");
        System.out.println(privateKey);
        System.out.println("publicKey:");
        System.out.println(publicKey);
     // 2.加签
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

    /**
     *  callback interface verify sign test
     * @throws Exception
     */
    @Test
    public void testCallbackVerify() throws Exception {
        String paramBody = "{\"orderId\":\"P23101802543812274\",\"orderNo\":\"2424231018025438544222\",\"appId\":\"L10378191362\",\"transType\":\"24\",\"orderType\":\"01\",\"amount\":999,\"couponAmount\":0,\"status\":1,\"completeTime\":1697597698516,\"orderStatus\":2,\"sign\":\"Dj9ycTD91W9ti4n%2FK6GbtVu2qNDE83tB6C7ToKrVe9tSM2aIhMrk%2Fqy9CgQ9fwXJhH6QQ1kg8kx2AV7O4tOIpSXs%2BnZ7aHXjricn8pUx06yQ%2BIqBXWSY9Hrhb6qkPXAzcskQ1MI%2B7SOieBVJTMf4vxIoxLOhMSYHeQB5jyfgZBA%3D\"}";
        //PalmPay public key in test env (not  the keys generate by merchant)
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVT+pLc1nkz9z803SOmF48bMFn0GYF4ng6nxj0ojUeu4KeNKkkw/nfureTtL77j9RpMjquJzzKdOZfHRvQyuAbaLoaSD1uU47npNiAL05bLYZEoZWvFOar9gNbIesea8MX0DeYncA2Tkr3kUo8K6XBrZ+TcV2Q8NEvm1T536LOGwIDAQAB";
        boolean res = PalmPayUtil.verifySignForCallback(paramBody,publicKey,SignType.RSA);
        Assert.assertTrue(res);
    }
}
