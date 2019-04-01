package com.fhs.common.utils;

import java.io.*;

/**
 * 序列化反序列化工具类
 */
public class SerializeUtil
{
    /**
     * 序列化
     *
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 序列化
     *
     * @param object
     * @return
     */
    public static boolean serializeToFile(Object data, String filePath) {
        FileOutputStream out = null;
        ObjectOutputStream oos = null;
        File file=new File(filePath);
        try {
            if(!file.exists()){
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            out = new FileOutputStream(file);
            // 序列化
            oos = new ObjectOutputStream(out);
            oos.writeObject(data);
            oos.flush();
            oos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 序列化
     *
     * @param object
     * @return
     */
    public static Object unserializeFromFile(String filePath) {
        FileInputStream in;
        ObjectInputStream ois;
        try {
            File file=new File(filePath);
            if(!file.exists()){
               return null;
            }
            in = new FileInputStream(file);
            ois = new ObjectInputStream(in);
            Object result = ois.readObject();
            ois.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
