package com.fhs.common.utils;


import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * java Base64编码实例
 *
 * @author wangpengfei
 * @version [版本号, 2016年9月11日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
public class Base64Util {
    /**
     * 将 s 进行 BASE64 编码
     *
     * @param s
     * @return
     */
    public static String getBASE64(String s) {
        if (s == null) {
            return null;
        }
        return byte2Base64(s.getBytes());
    }

    /**
     * byte数组转base64字符串
     * @param bytes
     * @return
     */
    public static String byte2Base64(byte[] bytes){
        try {
            return new String(Base64.getEncoder().encode(bytes), "UTF8");
        } catch (UnsupportedEncodingException e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 将 BASE64 编码的字符串 s 进行解码
     *
     * @param s
     * @return
     */
    public static String getFromBASE64(String s) {
        if (s == null) {
            return null;
        }
        try {
            byte[] b = Base64.getDecoder().decode(s);
            return new String(b);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据base64 获取byte
     *
     * @param base64 base64数据
     * @return byt数组
     */
    public static byte[] getByteFromBase64(String base64) {
        if (base64.startsWith("data:image/png;base64,")) {
            base64 = base64.replace("data:image/png;base64,", "");
        }
        if (base64.startsWith("data:image/jpeg;base64,")) {
            base64 = base64.replace("data:image/jpeg;base64,", "");
        }
        try {
            // Base64解码
            byte[] b = Base64.getDecoder().decode(base64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            return b;
        } catch (Exception e) {
            return new byte[]{};
        }
    }


}
