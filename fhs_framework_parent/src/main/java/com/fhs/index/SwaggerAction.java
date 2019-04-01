package com.fhs.index;

import com.fhs.core.config.EConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 接收info请求转发到swagger的页面上
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.index
 * @ClassName: SwaggerAction
 * @Author: JackWang
 * @CreateDate: 2018/9/10 0010 14:16
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/9/10 0010 14:16
 * @Version: 1.0
 */
@Controller
public class SwaggerAction {


    /**
     * 302到swagger页面
     * @param response
     */
    @RequestMapping("/docs")
    public void appInfo(HttpServletResponse response) throws IOException {
        response.sendRedirect(EConfig.getPathPropertiesValue("basePath") + "/swagger-ui.html");
    }
}
