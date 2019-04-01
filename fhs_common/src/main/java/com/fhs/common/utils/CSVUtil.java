package com.fhs.common.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CSV文件导入导出工具类
 * <p/>
 * Created on 2014-08-07
 *
 * @author
 * @reviewer
 */
public class CSVUtil {
    static Logger log = LoggerFactory.getLogger(CSVUtil.class);
    /**
     * CSV文件列分隔符
     */
    private static final String CSV_COLUMN_SEPARATOR = ",";

    /**
     * CSV文件列分隔符
     */
    private static final String CSV_RN = "\r\n";

    /**
     * 将检索数据输出的对应的csv列中
     */
    public static String formatCsvData(List<?> data,String[] fields,String title) {

        StringBuffer buf = new StringBuffer();
        StringBuffer bufTitle = new StringBuffer();
        bufTitle.append(title).append(CSV_RN);
        int dataCount = data.size();
        int successCount = 0;
        for (Object obj : data)
        {
            Class<?> objClass = obj.getClass();
            try {
                for (int i = 0; i < fields.length; i++)
                {
                    String name = fields[i];
                    String getMethodName = "get" + toFirstLetterUpperCase(name);
                    try {

                        Object value = objClass.getMethod(getMethodName).invoke(obj);
                        if (null == value || value == "") {
                            buf.append("");
                            if (i < fields.length - 1) {
                                buf.append(CSV_COLUMN_SEPARATOR);
                            }
                        } else {
                            String v = value.toString().replaceAll("\"", "\"\"");
                            buf.append(v.contains(",") ? String.format("\"%s\"", v) : value.toString());
                            if (i < fields.length - 1) {
                                buf.append(CSV_COLUMN_SEPARATOR);
                            }
                        }

                    } catch (Exception e) {
                        log.error("导出cvs异常:{}",e);
                        return null;
                    }
                }
                buf.append(CSV_RN);
            } catch (Exception e) {
                log.error("导出cvs异常:{}",e);
                return null;
            }
            successCount++;
        }
        if(dataCount<successCount)
        {
            log.error("导出数据不完整");
            return  null;
        }
        bufTitle.append(buf);
        return bufTitle.toString();
    }

    /**
     * 将检索数据输出的对应的csv列中
     */
    public static String formatCsvData4Map(List<Map<String, Object>> data,String[] fields,String title) {

        StringBuffer buf = new StringBuffer();
        StringBuffer bufTitle = new StringBuffer();
        bufTitle.append(title).append(CSV_RN);
        int dataCount = data.size();
        int successCount = 0;
        for (Map<String, Object> map : data)
        {

            try {
                for (int i = 0; i < fields.length; i++)
                {
                    try {

                        String value = ConverterUtils.toString(map.get(fields[i]));
                        if (null == value || value == "") {
                            buf.append("");
                            if (i < fields.length - 1) {
                                buf.append(CSV_COLUMN_SEPARATOR);
                            }
                        } else {
                            String v = value.replaceAll("\"", "\"\"");
                            buf.append(v.contains(",") ? String.format("\"%s\"", v) : value);
                            if (i < fields.length - 1) {
                                buf.append(CSV_COLUMN_SEPARATOR);
                            }
                        }

                    } catch (Exception e) {
                        log.error("导出cvs异常:{}",e);
                        return null;
                    }
                }
                buf.append(CSV_RN);
            } catch (Exception e) {
                log.error("导出cvs异常:{}",e);
                return null;
            }
            successCount++;
        }
        if(dataCount<successCount)
        {
            log.error("导出数据不完整");
            return  null;
        }
        bufTitle.append(buf);
        return bufTitle.toString();
    }

    public static void exportCsv(String fileName, String content,
                                 HttpServletResponse response) throws IOException {
        // 读取字符编码
        String csvEncoding = "GBK";

        // 设置响应
        response.setCharacterEncoding(csvEncoding);
        response.setContentType("text/csv; charset=" + csvEncoding);
        response.setHeader("Content-Disposition", "attachment; filename="
                + new String(fileName.getBytes(), csvEncoding));

        // 写出响应
        OutputStream os = response.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, csvEncoding);
        osw.write(content);
        osw.flush();
        osw.close();
    }

    public static List<String[]> readCSVFile(File f) throws Exception {
        InputStreamReader fr = null;
        BufferedReader br = null;
        String rec = null;// 一行
        String str;
        List<String[]> listFile = new ArrayList<>();
        FileInputStream fileInputStream = null;
        try {
            String code = codeString(f);
            fileInputStream =  new FileInputStream(f);
            fr = new InputStreamReader(fileInputStream, code);
            br = new BufferedReader(fr);
            while ((rec = br.readLine()) != null) {
                Pattern pCells = Pattern.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,?");
                Matcher mCells = pCells.matcher(rec);
                List<String> cells = new ArrayList<>();// 每行记录一个list
                // 读取每个单元格
                while (mCells.find()) {
                    str = mCells.group();
                    if (str.endsWith("\"")) {
                        str += ",";
                    }
                    str = str.replaceAll("(?sm)\"?([^\"]*((\"{2})*[^\"]*(\"{2})*)*[^\"]*)\"?,", "$1");
                    str = str.replaceAll("(?sm)(\"(\"))", "$2");
                    cells.add(str);
                }
                String[] s = new String[cells.size()];
                cells.toArray(s);
                listFile.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            IOUtils.closeQuietly(br);
            IOUtils.closeQuietly(fr);
            IOUtils.closeQuietly(fileInputStream);
        }
        return listFile;
    }


    /**
     * @param f
     * @param className
     * @param isAdd     是否新增
     * @param title     columns
     * @param table     文件识别
     * @return
     * @throws Exception
     */
    public static List<Object> setObject(File f, String className, boolean isAdd, String title, String table) throws Exception {
        List<Object> listObject = new ArrayList<>();
        List<String[]> listFile = readCSVFile(f);
        Iterator<String[]> it = listFile.iterator();
        String[] t = it.next();
        if (t[0] == null || t[0].isEmpty() || !t[0].contains(table)) {
            return listObject;
        }
        String[] a = it.next();
        StringUtils.stripAll(a);
        while (it.hasNext()) {
            String[] objSt = it.next();
            Class<?> cl = Class.forName(className);
            Object rt = cl.newInstance();//创建一个对象
            Field[] fields = cl.getDeclaredFields();

            Map<String, Field> m = new HashMap<>();
            for (int i = 0; i < fields.length; i++) {
                String name = fields[i].getName();
                m.put(name, fields[i]);
            }
            for (int i = 0; i < a.length; i++) {
                Field field = m.get(a[i]);
                if (null == field || (isAdd && field.getName().equalsIgnoreCase("id"))) {
                    continue;
                }
                field.setAccessible(true);
                String s = objSt[i].replaceAll("\\|", ",");
                s = s.replaceAll("，", ",");
                if (s.isEmpty() || s.contains("null")) {
                    continue;
                }
                if (field.getType() == String.class) {
                    field.set(rt, s);
                } else if (field.getType() == int.class || field.getType() == Integer.class) {
                    field.set(rt, Integer.valueOf(s));
                } else if (field.getType() == double.class || field.getType() == Double.class) {
                    field.set(rt, Double.valueOf(s));
                } else if (field.getType() == BigDecimal.class) {
                    field.set(rt, new BigDecimal(s));
                } else if (field.getType() == long.class || field.getType() == Long.class) {
                    field.set(rt, Long.valueOf(s));
                } else if (field.getType() == short.class || field.getType() == Short.class) {
                    field.set(rt, Short.valueOf(s));
                } else if (field.getType() == char.class) {
                    field.set(rt, s.charAt(0));
                } else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                    field.set(rt, Boolean.getBoolean(s));
                } else if (field.getType() == float.class || field.getType() == Float.class) {
                    field.set(rt, Float.valueOf(s));
                } else if (field.getType() == BigDecimal.class) {
                    field.set(rt, new BigDecimal(s));
                }
            }
            listObject.add(rt);
        }

        return listObject;
    }

    public static String toFirstLetterUpperCase(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        String firstLetter = str.substring(0, 1).toUpperCase();
        return firstLetter + str.substring(1, str.length());
    }

    /**
     * 获取编码方式
     *
     * @param
     * @return 编码方式
     * @throws Exception
     */
    public static String codeString(File file) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bin = new BufferedInputStream(fileInputStream);
        int p = (bin.read() << 8) + bin.read();
        String code = null;
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
        IOUtils.closeQuietly(bin);
        IOUtils.closeQuietly(fileInputStream);
        return code;
    }

}
