package com.fhs.core.exception;

/**
 *
 * 业务异常
 * 此类可以携带自定义错误码
 * jackwong
 * 2017年9月27日 下午1:16:15
 *
 * @version 1.0.0
 *
 */
public class BusinessException extends RuntimeException {


    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */

    private static final long serialVersionUID = 1L;

    /*
     *错误编码
     */

    private int code;

    public BusinessException(String message,int code)
    {
         super(message);
         this.code = code;
    }

    public BusinessException(String message)
    {
        super(message);
        this.code = 500;
    }


    public BusinessException()
    {
    }

    public int getCode(){
        return code;
    }
}
