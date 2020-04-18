package com.fhs.common.utils;

import com.fhs.common.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <文件工具类>
 *
 * @author jackwong
 * @version [版本号, 2013年8月5日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
public class FileUtils extends org.apache.commons.io.FileUtils {

    /**
     * byte缓存大小
     */
    private static final int CACHE_SIZE = 100;

    /**
     * <下载文件>
     *
     * @param path     文件路径
     * @param response HttpServletResponse
     * @param fileName 显示的文件名
     */
    public static void download(String path, HttpServletResponse response, String fileName) {
        download( new File(path),  response,  fileName);
    }

    /**
     * 下载js
     * @param path 文件路径
     * @param response
     */
    public static void jsDownload(String path, HttpServletResponse response) {
        String fileName = null;
        File file = new File(path);
        fileName = file.getName();
        if (!fileName.equals("")) {
            download(path, response, fileName);
        }

    }

    /**
     * BufferedImage 转byte数组
     * @param bImage
     * @return
     */
    public static byte[] imageToBytes(BufferedImage bImage) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, "png", out);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return out.toByteArray();
    }

    /**
     * <下载文件> 输入文件流
     *
     * @param inputStream 文件路径
     * @param response    HttpServletResponse
     * @param filename    显示的文件名
     */
    public static void downloadInputStream(InputStream inputStream, HttpServletResponse response, String filename) {
        downloadInputStream(inputStream, response, filename, null);
    }


    /**
     * <下载文件> 输入文件流
     *
     * @param inputStream 文件路径
     * @param response    HttpServletResponse
     * @param filename    显示的文件名
     */
    public static void downloadInputStream(InputStream inputStream, HttpServletResponse response, String filename, Long length) {

        try (OutputStream toClient = new BufferedOutputStream(response.getOutputStream());) {
            initResponseHeader(response, filename, length == null ? inputStream.available() : length);
            // 以流的形式下载文件。
            byte[] bytes = new byte[1024 * 200];
            int readLength = 0;
            while ((readLength = inputStream.read(bytes)) != -1) {
                toClient.write(bytes, 0, readLength);
            }
            toClient.flush();
        } catch (IOException ex) {
            log.error("download error",ex);
        } finally {
            closeInputStream(inputStream);
        }
    }

    /**
     * 格式化文件名称去掉特殊字符
     *
     * @param fileName 文件名称
     * @return 格式化后的文件明湖曾
     */
    private static String formartFileName(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = fileName.replace(suffix, "");
        String regex = "^[a-zA-Z0-9\u4E00-\u9FA5]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(fileName);
        if (match.matches()) {
            return fileName + suffix;
        }
        return "default" + suffix;

    }

    /**
     * <下载文件>
     *
     * @param file     需要下载的文件
     * @param response HttpServletResponse
     * @param fileName 显示的文件名
     */
    public static void download(File file, HttpServletResponse response, String fileName) {
        try {
            downloadInputStream(new FileInputStream(file), response, fileName, file.length());
        } catch (FileNotFoundException e) {
            log.error("文件不存在",e);
        }
    }

    /**
     * 初始化下载文件的response的header
     *
     * @param response response
     * @param fileName 文件名称
     */
    public static void initResponseHeader(HttpServletResponse response, String fileName, long length){
        fileName = formartFileName(fileName);
        // 清空response
        response.reset();
        String contentType = getContentType(fileName);
        if (!contentType.equals("application/javascript")) {
            String disposition = "application/octet-stream".equals(contentType) ? "attachment" : "inline";
            // 解决中文乱码
            try {
                response.setHeader("Content-Disposition",
                        disposition + ";filename=" + URLEncoder.encode(fileName, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                log.error("initResponseHeader set error",e);
            }

        }

        response.addHeader("Content-Length", "" + length);
        // response.setContentType(getContentType(filename));
        response.setHeader("Content-Type", contentType);
        //本页面允许在浏览器端或缓存服务器中缓存，时限为20秒。
        //20秒之内重新进入该页面的话不会进入该servlet的
        java.util.Date date = new java.util.Date();
        response.setDateHeader("Last-Modified", date.getTime()); //Last-Modified:页面的最后生成时间
        response.setDateHeader("Expires", date.getTime() + 1000 * 60 * 60 * 2); //Expires:过时期限值
        response.setHeader("Cache-Control", "public"); //Cache-Control来控制页面的缓存与否,public:浏览器和缓存服务器都可以缓存页面信息；
        response.setHeader("Pragma", "Pragma"); //Pragma:设置页面是否缓存，为Pragma则缓存，no-cache则不缓存
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    /**
     * <下载文件>
     *
     * @param bytes    需要下载的二进制
     * @param response HttpServletResponse
     * @param fileName 显示的文件名
     */
    public static void download(byte[] bytes, HttpServletResponse response, String fileName) {
        OutputStream toClient = null;
        InputStream fis = null;
        try {
            initResponseHeader(response, fileName, bytes.length);
            toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(bytes);
            toClient.flush();
        } catch (IOException ex) {
            log.error("下载文件错误:", ex);
        } finally {
            closeInputStream(fis);
            closeOutputStream(toClient);
        }
    }

    /**
     * 根据文件名获取返回类型 ContentType
     *
     * @param fileName 文件名
     * @return ContentType
     */
    private static String getContentType(String fileName) {
        String result = "application/octet-stream";
        if (fileName == null || (!fileName.contains("."))) {
            return result;
        }
        int index = fileName.lastIndexOf(".");
        String suffix = fileName.substring(index + 1);
        switch (suffix) {
            case "png":
                result = "image/png";
                break;
            case "jpg":
                result = "image/jpeg";
                break;
            case "mp4":
                result = "video/mpeg4";
                break;
            case "gif":
                result = "application/gif";
                break;
            case "js":
                result = "application/javascript";
                break;
        }
        return result;
    }

    /**
     * 将一个文件加载为properties对象
     *
     * @param filePath 文件路径
     * @return properties对象
     */
    public static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try {
            properties = PropertiesLoaderUtils.loadAllProperties(filePath);
        } catch (Exception e) {
            log.error(filePath + "is not find!");
        }
        return properties;
    }

    /**
     * 关闭输出流
     *
     * @param os 需要关闭的流对象
     */
    public static void closeOutputStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                log.error("关闭输出流错误",e);
            }
        }
    }

    /**
     * 关闭输入流
     *
     * @param is 需要关闭的流对象
     */
    public static void closeInputStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                log.error("关闭输入流错误",e);
            }
        }
    }

    /**
     * 文件分布式存储 存储方式 uuId 的hash 算法
     *
     * @param saveFilePathType 文件存放的目录
     * @param fileName         文件名字
     * @param suffix           文件后缀 * @param fileScope File类静态变量 跨域参数
     * @return
     */
    public static String addLocatedFilePath(String saveFilePathType, String fileName, String suffix, String fileScope) {
        // 获取uuId 首字母
        String fileOne = fileName.substring(0, 1);
        // 获取 uuId 的第二个字母
        String fileTwo = fileName.substring(1, 2);
        // 存放文件的父文件夹
        String saveFilePath = saveFilePathType + fileScope + fileOne;
        // 存放文件的子文件夹
        String saveFileSonPath = saveFilePath + fileScope + Constant.SLASH + fileTwo;
        // 创建父文件夹
        if (createFileDir(saveFileSonPath)) {
            return saveFileSonPath + Constant.SLASH + fileName;
        }
        return null;
    }

    /**
     * 创建文件夹
     *
     * @param filePath 文件夹的路径
     * @return
     */
    public static boolean createFileDir(String filePath) {
        File file = new File(filePath);
        // 如果文件夹不存在则创建
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        return true;
    }

    /**
     * 根据uuId 获得文件的路径
     *
     * @param fileName         文件名称
     * @param saveFilePathType 获取文件的目录
     * @param fileScope        File类静态变量 跨域参数
     * @return
     */
    public static String getFilePath(String saveFilePathType, String fileName, String fileScope) {
        if (saveFilePathType != null && fileName != null) {
            // 获取uuId 首字母
            String fileOne = fileName.substring(0, 1);
            // 获取 uuId 的第二个字母
            String fileTwo = fileName.substring(1, 2);
            // 获取文件的路径
            String loaclFilePath = saveFilePathType + fileScope + fileOne + Constant.SLASH + fileTwo;
            // 本地文件的目录
            if (createFileDir(loaclFilePath)) {
                // 获取文件的完整路径
                return loaclFilePath + Constant.SLASH + fileName;
            }

        }
        return "";
    }

    /**
     * 将文件从旧地址 copy到新目录
     *
     * @param oldPath 旧目录
     * @param newPath 新目录
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void copyFile(File oldPath, String newPath)
            throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(oldPath);
        FileOutputStream fos = new FileOutputStream(newPath);
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = fis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        fis.close();
        fos.close();
    }

    /***
     * InputStream 转 byte[]
     *
     * @param inStream
     * @return
     * @throws IOException
     */
    public static final byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        // buff用于存放循环读取的临时数据
        byte[] buff = new byte[CACHE_SIZE];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, CACHE_SIZE)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        // in_b为转换之后的结果
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }


    /**
     * 如果文件不存在，则创建新文件
     *
     * @param filePath
     * @return
     */
    public static boolean createNewFile(String filePath) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                File fileDir = file.getParentFile();
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                file.createNewFile();
            }
            return true;
        } catch (IOException e) {
            log.error("",e);
        }
        return false;

    }

    /**
     * <将byte数组转换为file对象>
     *
     * @param byteArray 需要转换为file对象的byte数组
     * @param filePath  生成file文件的路径
     */
    public static void byteArrayToFile(byte[] byteArray, String filePath) {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            createNewFile(filePath);
        }
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(targetFile);
            bos = new BufferedOutputStream(fos);
            bos.write(byteArray);
            bos.flush();
        } catch (FileNotFoundException e) {
            log.error("",e);
        } catch (IOException e) {
            log.error("",e);
        } finally {
            closeOutputStream(bos);
            closeOutputStream(fos);
        }
    }

    /**
     * <将图片>
     *
     * @param source
     * @param targetW
     * @param targetH
     * @return
     */
    public static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
        // targetW，targetH分别表示目标长和宽
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) targetW / source.getWidth();
        double sy = (double) targetH / source.getHeight();
        // 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
        // 则将下面的if else语句注释即可
        if (sx > sy) {
            sx = sy;
            targetW = (int) (sx * source.getWidth());
        } else {
            sy = sx;
            targetH = (int) (sy * source.getHeight());
        }
        if (type == BufferedImage.TYPE_CUSTOM) { // handmade
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else
            target = new BufferedImage(targetW, targetH, type);
        Graphics2D g = target.createGraphics();
        // smoother than exlax:
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }

    public static final InputStream byte2Input(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                log.info("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                log.info("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            log.info("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * @param base64Pic
     * @param imgFilePath
     * @return
     * @desc 转存文件
     */
    public static boolean savePicByBase64(String base64Pic, String imgFilePath) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            String baseValue = base64Pic.replaceAll(" ", "+");
            byte[] b = Base64.getDecoder().decode(baseValue.replace("data:image/png;base64,", ""));
            OutputStream out = null;
            try {
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {// 调整异常数据
                        b[i] += 256;
                    }
                }
                // 生成jpeg图片
                out = new FileOutputStream(imgFilePath);
                out.write(b);
                out.flush();
            } catch (Exception e) {
                log.error("base save error",e);
                return false;
            }
            finally {
                closeOutputStream(out);
            }
            return true;
        } catch (Exception e) {
            log.error("base save error",e);
            return false;
        }
    }

    /**
     * 读取文本文件
     *
     * @param is 输入流
     * @return 文本内容
     */
    public static String readTxtFile(InputStream is) { // 优化读取txt工具，防止io阻塞
        StringBuilder result = new StringBuilder();
        try {
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);// 构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                result.append(System.lineSeparator() + s);
            }
            br.close();
        } catch (Exception e) {
            log.error("readTxt Error",e);
        }
        return result.toString();
    }


}
