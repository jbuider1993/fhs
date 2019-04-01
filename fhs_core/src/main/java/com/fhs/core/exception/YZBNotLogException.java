package com.fhs.core.exception;

public abstract class YZBNotLogException  extends RuntimeException
{

    /**
     *serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public YZBNotLogException(String message)
    {
        super(message);
    }
    public YZBNotLogException()
    {
        super();
    }
}
