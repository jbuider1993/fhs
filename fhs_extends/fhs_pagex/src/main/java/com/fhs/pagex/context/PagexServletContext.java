package com.fhs.pagex.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PagexServletContext {
    private static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal<>();

    public static void init(HttpServletRequest req,HttpServletResponse resp){
        requestThreadLocal.set(req);
        responseThreadLocal.set(resp);
    }

    public static HttpServletRequest getRequest(){
        return requestThreadLocal.get();
    }

    public static HttpServletResponse getResponse(){
        return responseThreadLocal.get();
    }
}
