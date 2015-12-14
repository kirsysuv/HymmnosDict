package com.binasphere.hymmnosdict.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Kerstin on 2015/12/10.
 */
public class Md5Utils {
    public static String getMd5(String string) {

//            String seed="_device=android" +
//                    "&_hwid=ccbb856c97ccb8d2" +
//                    "&_ulv=10000" +
//                    "&access_key=29c682745336a81e443231a50b334488" +
//                    "&appkey=c1b107428d337928" +
//                    "&build=408016" +
//                    "&platform=android" +
//                    "&platformType=0" +
//                    "&ts=1449748663000";
//            LogUtil.d("Hymmnos",seed);
//            String key="c1b107428d337928";
//          String fina=seed+key;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toHexString(aByte >> 4 & 0xf))
                        .append(Integer.toHexString(aByte & 0xf));
            }

            LogUtil.d("Hymmnos", sb.toString());
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
