package com.fhs.core.exception;

/**
 *
 * 参数异常
 * jackwong
 * 2017年9月27日 下午1:16:15
 *
 * @version 1.0.0
 *
 */
public class ParamException extends RuntimeException {


    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */

    private static final long serialVersionUID = 1L;

    public ParamException(String message)
    {
         this.message = message;
    }

    public ParamException()
    {
    }

    /**
     * 异常的描述
     * @since 1.0.0
     */
    protected String message;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

}
