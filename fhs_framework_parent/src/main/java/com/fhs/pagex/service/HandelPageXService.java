package com.fhs.pagex.service;

import com.fhs.common.utils.Logger;
import com.fhs.core.config.EConfig;
import com.fhs.core.result.PubResult;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 处理pagex的服务
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.service
 * @ClassName: HandelPageXService
 * @Author: JackWang
 * @CreateDate: 2018/11/30 0030 21:16
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/11/30 0030 21:16
 * @Version: 1.0
 */
public enum HandelPageXService {

    /**
     * 一个牛逼的单利
     */
    SIGEL;

    private static final Logger LOG = Logger.getLogger(HandelPageXService.class);

    /**
     * 外部页面模板渲染服务都要注册进来
     */
    private static final Map<String, IPageXService> pagexServiceMap = new HashMap<>();

    /**
     * 根据request和response对外提供服务
     *
     * @param request  request
     * @param response response
     */
    public void service(HttpServletRequest request, HttpServletResponse response) {
        String namespace = getNamespace(request.getServletPath());
        String jsContent = PagexDataService.SIGNEL.getJsContent(namespace);
        if (jsContent == null) {
            wirteError(PubResult.NO_FIND.asResult().asJson(), PubResult.NO_FIND.getCode(), response);
            return;
        }
        String html = null;
        //
        try {
            String servletPath = request.getServletPath();
            for (String endWidthKey : pagexServiceMap.keySet()) {
                if (servletPath.endsWith(endWidthKey)) {
                    html = pagexServiceMap.get(endWidthKey).service(request, response, jsContent, namespace);
                }
            }
            if (html == null) {
                wirteError(PubResult.NO_FIND.asResult().asJson(), PubResult.NO_FIND.getCode(), response);
                return;
            }
        } catch (NoSuchMethodException e) {
            LOG.error(this, e);
            wirteError(PubResult.SYSTEM_ERROR.asResult().asJson(), PubResult.SYSTEM_ERROR.getCode(), response);
            return;
        } catch (ScriptException e) {
            LOG.error(this, e);
            wirteError(PubResult.SYSTEM_ERROR.asResult().asJson(), PubResult.SYSTEM_ERROR.getCode(), response);
            return;
        }
        html = handelParam(html, request);
        wirteSuccess(html, response);
    }

    /**
     * 处理文本中的${param.xx}和${path.xx}
     *
     * @param html    需要处理的内容
     * @param request request
     * @return 处理后的html
     */
    private String handelParam(String html, HttpServletRequest request) {
        //匹配所有的大括号
        List<String> needReplaceStrList = getMatchers("\\$\\{(.*?)\\}", html);
        String trimNeedReplace = null;
        // 替换${}
        for (String needReplace : needReplaceStrList) {
            trimNeedReplace = needReplace.trim();
            // 如果是path开头的就取path的
            if (trimNeedReplace.startsWith("path.")) {
                html = html.replace("${" + needReplace + "}", EConfig.getPathPropertiesValue(trimNeedReplace.replace("path.", "")));
            }
            //如果是param开头的就取param的
            else if (trimNeedReplace.startsWith("param.")) {
                String param = request.getParameter(trimNeedReplace.replace("param.", ""));
                param = param == null ? "" : param;
                html = html.replace("${" + needReplace + "}", param);
            }

        }
        return html;
    }
/*

    public static void main(String[] args)
    {
        System.out.println(getMatchers("\\$\\{(.*?)\\}","${path.code}"));
    }
*/


    private static List<String> getMatchers(String regex, String source) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group().replace("${", "").replace("}", ""));
        }
        return list;
    }

    /**
     * 获取namespace
     *
     * @param uri uri
     * @return namespace
     */
    private String getNamespace(String uri) {
        uri = uri.substring(uri.lastIndexOf("/") + 1);
        uri = uri.replace(".jsp", "")
                .replace("_add_update", "")
                .replace("_list", "")
                .replace("_tree", "");
        return uri;
    }


    /**
     * 写html给前端
     *
     * @param html     需要给前端写的html
     * @param response response
     */
    private void wirteSuccess(String html, HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html;charset=UTF-8");
        try {
            response.getWriter().write(html);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            LOG.error(this, e);
        }

    }

    /**
     * 写错误给前端
     *
     * @param body     body
     * @param httpCode http状态吗
     * @param response response
     */
    private void wirteError(String body, int httpCode, HttpServletResponse response) {
        response.setStatus(httpCode);
        wirteSuccess(body, response);
    }

    /**
     * 注册一个自定义模板渲染服务
     *
     * @param endWithKey 比如你的模板是namespace_xx.jsp 来访问就传xx.jsp
     * @param service    实现类
     */
    public void registerPageXService(String endWithKey, IPageXService service) {
        this.pagexServiceMap.put(endWithKey, service);
    }
}
