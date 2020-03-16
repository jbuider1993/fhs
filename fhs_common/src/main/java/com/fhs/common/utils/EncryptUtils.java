/*
 * 陕西小伙伴网络科技有限公司
 * 																	《修改记录》
 * 修改人-----------------------------------------------------------修改时间-----------------------------------------------------------修改原因
 * */
package com.fhs.common.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;

/**
 * EncryptUtils.java
 *
 * @author By:の朱俊 2015年08月07日18:17:29
 * */
@SuppressWarnings("restriction")
public class EncryptUtils
{

    private static final String STRING_MD5 = "MD5";

    private static final String STRING_ENCODING = "UTF-8";

    private static final String STRING_DES = "DES";

    private EncryptUtils()
    {
    }

    public static String encryptWithMD5(String src)
    {
        char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try
        {
            byte byteTemp[] = src.getBytes(STRING_ENCODING);
            MessageDigest messageDisgest = MessageDigest.getInstance(STRING_MD5);
            messageDisgest.update(byteTemp);
            byte md5Byte[] = messageDisgest.digest();
            int j = md5Byte.length;
            char ch[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++)
            {
                byte strTemp2 = md5Byte[i];
                ch[k++] = hexDigit[strTemp2 >>> 4 & 0xf];
                ch[k++] = hexDigit[strTemp2 & 0xf];
            }
            return new String(ch);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean equalsWithMD5(String src, String md5Value)
    {
        return md5Value.equals(encryptWithMD5(src));
    }

    /**
     * 加密用户密码
     * */
    public static String encryptByUser(String src)
    {
        return encryptWithMD5(encryptWithMD5(encryptWithMD5(src)));
    }

    /**
     * 校验用户密码
     * */
    public static boolean equalsWithMD5ByUser(String src, String md5Value)
    {
        return md5Value.equals(encryptByUser(src));
    }

    /**
     * DES加密
     *
     * @param src 明文 需要加密的内容
     * @param keys 加密时需要用到的key
     * @throws Exception 密文出现被修改或者key出现长度问题抛出此异常
     * @return java.lang.String 返回加密后的密文
     * */
    public static String encryptByDES(String src, String keys)
    {
        SecretKeySpec key = new SecretKeySpec(keys.getBytes(), STRING_DES);
        Cipher cipher;
        try
        {
            cipher = Cipher.getInstance(STRING_DES);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte encryptedData[] = cipher.doFinal(src.getBytes(STRING_ENCODING));
            String resultCode = new String((Base64.getEncoder()).encode(encryptedData),STRING_ENCODING);
            resultCode =
                resultCode.replaceAll("\\+", "ELTjh")
                    .replaceAll("/", "ELTxg")
                    .replaceAll("\\?", "ELTwh")
                    .replaceAll("%", "ELTbf")
                    .replaceAll("&", "ELTad")
                    .replaceAll("=", "ELTdy")
                    .replaceAll("#", "ELTjh");
            return resultCode;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DES解密
     *
     * @param src 解密的密文
     * @param keys 解密时需要用到的key
     * @throws Exception 密文出现被修改或者key出现长度问题抛出此异常
     * @return java.lang.String 返回解密后的明文
     * */
    public static String decryptByDES(String src, String keys)
    {
        try
        {
            if (CheckUtils.isNullOrEmpty(src))
            {
                return null;
            }
            src =
                src.replaceAll("ELTjh", "\\+")
                    .replaceAll("ELTxg", "/")
                    .replaceAll("ELTwh", "\\?")
                    .replaceAll("ELTbf", "%")
                    .replaceAll("ELTad", "&")
                    .replaceAll("ELTdy", "=")
                    .replaceAll("ELTjh", "#");
            byte byteMi[] = Base64.getDecoder().decode(src);
            SecretKeySpec key = new SecretKeySpec(keys.getBytes(), STRING_DES);
            Cipher cipher = Cipher.getInstance(STRING_DES);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte decryptedData[] = cipher.doFinal(byteMi);
            return new String(decryptedData, STRING_ENCODING);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证签名
     * */
    public static boolean signature(String src, String md5Value)
    {
        return equalsWithMD5(src, md5Value);
    }

    public static String getDESKey()
    {
        return "AFTP1QAM";
    }

    /**
     * 获取map里的idName数据，为外键或主键，加密 加密后的id会以传进来的idName+"E"作为主键，put进传进来的map中
     *
     * @param paramMap
     * @param idName(要加密的参数名)
     * @return 加密后的id
     */
    public static String getEncryptionId(Map<String, Object> paramMap, String idName)
    {
        String EncryptionIdE = null;
        if (!CheckUtils.isNullOrEmpty(paramMap.get(idName)))
        {
            String EncryptionId = paramMap.get(idName).toString();
            EncryptionIdE = encryptByDES(EncryptionId, EncryptUtils.getDESKey());
            paramMap.put(idName + "E", EncryptionIdE);
        }
        return EncryptionIdE;
    }

    /**
     *
     * 获取map里的idEName数据，为外键或主键，解密 解密后的id会以传进来的idEName去掉结尾的"E"为key，put进传进来的map中
     *
     * @param paramMap
     * @param idEName(要解密的参数名)
     * @return 解密后的id
     */
    public static Integer getDecodeId(Map<String, Object> paramMap, String idEName)
    {
        Integer decodeId = null;
        if (!CheckUtils.isNullOrEmpty(paramMap.get(idEName)))
        {
            String decodeIdE = paramMap.get(idEName).toString();
            decodeId = getDecodeIdFromIdE(decodeIdE);
            paramMap.put(idEName.substring(0, idEName.length() - 1), decodeId);
        }
        return decodeId;
    }

    /**
     *
     * 将传入的idE转换为id放入map
     *
     * @param paramMap
     * @param idENames 需要转的idEs
     */
    public static void getDecodeId(Map<String, Object> paramMap, String... idENames){
        for(String idE : idENames){
            if(idE.endsWith("E")){
                getDecodeId(paramMap, idE);
            }
        }
    }

    /**
     *
     * 根据加密后的idE,解密成id
     *
     * @param idE
     * @return
     */
    public static Integer getDecodeIdFromIdE(String idE)
    {
        String decoding = EncryptUtils.decryptByDES(idE, EncryptUtils.getDESKey());
        if (decoding == null)
        {
            return null;
        }
        return ConverterUtils.toInteger(decoding);
    }
    
    /**
    *
    * 根据加密后的idE,解密成id
    *
    * @param idE
    * @return
    */
   public static String getDecodeIdFromIdES(String idE)
   {
       String decoding = EncryptUtils.decryptByDES(idE, EncryptUtils.getDESKey());
       if (decoding == null)
       {
           return null;
       }
       return decoding;
   }

    /**
     *
     * 根据id,加密成idE
     *
     * @param id
     * @return
     */
    public static String getEncodeIdEFromId(Integer id)
    {
        if (id == null)
        {
            return null;
        }
        String encoding = EncryptUtils.encryptByDES(String.valueOf(id), EncryptUtils.getDESKey());
        return encoding;
    }
    
    /**
    *
    * 根据id,加密成idE
    *
    * @param id
    * @return
    */
   public static String getEncodeIdEFromId(String id)
   {
       if (id == null)
       {
           return null;
       }
       String encoding = EncryptUtils.encryptByDES(String.valueOf(id), EncryptUtils.getDESKey());
       return encoding;
   }

    /**
     *
     * 将加密后的idEs的数组转换成用,拼接的id
     *
     * @param idEs
     * @return
     */
    public static String getDecodeIdsFromIdEs(String[] idEs)
    {
        if (idEs == null || idEs.length == 0)
        {
            return null;
        }
        String decoding = null;
        StringBuilder decodeIdsSb = new StringBuilder();
        for (int i = 0; i < idEs.length; i++)
        {
            decoding = EncryptUtils.decryptByDES(idEs[i], EncryptUtils.getDESKey());
            if (decoding != null)
            {
                decodeIdsSb.append(",").append(decoding);
            }
        }
        String decodeIds = decodeIdsSb.toString();
        return decodeIds.startsWith(",") ? decodeIds.substring(1) : decodeIds;
    }

    /**
     *
     * 对list<Map> 里的idE解密到id里
     *
     * @param list
     * @param idEName
     */
    public static void decodeIdEtoIdList(List<Map<String, Object>> list, String idEName)
    {

        if (!idEName.endsWith("E") || CheckUtils.checkCollectionIsNullOrEmpty(list))
        {
            return;
        }
        String idName = idEName.substring(0, idEName.length() - 1);
        for (Map<String, Object> map : list)
        {
            map.put(idName, getDecodeIdFromIdE(ConverterUtils.toString(map.get(idEName))));
        }
    }

    /**
     *
     * 对list<Map> 里的id加密到idE里
     *
     * @param list
     * @param idName
     */
    public static void encodeIdtoIdEList(List<Map<String, Object>> list, String idName)
    {
        if (CheckUtils.checkCollectionIsNullOrEmpty(list))
        {
            return;
        }
        String idEName = idName + 'E';
        for (Map<String, Object> map : list)
        {
            if (ConverterUtils.toInt(map.get(idName)) == 0)
            {
                continue;
            }
            map.put(idEName, getEncodeIdEFromId(ConverterUtils.toInteger(map.get(idName))));
        }
    }
}
