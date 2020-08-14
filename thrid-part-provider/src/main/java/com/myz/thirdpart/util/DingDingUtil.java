package com.myz.thirdpart.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;

/**
 * @author yzMa
 * @desc
 * @date 2020/8/14 14:29
 * @email 2641007740@qq.com
 */
public class DingDingUtil {

    public static String getSign(long timestamp) throws Exception{
        String secret = "SECb90eb323a8fed31fc7828a3750faedac3cf66bc0889a444f93b31d8b943840b9";

        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        return URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
    }
}
