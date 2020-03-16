
package com.fhs.logger;

import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.ThreadKey;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description:Log工具类
 * @Version: 1.0
 * @Author: duanwei
 * @Email: duanwei@sxpartner.com 陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
public class Logger
{

    /** The now time. */
    private Long nowTime;

    /** 日志. */
    private org.slf4j.Logger logger;


    /** 存储异常时间的map. */
    public static Map<String, Date> map = new HashMap<>();

    /** 配置文件. */
    public static Properties prop = new Properties();




    /**
     * 构造方法
     */
    public Logger()
    {
    }

    /**
     * 构造方法，初始化Log4j的日志对象.
     *
     * @param slf4jLogger the slf 4 j logger
     */
    private Logger(org.slf4j.Logger slf4jLogger)
    {
        logger = slf4jLogger;
    }

    /**
     * 获取构造器，根据类初始化Logger对象.
     *
     * @param classObject Class对象
     * @return Logger对象
     */
    public static Logger getLogger(Class<?> classObject)
    {
        return new Logger(LoggerFactory.getLogger(classObject));
    }

    /**
     * <打印对象的debug级别的日志>.
     *
     * @param object 任意对象
     */
    public void debug(Object object)
    {
        logger.debug(ConverterUtils.toString(object) + " BUS_KEY:\"" + ThreadKey.BUS_KEY.get() + "\"");
    }

    /**
     * 可以用占位符做替换的方法.
     *
     * @param msg message
     * @param args 替换变量
     */
    public void debugMsg(String msg, Object... args)
    {
        logger.debug(msg + " BUS_KEY:\"" + ThreadKey.BUS_KEY.get() + "\"", args);
    }

    /**
     * <打印对象的debug级别的日志,并且打印异常>.
     *
     * @param object 对象
     * @param e 异常
     */
    public void debug(Object object, Throwable e)
    {
        logger.debug(ConverterUtils.toString(object) + " BUS_KEY:\"" + ThreadKey.BUS_KEY.get() + "\"", e);
    }

    /**
     * <打印对象的debug级别的日志,并且打印异常>.
     *
     * @param message 对象
     * @param args 替换变量
     */
    public void debug(String message, Object... args)
    {
        logger.debug(message + " BUS_KEY:\"" + ThreadKey.BUS_KEY.get() + "\"", args);
    }

    /**
     * <打印对象的info级别的日志>.
     *
     * @param object 任何对象
     */
    public void info(Object object)
    {
        logger.info(ConverterUtils.toString(object) + " BUS_KEY:\"" + ThreadKey.BUS_KEY.get() + "\"");
    }

    /**
     * 可以用占位符做替换的方法.
     *
     * @param message message
     * @param args 替换变量
     */
    public void infoMsg(String message, Object... args)
    {
        logger.info(message + " BUS_KEY:\"" + ThreadKey.BUS_KEY.get() + "\"", args);
    }

    /**
     * <打印对象的info级别的日志,并且打印异常>.
     *
     * @param object 任何对象
     * @param e 异常
     */
    public void info(Object object, Throwable e)
    {
        logger.info(ConverterUtils.toString(object) + " BUS_KEY:\"" + ThreadKey.BUS_KEY.get() + "\"", e);
    }

    /**
     * <打印对象的warn级别的日志>.
     *
     * @param object 任何对象
     */
    public void warn(Object object)
    {
        logger.warn(ConverterUtils.toString(object) + " BUS_KEY:\"" + ThreadKey.BUS_KEY.get() + "\"");
    }

    /**
     * <打印对象的warn级别的日志,并且打印异常>.
     *
     * @param object 任何对象
     * @param e 异常
     */
    public void warn(Object object, Throwable e)
    {
        logger.warn(ConverterUtils.toString(object) + " BUS_KEY:\"" + ThreadKey.BUS_KEY.get() + "\"", e);
    }

    /**
     * 可以用占位符做替换的方法.
     *
     * @param message message
     * @param args 替换变量
     */
    public void warnMsg(String message, Object... args)
    {
        logger.warn(message + " BUS_KEY:\"" + ThreadKey.BUS_KEY.get() + "\"", args);
    }

    /**
     * <打印对象的error级别的日志>.
     *
     * @param object 任何对象
     */
    public void error(Object object)
    {
        logger.error(ConverterUtils.toString(object) + " BUS_KEY:\"" + ThreadKey.BUS_KEY.get() + "\"");
    }

    /**
     * <打印对象的warn级别的日志,并且打印异常>.
     *
     * @param object the object
     * @param e 异常
     */
    public void error(Object object, Throwable e)
    {
        logger.error(ConverterUtils.toString(object) + " BUS_KEY:\"" + ThreadKey.BUS_KEY.get() + "\"", e);
    }




    /**
     * 可以用占位符做替换的方法.
     *
     * @param message message
     * @param args 替换变量
     */
    public void errorMsg(String message, Object... args)
    {
        logger.error(message + " BUS_KEY:\"" + ThreadKey.BUS_KEY.get() + "\"", args);
    }

    /**
     * <获取name>.
     *
     * @return 返回name
     */
    public String getName()
    {
        return logger.getName();
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    /**
     * 记录时间.
     *
     * @param message the message
     * @return the long
     */
    public Long recordRunTimeToError(Object message)
    {
        return this.recordRunTimeToError(message, true);
    }

    /**
     * Record run time to error.
     *
     * @param message the message
     * @param isRecord the is record
     * @return the long
     */
    public Long recordRunTimeToError(Object message, boolean isRecord)
    {
        if (this.nowTime == null)
        {
            this.nowTime = System.currentTimeMillis();
            this.error(message + "记录时间戳: " + this.nowTime);
        }
        else
        {
            Long currentTime = System.currentTimeMillis();
            this.error(
                message + "记录时间戳 : " + (isRecord ? (currentTime + ", 用时 : " + (currentTime - this.nowTime)) : ""));
            this.nowTime = currentTime;
        }
        return this.nowTime;
    }

}
