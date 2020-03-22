package com.fhs.module.base.index;

import com.fhs.common.constant.Constant;
import com.fhs.core.config.EConfig;
import com.fhs.logger.Logger;
import org.pac4j.core.redirect.RedirectAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 核心跨域跳转Controller
 * @author qh
 */
@Controller
public class RedirectController {

    private static final Logger LOGGER = Logger.getLogger(RedirectAction.class);


    @RequestMapping(value = "ms/redirect")
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, String[]> parameterMap = request.getParameterMap ( );
        String redirectUrl = parameterMap.get ( "call" )[0].toString ();
        for (String key: parameterMap.keySet ()) {
            if (!"call".equals (key )){
                redirectUrl += ("&" + key + "=" + URLEncoder.encode(parameterMap.get (key)[0].toString (),"utf8")   );
            }
        }
        if(request.getSession().getAttribute(Constant.SESSION_USER) == null)
        {
            request.getSession().setAttribute("serviceURL",redirectUrl);
            redirectUrl = EConfig.getPathPropertiesValue("basePath")+"/ms/index";
        }
        try {
            response.sendRedirect ( redirectUrl );
        } catch (IOException e) {
            LOGGER.error ( redirectUrl + "转发异常", e );
        }
    }
}
