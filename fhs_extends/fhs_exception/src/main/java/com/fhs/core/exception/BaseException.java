package com.fhs.core.exception;

/**
 *
 * BaseException
 * 此类暂时没用
 * jackwong
 * 2017年9月27日 下午1:17:28
 *
 * @version 1.0.0
 *
 */
public class BaseException extends Exception
{
    /**
     *serialVersionUID
     */
    private static final long serialVersionUID = 1L;
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
