package com.fhs.common.utils;

import com.fhs.common.constant.Constant;

import java.security.MessageDigest;

/**
 * 加密解密工具类
 * @author  jackwong
 * @version  [版本号, 2015年3月3日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ENCodeUtils
{
  //十六进制下数字到字符的映射数组  
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",  
        "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};  
    
    
    /** 
     * 对字符串进行MD5加密
     * @param originString 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String encodeByMD5(String originString){  
        if (originString != null){  
            try{  
                //创建具有指定算法名称的信息摘要  
                MessageDigest md = MessageDigest.getInstance("MD5");  
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算  
                byte[] results = md.digest(originString.getBytes());  
                //将得到的字节数组变成字符串返回  
                String resultString = byteArrayToHexString(results);  
                return resultString.toUpperCase();  
            } catch(Exception ex){  
                ex.printStackTrace();  
            }  
        }  
        return Constant.EMPTY;
    }
    
    
    /**  
     * 转换字节数组为十六进制字符串 
     * @param   b  字节数组
     * @return    十六进制字符串 
     */  
    private static String byteArrayToHexString(byte[] b){  
        StringBuffer resultSb = new StringBuffer();  
        for (int i = 0; i < b.length; i++){  
            resultSb.append(byteToHexString(b[i]));  
        }  
        return resultSb.toString();  
    } 
    
    /** 将一个字节转化成十六进制形式的字符串     */  
    private static String byteToHexString(byte b){  
        int n = b;  
        if (n < 0)  
            n = 256 + n;  
        int d1 = n / 16;  
        int d2 = n % 16;  
        return hexDigits[d1] + hexDigits[d2];  
    }  
    
    /** 
     * 根据秘钥和密匙去加密出一段字符串
     * @param secretKey 秘钥  一般是一个当前日期的随机字符串
     * @param key 密匙  加密前的字符串
     * @return 加密后的字符串
     */
    public static String getAdEncode(String secretKey,String key)
    {
        return ENCodeUtils.encodeByMD5(secretKey+key + "sxpartnerApplication");
    }
    
    public static void main(String[] args)
    {
       
    }
}
