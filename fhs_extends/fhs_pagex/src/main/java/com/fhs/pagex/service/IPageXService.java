package com.fhs.pagex.service;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * pagex service
 * 实现了此接口可以自己扩展pagex的页面模板
 * 比如你想自己写一个公共流的页面模板
 */
public interface IPageXService {

    /**
     * 给js 内容 ，namespace，request ，response，返回根据模板渲染后的html
     * @param request  request
     * @param response response
     * @param js namespace对应的js内容
     * @param namespace namespace
     * @return html代码
     * @throws NoSuchMethodException
     * @throws ScriptException
     */
     String service(HttpServletRequest request, HttpServletResponse response, String js, String namespace) throws NoSuchMethodException, ScriptException;
}
