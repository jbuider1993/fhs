package com.fhs.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie 操作工具类
 * @author  jackwong
 * @version  [版本号, 2015年6月2日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CookieUtil
{
    /**
     * 添加一个带时长的cookie
     * @param key 主键
     * @param value 值
     * @param maxAge 有效时间
     * @param response 响应
     */
    public static void writeCookie(String key, String value, int maxAge, HttpServletResponse response)
    {
        Cookie namecookie = new Cookie(key, value);
        namecookie.setPath("/");
        namecookie.setMaxAge(maxAge);
        response.addCookie(namecookie);
    }
    
    /**
     * 添加一个临时的cookie
     * @param key 主键
     * @param value 值
     */
    public static void writeCookie(String key, String value, HttpServletResponse response)
    {
        Cookie namecookie = new Cookie(key, value);
        namecookie.setPath("/");
        namecookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(namecookie);
    }
    
    /**
     * 读取cookie
     * @param request 请求
     * @param key 主键
     * @return String 读取key 对应的值
     */
    public static String readCookie(String key, HttpServletRequest request)
    {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
        {
            
            for (int i = 0; i < cookies.length; i++)
            {
                Cookie c = cookies[i];
                if (c.getName().equalsIgnoreCase(key))
                {
                    value = c.getValue();
                    return value;
                }
            }
        }
        return value;
    }
    
    /***
     * 删除 cookie 
     * @param key 
     * @param request
     */
    public static void delCookie(String key, HttpServletRequest request)
    {
            Cookie[] cookies = request.getCookies();
            if (cookies != null)
            {
                for (int i = 0; i < cookies.length; i++)
                {
                    Cookie cookie = new Cookie(key, null);
                    cookie.setMaxAge(0);
                }
            }
            
    }
    
}