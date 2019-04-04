package com.fhs.core.exception;

import com.fhs.common.utils.AESUtil;
import com.fhs.common.utils.JsonUtils;
import com.fhs.common.utils.Logger;
import com.fhs.common.utils.ThreadKey;
import com.fhs.core.config.EConfig;
import com.fhs.core.result.HttpResult;
import com.fhs.core.result.PubResult;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 统一异常处理器
 *
 * @Filename: ExceptionHandler.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwong
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 *               陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 *
 */
public class ExceptionHandler implements HandlerExceptionResolver
{

    /** log */
    private static final Logger LOG = Logger.getLogger(ExceptionHandler.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
        Exception ex)
    {

        ModelAndView result = new ModelAndView();
        result.setViewName("platform/json");
        HttpResult httpResult = HttpResult.otherResult(PubResult.PARAM_ERROR);
        if (ex instanceof BaseException)
        {
            httpResult.setMessage(ex.getMessage());
        }
        else if (ex instanceof IllegalArgumentException)
        {
            httpResult.setMessage(ex.getMessage());
            JsonUtils.outJson(response, httpResult.asJson());
            return null;
        }
        else if (ex instanceof HttpException)
        {
            httpResult.setMessage(ex.getMessage());
            JsonUtils.outJson(response, httpResult.asJson(), ((HttpException)ex).getHttpCode());
            return null;
        }
        else if (ex instanceof BusinessException)
        {
            LOG.error("处理客户端请求错误，客户端NONCE为：" + ThreadKey.BUS_KEY.get(), ex);
            httpResult.setMessage(ex.getMessage());
            httpResult.setCode (((BusinessException) ex).getCode ());
            JsonUtils.outJson(response, httpResult.asJson());
            return null;
        }
        else if (ex instanceof ParamException)
        {
            httpResult.setMessage(ex.getMessage());
            httpResult.setCode (400);
            JsonUtils.outJson(response, httpResult.asJson());
            return null;
        }
        else if (ex instanceof CheckException)
        {
            JsonUtils.outJson(response, ((CheckException)ex).getResult().asJson());
            return null;
        }
        else if (ex instanceof DuplicateKeyException)
        {
            JsonUtils.outJson(response, HttpResult.otherResult(PubResult.PRIMARY_KEY_CONFLICT).asJson());
            return null;
        }
        else if (ex instanceof NotPremissionException)
        {
            JsonUtils.outJson(response, HttpResult.otherResult(PubResult.NO_PERMISSION).asJson());
            return null;
        }
        else if (ex instanceof ResultException)
        {
            JsonUtils.outJson(response, ((ResultException)ex).getHttpResult().asJson());
            return null;
        }
        else
        {

            LOG.error("处理客户端请求错误，客户端NONCE为：" + ThreadKey.BUS_KEY.get(), ex);
            httpResult = HttpResult.otherResult(PubResult.SYSTEM_ERROR);
            httpResult.setMessage("系统错误，请联系管理员,NONCE:" + ThreadKey.BUS_KEY.get() );
            httpResult.setExceptionInfo(AESUtil.encrypt(getStackTrace(ex), EConfig.getOtherConfigPropertiesValue("exceptionInfoPassword")));
            JsonUtils.outJson(response, httpResult.asJson());
        }
        return null;
    }

    /**
     * 获取异常堆栈信息
     * @param throwable 异常
     * @return 堆栈描述
     */
    public static String getStackTrace(Throwable throwable)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try
        {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally
        {
            pw.close();
        }
    }
}
