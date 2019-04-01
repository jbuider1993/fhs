package com.fhs.common.utils;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;

/**
 * 强html转换为doc
 * @author  wanglei
 * @version  [版本号, 2016年6月1日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class HtmlToDoc
{

    public static void main(String[] args)
        throws Exception
    {
        // TODO Auto-generated method stub
        byte[] bytes =
            writeWordFile("<body>hello<img src='https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3832851394,4214400323&fm=58&s=39C718720E8EBE011B398BAC0300F024'></body>");
        FileUtils.writeByteArrayToFile(new File("d:/1.doc"), bytes);
    }

    /**
     * 读取html文件到word
     *
     * @param html html文件的路径
     * @return word的二进制
     * @throws Exception
     */
    public static byte[] writeWordFile(String html)
        throws Exception
    {
        ByteArrayInputStream bais = null;
        FileOutputStream fos = null;
        ByteArrayOutputStream bos = null;
        try
        {


            byte b[] = html.getBytes("gbk");
            bais = new ByteArrayInputStream(b);
            @SuppressWarnings("resource")
            POIFSFileSystem poifs = new POIFSFileSystem();
            bos = new ByteArrayOutputStream();
            poifs.writeFilesystem(bos);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fos != null)
            {
                fos.close();
            }

            if (bais != null)
            {
                bais.close();
            }
            if (bos != null)
            {
                bos.close();
            }
        }
        return bos.toByteArray();
    }

}
