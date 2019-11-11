package com.fhs.common.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;

/**
 * 
 * java Base64编码实例
 * 
 * @author wangpengfei
 * @version [版本号, 2016年9月11日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
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

        return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
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

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据base64 获取byte
     * 
     * @param base64
     *            base64数据
     * @return byt数组
     */
    public static byte[] getByteFromBase64(String base64) {
        if(base64.startsWith("data:image/png;base64,"))
        {
            base64 = base64.replace("data:image/png;base64,","");
        }
        if(base64.startsWith("data:image/jpeg;base64,"))
        {
            base64 = base64.replace("data:image/jpeg;base64,","");
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(base64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            return b;
        } catch (Exception e) {
            return new byte[] {};
        }
    }

    
}
