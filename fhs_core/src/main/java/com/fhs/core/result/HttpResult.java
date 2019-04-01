package com.fhs.core.result;

import com.fhs.common.utils.ThreadKey;
import com.fhs.core.base.bean.BaseObject;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * HttpResult
 *
 * jackwong jackwong 2017年9月27日 下午12:03:03
 *
 * @version 1.0.0
 *
 */
public class HttpResult<V> extends BaseObject<HttpResult>
{

    private static final long serialVersionUID = 1L;


    /**
     * 成功
     * @since 1.0.0
     */
    public static int SUCCESS = 200;


    /**
     * 有错误
     * @since 1.0.0
     */
    public static int ERROR = 500;

    /**
     * 权限不足
     * @since 1.0.0
     */
    public static int AUTHORITY_ERROR = 403;

    /**
     * 编码
     */
    private int code;

    /**
     * 数据
     */
    private V data;

    /**
     * 信息
     */
    private String message = "";

    /**
     * 请求随机字符串
     */
    private String businessKey = "";

    /**
     * 返回结果
     */
    private Boolean result;


    /**
     * 异常
     */
    private String exceptionInfo;

    /**
     * 成功包含data的返回结果
     */
    public static <T> HttpResult<T> success(T data)
    {
        HttpResult<T> result = new HttpResult<T> ();
        result.code = 200;
        result.data = data;
        result.businessKey = ThreadKey.BUS_KEY.get();
        return result;
    }



    /**
     * 成功不包含数据的返回结果
     */
    public static HttpResult<Map> success()
    {
        HttpResult<Map> result = new HttpResult<Map>();
        result.code = 200;
        result.data = new HashMap<>();
        result.businessKey = ThreadKey.BUS_KEY.get();
        return result;
    }





   /**
    * 失败,不包含数据的返回结果
    * @return
    */
   public static HttpResult<Map> error() {
       HttpResult<Map> result = new HttpResult<Map>();
       result.code = 500;
       result.data = new HashMap<>();
       result.businessKey = ThreadKey.BUS_KEY.get();
       return result;
   }

   /**
    * 失败，包含data的返回结果
    * @param data
    * @return
    */
   public static <T> HttpResult<T> error(T data) {
       HttpResult<T> result = new HttpResult<T>();
       result.code = 500;
       result.data = data;
       result.businessKey = ThreadKey.BUS_KEY.get();
       return result;
   }

    /**
     * 失败，包含数据的返回结果
     */
    public static <T> HttpResult<T> error(T data, String message)
    {
        HttpResult<T> result = new HttpResult<T>();
        result.code = 300;
        result.data = data;
        result.message = message;
        result.businessKey = ThreadKey.BUS_KEY.get();
        return result;
    }

    /**
     * 验证失败的返回结果
     */
    public static <T> HttpResult<T> validateError(T data, String message)
    {
        HttpResult<T> result = new HttpResult<T>();
        result.code = 400;
        result.data = data;
        result.message = message;
        return result;
    }

    /**
     * 返回其他code和msg的httpResult
     * @param code
     * @param message
     * @return
     */
    public static HttpResult<Map> otherCodeMsgResult(Integer code, String message){
        HttpResult<Map> result = new HttpResult<Map>();
        result.code = code;
        result.data = new HashMap<String,Object> (  );
        result.message = message;
        return result;
    }

    public HttpResult(){
        this.businessKey = ThreadKey.BUS_KEY.get();
    }

    /**
     * 其他的业务返回
     * @param data 给前台的数据
     * @param baseResult 业务result
     * @return HttpResult
     */
    public static <T> HttpResult<T> otherResult(T data, BaseResult baseResult) {
        HttpResult<T> result = new HttpResult<T>();
        result.code = baseResult.getCode();
        result.data = data;
        result.message = baseResult.getMessage();
        return result;
    }



    public String getBusinessKey()
    {
        return businessKey;
    }

    public void setBusinessKey(String businessKey)
    {
        this.businessKey = businessKey;
    }

    /**
     * 其他的业务返回
     * @param baseResult 业务result
     * @return HttpResult
     */
    public static HttpResult<Map> otherResult(BaseResult baseResult) {
        HttpResult<Map> result = new HttpResult<Map>();
        result.code = baseResult.getCode();
        result.data = new HashMap<String,Object> ();
        result.message = baseResult.getMessage();
        return result;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public V getData()
    {
        return data;
    }

    public void setData(V data)
    {
        this.data = data;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Boolean getResult() {
        return this.code == 200;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }
}
