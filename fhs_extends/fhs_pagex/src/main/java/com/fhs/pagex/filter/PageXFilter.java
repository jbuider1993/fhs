package com.fhs.pagex.filter;

import com.fhs.common.utils.Logger;
import com.fhs.pagex.context.PagexServletContext;
import com.fhs.pagex.service.HandelPageXService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  pagex结尾的jsp拦截
 *  本filter Z开头主要是为了让自己在shiroFilter 后面
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.filter
 * @ClassName: PageXFilter
 * @Author: JackWang
 * @CreateDate: 2018/11/30 0030 21:13
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/11/30 0030 21:13
 * @Version: 1.0
 */
public class PageXFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(PageXFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse rep = (HttpServletResponse)response;
        //受shiro的影响，虽然request不是以/ms/pagex/开头也走了这里
        if(req.getServletPath().startsWith("/ms/pagex/"))
        {
            PagexServletContext.init(req,rep);
            try
            {
                HandelPageXService.SIGEL.service(req,rep);
                return;
            }catch(Exception e)
            {
                LOG.error("渲染pagex页面错误:",e);
            }
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
