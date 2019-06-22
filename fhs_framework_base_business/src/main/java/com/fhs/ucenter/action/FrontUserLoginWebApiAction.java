package com.fhs.ucenter.action;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.*;
import com.fhs.core.config.EConfig;
import com.fhs.core.exception.BusinessException;
import com.fhs.core.exception.ParamException;
import com.fhs.ucenter.bean.UcenterAlipaySett;
import com.fhs.ucenter.bean.UcenterFrontUser;
import com.fhs.ucenter.bean.UcenterFrontUserBind;
import com.fhs.ucenter.service.LoginService;
import com.fhs.ucenter.service.UcenterAlipaySettService;
import com.fhs.ucenter.service.UcenterFrontUserBindService;
import com.fhs.ucenter.service.UcenterFrontUserService;
import com.fhs.ucenter.tools.WxTools;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 前段用户登录注册 by jackwong
 *
 */
@RestController
@RequestMapping("/webApi/front")
public class FrontUserLoginWebApiAction implements InitializingBean {
    //日志
    private static final Logger LOGGER = Logger.getLogger ( FrontUserLoginWebApiAction.class );

    /**
     * OAUTH_HANDEL_MAP
     */
    private static final Map<String,OAuthHandle> OAUTH_HANDEL_MAP = new HashMap<>();

    /** 登录服务 */
    @Autowired
    private LoginService loginService;

    /** 用户绑定服务 */
    @Autowired
    private UcenterFrontUserBindService userBindService;

    /**
     * 用户
     */
    @Autowired
    private  UcenterFrontUserService frontUserService;


    /**
     * 用户登录
     * 自动识别登录方式
     * @param request 请求
     * @param response 响应
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "用户登录")
    public void login(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        String code = request.getParameter("code");
        // 不存在的话在cookie中获取
        if (CheckUtils.isNullOrEmpty(code))
        {
            code = CookieUtil.readCookie("code", request);
        }
        // 还不存在就报错
        if (CheckUtils.isNullOrEmpty(code))
        {
            throw new ParamException("code 不可以为空");
        }
        HttpSession session = request.getSession();
        String callBack = request.getParameter("callBack");
        if (CheckUtils.isNullOrEmpty(callBack))
        {
            throw new ParamException("callBack 不可以为空");
        }
        session.setAttribute("code", code);
        session.setAttribute("callBack", callBack);
        String userParkCode = ConverterUtils.toString(session.getAttribute("code"));
        CookieUtil.writeCookie("code", code, response);
        if (session.getAttribute("accessToken") != null && userParkCode.equals(code))
        {
            String accessToken = ConverterUtils.toString(session.getAttribute("accessToken"));
            //通过accessToken获取用户id
            String userId = loginService.getUserIdByAccessToken(accessToken);
            if (CheckUtils.isNullOrEmpty(userId))
            {
                session.removeAttribute("accessToken");
            }
            else
            {
                response.sendRedirect(checkUrl(callBack, accessToken));
                return;
            }
        }
        //截下来处理各种 SSO请求
        String userAgent = ConverterUtils.toString(request.getHeader("user-agent")).toLowerCase();
        //看看谁去处理
        for (String ua : OAUTH_HANDEL_MAP.keySet())
        {
            if(userAgent.contains(ua))
            {
                OAUTH_HANDEL_MAP.get(ua).handleLogin(request,response,code);
                return;
            }
        }
        throw new ParamException("暂时不支持此种浏览器访问");
    }

    /**
     * 用userid去登录并且跳转到callback页面
     * @param request
     * @param response
     * @param userId 用户id
     */
    private void loginAndRedirect(HttpServletRequest request, HttpServletResponse response,String userId)
    {
        UcenterFrontUser user  = frontUserService.selectById(userId);
        if(user == null)
        {
            throw new ParamException("用户信息丢失:" + userId);
        }
        if(Constant.STR_YES.equals(user.getIsDisable()))
        {
            throw new ParamException("用户被禁用");
        }
        String accessToken = loginService.login(userId);
        request.getSession().setAttribute("accessToken",accessToken);
        String callback = request.getSession().getAttribute("callBack").toString();
        try {
            response.sendRedirect(checkUrl(callback, accessToken));
        } catch (IOException e) {
            LOGGER.error("302到登录前页面错误",e);
        }
    }

    /**
     * 对URL参数进行过滤处理.
     *
     * @param callback 回调地址
     * @param accessToken token信息
     * @return 解码后的回调地址
     */
    private String checkUrl(String callback, String accessToken)
            throws UnsupportedEncodingException
    {
        callback = new String(callback.getBytes(), "utf-8");
        callback = java.net.URLDecoder.decode(callback, "utf-8");
        if (!callback.startsWith("http"))
        {
            throw new ParamException("callback地址格式有误!");
        }
        if (callback.contains("?"))
        {
            if (callback.endsWith("?"))
            {
                return callback + "accessToken=" + accessToken;
            }
            else
            {
                return callback + "&accessToken=" + accessToken;
            }
        }
        else
        {
            return callback + "?accessToken=" + accessToken;
        }
    }

    /*
        ------------------------------------微信oauth2登录封装 start--------------------------------------------
    */
    /**
     * 微信相关业务逻辑封装工具
     */
    @Autowired
    private WxTools wxTools;

    /**
     * 微信公众号登录
     * @param request request
     * @param response response
     */
    public void wxMPHandleLogin(HttpServletRequest request,HttpServletResponse response,String code)  {
        // 存在openId
        if (request.getSession().getAttribute("openId") != null)
        {
            try {
                handelWXLoginWidthOpenId(request, response,(String) request.getSession().getAttribute("openId"));
            } catch (IOException e) {
                LOGGER.error("使用微信openid登录失败:",e);
            }
            return;
        }
        else
        {
            // 不存在的时候 构建地址 让用户去请求openId
            String url = EConfig.getPathPropertiesValue("basePath") + "/webApi/front/getOpenIdLogin";
            String wxUrl = wxTools.getWxMpService(code).oauth2buildAuthorizationUrl(url,
                    "snsapi_base",
                    null);
            try {
                response.sendRedirect(wxUrl);
            } catch (IOException e) {
                LOGGER.error("302错误:",e);
            }
        }
    }

    /**
     * 获取微信用户信息
     *
     * @param request 请求
     * @param response 响应
     * @param code 微信code
     * @return openId
     */
    @RequestMapping(value = "/getOpenIdLogin", method = RequestMethod.GET)
    @ApiOperation(value = "微信的回调地址 ,带回code")
    public void getOpenIdLogin(HttpServletRequest request, HttpServletResponse response,String code)
            throws WxErrorException, IOException
    {
        HttpSession session = request.getSession();
        String businessCode = (String)session.getAttribute("code");
        WxMpService wxMpService = wxTools.getWxMpService(businessCode);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        String openId = wxMpOAuth2AccessToken.getOpenId();
        session.setAttribute("openId", openId);
        handelWXLoginWidthOpenId(request, response,openId);
    }

    /**
     * 通过userId登录 如果没有绑定手机,要求他去绑定.
     *
     * @param request 请求
     * @param response 响应
     */
    private void handelWXLoginWidthOpenId(HttpServletRequest request, HttpServletResponse response,String openId)
            throws IOException
    {
        HttpSession session = request.getSession();
        UcenterFrontUserBind bind = userBindService.selectBean(UcenterFrontUserBind.builder().authOpenid(openId).authOpenidType(UcenterFrontUserBindService.OPENID_TYPE_WXMP)
                .build());
        String userId = null;
        if(bind == null)
        {
            String businessCode = (String)session.getAttribute("code");
            try {
                WxMpUser mpUser = wxTools.getWxMpService(businessCode).getUserService().userInfo(openId);
                UcenterFrontUser user = UcenterFrontUser.builder().userId(StringUtil.getUUID())
                        .nickName(mpUser.getNickname()).provinceId(mpUser.getProvince()).cityId(mpUser.getCity())
                        .sex(ConverterUtils.toString(mpUser.getSex())).language(mpUser.getLanguage()).imagePath(mpUser.getHeadImgUrl()).build();
                userId = loginService.addBindAndUser(user,openId,UcenterFrontUserBindService.OPENID_TYPE_WXMP);
            } catch (WxErrorException e) {
                LOGGER.error("根据openid获取用户信息错误:",e);
            }
        }
        else
        {
            userId = bind.getUserId();
        }
        loginAndRedirect( request,  response, userId);
    }


      /*
        ------------------------------------微信oauth2登录封装 end--------------------------------------------
     */



   /*
        ------------------------------------支付宝oauth2登录封装 start--------------------------------------------
     */

    /**
     * 支付宝sdkclient
     */
    private Map<String,AlipayClient> alipayClientMap = new HashMap<>();

    /**
     * code与支付宝appid的map
     */
    private Map<String,String> alipayAppIdCodeMap = new HashMap<>();

    /**
     * 支付宝服务窗配置
     */
    @Autowired
    private UcenterAlipaySettService alipaySettService;
    /**
     * 支付宝oauth2 登录url
     */
    private static  final  String ALIPAY_OAUTH2_URL = "https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?";

    /**
     * 支付宝oauth 登录
     * @param request request
     * @param response response
     * @param code 扩展编码
     * @throws WxErrorException
     * @throws IOException
     */
    public void alipayHandleLogin(HttpServletRequest request,HttpServletResponse response,String code)
    {
        //如果不包含此code参数则获取参数
        if(!alipayClientMap.containsKey(code))
        {
            UcenterAlipaySett alipaySett =alipaySettService.selectBean(UcenterAlipaySett.builder().extendsCode(code).build());
            AlipayClient alipayClient =
                    new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                            alipaySett.getAppId(),alipaySett.getAppPrivateKey(),
                            "json", "UTF-8", alipaySett.getAlipayKey(), "RSA2");
            alipayClientMap.put(code,alipayClient);
            alipayAppIdCodeMap.put(code,alipaySett.getAppId());
        }
        String url = null;
        try {

            url = ALIPAY_OAUTH2_URL + "app_id=" + alipayAppIdCodeMap.get(code)
                    + "&scope=auth_user&redirect_uri=" + URLEncoder.encode(EConfig.getPathPropertiesValue("basePath")
                    + "/webApi/front/alipayCodeLogin","UTF-8");
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理支付宝登录
     * @param auth_code 授权码(原谅我没有用驼峰)
     * @param request
     * @param response
     */
    @RequestMapping("/alipayCodeLogin")
    public void alipayOpenIdLogin(String auth_code,HttpServletRequest request,HttpServletResponse response) throws IOException {
        AlipaySystemOauthTokenRequest getUserIdRequest = new AlipaySystemOauthTokenRequest();
        getUserIdRequest.setCode(auth_code);
        getUserIdRequest.setGrantType("authorization_code");
        String businessCode = (String)request.getSession().getAttribute("code");
        try {
            AlipaySystemOauthTokenResponse alipayResponse =  alipayClientMap.get(businessCode).execute(getUserIdRequest);
            String alipayUserId = alipayResponse.getUserId();
            handleAlipayUserIdLogin(alipayUserId,alipayResponse.getAccessToken() ,businessCode,request,response);
        } catch (AlipayApiException e) {
            LOGGER.error("支付宝方法调用错误",e);
            throw new BusinessException(e.getErrMsg());
        }
    }

    /**
     * 处理根据支付宝userid登录
     * @param alipayUserId
     * @param request
     * @param response
     */
    private void handleAlipayUserIdLogin(String alipayUserId,String alipayAccessToken,String businessCode,
                                         HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UcenterFrontUserBind bind = userBindService.selectBean(UcenterFrontUserBind.builder().authOpenid(alipayUserId).authOpenidType(UcenterFrontUserBindService.OPENID_TYPE_ALIPAY)
                .build());
        String userId = null;
        if(bind == null)
        {
            try {
                AlipayUserInfoShareRequest alipayUserInfoShareRequest = new AlipayUserInfoShareRequest();
                AlipayUserInfoShareResponse userinfoShareResponse = alipayClientMap.get(businessCode).execute(alipayUserInfoShareRequest, alipayAccessToken);
                // 默认是未知
                String sex =UcenterFrontUserService.SEX_UNKNOWN;
                if("M".equals(userinfoShareResponse.getGender()))
                {
                    sex = UcenterFrontUserService.SEX_BOY;
                }else  if("F".equals(userinfoShareResponse.getGender()))
                {
                    sex = UcenterFrontUserService.SEX_GIRL;
                }
                UcenterFrontUser user = UcenterFrontUser.builder().userId(StringUtil.getUUID())
                        .nickName(userinfoShareResponse.getNickName()).provinceId(userinfoShareResponse.getProvince()).
                                cityId(userinfoShareResponse.getCity())
                        .sex(sex).imagePath(userinfoShareResponse.getAvatar()).build();
                userId = loginService.addBindAndUser(user,alipayUserId,UcenterFrontUserBindService.OPENID_TYPE_ALIPAY);
            }  catch (AlipayApiException e) {
                LOGGER.error("根据openid获取用户信息错误:",e);
                throw new BusinessException(e.getErrMsg());
            }
        }
        else
        {
            userId = bind.getUserId();
        }
        loginAndRedirect( request,  response, userId);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        //key配置UA
        OAUTH_HANDEL_MAP.put("micromessenger",this::wxMPHandleLogin);
        OAUTH_HANDEL_MAP.put("alipayclient",this::alipayHandleLogin);
    }
}

/**
 * 第三方登录处理，默认给了微信的demo
 */
@FunctionalInterface
interface OAuthHandle{
    public void handleLogin(HttpServletRequest request,HttpServletResponse response,String code);
}