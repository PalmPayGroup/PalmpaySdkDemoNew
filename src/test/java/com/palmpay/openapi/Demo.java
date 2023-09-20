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

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("keyword", null);
        hashMap.put("templateCode", "1002");
        hashMap.put("serviceSign", null);
        hashMap.put("applicantDepartment", null);
        hashMap.put("businessScenario", null);
        hashMap.put("threshold", null);
        hashMap.put("dataType", null);
        hashMap.put("pageNum", 1);
        hashMap.put("pageSize", 10);
        hashMap.put("requestTime", 1662171389940L);
        hashMap.put("version", "V1.1");
        hashMap.put("nonceStr", "IBJGAeTa4ZJQv4Z2qufomVo9eI1YnJ9Y");

        String sign = PalmPayUtil.generateSign(hashMap, privateKey, SignType.RSA);
        System.out.println("sign： "+sign);
        // 3.验签
        boolean result = PalmPayUtil.verifySign(hashMap, publicKey, sign, SignType.RSA);

        Assert.assertTrue(result);
    }
}
