package com.fhs.flow.pagex.service.xsimpl;

import com.fhs.logger.Logger;
import com.fhs.pagex.common.BeetlUtil;
import com.fhs.pagex.service.HandelPageXService;
import com.fhs.pagex.service.IPageXService;
import com.fhs.pagex.service.xsimpl.PagexListService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理pagex列表页面的服务
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex
 * @ClassName: PagexListService
 * @Author: JackWang
 * @CreateDate: 2018/11/30 0030 20:07
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/11/30 0030 20:07
 * @Version: 1.0
 */
@Component
public class PagexFlowViewService implements IPageXService, InitializingBean {


    private static final Logger LOG = Logger.getLogger(PagexFlowViewService.class);


    @Override
    public void afterPropertiesSet() throws Exception {
        HandelPageXService.SIGEL.registerPageXService("flow_view.jsp", this);
    }

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response, String js, String namespace) throws NoSuchMethodException, ScriptException {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("namespace", namespace);
        return BeetlUtil.renderBeelt("/pagex/flow_view_template.html",paramMap);
    }
}
