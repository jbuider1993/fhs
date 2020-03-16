/**
 * 文 件 名：CalendarUtil.java
 * 创 建 人：王磊
 * 日       期：2013年8月7日
 * 修 改 人：王磊
 * 日       期：2013年8月7日
 * 描       述：工具类处理
 * 版 本 号：
 */
package com.fhs.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * <日期处理工具类>
 *
 * @author jackwong
 * @version [版本号, 2013年8月5日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
public class Md5Util
{


    public final static String MD5(String s)
    {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try
        {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++)
            {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public final static String md5(String s)
    {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try
        {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++)
            {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public final static String sxpartnerMD5(String s)
    {
        String oldMd5String = MD5(s);
        return MD5(oldMd5String + "sxpartner");
    }

    /**
     * 根据文件计算出文件的MD5
     *
     * @param file
     * @return
     */
    public static String getFileMD5(File file)
    {
        if (!file.isFile())
        {
            return null;
        }

        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try
        {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1)
            {
                digest.update(buffer, 0, len);
            }
            in.close();

        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());

        return bigInt.toString(16);
    }


    /**
     * md5 签名 最后转换为大写
     * @param map 需要签名的参数
     * @param extendsParam secret 拼接参数
     * @return
     */
    public static String getSign(Map<String, String> map, String extendsParam)
    {

        String result = "";
        try
        {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>()
            {
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2)
                {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });

            // 构造签名键值对的格式
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds)
            {
                if (item.getKey() != null || item.getKey() != "")
                {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (!(val == "" || val == null))
                    {
                        sb.append(key + "=" + val + "&");
                    }
                }
            }
            if (sb.length() > 0)
            {
                sb = sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(extendsParam);
            result = sb.toString();
            log.info("签名前字符串:" + result);
            // 进行MD5加密
            result = DigestUtils.md5Hex(result).toUpperCase();
            log.info("签名后字符串:" + result);
        }
        catch (Exception e)
        {
            return null;
        }
        return result;
    }

    /**
     * 对MAP进行排序，生成签名 再MD5加密 大写 带参数
     *
     * @param map
     * @return
     */
    public static String getSign(Map<String, String> map)
    {

        String result = "";
        try
        {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>()
            {
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2)
                {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });

            // 构造签名键值对的格式
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds)
            {
                if (item.getKey() != null || item.getKey() != "")
                {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (!(val == "" || val == null))
                    {
                        sb.append(key + "=" + val + "&");
                    }
                }
            }
            if (sb.length() > 0)
            {
                sb = sb.deleteCharAt(sb.length() - 1);
            }
            result = sb.toString();
            log.info("签名前字符串:" + result);
            // 进行MD5加密
            result = DigestUtils.md5Hex(result).toUpperCase();
            log.info("签名后字符串:" + result);
        }
        catch (Exception e)
        {
            return null;
        }
        return result;
    }



}