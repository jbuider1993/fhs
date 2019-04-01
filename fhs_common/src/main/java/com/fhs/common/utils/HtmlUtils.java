package com.fhs.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;


/**
 * HTML 工具类
 *
 * @author  wanglei
 * @version  [版本号, 2016年5月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class HtmlUtils
{
    /**
     * 获取jsp输出内容
     * @param jsppath jsp的路径
     * @param request request
     * @param response response
     * @return  jsp模板引擎编译好的html
     * @throws Exception 如果出现异常
     */
    public static String getJspOutput(String jsppath, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        WrapperResponse wrapperResponse = new WrapperResponse(response);
        request.getRequestDispatcher(jsppath).include(request, wrapperResponse);
        return wrapperResponse.getContent();

    }

}

/**
 * 自己重写的response,主要是替换PrintWriter 用于获取写给前台的数据
 * @author  wanglei
 * @version  [版本号, 2016年5月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
class WrapperResponse extends HttpServletResponseWrapper
{
    private MyPrintWriter tmpWriter;

    private ByteArrayOutputStream output;

    public WrapperResponse(HttpServletResponse httpServletResponse)
    {
        super(httpServletResponse);
        output = new ByteArrayOutputStream();
        try
        {
            tmpWriter = new MyPrintWriter(output);
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void finalize()
        throws Throwable
    {
        super.finalize();
        output.close();
        tmpWriter.close();
    }

    public String getContent()
    {
        try
        {
            tmpWriter.flush(); // 刷新该流的缓冲，详看java.io.Writer.flush()
            String s = tmpWriter.getByteArrayOutputStream().toString("UTF-8");
            // 此处可根据需要进行对输出流以及Writer的重置操作
            // 比如tmpWriter.getByteArrayOutputStream().reset()
            return s;
        }
        catch (UnsupportedEncodingException e)
        {
            return "UnsupportedEncoding";
        }
    }

    // 覆盖getWriter()方法，使用我们自己定义的Writer
    public PrintWriter getWriter()
        throws IOException
    {
        return tmpWriter;
    }

    public void close()
        throws IOException
    {
        tmpWriter.close();
    }

    // 自定义PrintWriter，为的是把response流写到自己指定的输入流当中
    // 而非默认的ServletOutputStream
    private static class MyPrintWriter extends PrintWriter
    {
        ByteArrayOutputStream myOutput; // 此即为存放response输入流的对象

        public MyPrintWriter(ByteArrayOutputStream output) throws UnsupportedEncodingException
        {
            super(new OutputStreamWriter(output,"utf-8"));
            myOutput = output;
        }

        public ByteArrayOutputStream getByteArrayOutputStream()
        {
            return myOutput;
        }
    }
}