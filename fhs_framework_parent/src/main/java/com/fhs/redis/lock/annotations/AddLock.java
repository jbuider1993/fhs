package com.fhs.lock.annotations;

import java.lang.annotation.*;

/**
 * by jackwang
 * redis分布式锁注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AddLock {

    /**
     * 锁的key
     * @return 锁的key
     */
    String key();

    /**
     * 最大等待时间 毫秒
     * @return 最大等待时间毫秒
     */
    long maxWait();

    /**
     * 锁最大占用时间秒
     * @return 锁最大占用时间秒
     */
    int timeout();
}
