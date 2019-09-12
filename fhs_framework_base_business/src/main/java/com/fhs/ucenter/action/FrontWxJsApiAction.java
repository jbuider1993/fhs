package com.fhs.ucenter.action;

import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.JsonUtils;
import com.fhs.common.utils.Logger;
import com.fhs.core.base.action.BaseAction;
import com.fhs.core.exception.ParamChecker;
import com.fhs.ucenter.tools.WxTools;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取微信js api调用签名
 * by wanglei
 */
@Controller
@RequestMapping("/webApi/jssdk")
public class FrontWxJsApiAction extends BaseAction {

    private static final  Logger LOG = Logger.getLogger(FrontWxJsApiAction.class);

    @Autowired
    private WxTools wxTools;

    /**
     * 获取js sdk签名
     * @param request
     * @param response
     */
    @RequestMapping("getSignature")
    public void getJsSdkSignature(HttpServletRequest request, HttpServletResponse response,String extendsCode)
    {
        WxMpService wxMpService = wxTools.getWxMpService(extendsCode);
        String url = ConverterUtils.toString(request.getHeader("Referer"));
        url = CheckUtils.isNullOrEmpty(url) ? request.getHeader("referer") : url;
        ParamChecker.isNotNullOrEmpty(url,"header中referer不能为空");
        try {
            WxJsapiSignature jsapiSignature = wxMpService.createJsapiSignature(url);
            super.outJsonp(JsonUtils.bean2json(jsapiSignature),response,request);
        } catch (WxErrorException e) {
            LOG.error("获取jssdk签名错误,url:" + url +",extendsCode:" + extendsCode,e);
        }
    }

}
