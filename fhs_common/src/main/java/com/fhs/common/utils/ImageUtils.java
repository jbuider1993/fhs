package com.fhs.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 *
 * <图片处理工具类>
 *
 * @author lishibin
 * @version [版本号, 2017年3月9日]
 * @see [相关类/方法]
 * @since [产品/模块版本] desprition:com.sxpartner.ImageUtils
 */
@SuppressWarnings("restriction")
public final class ImageUtils
{
    public static String[] getXy(int size)
    {
        String[] s = new String[size];
        int _x = 0;
        int _y = 0;
        if (size == 1)
        {
            _x = _y = 6;
            s[0] = "6,6";
        }
        if (size == 2)
        {
            _x = _y = 4;
            s[0] = "4," + (132 / 2 - 60 / 2);
            s[1] = 60 + 2 * _x + "," + (132 / 2 - 60 / 2);
        }
        if (size == 3)
        {
            _x = _y = 4;
            s[0] = (132 / 2 - 60 / 2) + "," + _y;
            s[1] = _x + "," + (60 + 2 * _y);
            s[2] = (60 + 2 * _y) + "," + (60 + 2 * _y);
        }
        if (size == 4)
        {
            _x = _y = 4;
            s[0] = _x + "," + _y;
            s[1] = (_x * 2 + 60) + "," + _y;
            s[2] = _x + "," + (60 + 2 * _y);
            s[3] = (60 + 2 * _y) + "," + (60 + 2 * _y);
        }
        if (size == 5)
        {
            _x = _y = 3;
            s[0] = (132 - 40 * 2 - _x) / 2 + "," + (132 - 40 * 2 - _y) / 2;
            s[1] = ((132 - 40 * 2 - _x) / 2 + 40 + _x) + "," + (132 - 40 * 2 - _y) / 2;
            s[2] = _x + "," + ((132 - 40 * 2 - _x) / 2 + 40 + _y);
            s[3] = (_x * 2 + 40) + "," + ((132 - 40 * 2 - _x) / 2 + 40 + _y);
            s[4] = (_x * 3 + 40 * 2) + "," + ((132 - 40 * 2 - _x) / 2 + 40 + _y);
        }
        if (size == 6)
        {
            _x = _y = 3;
            s[0] = _x + "," + ((132 - 40 * 2 - _x) / 2);
            s[1] = (_x * 2 + 40) + "," + ((132 - 40 * 2 - _x) / 2);
            s[2] = (_x * 3 + 40 * 2) + "," + ((132 - 40 * 2 - _x) / 2);
            s[3] = _x + "," + ((132 - 40 * 2 - _x) / 2 + 40 + _y);
            s[4] = (_x * 2 + 40) + "," + ((132 - 40 * 2 - _x) / 2 + 40 + _y);
            s[5] = (_x * 3 + 40 * 2) + "," + ((132 - 40 * 2 - _x) / 2 + 40 + _y);
        }
        if (size == 7)
        {
            _x = _y = 3;
            s[0] = (132 - 40) / 2 + "," + _y;
            s[1] = _x + "," + (_y * 2 + 40);
            s[2] = (_x * 2 + 40) + "," + (_y * 2 + 40);
            s[3] = (_x * 3 + 40 * 2) + "," + (_y * 2 + 40);
            s[4] = _x + "," + (_y * 3 + 40 * 2);
            s[5] = (_x * 2 + 40) + "," + (_y * 3 + 40 * 2);
            s[6] = (_x * 3 + 40 * 2) + "," + (_y * 3 + 40 * 2);
        }
        if (size == 8)
        {
            _x = _y = 3;
            s[0] = (132 - 80 - _x) / 2 + "," + _y;
            s[1] = ((132 - 80 - _x) / 2 + _x + 40) + "," + _y;
            s[2] = _x + "," + (_y * 2 + 40);
            s[3] = (_x * 2 + 40) + "," + (_y * 2 + 40);
            s[4] = (_x * 3 + 40 * 2) + "," + (_y * 2 + 40);
            s[5] = _x + "," + (_y * 3 + 40 * 2);
            s[6] = (_x * 2 + 40) + "," + (_y * 3 + 40 * 2);
            s[7] = (_x * 3 + 40 * 2) + "," + (_y * 3 + 40 * 2);
        }
        if (size == 9)
        {
            _x = _y = 3;
            s[0] = _x + "," + _y;
            s[1] = _x * 2 + 40 + "," + _y;
            s[2] = _x * 3 + 40 * 2 + "," + _y;
            s[3] = _x + "," + (_y * 2 + 40);
            s[4] = (_x * 2 + 40) + "," + (_y * 2 + 40);
            s[5] = (_x * 3 + 40 * 2) + "," + (_y * 2 + 40);
            s[6] = _x + "," + (_y * 3 + 40 * 2);
            s[7] = (_x * 2 + 40) + "," + (_y * 3 + 40 * 2);
            s[8] = (_x * 3 + 40 * 2) + "," + (_y * 3 + 40 * 2);
        }
        return s;
    }

    public static int getWidth(int size)
    {
        int width = 0;
        if (size == 1)
        {
            width = 120;
        }
        if (size > 1 && size <= 4)
        {
            width = 60;
        }
        if (size >= 5)
        {
            width = 40;
        }
        return width;
    }

    /**
     *
     * <压缩输入流>
     *
     * @param in 需要压缩输入流
     * @param width 需要压缩的宽度
     * @param height 需要压缩的高度
     * @return 返回图片缓冲
     */
    public static BufferedImage zoom(InputStream in, int width, int height)
    {
        BufferedImage image = null;
        try
        {
            image = ImageIO.read(in);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        image = zoom(image, width, height);
        return image;
    }

    private static BufferedImage zoom(BufferedImage sourceImage, int width, int height)
    {
        BufferedImage zoomImage = new BufferedImage(width, height, sourceImage.getType());
        Image image = sourceImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Graphics gc = zoomImage.getGraphics();
        gc.setColor(Color.WHITE);
        gc.drawImage(image, 0, 0, null);
        return zoomImage;
    }

    /**
     *
     * <合并多张图片>
     *
     * @param inArray
     * @param outPath
     * @throws Exception
     */
    public static byte[] multiply(InputStream[] inArray)
        throws Exception
    {
        String[] imageSize = getXy(inArray.length);
        int width = getWidth(inArray.length);
        BufferedImage ImageNew = new BufferedImage(132, 132, BufferedImage.TYPE_INT_RGB);
        for (int m = 0; m < 132; m++)
        {
            for (int n = 0; n < 132; n++)
            {
                ImageNew.setRGB(m, n, 0xe1e1e1);
            }
        }
        for (int i = 0; i < imageSize.length; i++)
        {
            String size = imageSize[i];
            String[] sizeArr = size.split(",");
            int x = Integer.valueOf(sizeArr[0]);
            int y = Integer.valueOf(sizeArr[1]);
            BufferedImage ImageOne = zoom(inArray[i], width, width);
            int[] ImageArrayOne = new int[width * width];
            ImageArrayOne = ImageOne.getRGB(0, 0, width, width, ImageArrayOne, 0, width);
            ImageNew.setRGB(x, y, width, width, ImageArrayOne, 0, width);
        }

        // 创建储存图片二进制流的输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 创建ImageOutputStream流
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(baos);
        // 将二进制数据写进ByteArrayOutputStream
        ImageIO.write(ImageNew, "png", imageOutputStream);
        // 输出数组
        return baos.toByteArray();
    }

    /**
     *
     * <byte数组转换成file>
     *
     * @param buf
     * @param filePath
     * @param fileName
     */
    public static void byte2File(byte[] buf, String filePath, String fileName)
    {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try
        {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory())
            {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (bos != null)
            {
                try
                {
                    bos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean generateImage(String imgStr, String path)
    {
        if (imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i)
            {
                if (b[i] < 0)
                {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author:
     * @CreateTime:
     * @return
     */
    public static String getImageStr(String imgFile)
    {
        InputStream inputStream = null;
        byte[] data = null;
        try
        {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    public static void main(String[] args)
        throws Exception
    {
        String filePath = "D:\\upfile\\6d289b7d0631427ca83e108a7762b5d6.jpg";
        String imageStr = getImageStr(filePath);
        generateImage(imageStr,"D:\\upfile\\test.jpg");
        System.out.print(imageStr);
    }
}