package com.weixin.smallpro.tools;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

/**
 * sha1加密工具工具类
 */
public class Sha1Unit {

    /**
     * 微信公众号taken
     */
    private static final String token = "ragdollhouse8399";

    /**
     * 接入校验
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {

        String[] arr = new String[]{token, timestamp, nonce};

        // 排序
        Arrays.sort(arr);
        // 生成字符串
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }

        // sha1加密
        String temp = getSHA1String(content.toString());
        System.out.println("这个sha1密钥我自己看：" + temp);
        return temp.equals(signature); // 与微信传递过来的签名进行比较
    }

    private static String getSHA1String(String data) {
        // 使用commons codec生成sha1字符串【maven添加依赖commons_codec】
        return DigestUtils.sha1Hex(data);
    }
}
