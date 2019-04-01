package com.fhs.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


/**
 *
 * 实现sha1签名算法
 *
 * jackwong
 * 2017年9月28日 下午2:53:41
 *
 * @version 1.0.0
 *
 */
public class SHA1Utils
{
    /**
     * SHA1 安全加密算法
     * c) 将排序后的所有参数字符串拼接成一个字符串进行 SHA1 编码
     * d) SHA1 编码后的 40 位字符串作为 sign
     * @param maps 参数key-value map集合
     * @return
     * @throws DigestException
     * @throws UnsupportedEncodingException
     */
    public static String SHA1(Map<String,Object> maps) throws DigestException, UnsupportedEncodingException {

        List<String> values = getValues(maps);
        StringBuilder sb = new StringBuilder();
        for (String s : values) {
           sb.append(s);
        }
        try {
            //指定sha1算法
            MessageDigest md = MessageDigest.getInstance("sha1");
            System.out.println(DateUtils.getCurrentDateStr(DateUtils.DATETIME_PATTERN)+"  "+sb);
            md.update(sb.toString().getBytes("UTF-8"));
            //获取字节数组
            byte messageDigest[] = md.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString().toUpperCase();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new DigestException("签名错误！");
        }
    }

    public static void main(String [] args) throws DigestException, UnsupportedEncodingException {

    }







    /**
     * 获取参数名称 key
     * @param maps 参数key-value map集合
     * @return
     */
    private static List<String> getValues(Map<String,Object> maps){
        List<String> values = new ArrayList<>();
        for(Map.Entry<String,Object> entry : maps.entrySet()){
            values.add(StringUtil.toString(entry.getValue()));
        }
        Collections.sort(values);
        return values;
    }








    /**
     * 对MAP进行排序
     * @param map
     * @return
     */
    public static String getMap(Map<String, String> map) {

        String result = "";
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });

            // 构造签名键值对的格式
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (item.getKey() != null || item.getKey() != "") {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (!(val == "" || val == null)) {
                        sb.append(key + "=" + val + "&");
                    }
                }
            }
            if(sb.length()>0){
                sb = sb.deleteCharAt(sb.length()-1);
            }
            result=sb.toString();
        } catch (Exception e) {
            return null;
        }
        return result;
    }




    /**
     * SHA1 安全加密算法
     * c) 将排序后的所有参数字符串拼接成一个字符串进行 SHA1 编码
     * d) SHA1 编码后的 40 位字符串作为 sign
     * @param maps 参数key-value map集合
     * @return
     * @throws DigestException
     * @throws UnsupportedEncodingException
     */
    public static String SHA1(String sb) throws DigestException, UnsupportedEncodingException {
        try {
            //指定sha1算法
            MessageDigest md = MessageDigest.getInstance("sha1");
            System.out.println(DateUtils.getCurrentDateStr(DateUtils.DATETIME_PATTERN)+"  "+sb);
            md.update(sb.toString().getBytes("UTF-8"));
            //获取字节数组
            byte messageDigest[] = md.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new DigestException("签名错误！");
        }
    }





}
